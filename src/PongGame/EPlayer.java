package PongGame;

public enum EPlayer {
    PLAYER1("Player 1"),
    PLAYER2("Player 2");

    String description;

    private EPlayer(String description) {
        this.description = description;
    }
}
