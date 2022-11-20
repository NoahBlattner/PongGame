package PongGame.GameManagement;

public class GameAlreadyStartedException extends RuntimeException {
    public GameAlreadyStartedException() {
        super("The game has already been started");
    }
}
