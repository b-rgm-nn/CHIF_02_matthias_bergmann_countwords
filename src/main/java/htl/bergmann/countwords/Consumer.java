package htl.bergmann.countwords;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable {

    private Queue<Book> queue;

    public Consumer(Queue<Book> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Book book;
            synchronized (queue) {
                try {
                    book = queue.get();
                    queue.notifyAll();
                } catch (EmptyException ex) {
                    try {
                        queue.wait();
                    } catch (InterruptedException ex1) {
                    }
                    continue;
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./files/" + book.getInputfilename() + "-output.txt")))) {
                HashMap<String, Integer> countWords = book.countWords();
                for (String key : countWords.keySet()) {
                    bw.write(key + ": " + countWords.get(key) + "\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
