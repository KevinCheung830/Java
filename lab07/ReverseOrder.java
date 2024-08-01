/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author vanting
 */
public class ReverseOrder {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        File in = new File("I Have a Dream Speech.txt");
        File out = new File("I Have Another Dream Speech.txt");
        Scanner scanner = null;
        FileWriter writer = null;

        try {
            scanner = new Scanner(in);
            writer = new FileWriter(out);

            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }

            for (int i = list.size(); i > 0; i--) {
                writer.write((String) list.get(i - 1) + "\n");
            }

            scanner.close();
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found.");
        } catch (IOException ex) {
            System.out.println("IO errors.");
        }

        
    }
}
