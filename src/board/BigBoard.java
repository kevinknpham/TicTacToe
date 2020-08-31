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
            for (int j = 0; j < dimension; i++) {
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
        return board[row][col];
    }
}
