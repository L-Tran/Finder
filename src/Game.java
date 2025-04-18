import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener, ActionListener {

    // Instance Variables
    GameViewer window;
    private Player p;
    private static final int SLEEP_TIME = 110;
    private static final int STEP_SIZE = 10;
    private static  int DELAY_IN_MILLISECONDS = 5;
    private Timer clock;
    private int isMoving;

    // Constructor
    public Game() {
        p = new Player(window);
        this.window = new GameViewer(this, p);
        window.addKeyListener(this);
        clock = new Timer(DELAY_IN_MILLISECONDS, this);
        clock.start();

    }

    // Methods
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                isMoving = 0;
                break;
            case KeyEvent.VK_RIGHT:
                isMoving = 0;
                break;
            case KeyEvent.VK_UP:
                isMoving = 0;
                break;
            case KeyEvent.VK_DOWN:
                isMoving = 0;
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                p.setImage(p.leftImage);
                isMoving = -1;
                break;
            case KeyEvent.VK_RIGHT:
                p.setImage(p.rightImage);
                isMoving = 1;
                break;
            case KeyEvent.VK_UP:
                p.setImage(p.leftJumpImage);
                isMoving = 2;
                break;
            case KeyEvent.VK_DOWN:
                p.setImage(p.rightJumpImage);
                isMoving = -2;
                break;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(isMoving == - 1) {
            p.shiftX(-STEP_SIZE, 0, window.SCREEN_WIDTH);
        }
        else if(isMoving == 1) {
            p.shiftX(STEP_SIZE, 0, window.SCREEN_WIDTH);
        }
        else if(isMoving == 2) {
            int topOfPane = window.getInsets().top;
            p.shiftY(-STEP_SIZE, topOfPane, window.SCREEN_HEIGHT);
        }
        else if(isMoving == -2) {
            p.shiftY(STEP_SIZE, 0, window.SCREEN_HEIGHT);
        }
        window.repaint();
    }

    public static void main(String[] args) {
        Game g = new Game();
    }
}
