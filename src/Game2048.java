import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game2048 extends JFrame implements KeyListener {
    private static final int SIZE = 4;
    private int[][] board;
    private JLabel[][] labels;
    private JPanel panel;

    public Game2048() {
        board = new int[SIZE][SIZE];
        labels = new JLabel[SIZE][SIZE];
        panel = new JPanel(new GridLayout(SIZE, SIZE));

        initializeBoard();
        addRandomTile();
        addRandomTile();

        setTitle("2048 Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        add(panel);
        addKeyListener(this);
        setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = 0;
                labels[i][j] = new JLabel("", SwingConstants.CENTER);
                labels[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                labels[i][j].setOpaque(true);
                labels[i][j].setBackground(Color.LIGHT_GRAY);
                panel.add(labels[i][j]);
            }
        }
    }

    private void addRandomTile() {
        int row, col;
        do {
            row = (int) (Math.random() * SIZE);
            col = (int) (Math.random() * SIZE);
        } while (board[row][col] != 0);

        board[row][col] = Math.random() < 0.9 ? 2 : 4;
        updateUI();
    }

    private void updateUI() {
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

    private boolean moveLeft() {
        boolean moved = false;
        boolean[][] merged = new boolean[SIZE][SIZE]; // Mảng để theo dõi các ô đã hợp nhất

        for (int i = 0; i < SIZE; i++) {
            for (int j = 1; j < SIZE; j++) {
                if (board[i][j] != 0) {
                    int col = j;
                    while (col > 0) {
                        if (board[i][col - 1] == 0) {
                            // Di chuyển ô sang trái nếu ô trái trống
                            board[i][col - 1] = board[i][col];
                            board[i][col] = 0;
                            col--;
                            moved = true;
                        } else if (board[i][col - 1] == board[i][col] && !merged[i][col - 1]) {
                            // Hợp nhất nếu ô trái có cùng giá trị và chưa được hợp nhất
                            board[i][col - 1] *= 2;
                            board[i][col] = 0;
                            merged[i][col - 1] = true; // Đánh dấu ô đã hợp nhất
                            moved = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return moved;
    }

    private boolean moveRight() {
        boolean moved = false;
        boolean[][] merged = new boolean[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = SIZE - 2; j >= 0; j--) {
                if (board[i][j] != 0) {
                    int col = j;
                    while (col < SIZE - 1) {
                        if (board[i][col + 1] == 0) {
                            // Di chuyển ô sang phải nếu ô phải trống
                            board[i][col + 1] = board[i][col];
                            board[i][col] = 0;
                            col++;
                            moved = true;
                        } else if (board[i][col + 1] == board[i][col] && !merged[i][col + 1]) {
                            // Hợp nhất nếu ô phải có cùng giá trị và chưa được hợp nhất
                            board[i][col + 1] *= 2;
                            board[i][col] = 0;
                            merged[i][col + 1] = true; // Đánh dấu ô đã hợp nhất
                            moved = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return moved;
    }

    private boolean moveUp() {
        boolean moved = false;
        boolean[][] merged = new boolean[SIZE][SIZE];

        for (int j = 0; j < SIZE; j++) {
            for (int i = 1; i < SIZE; i++) {
                if (board[i][j] != 0) {
                    int row = i;
                    while (row > 0) {
                        if (board[row - 1][j] == 0) {
                            // Di chuyển ô lên trên nếu ô trên trống
                            board[row - 1][j] = board[row][j];
                            board[row][j] = 0;
                            row--;
                            moved = true;
                        } else if (board[row - 1][j] == board[row][j] && !merged[row - 1][j]) {
                            // Hợp nhất nếu ô trên có cùng giá trị và chưa được hợp nhất
                            board[row - 1][j] *= 2;
                            board[row][j] = 0;
                            merged[row - 1][j] = true; // Đánh dấu ô đã hợp nhất
                            moved = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return moved;
    }

    private boolean moveDown() {
        boolean moved = false;
        boolean[][] merged = new boolean[SIZE][SIZE];

        for (int j = 0; j < SIZE; j++) {
            for (int i = SIZE - 2; i >= 0; i--) {
                if (board[i][j] != 0) {
                    int row = i;
                    while (row < SIZE - 1) {
                        if (board[row + 1][j] == 0) {
                            // Di chuyển ô xuống dưới nếu ô dưới trống
                            board[row + 1][j] = board[row][j];
                            board[row][j] = 0;
                            row++;
                            moved = true;
                        } else if (board[row + 1][j] == board[row][j] && !merged[row + 1][j]) {
                            // Hợp nhất nếu ô dưới có cùng giá trị và chưa được hợp nhất
                            board[row + 1][j] *= 2;
                            board[row][j] = 0;
                            merged[row + 1][j] = true; // Đánh dấu ô đã hợp nhất
                            moved = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return moved;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean moved = false;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                moved = moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moved = moveRight();
                break;
            case KeyEvent.VK_UP:
                moved = moveUp();
                break;
            case KeyEvent.VK_DOWN:
                moved = moveDown();
                break;
        }
        if (moved) {
            addRandomTile();
            updateUI();
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