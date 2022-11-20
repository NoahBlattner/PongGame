package PongGame.GameManagement;

public class Gamemode {
    public void pause() {
        System.out.println("Game paused");
    }

    public void resume() {
        System.out.println("Game resumed");
    }

    public void tick(long deltaTime) {
        System.out.println("Game ticked : " + deltaTime);
    }
}
