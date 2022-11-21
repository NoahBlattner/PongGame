package PongGame.GameManagement;

import PongGame.EPlayer;
import PongGame.GameWindow.BounceBar;
import PongGame.GameWindow.GameWindow;

public class Gamemode {
    // Game constants
    private static final int SPEED = 500;

    // Game variables
    private PlayerController playerController1;
    private PlayerController playerController2;

    private BounceBar bounceBar1;
    private BounceBar bounceBar2;

    private GameWindow gameWindow;

    public Gamemode(GameWindow gameWindow) {
        playerController1 = new PlayerController(EPlayer.PLAYER1);
        playerController2 = new PlayerController(EPlayer.PLAYER2);

        this.gameWindow = gameWindow;

        bounceBar1 = gameWindow.getPlayer1BounceBar();
        bounceBar2 = gameWindow.getPlayer2BounceBar();
    }
    public void pause() {
        System.out.println("Game paused");
    }

    public void resume() {
        System.out.println("Game resumed");
    }

    public void tick(float deltaTime) {
        bounceBar1.moveY((int) (playerController1.getYAxis() * SPEED * deltaTime));
        bounceBar2.moveY((int) (playerController2.getYAxis() * SPEED * deltaTime));
    }
}
