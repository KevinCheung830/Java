/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vanting
 */
public class WordCounter {

    private File file;

    public WordCounter(String filename) {
        file = new File(filename);
    }

    public int countWord() throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        int count = 0;
        while (scan.hasNext()) {
            //System.out.println(scan.next());
            scan.next();
            count++;
        }
        return count;
    }

    public int countLine() throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        int count = 0;
        while (scan.hasNextLine()) {
            //System.out.println(scan.nextLine());
            scan.nextLine();
            count++;
        }
        return count;
    }

    public int countCharacter() throws FileNotFoundException, IOException {
        FileReader reader = new FileReader(file);
        int c = 0;
        int count = 0;
        while ((c = reader.read()) != -1) {
            if (!Character.isWhitespace(c)) {
                //System.out.println((char)c);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        WordCounter wc = new WordCounter("I Have a Dream Speech.txt");
        int wordCount = 0;
        int lineCount = 0;
        int charCount = 0;

        try {
            wordCount = wc.countWord();
            lineCount = wc.countLine();
            charCount = wc.countCharacter();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordCounter.class.getName()).log(Level.SEVERE, "File Not Found", ex);
        } catch (IOException ex) {
            Logger.getLogger(WordCounter.class.getName()).log(Level.SEVERE, "Read File Error", ex);
        }

        System.out.println("Number of words: " + wordCount);
        System.out.println("Number of lines: " + lineCount);
        System.out.println("Number of characters: " + charCount);
    }
}
