package PongGame.GameManagement;

import PongGame.EPlayer;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PlayerController {

    private final int W_KEY = 87;
    private final int S_KEY = 83;

    private final int UP_KEY = 38;
    private final int DOWN_KEY = 40;

    // Player variables
    private EPlayer player;
    private int yAxis = 0;

    public PlayerController(EPlayer player) {
        this.player = player;

        GameEngine.getInstance().getGameWindow().addKeyListener(new KeyListener() {
            ArrayList<Integer> pressedKeys = new ArrayList<>();

            @Override
            public void keyTyped(KeyEvent e) {

            }

            // When a key is pressed (called as long as the key is pressed)
            @Override
            public void keyPressed(KeyEvent e) {
                if (isValidKey(e)) {
                    if (!pressedKeys.contains(e.getKeyCode())) {
                        pressedKeys.add(e.getKeyCode());
                        keyDown(e);
                    }
                }
            }

            // When a key is released (called once when the key is released)
            @Override
            public void keyReleased(KeyEvent e) {
                if (isValidKey(e)) {
                    pressedKeys.remove((Integer) e.getKeyCode());
                    yAxis -= (isUpKey(e)) ? -1 : 1;
                }
            }

            // On key down (called once)
            public void keyDown(KeyEvent e) {
                if (isValidKey(e)) {
                    yAxis += (isUpKey(e)) ? -1 : 1;
                }
            }
        });

    }

    private boolean isValidKey(KeyEvent e) {
        return isUpKey(e) || isDownKey(e);
    }

    private boolean isUpKey(KeyEvent e) {
        return checkKey(e, W_KEY, UP_KEY);
    }

    private boolean isDownKey(KeyEvent e) {
        return checkKey(e, S_KEY, DOWN_KEY);
    }

    private boolean checkKey(KeyEvent e, int player1Key, int player2Key) {
        int pressedKey = e.getKeyCode();
        switch (player) {
            case PLAYER1:
                return pressedKey == player1Key;
            case PLAYER2:
                return pressedKey == player2Key;
            default:
                return false;
        }
    }

    public int getYAxis() {
        return yAxis;
    }
}
