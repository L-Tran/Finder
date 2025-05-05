import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameViewer extends JFrame {
    // Instance Variables
    private Image Background;
    private Image getPlayer;
    public static final int SCREEN_WIDTH = 1500;
    public static final int SCREEN_HEIGHT = 1200;
    private final int TITLE_BAR_HEIGHT = 23;
    private Game game;

    // Constructor
    public GameViewer(Game game, Player p) {
        this.game = game;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Finder");
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);

    }

    // Methods

    public Game getGame() {
        return game;
    }

    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void myPaint(Graphics g) {
        // First set the Graphics Color "state" to WHITE
        g.setColor(Color.WHITE);
        // Because g.Color was set to WHITE, the rectangle will be WHITE
        g.fillRect(0,  0, SCREEN_WIDTH, SCREEN_HEIGHT);
        game.getP().draw(g);
        g.setColor(Color.black);
        for (Platform p: game.getPlatforms()) {
            p.draw(g);
        }
    }

}

