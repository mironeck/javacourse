package ru.mironenko.chesstask;

/**
 * Created by nikita on 21.10.2016.
 */
public class King implements Figure {

    Color color;

    public King(Color color){
        this.color = color;
    }
    private String name = Figures.KING.getName();

    public String getName() {
        return name;
    }
    public Color getColor() {
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
