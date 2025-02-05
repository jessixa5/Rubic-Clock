import javax.swing.*;
import java.awt.*;

/**
 * The RoundButton class is a custom JButton that renders as a circular button.
 * It overrides default painting methods to provide a rounded appearance,
 * and customizes the preferred size and hit-test logic to ensure the button behaves
 * correctly as a circle in the UI.
 */
public class RoundButton extends JButton {

    /**
     * Constructs a new RoundButton with the specified label.
     *
     * @param label The text to display on the button.
     */
    public RoundButton(String label) {
        super(label);
        setFocusPainted(false); // remove focus border
        setContentAreaFilled(false); //content area transparent.
    }

    /**
     * Paints the component by rendering it as a circle. The button's appearance changes when it is pressed or clicked.
     *
     * @param g The graphics context used for painting the component.
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    /**
     * Paints the border of the button, ensuring the circular border is drawn.
     *
     * @param g The graphics context used for drawing the border.
     */
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1); // draw the circular border.
    }

    /**
     * Gets the preferred size of the button. The size is set to be a square where the width and height are equal.
     *
     * @return The preferred size of the button as a Dimension object.
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        return size;
    }

    /**
     * Determines whether the specified point (x, y) is within the bounds of the circular button.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return True if the point is inside the circle; false otherwise.
     */
    @Override
    public boolean contains(int x, int y) {
        int radius = getWidth() / 2;
        return (x - radius) * (x - radius) + (y - radius) * (y - radius) <= radius * radius; // check if the point is inside circle.
    }
}
