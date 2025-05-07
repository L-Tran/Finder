import javax.swing.*;
import java.awt.*;

public class Target {

    // Constants
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    // Instance Variables
    private int x, y;

    // Constructor
    public Target() {
        // Set random within the x
        this.x = (int) (Math.random() * 1450);
        // Set random within the upper half y
        this.y = (int) (Math.random() * 500);
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Draw the target on the screen
    public void draw(Graphics g) {
        g.fillRect(x, y, WIDTH, HEIGHT);  // Draw the target as a rectangle
    }
}
