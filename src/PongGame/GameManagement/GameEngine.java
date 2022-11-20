package PongGame.GameManagement;

import PongGame.GameWindow.GameWindow;

import java.util.concurrent.TimeUnit;

public class GameEngine implements Runnable {

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

    private void tick(long deltaTime) {
        gamemode.tick(deltaTime);
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
                long deltaTime = currentFrameTime - lastFrameTime;

                // Execute tick
                tick(deltaTime);

                // Update last frame time
                lastFrameTime = currentFrameTime;

                // Delay tick
                // Sleep for 1 millisecond while actually sleep for ~15 milliseconds
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
    public void startGame() {
        if (gameState == GameState.UNINITIALIZED) {
            gamemode = new Gamemode();
            gameWindow = new GameWindow();
            gameState = GameState.RUNNING;

            // Start tick thread
            tickThread.start();
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
            gamemode.pause();
            gameState = GameState.PAUSED;
            System.out.println("Game paused");
        } else {
            throw new GameNotRunningException();
        }
    }

    /**
     * Resume the game
     */
    public void resumeGame() {
        if (gameState == GameState.PAUSED) {
            gamemode.resume();
            gameState = GameState.RUNNING;
            System.out.println("Game resumed");
        } else {
            throw new GameNotPausedException();
        }
    }

    /**
     * Stop the game
     */
    public void stopGame() {
        if (gameState == GameState.RUNNING || gameState == GameState.PAUSED) {
            // Close the game window
            gameWindow.dispose();
            gamemode = null;
            gameWindow = null;
            gameState = GameState.STOPPED;
            System.out.println("Game stopped");
        } else {
            throw new GameNotRunningException();
        }
    }
}
