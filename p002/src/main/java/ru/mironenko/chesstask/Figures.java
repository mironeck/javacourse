package ru.mironenko.chesstask;

/**
 * Created by nikita on 30.10.2016.
 */
enum Figures {
    PAWN("Pawn"),
    BISHOP("Bishop"),
    KING("King"),
    KNIGHT("Knight"),
    ROOK("Rook"),
    QUEEN("Queen");

    private String name;

    Figures(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
