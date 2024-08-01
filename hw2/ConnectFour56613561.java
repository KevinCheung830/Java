/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2223.hw2;

/**
 *
 * @author Nova
 */
abstract class ConnectFour56613561 implements ConnectFour {

    int turn = 0;
    boolean winner = false;

    public void init() {
        String[][] Board = new String[6][7];
        //initialise 
        turn=0;
        winner=false;
        for (int row = 0; row <= Board.length; row++) {
            for (int col = 0; col < Board[0].length; col++) {
                Board[row][col] = "|_|";
            }
        }

    }

    
    
    public boolean hasNext() {
        if (winner = false && turn < 42) {
            turn++;
            return true;
        } else {
            return false;
        }
    }

    
      
     public Player getTurn() {
        if (turn % 2 == 0 && turn <= 42) {
            return Player.X;
        } else {
            return Player.O;
        }
    }

    
     
     
    public void drop(int col) throws IllegalArgumentException, IllegalStateException {
        
    }
     
    public void print() {
    }

    
    public boolean hasWinner() {
        return winner;
    }

    
    public Player getWinner() throws IllegalStateException {
        if (winner && turn % 2 == 0) {
            return Player.X;
        } else {
            return Player.O;
        }
    }

}



}
