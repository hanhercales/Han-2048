package GameSrc;

public class BoardMover {
    private int[][] board;
    private int size;

    public BoardMover(int[][] board, int size) {
        this.board = board;
        this.size = size;
    }

    public boolean moveLeft() {
        return moveTiles(0, -1);
    }

    public boolean moveRight() {
        return moveTiles(0, 1);
    }

    public boolean moveUp() {
        return moveTiles(-1, 0);
    }

    public boolean moveDown() {
        return moveTiles(1, 0);
    }

    private boolean moveTiles(int rowDelta, int colDelta) {
        boolean moved = false;
        boolean[][] merged = new boolean[size][size];

        int startRow = rowDelta > 0 ? size - 1 : 0;
        int endRow = rowDelta > 0 ? -1 : size;
        int rowStep = rowDelta > 0 ? -1 : 1;

        int startCol = colDelta > 0 ? size - 1 : 0;
        int endCol = colDelta > 0 ? -1 : size;
        int colStep = colDelta > 0 ? -1 : 1;

        for (int i = startRow; i != endRow; i += rowStep) {
            for (int j = startCol; j != endCol; j += colStep) {
                if (board[i][j] != 0) {
                    int currentRow = i;
                    int currentCol = j;

                    while (true) {
                        int nextRow = currentRow + rowDelta;
                        int nextCol = currentCol + colDelta;

                        if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) {
                            break;
                        }

                        if (board[nextRow][nextCol] == 0) {
                            board[nextRow][nextCol] = board[currentRow][currentCol];
                            board[currentRow][currentCol] = 0;
                            currentRow = nextRow;
                            currentCol = nextCol;
                            moved = true;
                        } else if (board[nextRow][nextCol] == board[currentRow][currentCol] && !merged[nextRow][nextCol]) {
                            board[nextRow][nextCol] *= 2;
                            board[currentRow][currentCol] = 0;
                            merged[nextRow][nextCol] = true;
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
}