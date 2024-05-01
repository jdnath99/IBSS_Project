package words;

import java.util.ArrayList;
import java.util.List;

public class Words {
    public static List<String> getUniqueWordsFromSentence(String sentence) {

        // Used a regular expression to split the sentence into words
        String[] words_split = sentence.split("\\W+");

        // Creating an ArrayList to store unique words
        List<String> uWords = new ArrayList<>();

        // Add each word to the list if it is not already present
        for (String word : words_split) {
            if (!word.isEmpty()) {
                String lowerCaseWord = word.toLowerCase();
                if (!uWords.contains(lowerCaseWord)) {
                    uWords.add(lowerCaseWord);
                }
            }
        }
        return uWords;
    }
    }

