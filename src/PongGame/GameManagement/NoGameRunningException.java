package PongGame.GameManagement;

public class NoGameRunningException extends RuntimeException {
    public NoGameRunningException() {
        super("The game is not running");
    }
}
