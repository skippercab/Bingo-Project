/**
 * @author chrisbell
 */

import javax.swing.JComponent;
import java.util.ArrayList;

public class gameBoard extends JComponent {    
    protected individualSquare[][] board;
    protected ArrayList<Integer> numbers;
    protected final int SQUARE_SIZE = 60;
    protected final int LENGTH = 5;
    protected final int WIDTH = 5;
    public static final String[] BINGO = {"B", " I", "N", "G", "O"};
    public int indentX;
    public int indentY;
    protected String winnerMessage;

    public gameBoard() {
        board = new individualSquare[WIDTH][LENGTH];
        numbers = new ArrayList<>();
        winnerMessage = "";
    }

    public boolean isFound(int value, int r, int c) {
        for (int row = 0; row < r; row++) {
            for (int column = 0; column <= c; column++) {
                if (value == board[row][column].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void initializeGrid() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                board[row][column] = new individualSquare(column * SQUARE_SIZE + indentX, row * SQUARE_SIZE + indentY, SQUARE_SIZE, SQUARE_SIZE);
                int value = board[row][column].createNum(column + 1);
                while (isFound(value,row,column))
                    value = board[row][column].createNum(column + 1);
            }
        }

        board[WIDTH / 2][LENGTH / 2].setStatus(true);
        board[WIDTH / 2][LENGTH / 2].setIsClicked(true);
        winnerMessage = "";
    }

    public boolean checkWin() {
        int winningNumber = 5;
        int count;

        //checks the rows
        for (individualSquare[] board1 : board) {
            count = 0;
            for (int column = 0; column < board[0].length; column++) {
                if (board1[column].getStatus()) {
                    board1[column].setIsWinner(true);
                    count++;                
                }
                if (count == winningNumber) {
                    return true;             
                }
            }
            this.removeIsWinnerMark();
        }
        
        for (int column = 0; column < board[0].length; column++) {
            count = 0;
            for (int row = 0; row < board.length; row++) {
                if (board[row][column].getStatus()) {
                    board[row][column].setIsWinner(true);
                    count++;                
                }
                if (count == winningNumber) {
                    return true;             
                }
            }
            this.removeIsWinnerMark();
        }
        
        count = 0;
        for (int index = 0; index < board.length; index++) {
            individualSquare square = board[index][index];
            if (square.getStatus()) {
                square.setIsWinner(true);
                count++;            
            }
            if (count == winningNumber) {
                return true;
            }
        }        
        this.removeIsWinnerMark();

        count = 0;
        for (int index = board.length - 1; index >= 0; index--) {
            individualSquare square = board[index][(board.length - 1) - index];
            if (square.getStatus()) {
                square.setIsWinner(true);
                count++;
            }
            if (count == winningNumber) {
                return true;
                }
        }
        this.removeIsWinnerMark();
        return false;
    }
    
    public void removeIsWinnerMark() {
        for (individualSquare[] board1 : board) {
            for (int column = 0; column < board[0].length; column++) {
                board1[column].setIsWinner(false);
            }
        }
    }

    public void setWinnerMessage(String newMsg) {
        winnerMessage = newMsg;
    }
}
