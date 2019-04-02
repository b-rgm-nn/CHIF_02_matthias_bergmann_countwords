package htl.bergmann.countwords;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toMap;

public class Consumer implements Runnable {

    private Queue<Book> queue;
    private volatile boolean running = true;

    public Consumer(Queue<Book> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (running) {
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

            LinkedHashMap<String, Integer> countWords = book.countWords()
                    .entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                LinkedHashMap::new));
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./files/output/" + book.getInputfilename() + "-output.txt")))) {
                for (String key : countWords.keySet()) {
                    if(countWords.get(key) == 1) continue;
                    bw.write(String.format("%-10s: %d\n", key, countWords.get(key)));
                }
            } catch (IOException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stop() {
        running = false;
    }

}
