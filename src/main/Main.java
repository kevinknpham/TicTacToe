package main;

import board.BigBoard;
import utils.Constants.TileState;
import utils.Pair;

import java.util.Scanner;

public class Main {
    private static final int BOARD_SIZE = 3;

    public static void main(String[] arg) {
        BigBoard board = new BigBoard(BOARD_SIZE);
        boolean xTurn = Math.random() < .5;
        Pair coords = Pair.NULL_PAIR;
        Scanner console = new Scanner(System.in);
        while (!board.isDone()) {
            coords = playTurn(board, xTurn ? TileState.X : TileState.O, coords);
            xTurn = !xTurn;
        }
    }

    private static Pair playTurn(BigBoard board, TileState player, Pair lastMove) {

    }
}
