package htl.bergmann.countwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        File books = new File("./files");
        for (File listFile : books.listFiles()) {
            Book book = new Book(listFile.getName(), loadText(listFile));
            synchronized(queue) {
                try{
                    queue.put(book);
                    queue.notifyAll();
                } catch (FullException ex) {
                    try {
                        queue.wait();
                    } catch (InterruptedException ex1) {
                    }
                    try {
                        queue.put(book);
                    } catch (FullException ex1) {
                    }
                }
            }
        }
    }

}
