package ru.mironenko.chesstask;

/**
 * Created by nikita on 21.10.2016.
 */
public class King implements Figure {

    String color;

    public King(String color){
        this.color = color;
    }
    public String getColor() {
        return this.color;
    }

    public boolean isValidPath(int startX, int startY, int finalX, int finalY) {
        boolean result = false;

        if( ((Math.abs(finalY - startY) == 1) && (finalX == startX)) ||
                ((Math.abs(finalY - startY) == 1) && (Math.abs(finalX - startX) == 1)) ||
                ((Math.abs(finalX - startX) == 1) && (finalY == startY))
                ){
            result = true;
        }

        return result;
    }
}
