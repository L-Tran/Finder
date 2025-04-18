import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    // Instance Variables
    private Image Background;
    private Image getPlayer;
    public final int SCREEN_WIDTH = 1500;
    public final int SCREEN_HEIGHT = 1200;
    private final int TITLE_BAR_HEIGHT = 23;
    private Game game;
    private Player p;

    // Constructor
    public GameViewer(Game game, Player p) {
        this.p = p;
        this.game = game;

        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setTitle("Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    // Methods
    public void paint(Graphics g)
    {
        // Color the entire KeyListenerDemo window white
        // First set the Graphics Color "state" to WHITE
        g.setColor(Color.WHITE);

        // Because g.Color was set to WHITE, the rectangle will be WHITE
        g.fillRect(0,  0, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Now have the ball draw itself on top of the White window.
        p.draw(g);
    }
}

