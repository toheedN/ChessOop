package pieces;

import chess.Cell;

import java.util.ArrayList;


/**
 * This is the Piece Class. It is an abstract class from which all the actual pieces are inherited.
 * It defines all the function common to all the pieces
 * The move() function an abstract function that has to be overridden in all the inherited class
 * It implements Cloneable interface as a copy of the piece is required very often
 */
public abstract class Piece implements Cloneable {

    private static final int MIN_CORD = 0;
    private static final int MAX_CORD = 8;
    //Member Variables
    private int color;
    private String id = null;
    private String path;
    private ArrayList<Cell> possiblemoves = new ArrayList<Cell>();  //Protected (access from child classes)

    public static int getMinCord() {
        return MIN_CORD;
    }

    public static int getMaxCord() {
        return MAX_CORD;
    }

    public abstract ArrayList<Cell> move(Cell pos[][], int x, int y);  //Abstract Function. Must be overridden
    
    public abstract void setAttributes(String i, String p, int c); 

    //Path getter
    public String getPath() {
        return path;
    }

    //Path Setter
    public void setPath(String path) {
        this.path = path;
    }

    //Id getter
    public String getId() {
        return id;
    }

    //Id Setter
    public void setId(String id) {
        this.id = id;
    }

    //Color Getter
    public int getcolor() {
        return this.getColor();
    }

    //Function to return the a "shallow" copy of the object. The copy has exact same variable value but different reference
    public Piece getcopy() throws CloneNotSupportedException {
        return (Piece) this.clone();
    }

    public int getColor() {
        return color;
    }

    //Color Setter
    public void setColor(int c) {
        this.color = c;
    }

    public ArrayList<Cell> getPossiblemoves() {
        return possiblemoves;
    }

    public void setPossiblemoves(ArrayList<Cell> possiblemoves) {
        this.possiblemoves = possiblemoves;
    }
}