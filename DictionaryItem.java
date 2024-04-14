/* Name - Ekagra Jain
 *  Pledge - I pledge my honor that I have abided by the Stevens Honor System */

package HW2;

public class DictionaryItem {
    private String word;
    private int count;

    // A constructor for initialising data fields.
    public DictionaryItem(String word, int count){
        this.word = word;
        this.count = count;
    }

    // Getter and Setter
    public String getWord(){
        return word;
    }
    public int getCount(){
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void setWord(String word) {
        this.word = word;
    }
}
