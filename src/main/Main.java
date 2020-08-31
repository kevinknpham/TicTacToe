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
            coords = playTurn(board, xTurn ? TileState.X : TileState.O, coords, console);
            xTurn = !xTurn;
        }
    }

    private static Pair playTurn(BigBoard board, TileState player, Pair lastMove, Scanner console) {
        while (!board.smallBoardIsPlayable(lastMove.getRow(), lastMove.getCol())) {
            System.out.println("Board at row " + lastMove.getRow() + " and column " + lastMove.getCol() + " is done.");
            System.out.println("Pick the row and column of the board you want to use next.");
            lastMove = new Pair(
                    promptForValidInteger(console, "Row?"),
                    promptForValidInteger(console, "Column?")
            );
        }
        System.out.println("Playing in the small board at row " + lastMove.getRow() + " and column " + lastMove.getCol() + ".");
        System.out.println("It is " + player + "'s turn.");
        int row = promptForValidInteger(console, "Row?");
        int col = promptForValidInteger(console, "Column?");
        while (!board.updateTile(lastMove.getRow(), lastMove.getCol(), row, col, player)) {
            System.out.println("That square is already taken!  Try a different one.");
            row = promptForValidInteger(console, "Row?");
            col = promptForValidInteger(console, "Column?");
        }
        return new Pair(row, col);
    }

    private static int promptForValidInteger(Scanner console, String prompt) {
        int result = promptForInteger(console, prompt);
        while (result <= 0 || result > BOARD_SIZE) {
            System.out.println("Please give an integer between 1 and " + BOARD_SIZE);
            result = promptForInteger(console, prompt);
        }
        return result;
    }

    private static int promptForInteger(Scanner console, String prompt) {
        System.out.println(prompt);
        while (!console.hasNextInt()) {
            System.out.println("Please enter an integer.");
            System.out.println(prompt);
            console.next();
        }
        return console.nextInt();
    }
}
