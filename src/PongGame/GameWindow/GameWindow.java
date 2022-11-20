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

        /*
        player2BounceBar = new BounceBar(EPlayer.PLAYER2);
        player2BounceBar.setPreferredSize(new Dimension(20, 100));
        player2BounceBar.setBounds(getContentPane().getWidth() - 10, getContentPane().getHeight() / 2, 20, 100);


         */
//      getContentPane().add(player2BounceBar);


        setVisible(true);
    }

}
