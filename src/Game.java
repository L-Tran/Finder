import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener, ActionListener {

    // Instance Variables
    GameViewer window;
    private static final int SLEEP_TIME = 110;

    // Constructor
    public Game(GameViewer window) {
        this.window = window;
    }

    // Methods
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    public void actionPerformed(ActionEvent e) {

    }
}
