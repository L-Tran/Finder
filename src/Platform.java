import java.awt.*;

public class Platform {

    // Instance Variables
    GameViewer map;
    public static final int PLATFORM_WIDTH = 300;
    public static final int PLATFORM_HEIGHT = 20;
    int x, y;

    // Constructor
    public Platform(int x, int y, GameViewer map) {
        this.x = x;
        this.y = y;
        this.map = map;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.drawRect(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }
}
