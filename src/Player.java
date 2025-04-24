import javax.swing.*;
import java.awt.*;

public class Player {

    // Instance Variables
    private GameViewer map;
    int x, y, dx, dy, width, height;
    int mapWidth;
    int mapHeight;
    public Image leftImage, rightImage;
    private Image currentImage;
    private static final int MAX_SPEED = 8;

    // Constructor
    public Player(GameViewer map) {
        width = 50;
        height = 75;
        x = 350;
        y = 250;
        this.map = map;
        leftImage = new ImageIcon("Resources/jabbaLeft.png").getImage();
        rightImage = new ImageIcon("Resources/jabbaRight.png").getImage();
        currentImage = leftImage;
    }

    // Methods
    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void move() {
        x += dx;
        if (x > GameViewer.SCREEN_WIDTH - width) {
            x = 0;
        }
        else if (x < 0){
            x = GameViewer.SCREEN_WIDTH - width;
        }
        if(y >= 1000) {
            dy = 0;
        }
        else {
            y += dy;
            dy += 2;
            if (dy > 4) {
                dy = 4;
            }
        }
    }

    public void setImage(Image newImage) {
        currentImage = newImage;
    }

    public void draw(Graphics g) {
        if (dx > 0) {
            currentImage = rightImage;
        } else {
            currentImage = leftImage;
        }
        g.drawImage(currentImage, x, y, 50, 75, map);
    }
}
