import javax.swing.*;

/**
 * The Main class is the entry point for the Rubik's Clock game application.
 * It initializes the GUI by creating an instance of the {@link RubiksClockGUI} class
 * and setting up the main game window.
 */
public class Main {

    /**
     * The main method is the entry point for the Rubik's Clock game application.
     * It runs the GUI creation on the Event Dispatch Thread using {@link SwingUtilities#invokeLater}.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RubiksClockGUI::new);
    }
}
