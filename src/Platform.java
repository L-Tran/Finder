import java.awt.*;

public class Platform {

    // Constants
    public static final int PLATFORM_WIDTH = 150;
    public static final int PLATFORM_HEIGHT = 20;

    // Instance Variables
    private int x, y;
    private GameViewer map;

    // Constructor
    public Platform(int x, int y, GameViewer map) {
        this.x = x;
        this.y = y;
        this.map = map;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Draws the platform
    public void draw(Graphics g) {
        g.drawRect(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }
}
