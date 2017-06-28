package ru.mironenko.bomberman.field;

/**
 * Created by nikita on 15.06.2017.
 */
public class Field {

    private final int[][] field;
    private final int countOfMonsters;
    private final int countOfBlocks;

    public Field(int x, int y, int countOfMonsters, int countOfBlocks){
        this.field = new int[x][y];
        this.countOfMonsters = countOfMonsters;
        this.countOfBlocks = countOfBlocks;
    }


}
