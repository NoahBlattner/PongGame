package PongGame.GameWindow;

import PongGame.EPlayer;

import java.awt.*;

public class BounceBar extends Panel {

    private float sizeRelativeToScreen = 0.15f;

    /**
     * @param player The player this bar belongs to
     * @param gameWindowWidth The width of the parent window
     * @param gameWindowHeight The height of the parent window
     */
    public BounceBar(EPlayer player, int gameWindowWidth, int gameWindowHeight) {
        // Call parent constructor
        super();

        // Set size
        setPreferredSize(new Dimension(20, (int) (gameWindowHeight * sizeRelativeToScreen)));
        setSize(getPreferredSize());

        // Set style
        setBackground(Color.green);

        // Set position
        // If player is player 1
        if (player == EPlayer.PLAYER1) { // Set position to left side
            setLocation(gameWindowWidth / 8 - getWidth(), (int) (gameWindowHeight / 2 - getHeight() / 1.5));
        } else { // Set position to right side
            setLocation(gameWindowWidth / 8 * 7 - getWidth(), (int) (gameWindowHeight / 2 - getHeight() / 1.5));
        }
    }


    /**
     * Moves the bounce bar up
     * @param moveDistance The distance the bounce bar should move
     */
    public void moveY(int moveDistance) {
        int newY = getY() + moveDistance;
        if (newY > 0 && newY < getParent().getHeight() - getHeight()) {
            setLocation(getX(), getY() + moveDistance);
        }
    }

}
