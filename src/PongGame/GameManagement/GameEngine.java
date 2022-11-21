package PongGame.GameManagement;

import PongGame.GameWindow.GameWindow;

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
            // Close the game window
            gamemode = null;
            gameWindow = null;
            gameWindow.dispose();
            System.out.println("Game stopped");
        } else {
            throw new NoGameRunningException();
        }
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }
}
