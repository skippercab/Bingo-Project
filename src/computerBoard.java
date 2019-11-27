/**
 * @author chrisbell
 */

import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;

public class computerBoard extends gameBoard{
    public computerBoard() {
        super();     

        indentX = 825;
        indentY = 250;        
        initializeGrid();
    }

    public void paintComponent(Graphics g) {        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int fontSize = 26;
        int letterFont = 46;
        Font regularText = new Font("SansSerif", Font.PLAIN, fontSize);
        Font bigText = new Font("SansSerif", Font.BOLD, letterFont);
        g2.setFont(regularText);;

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                individualSquare square = board[row][column];

                g2.draw(square);
                if (square.getIsClicked() ) {
                    g2.setColor(Color.PINK);
                    if (square.getIsWinner()) {
                        g2.setColor(Color.RED);
                    }
                    g2.fill(square);                    
                    g2.setColor(Color.BLACK);
                    g2.draw(square);
                }
                
                if (row == 2 && column == 2) {}
                else {
                    int value = board[row][column].getValue();
                    int xCoord = (int)square.getX() + (SQUARE_SIZE / 4);
                    int yCoord = (int)square.getY() + (SQUARE_SIZE / 2) + (SQUARE_SIZE / 8);
                    if (value < 10)
                        g2.drawString(" " + value + "", xCoord, yCoord);
                    else
                        g2.drawString(value + "", xCoord, yCoord);
                }

                if (row == 0) {
                    g2.setFont(bigText);;
                    g2.drawString(BINGO[column], (int)square.getX() + (SQUARE_SIZE / 6), (int)square.getY() - (SQUARE_SIZE / 4) );
                    g2.setFont(regularText);
                }

                if (row == board.length - 1 && column == 0) {
                    g2.setFont(bigText);
                    g2.setColor(Color.LIGHT_GRAY);
                    g2.drawString("Computer", (int)square.getX() + (SQUARE_SIZE / 3) * 2, (int)square.getY() + SQUARE_SIZE + ((SQUARE_SIZE / 3) * 2));
                    g2.setFont(regularText);
                    g2.setColor(Color.BLACK);
                }
            }
        }

        g2.setColor(Color.RED);
        g2.drawString(winnerMessage, 850, 190);
    }

    public void highlightSquare() {
        for (int x : numberGen.numbers) {
            for (int row = 0; row < WIDTH; row++) {
                for (int column = 0; column < LENGTH; column++) {
                    if (x == board[row][column].getValue()) {
                        board[row][column].setStatus(true);
                        board[row][column].setIsClicked(true);
                    }
                }
            }
        }
    }
}
