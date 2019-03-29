
package htl.bergmann.countwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Book {
    private String inputfilename;
    private String text;

    public Book(String inputfilename, String text) {
        this.inputfilename = inputfilename;
        this.text = text;
    }
    
    public void loadText() {
        text = "";
        try (BufferedReader br = new BufferedReader(new FileReader(new File(inputfilename)))) {
            while(br.ready())
                text += br.readLine();
        } catch (Exception e) {
            
        }
    }
    
    public HashMap<String, Integer> countWords() {
        String[] words = text.split("[^a-zA-Z]+");
        HashMap<String, Integer> countWords = new HashMap<>();
        
        for (String word : words) {
            if(countWords.containsKey(word)) {
                countWords.put(word, countWords.get(word) + 1);
            } else {
                countWords.put(word, 1);
            }
        }
        for (String key : countWords.keySet()) {
            if(countWords.get(key) == 1) {
                countWords.remove(key);
            }
        }
        
        return countWords;
    }
}
