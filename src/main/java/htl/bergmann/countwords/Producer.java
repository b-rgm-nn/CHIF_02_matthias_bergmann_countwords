package htl.bergmann.countwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Producer implements Runnable {

    private Queue<Book> queue;

    public Producer(Queue<Book> queue) {
        this.queue = queue;
    }

    public String loadText(File f) {
        String text = "";
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            while (br.ready()) {
                text += br.readLine();
            }
        } catch (Exception e) {
        }
        return text;
    }

    @Override
    public void run() {
        File booksFolder = new File("./files");
        File[] books = booksFolder.listFiles();
        for (int i = 0; i < books.length; i++) {
            if(!books[i].isFile()) continue;
            Book book = new Book(books[i].getName(), loadText(books[i]));
            synchronized(queue) {
                try{
                    queue.put(book);
                    queue.notifyAll();
                } catch (FullException ex) {
                    --i;
                    try {
                        queue.wait();
                    } catch (InterruptedException ex1) {
                    }
                }
            }
        }
        System.out.println("done");
    }

}
