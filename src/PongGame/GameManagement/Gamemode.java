package PongGame.GameManagement;

import PongGame.EPlayer;
import PongGame.GameWindow.BounceBar;
import PongGame.GameWindow.BouncyBall;
import PongGame.GameWindow.GameWindow;

public class Gamemode {
    // Game constants
    private static final int SPEED = 500;

    // Game variables
    private PlayerController playerController1;
    private PlayerController playerController2;

    // Components
    private BounceBar bounceBar1;
    private BounceBar bounceBar2;
    private BouncyBall bouncyBall;

    private GameWindow gameWindow;

    public Gamemode(GameWindow gameWindow) {
        playerController1 = new PlayerController(EPlayer.PLAYER1);
        playerController2 = new PlayerController(EPlayer.PLAYER2);

        this.gameWindow = gameWindow;

        bounceBar1 = gameWindow.getBounceBar(EPlayer.PLAYER1);
        bounceBar2 = gameWindow.getBounceBar(EPlayer.PLAYER2);
        bouncyBall = gameWindow.getBouncyBall();
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
        bouncyBall.move(deltaTime);
    }
}
