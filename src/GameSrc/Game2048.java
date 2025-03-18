package GameSrc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game2048 extends JFrame implements KeyListener {
    private Board board;
    private BoardMover boardMover;
    private GameUI gameUI;

    public Game2048() {
        board = new Board();
        boardMover = new BoardMover(board.getBoard(), Board.getSIZE());
        gameUI = new GameUI();

        initializeFrame();
    }

    private void initializeFrame() {
        setTitle("2048 Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);

        // Tạo panel chứa các nút
        JPanel buttonPanel = new JPanel();
        JButton restartButton = new JButton("Restart");
        JButton undoButton = new JButton("Undo");

        // Thêm sự kiện cho nút Restart
        restartButton.addActionListener(e -> {
            board.restartGame();
            gameUI.updateUI(board.getBoard());
            requestFocusInWindow(); // Yêu cầu focus trở lại JFrame
        });

        // Thêm sự kiện cho nút Undo
        undoButton.addActionListener(e -> {
            board.undoMove();
            gameUI.updateUI(board.getBoard());
            requestFocusInWindow(); // Yêu cầu focus trở lại JFrame
        });

        buttonPanel.add(restartButton);
        buttonPanel.add(undoButton);

        // Thêm các panel vào frame
        add(buttonPanel, BorderLayout.NORTH);
        add(gameUI.getGamePanel(), BorderLayout.CENTER);

        addKeyListener(this);
        setFocusable(true); // Đảm bảo JFrame có thể nhận sự kiện bàn phím
        requestFocusInWindow(); // Yêu cầu focus để nhận sự kiện bàn phím
        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        board.saveState(); // Lưu trạng thái trước khi di chuyển
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
            board.addRandomTile(); // Thêm ô mới sau khi di chuyển
            gameUI.updateUI(board.getBoard());
        } else {
            board.undoMove(); // Khôi phục trạng thái nếu không có di chuyển
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new Game2048();
    }
}