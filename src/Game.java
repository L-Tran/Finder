import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game implements KeyListener, ActionListener {

    // Constants
    private static final int STEP_SIZE = 15;
    private static final int JUMP_STRENGTH = -80;
    private static final int DELAY_IN_MILLISECONDS = 90;
    private static final int NUM_PLATFORMS = 7;
    private static final int PLATFORM_SPACING = 215;
    private static final int INITIAL_TIME = 190;

    // Instance Variables
    private GameViewer window;
    private int gameState;
    private Player p;
    private Target target;
    private ArrayList<Platform> platforms;
    private Timer clock;
    private Timer countDownTimer;

    private int timeLeft;
    private int timePenalty;
    private int score;

    // Constructor
    public Game() {
        this.window = new GameViewer(this);
        this.gameState = 1;
        this.p = new Player(window);
        this.platforms = new ArrayList<Platform>();
        this.target = new Target();
        this.score = 0;
        this.timeLeft = INITIAL_TIME;

        makePlatforms();

        window.addKeyListener(this);
        clock = new Timer(DELAY_IN_MILLISECONDS, this);
        countDownTimer = new Timer(1000, this);
        clock.start();
        countDownTimer.start();
    }

    // Getters
    public Player getP() {return p;}

    public ArrayList<Platform> getPlatforms() {return platforms;}

    public Target getTarget() {return target;}

    public int getTimeLeft() {return timeLeft;}

    public int getScore() {return score;}

    public int getGameState() {return gameState;}

    public void playGame() {
        window.repaint();
    }

    public void resetGame() {
        this.p = new Player(window); // Reset player
        this.platforms.clear();
        makePlatforms();             // Reset platforms
        this.target = new Target();  // New target
        this.score = 0;
        this.timePenalty = 0;
        this.timeLeft = INITIAL_TIME;

        clock.start();
        countDownTimer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (gameState == 2) {
            p.move();
            checkTargetCollision();
            window.repaint();

            if (timeLeft > 0) {
                timeLeft--;
            } else {
                gameOver();  // Trigger game over if time runs out
            }
            countDownTimer.start();
        }
    }

    public void makePlatforms() {
        for (int i = 0; i < NUM_PLATFORMS; i++) {
            int x = (int)(Math.random() * 30) + i * PLATFORM_SPACING;
            int y = (int)(Math.random() * 500 + 500);
            platforms.add(new Platform(x, y, window));
        }
    }

    // Check if the player collides with the target
    public void checkTargetCollision() {
        // Get the player's coordinates
        int px = p.getX();
        int py = p.getY();
        int tx = target.getX();
        int ty = target.getY();

        // Check for collision with the target
        if (px + p.WIDTH >= tx && px <= tx + target.WIDTH && py + p.HEIGHT >= ty && py <= ty + target.HEIGHT) {
            platforms.clear();
            makePlatforms();  // Randomize the platforms if the target is hit
            target = new Target();

            score++;
            timePenalty += 10;
            timeLeft = INITIAL_TIME - timePenalty;
        }
    }

    public void gameOver() {
        clock.stop();
        countDownTimer.stop();
        platforms.clear();
        gameState = 3;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                if (gameState == 2) {
                    p.setDx(0);
                    break;
                }
            case KeyEvent.VK_RIGHT:
                if (gameState == 2) {
                    p.setDx(0);
                    break;
                }
            case KeyEvent.VK_UP:
                if (gameState == 2) {
                    p.setDy(0);
                    break;
                }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_SPACE:
                if (gameState == 3) {
                    gameState = 1;
                    window.repaint();
                    break;
                }
            case KeyEvent.VK_ENTER:
                if (gameState == 1) {
                    resetGame();
                    gameState = 2;
                    break;
                }
            case KeyEvent.VK_LEFT:
                if (gameState == 2) {
                    p.setDx(-STEP_SIZE);
                    break;
                }
            case KeyEvent.VK_RIGHT:
                if (gameState == 2) {
                    p.setDx(STEP_SIZE);
                    break;
                }
            case KeyEvent.VK_UP:
                if (gameState == 2) {
                    if (!p.isJumping()) {
                        p.setDy(JUMP_STRENGTH);
                        p.setJumping(true);
                    }
                    break;
                }
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
