package PongGame.GameManagement;

public class GameNotPausedException extends RuntimeException{
    public GameNotPausedException()
    {
        super("The game is not paused");
    }
}
