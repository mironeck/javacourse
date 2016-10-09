package ru.mironenko.chesstask;

/**
 * Created by Nikita on 05.10.2016.
 */
public abstract class Piece {

    private int x;
    private int y;

    public Piece(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
