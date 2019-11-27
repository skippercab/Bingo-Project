/**
 * @author chrisbell
 */

import javax.swing.JFrame; 

public class startFile{
    public static void main(String[] args) {
            bingoGame game = new bingoGame();

            game.setVisible(true);
            game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }    
}