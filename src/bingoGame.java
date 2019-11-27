/**
 * @author chrisbell
 */

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Container;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class bingoGame extends JFrame {
    public static final int WIDTH = 1440;
    public static final int LENGTH = 900;

    private JPanel panel;
    private JButton reset;
    private JButton bingo;
    private JButton nextNum;
    private JButton start;
    private JButton pause;
    private Container layout;
    private Container boxLayout;

    private numberGen bingoNumbers;
    private gameBoard testGrid;
    private userBoard userGrid;
    private computerBoard computerGrid;

    private MouseListener mouseListener;
    private ActionListener timer;
    private ActionListener buttonListener;
    private final int DELAY;
    private boolean startGame = false;
    private boolean winner = false;

    /**
     * Constructs the game window
     */
    public bingoGame() {
        super("CB's Senior Project - Bingo");
        setSize(WIDTH, LENGTH);

        panel = new JPanel();

        //JButtons
        reset = new JButton("Reset");
        bingo = new JButton("Call Bingo");
        nextNum = new JButton("Next Number");
        start = new JButton("Start");
        pause = new JButton("Pause");

        //add JButtons to the panel
        panel.add(start);
        panel.add(pause);
        panel.add(reset);
        panel.add(bingo);
        panel.add(nextNum);

        //This is for if the user needs help navigating the buttons.
        reset.setToolTipText("Resets the game to original layout.");
        bingo.setToolTipText("I have bingo!");
        nextNum.setToolTipText("Call the next number/letter combination.");
        start.setToolTipText("Starts/resumes the game from the beginning if reset or from the current number/letter.");
        pause.setToolTipText("Pauses/stops the game at the current number/letter combination.");

        //adds ActionListeners to buttons
        buttonListener = new ButtonListener();
        reset.addActionListener(buttonListener);
        bingo.addActionListener(buttonListener);
        nextNum.addActionListener(buttonListener);
        start.addActionListener(buttonListener);
        pause.addActionListener(buttonListener);

        layout = this.getContentPane();
        layout.add(panel, "South");
        setVisible(true);

        mouseListener = new MouseClickListener();
        timer = new MyTimer();
        DELAY = 2500;
        Timer t = new Timer(DELAY, timer);
        t.start();

        userGrid = new userBoard();
        computerGrid = new computerBoard();
        bingoNumbers = new numberGen();

        add(userGrid); 
        setVisible(true);

        add(computerGrid);
        setVisible(true);

        add(bingoNumbers);
        setVisible(true);

        userGrid.addMouseListener(mouseListener);
        setVisible(true);
    }

    //the timer
    class MyTimer implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (startGame && !winner) {
                bingoNumbers.generateNumber();
                userGrid.isCalled();
                computerGrid.highlightSquare();
                userGrid.setWinnerMessage("");
                if (computerGrid.checkWin()) {
                    computerGrid.setWinnerMessage("Winner: COMPUTER");
                    userGrid.setWinnerMessage("Sorry, you lost!");
                    winner = true;
                }
                bingoNumbers.repaint();
                computerGrid.repaint();
            }
        }
    }

    //The Mouse listener
    class MouseClickListener implements MouseListener {
        public void mousePressed(MouseEvent event) {
            int x = event.getX();
            int y = event.getY();
            userGrid.highlightSquare(x, y);            
        }

        public void mouseReleased(MouseEvent event) {}
        public void mouseClicked (MouseEvent event) {}
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
    }

    //the button listener
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if (source == reset) {
                userGrid.initializeGrid();
                computerGrid.initializeGrid();
                bingoNumbers.numbers.clear();
                winner = false;
            } else if (source == bingo) {
                if (!winner) {
                    if (userGrid.checkWin()) {
                        userGrid.setWinnerMessage("Winner: User");
                        computerGrid.setWinnerMessage("Users > Computers. Always");
                        winner = true;
                    } else {
                        userGrid.setWinnerMessage("Sorry, you do not have a legal bingo.");
                    }
                }
            } else if (source == nextNum) {
                if (!winner) {
                    bingoNumbers.generateNumber();
                    userGrid.setWinnerMessage("");
                    computerGrid.highlightSquare();   
                    userGrid.isCalled();         
                    if (computerGrid.checkWin()) {
                        computerGrid.setWinnerMessage("Winner: COMPUTER");
                        userGrid.setWinnerMessage("No buts, Clu. That's for Users.");
                        winner = true;
                    }
                }
            } else if (source == start) { //starts the game
                startGame = true;
            } else if (source == pause) { //stops/pauses the game
                startGame = false;
            }
            computerGrid.highlightSquare();
            userGrid.repaint();
            computerGrid.repaint();
            bingoNumbers.repaint();
            layout.repaint();
        }
    }
}