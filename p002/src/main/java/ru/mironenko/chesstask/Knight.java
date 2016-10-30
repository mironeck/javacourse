package ru.mironenko.chesstask;

/**
 * Created by nikita on 21.10.2016.
 */
public class Knight implements Figure {

    private Color color;
    private String name = Figures.KNIGHT.getName();
    private int count = 0;

    public Knight(Color color){
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

        if( ((Math.abs(finalX - startX) == 1) && ((Math.abs(finalY - startY) == 2))) ||
                ((Math.abs(finalY - startY) == 1) && ((Math.abs(finalX - startX) == 2)))
                ){
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
        if (!(o instanceof Knight)) return false;

        Knight knight = (Knight) o;

        if (getColor() != knight.getColor()) return false;
        return getName().equals(knight.getName());

    }

    @Override
    public int hashCode() {
        int result = getColor().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
