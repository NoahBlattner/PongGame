package PongGame.GameManagement;

import PongGame.GameWindow.GameWindow;

public class Game {
    private Gamemode gamemode;
    private GameWindow gameWindow;

    protected float deltaTime = 0;

    /**
     * Start the game
     */
    public void start() {
        gamemode = new Gamemode();
        gameWindow = new GameWindow();
    }

    /**
     * Pause the game
     */
    public void pause() {
        // gamemode.pause();
    }

    /**
     * Resume the game
     */
    public void resume() {
       // gamemode.resume();
    }

    /**
     * Stop the game
     */
    public void stop() {
        // Close the game window
        gameWindow.dispose();
        gamemode = null;
        gameWindow = null;
    }
}
