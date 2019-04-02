
package htl.bergmann.countwords;

import java.util.HashMap;

public class Book {
    private String inputfilename;
    private String text;

    public Book(String inputfilename, String text) {
        this.inputfilename = inputfilename;
        this.text = text;
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
        return countWords;
    }

    public String getInputfilename() {
        return inputfilename;
    }
}
