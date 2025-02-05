import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link RubiksClockGame} class. This class tests various functionalities
 * of the game such as initialization, clock rotations, step counting, win conditions, and reset behavior.
 */
public class RubiksClockGameTest {

    private RubiksClockGame game;

    /**
     * Sets up the game before each test case. Initializes the game with default clock values.
     */
    @BeforeEach
    public void setup() {
        game = new RubiksClockGame();
    }

    /**
     * Tests the initialization of the game to ensure that clock values are within the valid range (1 to 12).
     */
    @Test
    public void testInitialization() {
        int[][] clockValues = game.getClockValues();
        for (int[] row : clockValues) {
            for (int value : row) {
                assertTrue(value >= 1 && value <= 12, "Clock value should be between 1 and 12");
            }
        }
    }

    /**
     * Tests the rotation of the top-left control. The clock values in the top-left quadrant should increase by 1.
     */
    @Test
    public void testRotateTopLeftControl() {
        game = new RubiksClockGame(new int[][] {
                {3, 4, 5},
                {6, 7, 8},
                {9, 10, 11}
        });
        game.rotateClocks(0);
        assertEquals(4, game.getClockValues()[0][0]);
        assertEquals(5, game.getClockValues()[0][1]);
        assertEquals(7, game.getClockValues()[1][0]);
        assertEquals(8, game.getClockValues()[1][1]);
    }

    /**
     * Tests the rotation of the top-right control. The clock values in the top-right quadrant should increase by 1.
     */
    @Test
    public void testRotateTopRightControl() {
        game = new RubiksClockGame(new int[][] {
                {3, 4, 5},
                {6, 7, 8},
                {9, 10, 11}
        });
        game.rotateClocks(1);

        assertEquals(5, game.getClockValues()[0][1]);
        assertEquals(6, game.getClockValues()[0][2]);
        assertEquals(8, game.getClockValues()[1][1]);
        assertEquals(9, game.getClockValues()[1][2]);
    }

    /**
     * Tests the rotation of the bottom-left control. The clock values in the bottom-left quadrant should increase by 1.
     */
    @Test
    public void testRotateBottomLeftControl() {
        game = new RubiksClockGame(new int[][] {
                {3, 4, 5},
                {6, 7, 8},
                {9, 10, 11}
        });
        game.rotateClocks(2);

        assertEquals(7, game.getClockValues()[1][0]);
        assertEquals(8, game.getClockValues()[1][1]);
        assertEquals(10, game.getClockValues()[2][0]);
        assertEquals(11, game.getClockValues()[2][1]);
    }

    /**
     * Tests the rotation of the bottom-right control. The clock values in the bottom-right quadrant should increase by 1.
     */
    @Test
    void testRotateBottomRightControl() {
        int[][] initialValues = {
                {11, 5, 3},
                {7, 8, 6},
                {4, 1, 2}
        };

        RubiksClockGame game = new RubiksClockGame(initialValues);

        game.rotateClocks(3);

        int[][] expectedValues = {
                {11, 5, 3},
                {7, 9, 7},
                {4, 2, 3}
        };

        assertArrayEquals(expectedValues, game.getClockValues());
    }

    /**
     * Prints the current clock values in a 3x3 grid format for debugging purposes.
     *
     * @param clockValues A 2D array of clock values to be printed.
     */
    private void printClockValues(int[][] clockValues) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(clockValues[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Tests the step counter. After a rotation, the step counter should increment by 1.
     */
    @Test
    public void testStepCounter() {
        int initialSteps = game.getSteps();
        game.rotateClocks(0);
        assertEquals(initialSteps + 1, game.getSteps(), "Step counter should increment by 1 after each rotation");
    }

    /**
     * Tests the wrap-around functionality at clock value 12. After rotation, a clock value of 12 should wrap to 1.
     */
    @Test
    public void testWrapAroundAt12() {
        int[][] clockValues = game.getClockValues();
        clockValues[0][0] = 12;
        game.rotateClocks(0);
        assertEquals(1, game.getClockValues()[0][0], "Clock should wrap around from 12 to 1");
    }

    /**
     * Tests the win condition when all clock values are 12. The game should detect a win.
     */
    @Test
    public void testWinConditionAll12s() {
        int[][] clockValues = game.getClockValues();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clockValues[i][j] = 12;
            }
        }
        assertTrue(game.checkWinCondition(), "Game should detect a win when all clocks show 12");
    }

    /**
     * Tests the non-win condition when at least one clock does not show 12. The game should not detect a win.
     */
    @Test
    public void testNonWinCondition() {
        int[][] clockValues = game.getClockValues();
        clockValues[0][0] = 11;
        assertFalse(game.checkWinCondition(), "Game should not detect a win when not all clocks show 12");
    }

    /**
     * Tests the reset game functionality. After resetting, the clock values should be between 1 and 12 and the step counter should reset.
     */
    @Test
    public void testResetGame() {
        game.rotateClocks(0);
        game.resetGame();
        int[][] clockValues = game.getClockValues();
        for (int[] row : clockValues) {
            for (int value : row) {
                assertTrue(value >= 1 && value <= 12, "Clock value should be between 1 and 12 after reset");
            }
        }
        assertEquals(0, game.getSteps(), "Step counter should reset to 0 after game reset");
    }
}
