/* Name - Ekagra Jain
 *  Pledge - I pledge my Honor that I have abided by the Stevens Honor System */

package HW2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class DictionaryCreator {

    private Dictionary dictionary;

    // A constructor DictionaryCreator() for reading the file ‘IonDictionary.txt’ from the current folder your project is residing in.
    public DictionaryCreator(){
        Dictionary dict = readFile("IonDictionary.txt");
        printMenu(dict);
    }

    // A constructor for reading the file from the param: filename.
    public DictionaryCreator(String filename){
        Dictionary dict = readFile(filename);
        printMenu(dict);
    }

    // A method checks if the file exists in the given path/current folder.
    public boolean fileExists(String filename) {
        try {
            File file = new File(filename);
            if (file.exists() && !file.isDirectory()) {
                return true;
            } else {
                throw new FileNotFoundException("File not available");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Opens the file with the given filename, creates a dictionary object, splits each line in the given file,
    public Dictionary readFile(String filename) {
        Dictionary dict = new Dictionary();
        Scanner scanner = null;
        try {
            File file = new File(filename);
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                DictionaryItem item = splitWordCountPair(line);
                if (item != null) {
                    dict.addWordToDictionary(item);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return dict;
    }

    // This method splits the line read from the file and stores the word-count pairs in a new DictionaryItem object that the method returns.
    public DictionaryItem splitWordCountPair(String line) {
        if (line.startsWith("-") || line.contains("Word                | Count") || line.contains("Complete Dictionary")) {
            return null;
        }
        String[] pair = line.trim().split("\\s+\\|\\s+");
        if (pair.length == 2) {
            String word = pair[0];
            try {
                int count = Integer.parseInt(pair[1]);
                return new DictionaryItem(word, count);
            } catch (NumberFormatException e) {
                System.out.println("Invalid count format for line: " + line);
                return null;
            }
        } else {
            System.out.println("Invalid format for line: " + line);
            return null;
        }
    }

    // A method that creates a Dictionary object and returns it.
    public Dictionary createADictionary() {
        return readFile("IonDictionary.txt");
    }


    // printMenu operation is used for printing the 3 menu items.
    public void printMenu(Dictionary dict) {

        Scanner scan = new Scanner(System.in);
        int choice = 0;
        boolean Cond = true;
        System.out.println("Welcome to the Ion Dictionary! This dictionary is created from the book Ion by Plato!");
        while (Cond) {
            System.out.println("Please choose one of the following menu items indicated with 1-3");
            System.out.println("1: To print all the words in the dictionary, choose 1");
            System.out.println("2: To search a word in the dictionary, choose 2");
            System.out.println("3: To quit the program, choose 3");
            try {
                choice = Integer.parseInt(scan.nextLine());
                if (choice > 3 || choice < 1) {
                    throw new IllegalArgumentException();
                }

            } catch (IllegalArgumentException e) {
                System.out.println("ERROR! Please enter a number between 1 and 3.");
                continue;
            }
            Cond = processMenuItem(choice, scan, dict);
        }
    }

    //  processMenuItem reads the word from the user for searching a word in dictionary, and calls appropriate functions according to the choice by user.
    private boolean processMenuItem(int menuItem, Scanner scan,Dictionary dict) {
        if (menuItem == 1) {
            System.out.println("All the words mentioned in the Ion book!\nWords\n-----");
            dict.printDictionary();
            return true;

        } else if (menuItem == 2) {
            System.out.println("Please enter the word you would like to search:");
            String word = scan.nextLine();
            if ((dict.searchDictionary(word) == 0)) {
                System.out.println("The word '" + word + "' does not exist in the Ion dictionary!");
            } else {
                System.out.println("The word '" + word + "' occurred " + dict.searchDictionary(word) + " times in the book!");
            }
            return true;
        } else if (menuItem == 3) {
            System.out.println("Thanks for using Ion Dictionary! Bye!");
            return false;

        } else {
            System.out.println("ERROR! Please enter a number between 1 and 3.");
            return true;
        }
    }


}
