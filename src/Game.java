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
    private static  int DELAY_IN_MILLISECONDS = 7;
    private Timer clock;
    private int isMoving;

    // Constructor
    public Game() {
        this.window = new GameViewer(this, p);
        p = new Player(window);
        window.addKeyListener(this);
        clock = new Timer(DELAY_IN_MILLISECONDS, this);
        clock.start();

    }

    // Methods

    public Player getP() {
        return p;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                p.setDx(0);;
                break;
            case KeyEvent.VK_RIGHT:
                p.setDx(0);;
                break;
            case KeyEvent.VK_UP:
                p.setDy(0);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                p.setDx(-4);
                break;
            case KeyEvent.VK_RIGHT:
                p.setDx(4);
                break;
            case KeyEvent.VK_UP:
                p.setDy(-20);
                break;
        }
    }

    public void actionPerformed(ActionEvent e) {
        p.move();
        window.repaint();
    }

    public static void main(String[] args) {
        Game g = new Game();
    }
}
