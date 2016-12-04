package pieces;

import chess.Cell;

import java.util.ArrayList;

/**
 * This is the Queen Class inherited from the abstract Piece class
 */
public class Queen extends Piece {

    //Constructors
    public Queen(String i, String p, int c) {
        setAttributes(i, p, c);
    }

//    private void setAttributes(String i, String p, int c) {
//        setId(i);
//        setPath(p);
//        setColor(c);
//    }

    //Move Function Defined
    public ArrayList<Cell> move(Cell state[][], int x, int y) {
        //Queen has most number of possible moves
        //Queen can move any number of steps in all 8 direction
        //The possible moves of queen is a combination of Rook and Bishop
        getPossiblemoves().clear();

        //Check in which direction to move and return possible moves
        moveVertically(state, x, y);
        moveHorizontally(state, x, y);
        moveDiagonally(state, x, y);

        return getPossiblemoves();
    }

    private void moveDiagonally(Cell[][] state, int x, int y) {
        moveSE(state, x, y);

        moveNW(state, x, y);


        moveSW(state, x, y);

        moveNE(state, x, y);
    }

    private void moveHorizontally(Cell[][] state, int x, int y) {
        moveLeft(state, x, y);

        moveRight(state, x, y);
    }

    private void moveVertically(Cell[][] state, int x, int y) {
        moveDown(state, y, x);
        moveUp(state, y, x);
    }

    private void moveNE(Cell[][] state, int x, int y) {
        int tempx = x + 1;
        int tempy = y + 1;
        while (tempx < 8 && tempy < 8) {
            if (state[tempx][tempy].getpiece() == null)
                getPossiblemoves().add(state[tempx][tempy]);
            else if (state[tempx][tempy].getpiece().getcolor() == this.getcolor())
                break;
            else {
                getPossiblemoves().add(state[tempx][tempy]);
                break;
            }
            tempx++;
            tempy++;
        }
    }

    private void moveSW(Cell[][] state, int x, int y) {
        int tempx = x - 1;
        int tempy = y - 1;

        while (tempx >= 0 && tempy >= 0) {
            if (state[tempx][tempy].getpiece() == null)
                getPossiblemoves().add(state[tempx][tempy]);
            else if (state[tempx][tempy].getpiece().getcolor() == this.getcolor())
                break;
            else {
                getPossiblemoves().add(state[tempx][tempy]);
                break;
            }
            tempx--;
            tempy--;
        }
    }

    private void moveNW(Cell[][] state, int x, int y) {
        int tempx = x - 1;
        int tempy = y + 1;

        while (tempx >= 0 && tempy < 8) {
            if (state[tempx][tempy].getpiece() == null)
                getPossiblemoves().add(state[tempx][tempy]);
            else if (state[tempx][tempy].getpiece().getcolor() == this.getcolor())
                break;
            else {
                getPossiblemoves().add(state[tempx][tempy]);
                break;
            }
            tempx--;
            tempy++;
        }
    }

    private void moveSE(Cell[][] state, int x, int y) {
        int tempx = x + 1;
        int tempy = y - 1;
        while (tempx < 8 && tempy >= 0) {
            if (state[tempx][tempy].getpiece() == null)
                getPossiblemoves().add(state[tempx][tempy]);
            else if (state[tempx][tempy].getpiece().getcolor() == this.getcolor())
                break;
            else {
                getPossiblemoves().add(state[tempx][tempy]);
                break;
            }
            tempx++;
            tempy--;
        }
    }

    private void moveRight(Cell[][] state, int x, int y) {
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

    private void moveLeft(Cell[][] state, int x, int y) {
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

    private void moveUp(Cell[][] state, int y, int x) {
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

    private void moveDown(Cell[][] state, int y, int x) {
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