package board;

import utils.Constants.TileState;

public class BigBoard {
    private boolean isDone;
    private TileState winner;
    private SmallBoard[][] board;
    private int dimension;

    public BigBoard(int dimension) {
        this.dimension = dimension;
        this.isDone = false;
        this.winner = TileState.BLANK;
        this.board = new SmallBoard[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = new SmallBoard(dimension);
            }
        }
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

    public SmallBoard getSmallBoard(int row, int col) {
        return board[row - 1][col - 1];
    }

    public boolean smallBoardIsPlayable(int row, int col) {
        return !board[row - 1][col - 1].isDone();
    }

    /**
     * @return true on success, false if spot on board is already marked
     */
    public boolean updateTile(int row, int col, int smallBoardRow, int smallBoardCol, TileState player) {
        SmallBoard smallBoard = board[row - 1][col - 1];
        if (smallBoard.isDone()) {
            throw new IllegalStateException("The board in row " + row + " and column " + col + " is already done");
        }
        boolean success = smallBoard.updateTileState(smallBoardRow, smallBoardCol, player);
        if (success && smallBoard.isDone()) {
            checkForWinner(row, col, player);
        }
        return success;
    }

    private void checkForWinner(int row, int col, TileState player) {
        // check for horizontal match
        boolean rowMatches = true;
        for (int i = 0; i < dimension; i++) {
            SmallBoard smallBoard = board[row - 1][i];
            if (!smallBoard.isDone() || smallBoard.getWinner() != player) {
                rowMatches = false;
            }
        }

        // check for vertical match
        boolean colMatches = true;
        for (int i = 0; i < dimension; i++) {
            SmallBoard smallBoard = board[i][col - 1];
            if (!smallBoard.isDone() || smallBoard.getWinner() != player) {
                colMatches = false;
            }
        }

        // check for diagonal matches
        boolean diagonalMatch1;     // top left to bottom right
        if (row == col) {
            diagonalMatch1 = true;
            for (int i = 0; i < dimension; i++) {
                SmallBoard smallBoard = board[i][i];
                if (!smallBoard.isDone() || smallBoard.getWinner() != player) {
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
                SmallBoard smallBoard = board[i][dimension - 1 - i];
                if (!smallBoard.isDone() || smallBoard.getWinner() != player) {
                    diagonalMatch2 = false;
                }
            }
        } else {
            diagonalMatch2 = false;
        }

        if (rowMatches || colMatches || diagonalMatch1 || diagonalMatch2) {
            isDone = true;
            winner = player;
        } else if (boardIsFull()) {
            isDone = true;
            winner = TileState.DRAW;
        }
    }

    private boolean boardIsFull() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (!board[i][j].isDone()) {
                    return false;
                }
            }
        }
        return true;
    }
}
