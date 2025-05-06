import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameViewer extends JFrame {

    // Constants
    public static final int SCREEN_WIDTH = 1500;
    public static final int SCREEN_HEIGHT = 1200;
    private final int TITLE_BAR_HEIGHT = 23;

    // Instance Variables
    private Game game;

    // Constructor
    public GameViewer(Game game) {
        this.game = game;

        // Window setup
        this.setTitle("Finder");
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // Double buffering
        createBufferStrategy(2);
    }


    // Getter
    public Game getGame() {
        return game;
    }

    // Handles repainting using double buffering
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

    // Paint method for background, player, and platforms
    public void myPaint(Graphics g) {
        // Clear the screen
        g.setColor(Color.WHITE);
        g.fillRect(0,  0, SCREEN_WIDTH, SCREEN_HEIGHT);

        int state = game.getGameState();
        if (state == 1) {
            // Instructions Screen
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("INSTRUCTIONS", 100, 100);

            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Use arrow keys to move and jump. Hold Jump to jump higher.", 100, 150);
            g.drawString("Reach the red target to score.", 100, 180);
            g.drawString("Avoid running out of time!", 100, 210);
            g.drawString("Press ENTER to start.", 100, 260);
        }
        else if (state == 2) {
            // Draw player
            game.getP().draw(g);

            // Draw Target
            g.setColor(Color.red);
            game.getTarget().draw(g);

            // Draw all platforms
            g.setColor(Color.black);
            for (Platform p: game.getPlatforms()) {
                p.draw(g);
            }

            g.drawString("Time Left: " + game.getTimeLeft() / 11 + "s", 20, 40);
            g.drawString("Score: " + game.getScore(), 20, 60);
        }
        else if (state == 3) {
            // Game Over Screen
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString("GAME OVER", 100, 100);

            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Final Score: " + game.getScore(), 100, 150);
            g.drawString("Press space to play again",100, 170);
        }

    }

}

