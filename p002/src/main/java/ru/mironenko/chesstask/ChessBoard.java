package ru.mironenko.chesstask;

/**
 * Created by nikita on 19.10.2016.
 */
public class ChessBoard {

    public final int BOARD_WIDTH = 8;
    public final int BOARD_HEIGHT = 8;

    public Figure[][] board = new Figure[BOARD_HEIGHT][BOARD_WIDTH];

    public Figure[] figures = new Figure[6];

    /**
     * A method that determines is destination tile on board
     * @param finalX
     * @param finalY
     * @return a boolean indicating whether the tile on board
     */
    public boolean isDestinationTileOnBoard(ChessBoard board, int finalX, int finalY){

        return (finalX < board.BOARD_WIDTH) && (finalY < board.BOARD_HEIGHT); //true if tile on the board
    }


    /**
     * A method that determines whether the destination tile with figure opposite color
     * @param board chessboard
     * @param startX the start x location
     * @param startY the start y location
     * @param finalX the final x location
     * @param finalY the final y location
     * @return a boolean indicating whether the destination tile with figure opposite color
     */

    public boolean isTileOccupiedByFigureTheOppositeColor(Figure[][] board, int startX, int startY, int finalX, int finalY) {
        boolean result = false;
        if (!board[finalX][finalY].getColor().equals(board[startX][startY].getColor())) {
            result = true;
        }
        return result;  // return true if opposite color
    }


    public void makeMove(Figure[][] board, int startX, int startY, int finalX, int finalY){

        board[finalX][finalY] = board[startX][startY];
        board[startX][startY] = null;
    }

}
