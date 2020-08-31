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
    private static ArrayList<SmallBoard> rowOfSmallBoards(BigBoard board, int row) {
        ArrayList<SmallBoard> boardList = new ArrayList<>();
        for(int i = 1; i <= board.getDimension(); i++) {
            for(int j = 1; j <= board.getDimension(); j++) {
                boardList.add(board.getSmallBoard(i, j));
            }
        }
        return boardList;
    }

    private static void printRowOfSmallBoards(BigBoard board, int row) {
        StringBuilder sb = new StringBuilder
    }
}

