package PongGame.GameWindow;

import PongGame.EPlayer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BounceBar extends Panel {

    /**
     * @param player The player this bar belongs to
     * @param gameWindowWidth The width of the parent window
     * @param gameWindowHeight The height of the parent window
     */
    public BounceBar(EPlayer player, int gameWindowWidth, int gameWindowHeight) {
        // Call parent constructor
        super();

        // Set size
        setSize(20, 100);
        setPreferredSize(new Dimension(20, (int) (gameWindowHeight * 0.15)));

        // Set style
        setBackground(Color.white);

        // Set position
        // If player is player 1
        if (player == EPlayer.PLAYER1) { // Set position to left side
            setLocation(gameWindowWidth / 8 - getWidth(), gameWindowHeight / 2 - getHeight());
        } else { // Set position to right side
            setLocation(gameWindowWidth / 8 * 7 - getWidth(), gameWindowHeight / 2 - getHeight());
        }
    }


    /**
     * Moves the bounce bar up
     * @param moveDistance The distance the bounce bar should move
     */
    public void moveUp(int moveDistance) {
        setLocation(getX(), getY() - moveDistance);
    }

    /**
     * Moves the bounce bar down
     * @param moveDistance The distance the bounce bar should move
     */
    public void moveDown(int moveDistance) {
        setLocation(getX(), getY() + moveDistance);
    }
}
