package PongGame.GameManagement;

import PongGame.EPlayer;
import PongGame.GameWindow.GameWindow;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class GameEngine implements Runnable {

    private static GameEngine instance;

    // Game variables
    private Gamemode gamemode;
    private GameWindow gameWindow;
    private GameState gameState = GameState.UNINITIALIZED;

    // Tick variables
    protected long lastFrameTime = System.currentTimeMillis();
    Thread tickThread = new Thread(this);

    /**
     * Enum for the state of the game
     */
    enum GameState {
        UNINITIALIZED,
        RUNNING,
        PAUSED,
        STOPPED
    }

    /**
     * Singleton constructor
     */
    private GameEngine() {
        instance = this;
    }

    /**
     * Singleton getter
     * @return GameEngine instance
     */
    public static GameEngine getInstance() {
        if (instance != null) {
            return instance;
        } 
        throw new NoGameRunningException();
    }

    private void tick(float deltaTimeSeconds) {
        gamemode.tick(deltaTimeSeconds);
    }

    @Override
    public void run() {
        // Game loop
        // As long as the game isn't stopped
        while (gameState != GameState.STOPPED && gameState != GameState.UNINITIALIZED) { // Verify if the game is running

            // While the game is running
            while (gameState == GameState.RUNNING) { // Tick the game

                // Calculate delta time
                long currentFrameTime = System.currentTimeMillis();
                long deltaTimeMS = currentFrameTime - lastFrameTime;

                // Execute tick
                tick(deltaTimeMS / 1000F);

                // Update last frame time
                lastFrameTime = currentFrameTime;

                // Delay tick or else the game will run too fast
                // Or not at all -> multiplications with deltatime 0
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Start the game
     */
    public static void startGame() {
        if (instance == null) {
            instance = new GameEngine();
            instance.gameState = GameState.RUNNING;
            instance.gameWindow = new GameWindow();
            instance.gamemode = new Gamemode(instance.gameWindow);

            instance.gameWindow.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (instance.gameState == GameState.PAUSED) {
                            instance.resumeGame();
                        } else if (instance.gameState == GameState.RUNNING) {
                            instance.pauseGame();
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        instance.stopGame();
                    }
                }
            });

            // Start tick thread
            instance.tickThread.start();
            System.out.println("Game started");
        } else {
            throw new GameAlreadyStartedException();
        }
    }

    /**
     * Pause the game
     */
    public void pauseGame() {
        if (gameState == GameState.RUNNING) {
            gameState = GameState.PAUSED;
            gamemode.pause();
            System.out.println("Game paused");
        } else {
            throw new NoGameRunningException();
        }
    }

    /**
     * Resume the game
     */
    public void resumeGame() {
        if (gameState == GameState.PAUSED) {
            lastFrameTime = System.currentTimeMillis();
            gameState = GameState.RUNNING;
            gamemode.resume();
            System.out.println("Game resumed");
        } else {
            throw new GameNotPausedException();
        }
    }

    /**
     * Stop the game
     */
    public void stopGame() {
        if (instance != null) {
            gameState = GameState.STOPPED;
            instance = null;
            gamemode = null;

            // Close the game window
            gameWindow.dispose();
            gameWindow = null;

        } else {
            throw new NoGameRunningException();
        }
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public Dimension getBounceBarSize(EPlayer player) {
        if (player == EPlayer.PLAYER1) {
            return gameWindow.getBounceBar(EPlayer.PLAYER1).getSize();
        } else {
            return gameWindow.getBounceBar(EPlayer.PLAYER2).getSize();
        }
    }
}
