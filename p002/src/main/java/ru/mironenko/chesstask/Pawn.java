package ru.mironenko.chesstask;

/**
 * Created by nikita on 18.10.2016.
 */
public class Pawn implements ChessAction {


    private String color;
    private final String NAME = "Pawn";

    public String getColor() {
        return this.color;
    }

    public String getNAME() {
        return NAME;
    }

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
     * A method that determines whether the Pawn is moving
     * @param startX the start x location
     * @param startY the start y location
     * @param finalX the final x location
     * @param finalY the final y location
     * @return a boolean indicating whether the path is valid
     */
    public boolean isValidPath(int startX, int startY, int finalX, int finalY) {

        boolean result = false;
        if (((finalX == startX) && (finalY - startY) == 1) ||
        ((Math.abs(finalX - startX) == 1) && ((finalY - startY) == 1))){
            result = true;
        }
        return result;  //return true if can move
    }

    /**
     * A method that determines whether the destination tile with figure opposite color
     * @param board chessboard
     * @param finalX the final x location
     * @param finalY the final y location
     * @return a boolean indicating whether the destination tile with figure opposite color
     */

    public boolean isTileOccupiedByFigureTheOppositeColor(ChessAction[][] board, int finalX, int finalY) {
        boolean result = false;
        if (!board[finalX][finalY].getColor().equals(this.color)) {
            result = true;
        }
        return result;  // return true if opposite color
    }


}
