package GameLauncher;

import PongGame.GameManagement.*;

import java.util.concurrent.TimeUnit;

public class GameLauncher {
    public static void main(String[] args) throws InterruptedException {
        GameEngine game = new GameEngine();
        game.startGame();

        TimeUnit.SECONDS.sleep(1);

        game.pauseGame();

        TimeUnit.SECONDS.sleep(1);

        game.resumeGame();

        TimeUnit.SECONDS.sleep(1);

        game.stopGame();
    }
}
