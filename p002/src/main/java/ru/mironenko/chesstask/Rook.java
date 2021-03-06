package ru.mironenko.chesstask;

/**
 * Created by nikita on 21.10.2016.
 */
public class Rook implements Figure {

    private Color color;
    private String name = Figures.ROOK.getName();
    private int count = 0;

    public Rook(Color color){
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

        if ((finalY == startY)  || (finalX == startX)){
            result = true;
        }
        return result;
    }

    public void changeCount(){
        this.count++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rook)) return false;

        Rook rook = (Rook) o;

        if (getColor() != rook.getColor()) return false;
        return getName().equals(rook.getName());

    }

    @Override
    public int hashCode() {
        int result = getColor().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
