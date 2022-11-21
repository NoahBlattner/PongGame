package GameLauncher;

import PongGame.GameManagement.*;

import java.util.concurrent.TimeUnit;

public class GameLauncher {
    public static void main(String[] args) throws InterruptedException {
        GameEngine.startGame();
        GameEngine game = GameEngine.getInstance();

    }
}
