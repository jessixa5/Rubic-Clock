import javax.swing.*;
import java.awt.*;

/**
 * The RubiksClockGUI class is responsible for the graphical user interface of the Rubik's Clock game.
 * It creates and displays the game grid, control buttons, and updates the clock values as the game progresses.
 */
public class RubiksClockGUI {

    private JFrame frame;
    private JPanel gridPanel;
    private RoundButton[][] clockButtons = new RoundButton[3][3];
    private JButton[] controlButtons = new JButton[4];
    private RubiksClockGame game;

    /**
     * Constructs a new RubiksClockGUI instance and initializes the game, window, and grid display.
     */
    public RubiksClockGUI() {
        game = new RubiksClockGame();
        frame = new JFrame("Rubik's Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gridPanel = new JPanel(new GridLayout(5, 5));
        gridPanel.setPreferredSize(new Dimension(400, 400));
        initializeGrid();

        frame.add(gridPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Initializes the grid with clock buttons and control buttons.
     * The grid layout is 5x5 with clock buttons placed at specific intervals,
     * and control buttons placed at the center of the grid.
     */
    private void initializeGrid() {
        int[][] clockValues = game.getClockValues(); //current clock values
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    int clockRow = i / 2, clockCol = j / 2;
                    RoundButton clockButton = new RoundButton(String.valueOf(clockValues[clockRow][clockCol]));
                    clockButton.setFont(new Font("Arial", Font.BOLD, 20));
                    clockButtons[clockRow][clockCol] = clockButton;
                    gridPanel.add(clockButton);
                } else if ((i == 1 || i == 3) && (j == 1 || j == 3)) {
                    int controlIndex = (i == 1 ? 0 : 2) + (j == 3 ? 1 : 0);
                    JButton controlButton = new JButton("+");
                    controlButton.setFont(new Font("Arial", Font.BOLD, 20));
                    controlButton.addActionListener(new ControlButtonListener(controlIndex, game, this));
                    controlButtons[controlIndex] = controlButton;
                    gridPanel.add(controlButton);
                } else {
                    gridPanel.add(new JPanel());
                }
            }
        }
    }

    /**
     * Updates the clock display to reflect the current values from the game.
     * This method is called after each rotation to refresh the UI.
     */
    public void updateClockDisplay() {
        int[][] clockValues = game.getClockValues();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clockButtons[i][j].setText(String.valueOf(clockValues[i][j]));
            }
        }
    }

    /**
     * Gets the JFrame that contains the entire GUI window.
     *
     * @return The JFrame of the GUI window.
     */
    public JFrame getFrame() {
        return frame;
    }
}
