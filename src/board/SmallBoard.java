package board;

import utils.Constants.TileState;

public class SmallBoard {
    private boolean isDone;
    private TileState winner;
    private TileState[][] board;
    private int dimension;
    private int moveCount;

    public SmallBoard(int dimension) {
        board = new TileState[this.dimension][this.dimension];
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                board[i][j] = TileState.BLANK;
            }
        }
        isDone = false;
        winner = TileState.BLANK;
        this.dimension = dimension;
        this.moveCount = 0;
    }

    public int getDimension() {
        return dimension;
    }

    public boolean isDone() {
        return isDone;
    }

    public TileState getWinner() {
        return winner;
    }

    public TileState getTileState(int row, int col) {
        return board[row - 1][col - 1];
    }

    /**
     *
     * @param row
     * @param col
     * @param player
     * @return true on success, false if spot already marked
     */
    public boolean updateTileState(int row, int col, TileState player) {
        if (isDone) {
            throw new IllegalStateException("This board is already done");
        }
        if (row > dimension || row <= 0 || col > dimension || row <= 0) {
            throw new IllegalArgumentException(
                    "Invalid row and column given:\n" +
                    "\texpected positive row less than " + dimension + " but got " + row + "\n" +
                    "\texpected positive column less than " + dimension + " but got " + col
            );
        }
        if (player == null) {
            throw new IllegalArgumentException(
                    "Invalid player given:\n" +
                    "\texpected non-null value but got null"
            );
        }
        if (board[row - 1][col - 1] != TileState.BLANK) {
            return false;
        }
        board[row - 1][col - 1] = player;
        checkForWinner(row, col, player);
        moveCount++;
        return true;
    }

    private void checkForWinner(int row, int col, TileState player) {
        // check for horizontal match
        boolean rowMatches = true;
        for (int i = 0; i < dimension; i++) {
            if (board[row - 1][i] != player) {
                rowMatches = false;
            }
        }

        // check for vertical match
        boolean colMatches = true;
        for (int i = 0; i < dimension; i++) {
            if (board[i][col - 1] != player) {
                colMatches = false;
            }
        }

        // check for diagonal matches
        boolean diagonalMatch1;     // top left to bottom right
        if (row == col) {
            diagonalMatch1 = true;
            for (int i = 0; i < dimension; i++) {
                if (board[i][i] != player) {
                    diagonalMatch1 = false;
                }
            }
        } else {
            diagonalMatch1 = false;
        }

        boolean diagonalMatch2;
        if (row == dimension + 1 - col) {
            diagonalMatch2 = true;
            for (int i = 0; i < dimension; i++) {
                if (board[i][dimension + 1 - i] != player) {
                    diagonalMatch2 = false;
                }
            }
        } else {
            diagonalMatch2 = false;
        }

        if (rowMatches || colMatches || diagonalMatch1 || diagonalMatch2) {
            isDone = true;
            winner = player;
        } else if (moveCount == dimension * dimension) {
            isDone = true;
            winner = TileState.DRAW;
        }
    }
}
