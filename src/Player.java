import javax.swing.*;
import java.awt.*;

public class Player {

    // Instance Variables
    private GameViewer map;
    int x, y, width, height;
    int mapWidth;
    int mapHeight;
    public Image leftImage, rightImage, leftJumpImage, rightJumpImage;
    private Image currentImage;
    private static final int MAX_SPEED = 8;

    // Constructor
    public Player(GameViewer map) {
        width = 200;
        height = 200;
        x = 350;
        y = 250;
        this.map = map;
        leftImage = new ImageIcon("Resources/jabbaLeft.png").getImage();
        rightImage = new ImageIcon("Resources/jabbaRight.png").getImage();
        leftJumpImage = new ImageIcon("Resources/jabbaJumpLeft.png").getImage();
        rightJumpImage = new ImageIcon("Resources/jabbaJumpRight.png").getImage();
        currentImage = leftImage;
    }

    // Methods
    public void shiftX(int shift, int xLow, int xHigh) {
        if (x - width + shift <= xLow && shift < 0) {
            x = xLow + width;
        }
        else if (x + width + shift >= xHigh && shift > 0) {
            x = xHigh - width;
        }
        else {
            x += shift;
        }
    }

    /**
     * Shift the ball by the given amount in the y direction.
     * @param shift     How much to shift the ball's location
     */
    public void shiftY(int shift, int yLow, int yHigh) {
        if (y - height + shift <= yLow && shift < 0) {
            y = yLow + height;
        }
        else if (y + height + shift >= yHigh && shift > 0) {
            y = yHigh - height;
        }
        else {
            y += shift;
        }
    }

    public void setImage(Image newImage) {
        currentImage = newImage;
    }


    public void jump() {

    }

    public void draw(Graphics g) {
        g.drawImage(currentImage,x,y,map);
    }
}
