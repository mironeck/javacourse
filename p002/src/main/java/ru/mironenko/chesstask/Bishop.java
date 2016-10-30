package ru.mironenko.chesstask;

/**
 * Created by nikita on 21.10.2016.
 */
public class Bishop implements Figure {

    private Color color;
    private String name = Figures.BISHOP.getName();
    private int count = 0;

    public Bishop(Color color){
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
        if ((Math.abs(finalX - startX) == (Math.abs(finalY - startY)))){
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
        if (!(o instanceof Bishop)) return false;

        Bishop bishop = (Bishop) o;

        if (getColor() != bishop.getColor()) return false;
        return getName().equals(bishop.getName());

    }

    @Override
    public int hashCode() {
        int result = getColor().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
