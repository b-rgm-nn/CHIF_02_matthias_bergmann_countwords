
package htl.bergmann.countwords;

public class Main {
    public static void main(String[] args) {
        Queue<Book> books = new Queue<>(3);
        Producer producer = new Producer(books);
        Consumer consumer1 = new Consumer(books);
        Consumer consumer2 = new Consumer(books);
        
        Thread producerThread = new Thread(producer);
        new Thread(consumer1).start();
        new Thread(consumer2).start();
        producerThread.start();
        try {
            producerThread.join();
            consumer1.stop();
            consumer2.stop();
        } catch (InterruptedException ex) {
        }
    }
}
