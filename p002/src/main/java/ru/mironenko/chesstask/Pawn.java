package ru.mironenko.chesstask;

/**
 * Created by nikita on 18.10.2016.
 */
public class Pawn implements Figure {


    private String color;
    private final String NAME = "Pawn";
    int count = 0;

    public Pawn(String color){
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public String getNAME() {
        return NAME;
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
        if ((isTheFirstMove() && (((finalX == startX) && (finalY - startY) == 2)  ||
                ((finalX == startX) && (finalY - startY) == 1) ||
                ((Math.abs(finalX - startX) == 1) && ((finalY - startY) == 1))
                ))  ||
                (((finalX == startX) && (finalY - startY) == 1) ||
        ((Math.abs(finalX - startX) == 1) && ((finalY - startY) == 1))
                )){
            result = true;
        }
        return result;  //return true if can move
    }

    public boolean isTheFirstMove(){
        return count == 0;
    }

    public void changeCount(){
        count++;
    }

}