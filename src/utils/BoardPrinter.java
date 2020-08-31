package utils;

import board.BigBoard;
import board.SmallBoard;

import java.util.ArrayList;

public class BoardPrinter {
    /*
    board design:
    x | o | x   ||   x | o | x   ||   x | o | x
    -------------------------------------------
    o | x | o   ||   o | x | o   ||   o | x | o
    -------------------------------------------
    x | o | x   ||   x | o | x   ||   x | o | x

    ===========================================

    x | o | x   ||   x | o | x   ||   x | o | x
    -------------------------------------------
    o | x | o   ||   o | x | o   ||   o | x | o
    -------------------------------------------
    x | o | x   ||   x | o | x   ||   x | o | x

    ===========================================

    x | o | x   ||   x | o | x   ||   x | o | x
    -------------------------------------------
    o | x | o   ||   o | x | o   ||   o | x | o
    -------------------------------------------
    x | o | x   ||   x | o | x   ||   x | o | x
     */
    public static String printBoard(BigBoard board) {
        StringBuilder sb = new StringBuilder();

        // TODO: stuff
        for(int i = 0; i < board.getDimension(); i++) {
            printRowOfBigBoard(board, i, sb);
            if(i != board.getDimension() - 1) {
                sb.append("\n");
                printEquals(board, sb);
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    private static void printRowOfBigBoard(BigBoard board, int row, StringBuilder sb) {
        ArrayList<SmallBoard> smallBoards = rowOfSmallBoards(board, row);
        for(int i = 0; i < board.getDimension(); i++) {
            printRowFromRowOfSmallBoard(sb, board, smallBoards, i);
            if(i != board.getDimension() - 1) {
                printDashes(board, sb);
            }
        }
    }

    private static ArrayList<SmallBoard> rowOfSmallBoards(BigBoard board, int row) {
        ArrayList<SmallBoard> smallBoardList = new ArrayList<>();
        for(int i = 1; i <= board.getDimension(); i++) {
            smallBoardList.add(board.getSmallBoard(row, i));
        }
        return smallBoardList;
    }

    private static void printRowFromRowOfSmallBoard(StringBuilder sb, BigBoard board, ArrayList<SmallBoard> smallBoards, int smallBoardRow) {
        for(int i = 1; i <= smallBoards.size(); i++) {
            for(int j = 1; j <= board.getDimension(); j++) {
                sb.append(smallBoards.get(i - 1).getTileState(smallBoardRow, j));
                if(j != board.getDimension()) {
                    sb.append(" | ");
                }
            }
            if(i != smallBoards.size()) {
                sb.append("   ||   ");
            }
        }
        sb.append("\n");
    }

    private static void printDashes(BigBoard board, StringBuilder sb) {
        for(int i = 0; i < rowLength(board.getDimension()); i++) {
            sb.append("-");
        }
        sb.append("\n");
    }

    private static void printEquals(BigBoard board, StringBuilder sb) {
        for (int i = 0; i < rowLength(board.getDimension()); i++) {
            sb.append("=");
        }
        sb.append("\n");
    }

    private static int rowLength(int dimension) {
        return 4 * dimension * dimension + 5 * dimension - 8;
    }
}

