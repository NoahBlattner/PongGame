package PongGame.GameWindow;

import PongGame.EPlayer;
import PongGame.GameManagement.GameEngine;

import java.awt.*;

public class BouncyBall extends Panel {

    private int ballSpeed = 500;
    private Vector2D ballDirection = new Vector2D(0,0);
    private int size = 10;

    private BounceBar lastCollidedBounceBar;

    GameWindow gameWindow;

    private class Vector2D {
        private float x;
        private float y;

        public Vector2D(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public BouncyBall(GameWindow gameWindow) {
        // Call parent constructor
        super();

        this.gameWindow = gameWindow;

        setLocation(gameWindow.getWidth() / 2 - size / 2, gameWindow.getHeight() / 2 - size / 2);
        setSize(size, size);
        setBackground(Color.WHITE);

        // Set random direction
        ballDirection.x = (Math.random() > 0.5) ? 1 : -1;
    }

    public void move(float deltaTime) {
        setLocation(getX() + (int) (ballDirection.x * ballSpeed * deltaTime),
                getY() + (int) (ballDirection.y * ballSpeed * deltaTime));


        // Check if ball is touching a bounce bar
        BounceBar player1BounceBar = gameWindow.getBounceBar(EPlayer.PLAYER1);
        BounceBar player2BounceBar = gameWindow.getBounceBar(EPlayer.PLAYER2);

        if (isCollidingWith(player1BounceBar) && lastCollidedBounceBar != player1BounceBar) {
            lastCollidedBounceBar = player1BounceBar;
            ballDirection.x = 1;
        } else if (isCollidingWith(player2BounceBar) && lastCollidedBounceBar != player2BounceBar) {
            lastCollidedBounceBar = player2BounceBar;
            ballDirection.x = -1;
        }
    }

    public boolean isCollidingWith(Panel panel) {
        return !getBounds().intersection(panel.getBounds()).isEmpty();
    }
}
