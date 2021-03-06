package utils;

import java.util.Objects;

public class Pair {
    public static final Pair NULL_PAIR = new Pair(-1, -1);

    private int row;
    private int col;

    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return row == pair.row &&
                col == pair.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
