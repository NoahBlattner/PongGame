package PongGame.GameWindow;

import PongGame.EPlayer;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private BounceBar player1BounceBar;
    private BounceBar player2BounceBar;

    public GameWindow() {
        super("Pong");

        // Set window close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set window size
        setSize(800, 600);
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);

        // Set window style
        setLayout(null);
        getContentPane().setBackground(Color.black);

        // Add bounce bars
        player1BounceBar = new BounceBar(EPlayer.PLAYER1, getWidth(), getHeight());
        add(player1BounceBar);
        player2BounceBar = new BounceBar(EPlayer.PLAYER2, getWidth(), getHeight());
        add(player2BounceBar);

        setVisible(true);
    }

    public BounceBar getPlayer1BounceBar() {
        return player1BounceBar;
    }

    public BounceBar getPlayer2BounceBar() {
        return player2BounceBar;
    }

}
