package ru.mironenko.chesstask;

/**
 * Created by nikita on 19.10.2016.
 */
public interface ChessAction {

        String getColor();

        /**
         * A method that determines is destination tile on board
         * @param board chessboard
         * @param finalX the final x location
         * @param finalY the final y location
         * @return a boolean indicating whether the tile on board
         */
        boolean isDestinationTileOnBoard(ChessBoard board, int finalX, int finalY);

        /**
         * A method that determines can figure move like this
         * @param finalX the final x location
         * @param finalY the final y location
         * @return a boolean indicating whether the path is valid
         */
        boolean isValidPath(int startX, int startY, int finalX, int finalY);

        /**
         * A method that determines whether the destination tile with figure opposite color
         * @param board chessboard
         * @param finalX the final x location
         * @param finalY the final y location
         * @return a boolean indicating whether the destination tile with figure opposite color
         */

        boolean isTileOccupiedByFigureTheOppositeColor(ChessAction[][] board, int finalX, int finalY);
}
