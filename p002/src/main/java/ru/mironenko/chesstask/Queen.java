package ru.mironenko.chesstask;

/**
 * Created by nikita on 21.10.2016.
 */
public class Queen implements Figure {

    String color;

    public Queen(String color){
        this.color = color;
    }
    public String getColor() {
        return this.color;
    }

    public boolean isValidPath(int startX, int startY, int finalX, int finalY) {
        boolean result = false;
        if ( (((finalX == startX) && (finalY - startY) == 1) ||
                ((Math.abs(finalX - startX) == 1) && ((finalY - startY) == 1))) ||
                ((finalY == startY) || (finalX == startX)) ||
                ((Math.abs(finalX - startX) == (Math.abs(finalY - startY))))
                ){
            result = true;
        }

        return result;
    }
}
