package GameSrc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {
    private Board board;
    private BoardMover boardMover;
    private GameUI gameUI;
    private JFrame frame;

    public GameController() {
        board = new Board();
        boardMover = new BoardMover(board.getBoard(), Board.getSIZE());
        gameUI = new GameUI();
        frame = new JFrame("2048 Game");

        initializeFrame();
    }

    private void initializeFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLocationRelativeTo(null);

        JPanel buttonPanel = new JPanel();
        JButton restartButton = new JButton("Restart");
        JButton undoButton = new JButton("Undo");

        restartButton.addActionListener(e -> {
            board.restartGame();
            gameUI.updateUI(board.getBoard());
            frame.requestFocusInWindow();
        });

        undoButton.addActionListener(e -> {
            board.undoMove();
            gameUI.updateUI(board.getBoard());
            frame.requestFocusInWindow();
        });

        buttonPanel.add(restartButton);
        buttonPanel.add(undoButton);

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(gameUI.getGamePanel(), BorderLayout.CENTER);

        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        board.saveState();
        boolean moved = false;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                moved = boardMover.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moved = boardMover.moveRight();
                break;
            case KeyEvent.VK_UP:
                moved = boardMover.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                moved = boardMover.moveDown();
                break;
        }
        if (moved) {
            board.addRandomTile();
            gameUI.updateUI(board.getBoard());
        } else {
            board.undoMove();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new GameController();
    }
}