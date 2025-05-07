// Finder by Logan Tran
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

        // Make Initial Platforms
        makePlatforms();

        // Start Timers and Key listener
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

    // Start Game
    public void playGame() {
        window.repaint();
    }

    // Reset game
    public void resetGame() {
        // New player, platforms, target, timer
        this.p = new Player(window);
        this.platforms.clear();
        makePlatforms();
        this.target = new Target();
        this.score = 0;
        this.timePenalty = 0;
        this.timeLeft = INITIAL_TIME;
        // Restart Timer
        clock.start();
        countDownTimer.start();
    }

    // Animation
    public void actionPerformed(ActionEvent e) {
        // Only in Second state of the game
        if (gameState == 2) {
            // Allow player to move
            p.move();
            // Check Target Collision
            checkTargetCollision();
            window.repaint();

            // Slowly deplete time
            if (timeLeft > 0) {
                timeLeft--;
            } else {
                // If no time left game over
                gameOver();
            }
        }
    }

    // Generate platforms
    public void makePlatforms() {
        // Seven platforms
        for (int i = 0; i < NUM_PLATFORMS; i++) {
            // Random within 30 in each part
            int x = (int)(Math.random() * 30) + i * PLATFORM_SPACING;
            // lower have the screen
            int y = (int)(Math.random() * 500 + 500);
            // Add to Arraylist
            platforms.add(new Platform(x, y, window));
        }
    }

    // Check if the player collides with the target
    public void checkTargetCollision() {
        // Get the player's coordinates
        int px = p.getX();
        int py = p.getY();
        // Get target's coordinates
        int tx = target.getX();
        int ty = target.getY();

        // Check for collision with the target
        if (px + p.WIDTH >= tx && px <= tx + target.WIDTH && py + p.HEIGHT >= ty && py <= ty + target.HEIGHT) {
            // Randomize the platforms and create new target if the target is hit
            platforms.clear();
            makePlatforms();
            target = new Target();
            // Add score and reduce time
            score++;
            timePenalty += 10;
            timeLeft = INITIAL_TIME - timePenalty;
        }
    }

    // When game is lost
    public void gameOver() {
        // Stop Timers
        clock.stop();
        countDownTimer.stop();
        // Set game state
        gameState = 3;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            // When released stop acceleration
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
            // Restart game
            case KeyEvent.VK_SPACE:
                if (gameState == 3) {
                    // Change game state
                    gameState = 1;
                    window.repaint();
                    break;
                }
            // Start game
            case KeyEvent.VK_ENTER:
                if (gameState == 1) {
                    // Reset and change game state
                    resetGame();
                    gameState = 2;
                    break;
                }
            // Change accel left when left arrow key pressed
            case KeyEvent.VK_LEFT:
                if (gameState == 2) {
                    p.setDx(-STEP_SIZE);
                    break;
                }
            // Change accel right when right arrow key pressed
            case KeyEvent.VK_RIGHT:
                if (gameState == 2) {
                    p.setDx(STEP_SIZE);
                    break;
                }
            // Jump when up arrow pressed
            case KeyEvent.VK_UP:
                if (gameState == 2) {
                    // Only allow jumping when not already jumping
                    if (!p.isJumping()) {
                        p.setDy(JUMP_STRENGTH);
                        p.setJumping(true);
                    }
                    break;
                }
        }
    }

    // Main
    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
