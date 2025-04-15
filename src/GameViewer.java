import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    // Instance Variables
    Image Background;
    Image Player;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private final int TITLE_BAR_HEIGHT = 23;
    private Game game;

    // Constructor
    public GameViewer(Game game) {
        this.game = game;
    }

    // Methods
    public void paint(Graphics g){

    }
}
