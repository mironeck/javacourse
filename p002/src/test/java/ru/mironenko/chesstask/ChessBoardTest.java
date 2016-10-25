package ru.mironenko.chesstask;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static ru.mironenko.chesstask.ChessBoard.BOARD_SIZE;

/**
 * Created by nikita on 25.10.2016.
 */
public class ChessBoardTest {
    @Test
    public void whenDestinationTileOnBoard() throws Exception {

        ChessBoard chessBoard = new ChessBoard();
        Figure[][] board = new Figure[BOARD_SIZE][BOARD_SIZE];

        boolean result = chessBoard.isDestinationTileOnBoard(board, 7, 7);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenDestinationTileNotOnBoard() throws Exception {

        ChessBoard chessBoard = new ChessBoard();
        Figure[][] board = new Figure[BOARD_SIZE][BOARD_SIZE];

        boolean result = chessBoard.isDestinationTileOnBoard(board, 7, 8);
        boolean checked = false;

        assertThat(result, is(checked));
    }

    @Test
    public void whenTileOccupiedByFigureTheSameColor() throws Exception {

        ChessBoard chessBoard = new ChessBoard();
        Figure[][] board = chessBoard.initStartPosition();

        boolean result = chessBoard.isTileOccupiedByFigureTheOppositeColor(board, 0, 0, 0, 1);
        boolean checked = false;

        assertThat(result, is(checked));
    }

    @Test
    public void whenTileOccupiedByFigureTheOppositeColor() throws Exception {

        ChessBoard chessBoard = new ChessBoard();
        Figure[][] board = chessBoard.initStartPosition();

        boolean result = chessBoard.isTileOccupiedByFigureTheOppositeColor(board, 0, 0, 7, 0);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenMakeMove() throws Exception {

        ChessBoard chessBoard = new ChessBoard();
        Figure[][] board = chessBoard.initStartPosition();

        chessBoard.makeMove(board, 1, 0, 3, 0);

        String result = board[3][0].getName();
        String checked = "Pawn";

        assertThat(result, is(checked));
    }

//    @Test
//    public void whenInitStartPosition() throws Exception {
//
//        ChessBoard chessBoard = new ChessBoard();
//        Figure[][] board = chessBoard.initStartPosition();
//
//        Figure[][] checked = new Figure[][]{
//                {new Rook("white"),new Bishop("white"), new Knight("white"), new Queen("white"), new King("white"), new Knight("white"), new Bishop("white"), new Rook("white")},
//                {new Pawn("white"), new Pawn("white"), new Pawn("white"), new Pawn("white"), new Pawn("white"), new Pawn("white"), new Pawn("white"), new Pawn("white")},
//                {null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null},
//                {new Pawn("black"), new Pawn("black"), new Pawn("black"), new Pawn("black"),new Pawn("black"), new Pawn("black"), new Pawn("black"), new Pawn("black")},
//                {new Rook("black"),new Bishop("black"), new Knight("black"), new Queen("black"), new King("black"), new Knight("black"), new Bishop("black"), new Rook("black")}
//        };
//
//        boolean result = java.util.Arrays.deepEquals(board, checked);
//        boolean check = true;
//
//        assertThat(result, is(check));
//    }

}