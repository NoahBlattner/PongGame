package GameLauncher;

import PongGame.GameManagement.*;

import java.util.concurrent.TimeUnit;

public class GameLauncher {
    public static void main(String[] args) {
        GameEngine.startGame();
        GameEngine game = GameEngine.getInstance();
    }
}
