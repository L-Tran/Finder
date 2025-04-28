import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game implements KeyListener, ActionListener {

    // Instance Variables
    GameViewer window;
    private Player p;
    private ArrayList<Platform> platforms;
    private static final int SLEEP_TIME = 110;
    private static final int STEP_SIZE = 10;
    private static  int DELAY_IN_MILLISECONDS = 90;
    private Timer clock;
    private int isMoving;

    // Constructor
    public Game() {
        this.window = new GameViewer(this, p);
        p = new Player(window);
        platforms = new ArrayList<Platform>();
        makePlatforms();
        window.addKeyListener(this);
        clock = new Timer(DELAY_IN_MILLISECONDS, this);
        clock.start();

    }

    // Methods
    public void playGame() {
        window.repaint();
    }

    public Player getP() {
        return p;
    }

    public void makePlatforms() {
        for (int i = 0; i < 15; i++) {
            platforms.add(new Platform((int) (Math.random() * 1300 + 100),(int) (Math.random() * 1100), window));
        }
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
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
                p.setDx(-12);
                break;
            case KeyEvent.VK_RIGHT:
                p.setDx(12);
                break;
            case KeyEvent.VK_UP:
                p.setDy(-30);
                break;
        }
    }

    public void actionPerformed(ActionEvent e) {
        p.move();
        window.repaint();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
