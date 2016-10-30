package ru.mironenko.chesstask;

import org.junit.Ignore;
import org.junit.Test;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 25.10.2016.
 */
public class ChessBoardTest {
    @Test
    public void whenDestinationTileOnBoard() throws Exception {

        ChessBoard chessBoard = new ChessBoard();

        boolean result = chessBoard.isDestinationTileOnBoard(7, 7);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenDestinationTileNotOnBoard() throws Exception {

        ChessBoard chessBoard = new ChessBoard();

        boolean result = chessBoard.isDestinationTileOnBoard(7, 8);
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

    @Test
    @Ignore("reason")
    public void whenInitStartPosition() throws Exception {

        ChessBoard chessBoard = new ChessBoard();
        Figure[][] board = chessBoard.initStartPosition();

        Figure[][] checked = new Figure[][]{
                {new Rook(Color.White),new Bishop(Color.White), new Knight(Color.White), new Queen(Color.White), new King(Color.White), new Knight(Color.White), new Bishop(Color.White), new Rook(Color.White)},
                {new Pawn(Color.White), new Pawn(Color.White), new Pawn(Color.White), new Pawn(Color.White), new Pawn(Color.White), new Pawn(Color.White), new Pawn(Color.White), new Pawn(Color.White)},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {new Pawn(Color.Black), new Pawn(Color.Black), new Pawn(Color.Black), new Pawn(Color.Black),new Pawn(Color.Black), new Pawn(Color.Black), new Pawn(Color.Black), new Pawn(Color.Black)},
                {new Rook(Color.Black),new Bishop(Color.Black), new Knight(Color.Black), new Queen(Color.Black), new King(Color.Black), new Knight(Color.Black), new Bishop(Color.Black), new Rook(Color.Black)}
        };

        assertThat(board, is(checked));
    }

}