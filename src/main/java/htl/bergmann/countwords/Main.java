
package htl.bergmann.countwords;

public class Main {
    public static void main(String[] args) {
        Queue<Book> books = new Queue<>(5);
        Producer producer = new Producer(books);
        Consumer consumer1 = new Consumer(books);
        Consumer consumer2 = new Consumer(books);
        
        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}
