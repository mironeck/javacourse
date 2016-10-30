package ru.mironenko.chesstask;

/**
 * Created by nikita on 21.10.2016.
 */
public class Rook implements Figure {

    private Color color;
    private String name = Figures.ROOK.getName();

    public Rook(Color color){
        this.color = color;
    }

    public String getName() {
        return name;
    }
    public Color getColor() {
        return this.color;
    }

    public boolean isValidPath(int startX, int startY, int finalX, int finalY) {

        boolean result = false;

        if ((finalY == startY)  || (finalX == startX)){
            result = true;
        }
        return result;
    }

}
