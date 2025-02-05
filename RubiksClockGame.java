/**
 * The RubiksClockGame class represents the game logic for a 3x3 Rubik's Clock puzzle.
 * It manages the clock values on a 3x3 grid and handles game state changes such as rotations, win condition checks, and game resets.
 */
public class RubiksClockGame {

    private int[][] clockValues = new int[3][3];
    private int steps = 0;

    /**
     * Constructs a RubiksClockGame with the specified initial clock values.
     *
     * @param initialValues A 3x3 array of integers representing the initial clock values.
     */
    public RubiksClockGame(int[][] initialValues) {
        clockValues = initialValues;
    }

    /**
     * Constructs a RubiksClockGame and initializes the clock values to random values.
     */
    public RubiksClockGame() {
        resetGame();
    }

    /**
     * Rotates the clocks in a specific region based on the controlId.
     * Each region of the 3x3 grid (top-left, top-right, bottom-left, bottom-right) is rotated based on the controlId.
     * After a rotation, the step counter is incremented.
     *
     * @param controlId An integer representing the control for the region to rotate:
     *                  0 for top-left, 1 for top-right, 2 for bottom-left, 3 for bottom-right.
     */
    public void rotateClocks(int controlId) {
        int[][] clockValues = getClockValues();

        switch (controlId) {
            case 0: // top-left
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        clockValues[i][j] = (clockValues[i][j] % 12) + 1;
                    }
                }
                break;
            case 1: // top-right
                for (int i = 0; i < 2; i++) {
                    for (int j = 1; j < 3; j++) {
                        clockValues[i][j] = (clockValues[i][j] % 12) + 1;
                    }
                }
                break;
            case 2: // bottom-left
                for (int i = 1; i < 3; i++) {
                    for (int j = 0; j < 2; j++) {
                        clockValues[i][j] = (clockValues[i][j] % 12) + 1;
                    }
                }
                break;
            case 3: // bottom-right
                for (int i = 1; i < 3; i++) {
                    for (int j = 1; j < 3; j++) {
                        if (clockValues[i][j] < 12) {
                            clockValues[i][j]++;
                        } else {
                            clockValues[i][j] = 12;
                        }
                    }
                }
                break;
        }

        steps++;

        setClockValues(clockValues);
    }

    /**
     * Checks if the current state of the game is a win condition.
     * The win condition is met when all clock values are set to 12.
     *
     * @return True if the win condition is met (all values are 12), false otherwise.
     */
    public boolean checkWinCondition() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clockValues[i][j] != 12) return false;
            }
        }
        return true;
    }

    /**
     * Returns the current clock values in the 3x3 grid.
     *
     * @return A 3x3 array of integers representing the current clock values.
     */
    public int[][] getClockValues() {
        return clockValues;
    }

    /**
     * Returns the number of steps taken in the game.
     *
     * @return The number of steps taken.
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Resets the game by assigning random values to the clock grid and resetting the step counter.
     */
    public void resetGame() {
        steps = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clockValues[i][j] = (int) (Math.random() * 12) + 1;
            }
        }
    }

    /**
     * Sets the clock values to the specified 3x3 grid of integers.
     *
     * @param newClockValues A 3x3 array of integers representing the new clock values.
     */
    public void setClockValues(int[][] newClockValues) {
        this.clockValues = newClockValues;
    }
}
