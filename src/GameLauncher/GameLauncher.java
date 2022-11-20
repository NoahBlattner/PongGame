package GameLauncher;

import PongGame.GameManagement.*;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GameLauncher {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.start();

        TimeUnit.SECONDS.sleep(1);

        game.pause();

        TimeUnit.SECONDS.sleep(1);

        game.resume();

        TimeUnit.SECONDS.sleep(1);

        game.stop();
    }
}
