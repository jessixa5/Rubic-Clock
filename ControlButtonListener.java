import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * The ControlButtonListener class listens for button click events in the Rubik's Clock game.
 * It is responsible for performing rotations on the clock grid, updating the display,
 * checking for a win condition, and showing a message when the game is won.
 */
public class ControlButtonListener implements ActionListener {

    private final int index; // region to rotate.
    private final RubiksClockGame game; // clock values.
    private final RubiksClockGUI gui;

    /**
     * Constructs a ControlButtonListener to handle button clicks.
     *
     * @param index The index representing which region of the clock grid to rotate (0-3).
     * @param game The RubiksClockGame instance that will perform the rotation and manage the game state.
     * @param gui The RubiksClockGUI instance that will update the display after each action.
     */
    public ControlButtonListener(int index, RubiksClockGame game, RubiksClockGUI gui) {
        this.index = index;
        this.game = game;
        this.gui = gui;
    }

    /**
     * Handles button click events by rotating the clocks, updating the display,
     * checking the win condition, and showing a message if the game is won.
     * If the game is won, the game is reset and the clock display is updated.
     *
     * @param e The ActionEvent generated when a button is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.rotateClocks(index);
        gui.updateClockDisplay();

        if (game.checkWinCondition()) {
            JOptionPane.showMessageDialog(gui.getFrame(),
                    "Congratulations! You solved it in " + game.getSteps() + " steps.");
            game.resetGame();
            gui.updateClockDisplay();
        }
    }
}
