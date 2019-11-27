/**
 * @author chrisbell
 */

import java.awt.Rectangle;
import java.util.Random;

public class individualSquare extends Rectangle {
    private int value;
    private boolean status; 
    private boolean isClicked; 
    private static final int RANGE = 15; // Tripped me up at first because I 
    // thought it should be 75 when in reality it's 15 due to each column having only fifteen.
    private boolean isWinner; 

    private Random generator = new Random();

    public individualSquare(int x1, int y1, int width, int height) {
        super(x1, y1, width, height);
        status = false;
        isClicked = false;
        isWinner = false;
    }

    public void setValue(int newValue) {value = newValue;}
    public int getValue() {return value;}
    
    public void setStatus(boolean newStatus) {status = newStatus;}
    public boolean getStatus() {return status;}
    
    public void setIsClicked(boolean newIsClicked) {isClicked = newIsClicked;}
    public boolean getIsClicked() {return isClicked;}
    
    public void setIsWinner(boolean newIsWinner) {isWinner = newIsWinner;}
    public boolean getIsWinner() {return isWinner;}
    
    public int createNum(int col) {
        int temp = RANGE * (col - 1) + (generator.nextInt(15) + 1);
        value = temp;
        return temp;
    }
}
