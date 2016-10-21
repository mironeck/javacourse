package ru.mironenko.chesstask;

/**
 * Created by nikita on 21.10.2016.
 */
public class Rook implements Figure {

    private String color;

    public Rook(String color){
        this.color = color;
    }
    public String getColor() {
        return this.color;
    }

    public boolean isValidPath(int startX, int startY, int finalX, int finalY) {

        boolean result = false;

        if ((finalY == startY) || (finalX == startX)){
            result = true;
        }
        return result;
    }
}
