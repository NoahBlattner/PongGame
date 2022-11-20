package PongGame.GameWindow;

import PongGame.EPlayer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BounceBar extends Panel {

    BorderLayout borderLayout = new BorderLayout();
    public BounceBar(EPlayer player, int gameWindowWidth, int gameWindowHeight) {
        super();
        setSize(20, 100);
        setBackground(Color.white);
        setPreferredSize(new Dimension(20, (int) (gameWindowHeight * 0.15)));
        setSize(getPreferredSize());


        if (player == EPlayer.PLAYER1) {
            setLocation(gameWindowWidth / 8 - getWidth() / 2, gameWindowHeight / 2 - getHeight() / 2);
        } else {
            setLocation(gameWindowWidth / 8 * 7 - getWidth() / 2, gameWindowHeight / 2 - getHeight() / 2);
        }
    }
}
