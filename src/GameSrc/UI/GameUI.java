package GameSrc.UI;

import javax.swing.*;
import java.awt.*;

public class GameUI {
    private static final int SIZE = 4;
    private JLabel[][] labels;
    private JPanel gamePanel;

    public GameUI() {
        labels = new JLabel[SIZE][SIZE];
        gamePanel = new JPanel(new GridLayout(SIZE, SIZE));
        initializeBoard();
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                labels[i][j] = new JLabel("", SwingConstants.CENTER);
                labels[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                labels[i][j].setOpaque(true);
                labels[i][j].setBackground(Color.LIGHT_GRAY);
                gamePanel.add(labels[i][j]);
            }
        }
    }

    public void updateUI(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    labels[i][j].setText("");
                    labels[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    labels[i][j].setText(String.valueOf(board[i][j]));
                    labels[i][j].setBackground(getTileColor(board[i][j]));
                }
            }
        }
    }

    private Color getTileColor(int value) {
        switch (value) {
            case 2:    return new Color(0xeee4da);
            case 4:    return new Color(0xede0c8);
            case 8:    return new Color(0xf2b179);
            case 16:   return new Color(0xf59563);
            case 32:   return new Color(0xf67c5f);
            case 64:   return new Color(0xf65e3b);
            case 128:  return new Color(0xedcf72);
            case 256:  return new Color(0xedcc61);
            case 512:  return new Color(0xedc850);
            case 1024: return new Color(0xedc53f);
            case 2048: return new Color(0xedc22e);
            default:   return new Color(0x3c3a32);
        }
    }
}