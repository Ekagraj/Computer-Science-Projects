/* Name - Ekagra Jain
*  Pledge - I pledge my honor that I have abided by the Stevens Honor System */

package HW2;

import java.io.File;
import java.util.ArrayList;
public class Dictionary {

    ArrayList<DictionaryItem> dictArrayList;
    ArrayList<String> wordList;

    // A constructor for initializing two arraylist data fields.
    public Dictionary() {
        wordList = new ArrayList<>(1300);
        dictArrayList = new ArrayList<>(1300);
    }

    // A method that adds a word to the dictionary by adding the word to the wordList (for faster access) and adding a dictionary item to dictArrayList to store both word and count.
    public boolean addWordToDictionary(DictionaryItem item) {
        wordList.add(item.getWord());
        dictArrayList.add(item);
        return true;
    }

    // A method that prints the word list that we created from the dictionary text file.
    public void printDictionary() {
        for (int i = 0; i < wordList.size(); i++) {
            System.out.println(wordList.get(i));
        }
    }

    // binarySearch() method to search the word in the wordList and using the index of the word in wordList, returns the count of that word from dictArrayList.
    public int searchDictionary(String word) {
        if (binarySearch(word, 0, wordList.size() - 1) != -1) {
            return dictArrayList.get(binarySearch(word, 0, wordList.size() - 1)).getCount();
        } else {
            return 0;
        }
    }

    // hasWord checks if a given word exists in the dictionary.
    public boolean hasWord(String word){
        for(String item : wordList){
            if (item.equals(word)){
                return true;
            }
        }
        return false;
    }

    // binarySearch is a helper method that performs the binary search algorithm on the sorted wordlist arraylist that we created.
    private int binarySearch(String word, int low, int high) {
        int mid = 0;
        while (high >= low) {
            mid = (high + low) / 2;
            if (word.compareTo(wordList.get(mid)) > 0) {
                low = mid + 1;
            } else if (word.compareTo(wordList.get(mid)) < 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

