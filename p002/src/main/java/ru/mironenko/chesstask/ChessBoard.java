package ru.mironenko.chesstask;

/**
 * Created by nikita on 19.10.2016.
 */
public class ChessBoard {

    public final int BOARD_WIDTH = 8;
    public final int BOARD_HEIGHT = 8;

    public ChessAction[][] board = new ChessAction[BOARD_HEIGHT][BOARD_WIDTH];

    public ChessAction[] figures = new ChessAction[6];




}
