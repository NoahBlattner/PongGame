package PongGame.GameManagement;

public class GameNotRunningException extends RuntimeException {
    public GameNotRunningException() {
        super("The game is not running");
    }
}
