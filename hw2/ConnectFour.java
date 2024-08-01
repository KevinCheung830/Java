//====================================================================================
// DO NOT MODIFY THIS INTERFACE
//====================================================================================

package a2223.hw2;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * EE3206 Assignment 2
 * 
 * The goal of this assignment is to implement a board game called "Connect Four".
 *
 * This interface contains seven abstract methods that specify the key operations 
 * of the game. You should follow the steps below to create and test the game.
 * 
 * 1. Create a project with the Java package a2223.hw2.
 * 2. Put this interface into the package.
 * 3. Create your own class ConnectFourXXXXXXXX in the same package, where the X-string
 *    is your student ID. The class implements this interface.
 * 4. Implement all abstract methods as required. 
 * 5. Define additional private fields and methods as needed. These fields are for your
 *    internal use and do not form a public API.
 * 6. Define a no-arg constructor. It can be empty but must exist.
 * 7. Run the main method in this interface to test your game implementation.
 *
 */

/**
 * A simple connection board game – Connect Four. The game has two players, X and O, and
 * starts with X. The players take turns dropping their tokens into a six-row, seven-column 
 * vertically suspended grid. The pieces fall straight down, occupying the lowest 
 * available space within the column. The objective of the game is to be the first to 
 * form a horizontal, vertical, or diagonal line of four of one's own tokens.
 * 
 * @author vanting
 */
public interface ConnectFour {

    // The mark for two game players
    public enum Player {X, O}
    
    /**
     * This method initialize the 6x7 grid. When it is called, the grid 
     * is re-initialized and all tokens currently on the grid are erased. 
     */
    public void init();
    
    /**
     * This method returns true if the game can continue. It returns false if a winner has
     * been determined or all grid positions have been filled.
     */
    public boolean hasNext();
    
    /**
     * This method returns the player of the current turn. 
     */
    public Player getTurn();

    /**
     * This method drops a token of the current player in the specified column.
     * 
     * @param col                           the column number ranging from 0 to 6 
     * @throws IllegalArgumentException     if the input column is out of the range
     * @throw IllegalStateException         if the input column is full
     */
    public void drop(int col) throws IllegalArgumentException, IllegalStateException;

    /**
     * This method prints the current grid state to the console (i.e. System.out).
     * The grid cells are either printed with a player's token or an underscore when
     * the position is empty. The column numbers are printed at the bottom of the grid.
     * 
     * For example, an initial look of a 6x7 grid with underscores in all cells:
     * |_|_|_|_|_|_|_|
     * |_|_|_|_|_|_|_|
     * |_|_|_|_|_|_|_|
     * |_|_|_|_|_|_|_|
     * |_|_|_|_|_|_|_|
     * |_|_|_|_|_|_|_|
     * |0|1|2|3|4|5|6|
     * 
     * Another example with 3 Xs and 2 Os.
     * |_|_|_|_|_|_|_|
     * |_|_|_|_|_|_|_|
     * |_|_|_|_|_|_|_|
     * |_|_|_|_|_|_|_|
     * |_|_|_|X|O|_|_|
     * |X|_|_|X|O|_|_|
     * |0|1|2|3|4|5|6|
     */
    public void print();

    /**
     * This method returns true if the game has a winner. It returns false when the game
     * is still in progress or there is no winner.
     */
    public boolean hasWinner();
    
    /**
     * This method returns the winner of the game.
     * 
     * @return                              the winner of the game
     * @throws IllegalStateException        if the game is in progress or has no winner
     */
    public Player getWinner() throws IllegalStateException;
    
    /**
     * Load your ConnectFour implementation class and create a new instance with 
     * your no-arg constructor.
     * 
     * @param sid   your student ID
     * @return      an instance of your ConnectFour implementation
     * 
     */
    public static ConnectFour newInstance(String sid) {
         
        ConnectFour instance = null;
        try {
            Class clazz = Class.forName("a2223.hw2.ConnectFour" + sid);
            instance = (ConnectFour) clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectFour.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return instance;
        }
    }
    
    /**
     * Enter your 8-digit SID to start the game.
     */
    public static void main(String[] args) {
        
        Scanner sin = new Scanner(System.in);  
        
        System.out.print("Enter your SID: ");
        ConnectFour game = newInstance(sin.next());
        game.init();
        System.out.println("");
                
        game.print();
        while(game.hasNext()) {
            while(true) {
                try {
                    System.out.print("Drop " + game.getTurn() + " at: ");
                    game.drop(sin.nextInt());
                    break;
                } catch(IllegalArgumentException e) {
                    System.out.println("Wrong column.");
                } catch(InputMismatchException e) {
                    System.out.println("Wrong input.");
                    sin.nextLine();
                }
            }
            
            System.out.println("");
            game.print();
        }
        
        if(game.hasWinner())
            System.out.println("The winner is " + game.getWinner() + " !!!");
        else
            System.out.println("DRAW game!");
        
    }

}
