package GameSrc.Board;

public class Board {
    private static final int SIZE = 4;
    private final int[][] board;
    private int[][] previousState;

    public Board() {
        board = new int[SIZE][SIZE];
        previousState = null;
    }

    public int[][] getBoard() {
        return board;
    }

    public void addRandomTile() {
        int row, col;
        do {
            row = (int) (Math.random() * SIZE);
            col = (int) (Math.random() * SIZE);
        } while (board[row][col] != 0);

        board[row][col] = Math.random() < 0.9 ? 2 : 4;
    }

    public void saveState() {
        previousState = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(board[i], 0, previousState[i], 0, SIZE);
        }
    }

    public void undoMove() {
        if (previousState != null) {
            for (int i = 0; i < SIZE; i++) {
                System.arraycopy(previousState[i], 0, board[i], 0, SIZE);
            }
            previousState = null;
        }
    }

    public void restartGame() {
        previousState = null;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = 0;
            }
        }
        addRandomTile();
        addRandomTile();
    }
    
    public static int getSIZE(){
        return SIZE;
    }
}