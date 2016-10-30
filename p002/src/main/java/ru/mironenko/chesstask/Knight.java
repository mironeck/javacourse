package ru.mironenko.chesstask;

/**
 * Created by nikita on 21.10.2016.
 */
public class Knight implements Figure {

    private Color color;
    private String name = Figures.KNIGHT.getName();

    public Knight(Color color){
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

        if( ((Math.abs(finalX - startX) == 1) && ((Math.abs(finalY - startY) == 2))) ||
                ((Math.abs(finalY - startY) == 1) && ((Math.abs(finalX - startX) == 2)))
                ){
            result = true;
        }

        return result;
    }

}
