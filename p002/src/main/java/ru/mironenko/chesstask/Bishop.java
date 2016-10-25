package ru.mironenko.chesstask;

/**
 * Created by nikita on 21.10.2016.
 */
public class Bishop implements Figure {

    private String color;
    private final String name = "Bishop";

    public Bishop(String color){
        this.color = color;
    }

    public String getName() {
        return name;
    }
    public String getColor() {
        return this.color;
    }

    public boolean isValidPath(int startX, int startY, int finalX, int finalY) {

        boolean result = false;
        if ((Math.abs(finalX - startX) == (Math.abs(finalY - startY)))){
            result = true;
        }
        return result;
    }
}
