package pieces;

import chess.Cell;

import java.util.ArrayList;

/**
 * This is the Rook class inherited from abstract Piece class
 */
public class Rook extends Piece {

    //Constructor
    public Rook(String i, String p, int c) {
        setRookfields(i, p, c);
    }

    private void setRookfields(String i, String p, int c) {
        setId(i);
        setPath(p);
        setColor(c);
    }

    //Move function defined
    public ArrayList<Cell> move(Cell state[][], int x, int y) {
        //Rook can move only horizontally or vertically
        getPossiblemoves().clear();

        moveLeft(state, y, x);

        moveRight(state, y, x);

        moveDown(state, x, y);

        moveUp(state, x, y);

        return getPossiblemoves();
    }

    private void moveUp(Cell[][] state, int x, int y) {
        int tempy = y + 1;
        while (tempy < 8) {
            if (state[x][tempy].getpiece() == null)
                getPossiblemoves().add(state[x][tempy]);
            else if (state[x][tempy].getpiece().getcolor() == this.getcolor())
                break;
            else {
                getPossiblemoves().add(state[x][tempy]);
                break;
            }
            tempy++;
        }

    }

    private void moveDown(Cell[][] state, int x, int y) {
        int tempy = y - 1;
        while (tempy >= 0) {
            if (state[x][tempy].getpiece() == null)
                getPossiblemoves().add(state[x][tempy]);
            else if (state[x][tempy].getpiece().getcolor() == this.getcolor())
                break;
            else {
                getPossiblemoves().add(state[x][tempy]);
                break;
            }
            tempy--;
        }

    }

    private void moveRight(Cell[][] state, int y, int x) {
        int tempx = x + 1;
        while (tempx < 8) {
            if (state[tempx][y].getpiece() == null)
                getPossiblemoves().add(state[tempx][y]);
            else if (state[tempx][y].getpiece().getcolor() == this.getcolor())
                break;
            else {
                getPossiblemoves().add(state[tempx][y]);
                break;
            }
            tempx++;
        }
    }

    private void moveLeft(Cell[][] state, int y, int x) {
        int tempx = x - 1;
        while (tempx >= 0) {
            if (state[tempx][y].getpiece() == null)
                getPossiblemoves().add(state[tempx][y]);
            else if (state[tempx][y].getpiece().getcolor() == this.getcolor())
                break;
            else {
                getPossiblemoves().add(state[tempx][y]);
                break;
            }
            tempx--;
        }
    }
}
