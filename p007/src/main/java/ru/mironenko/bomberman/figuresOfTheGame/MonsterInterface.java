package ru.mironenko.bomberman.figuresOfTheGame;

/**
 * Created by nikita on 15.06.2017.
 */
public interface MonsterInterface {

    void moveUp();
    void moveDown();
    void moveRight();
    void moveLeft();
    boolean canMove(int fromX, int fromY, int toX, int toY);

}
