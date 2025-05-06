import javax.swing.*;
import java.awt.*;

public class Player {

    // Constants
    public static final int WIDTH = 50;
    public static final int HEIGHT = 75;
    private static final int JUMP_FORCE = -80;
    private static final int GRAVITY_INCREMENT = 6;
    private static final int MAX_FALL_SPEED = 12;

    // Instance Variables
    private GameViewer map;
    public Image leftImage, rightImage;
    private Image currentImage;
    int x, y, dx, dy;
    private boolean falling, jumping;

    // Constructor
    public Player(GameViewer map) {
        this.map = map;

        // Set default position
        x = 500;
        y = 1500;
        falling = true;

        // Load images
        leftImage = new ImageIcon("Resources/jabbaLeft.png").getImage();
        rightImage = new ImageIcon("Resources/jabbaRight.png").getImage();
        currentImage = leftImage;
    }

    // Getters & Setters


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public boolean isJumping() {
        return jumping;
    }

    // Updates player position
    public void move() {
        x += dx;
        y += dy;
        // Wrap around screen horizontally
        if (x > GameViewer.SCREEN_WIDTH - WIDTH) {
            x = 0;
        }
        else if (x < 0){
            x = GameViewer.SCREEN_WIDTH - WIDTH;
        }

        // Ground check
        if(y >= 1000) {
            y = 1075 - HEIGHT;
            dy = 0;
            jumping = false;
        }
        else {
            // Platform collision check
            falling = true;
            for (Platform p: map.getGame().getPlatforms()) {
                if (isTouching(p) && dy > 0) {
                    falling = false;
                    jumping = false;
                    break;
                }
            }
            gravity();
        }
    }

    public void gravity() {
        if (falling) {
            y += dy;
            dy += GRAVITY_INCREMENT;
            if (dy > MAX_FALL_SPEED) {
                dy = MAX_FALL_SPEED;
            }
        }
        else {
            dy = 0;
        }
    }

    // Checks if the player is touching a platform from above
    public boolean isTouching(Platform p) {
        int px = p.getX();
        int py = p.getY();

        boolean horizontallyAligned = x + WIDTH >= px && x <= px + Platform.PLATFORM_WIDTH;
        boolean verticallyAligned = y + HEIGHT >= py && y <= py + Platform.PLATFORM_HEIGHT;

        if(horizontallyAligned && verticallyAligned){
            if (dy > 0) {
                y = py - HEIGHT;
            }
            return true;
        }
        return false;
    }

    // Sets current image
    public void setImage(Image newImage) {
        currentImage = newImage;
    }

    // Draws the player depending on movement
    public void draw(Graphics g) {
        if (dx > 0) {
            currentImage = rightImage;
        } else if (dx < 0) {
            currentImage = leftImage;
        }
        g.drawImage(currentImage, x, y, 50, 75, map);
    }
}
