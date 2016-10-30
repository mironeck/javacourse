package ru.mironenko.chesstask;

/**
 * Created by nikita on 19.10.2016.
 */
public class ChessBoard {


    public static final int BOARD_SIZE = 8;

    public Figure[][] board = new Figure[BOARD_SIZE][BOARD_SIZE];

    /**
     * A method that determines is destination tile on board
     * @param finalX
     * @param finalY
     * @return a boolean indicating whether the tile on board
     */
    public boolean isDestinationTileOnBoard(int finalX, int finalY){

        return (finalX < ChessBoard.BOARD_SIZE) && (finalY < ChessBoard.BOARD_SIZE); //true if tile on the board
    }


    /**
     * A method that determines whether the destination tile with figure opposite color
     * @param board chessboard with figures
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

    /**
     * The method that make a move (assign new coordinates to figure that moved)
     * @param board chessboard with figures
     * @param startX the start x location
     * @param startY the start y location
     * @param finalX the final x location
     * @param finalY the final y location
     */
    public void makeMove(Figure[][] board, int startX, int startY, int finalX, int finalY){

        board[finalX][finalY] = board[startX][startY];
        board[startX][startY] = null;
    }

    /**'
     * The method that initilize start position of figures
     * @return array with figures
     */
    public Figure[][] initStartPosition(){

        this.board = new Figure[][]{
                {new Rook(Color.White),new Bishop(Color.White), new Knight(Color.White), new Queen(Color.White), new King(Color.White), new Knight(Color.White), new Bishop(Color.White), new Rook(Color.White)},
                {new Pawn(Color.White), new Pawn(Color.White), new Pawn(Color.White), new Pawn(Color.White), new Pawn(Color.White), new Pawn(Color.White), new Pawn(Color.White), new Pawn(Color.White)},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {new Pawn(Color.Black), new Pawn(Color.Black), new Pawn(Color.Black), new Pawn(Color.Black),new Pawn(Color.Black), new Pawn(Color.Black), new Pawn(Color.Black), new Pawn(Color.Black)},
                {new Rook(Color.Black),new Bishop(Color.Black), new Knight(Color.Black), new Queen(Color.Black), new King(Color.Black), new Knight(Color.Black), new Bishop(Color.Black), new Rook(Color.Black)}
        };

        return board;
    }

}
