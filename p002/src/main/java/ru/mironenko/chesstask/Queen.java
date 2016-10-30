package ru.mironenko.chesstask;

/**
 * Created by nikita on 21.10.2016.
 */
public class Queen implements Figure {

    private Color color;
    private String name = Figures.QUEEN.getName();
    private int count = 0;

    public Queen(Color color){
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return this.color;
    }

    public int getCount(){
        return this.count;
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

    public void changeCount(){
        this.count++;
    }

}
