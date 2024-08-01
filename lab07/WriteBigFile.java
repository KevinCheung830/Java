/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab07;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author vanting
 */
public class WriteBigFile {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        FileOutputStream fos = new FileOutputStream("bigfile.dat");
        //BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("bigfile.dat"));
        
        for(int i=0; i<10_000_000; i++) {
            fos.write(1);
        }
        
        fos.close();
                
    }
    
}
