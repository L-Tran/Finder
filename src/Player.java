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
    private boolean falling;

    // Constructor
    public Player(GameViewer map) {
        width = 50;
        height = 75;
        x = 350;
        y = 250;
        falling = true;

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
        y += dy;
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
            falling = true;
            for (Platform p: map.getGame().getPlatforms()) {
                if (isTouching(p)) {
                    falling = false;
                    break;
                }
            }
            if (falling) {
                y += dy;
                dy += 6;
                if (dy > 18) {
                    dy = 12;
                }
            }
            else {
                dy = 0;
            }
        }
    }

    public boolean isTouching(Platform p) {
        int px = p.getX();
        int py = p.getY();

        boolean horizontallyAligned = x + width >= px && x <= px + Platform.PLATFORM_WIDTH;
        boolean verticallyAligned = y + height >= py && y + height <= py + Platform.PLATFORM_HEIGHT;
        return horizontallyAligned && verticallyAligned && dy > 0;
    }

    public void setImage(Image newImage) {
        currentImage = newImage;
    }

    public void draw(Graphics g) {
        if (dx > 0) {
            currentImage = rightImage;
        } else if (dx < 0) {
            currentImage = leftImage;
        }
        g.drawImage(currentImage, x, y, 50, 75, map);
    }
}
