package pieces;

import java.util.ArrayList;

import chess.Cell;
import chess.Constants;

public class King extends Piece {

    private int x;
    private int y; //Extra variables for King class to keep a track of king's position

    //King Constructor
    public King(String i, String p, int c, int x, int y) {
        setx(x);
        sety(y);
        setId(i);
        setPath(p);
        setColor(c);
    }

    //general value access functions
    public void setx(int x) {
        this.setX(x);
    }

    public void sety(int y) {
        this.setY(y);
    }

    public int getx() {
        return getX();
    }

    public int gety() {
        return getY();
    }

    //Move Function for King Overridden from Pieces
    public ArrayList<Cell> move(Cell state[][], int x, int y) {
        //King can move only one step. So all the adjacent 8 cells have been considered.
        getPossiblemoves().clear();
        int posx[] = {x, x, x + 1, x + 1, x + 1, x - 1, x - 1, x - 1};
        int posy[] = {y - 1, y + 1, y - 1, y, y + 1, y - 1, y, y + 1};
        for (int i = 0; i < getMaxCord(); i++) {
            if ((posx[i] >= getMinCord() && posx[i] < getMaxCord() && posy[i] >= getMinCord() && posy[i] < getMaxCord()))
                if ((state[posx[i]][posy[i]].getpiece() == null || state[posx[i]][posy[i]].getpiece().getcolor() != this.getcolor()))
                    getPossiblemoves().add(state[posx[i]][posy[i]]);
        }
        
        if (isKingNotMoved(x, y) && isPathFree(state) && isRookNotMoved(state)) {
        	if (isWhite()) {
        		 getPossiblemoves().add(state[7][0]);
        	} else {
        		getPossiblemoves().add(state[0][0]);
        	}
        }
        return getPossiblemoves();
    }
    
    boolean isKingNotMoved (int x, int y) {
    	return (isWhite() && x == 7 && y == 3)
    			|| (!isWhite() && x == 0 && y == 3);
    }
    
    boolean isPathFree (Cell state[][]) {
    	if (isWhite()) {
    		return state[7][1].getpiece() == null && state[7][2].getpiece() == null;
    	} else {
    		return state[0][1].getpiece() == null && state[0][2].getpiece() == null;
    	}
    }

    boolean isRookNotMoved (Cell state[][]) {
    	if (isWhite()) {
    		return state[7][0].getpiece() instanceof Rook;
    	} else { // black
    		return state[0][0].getpiece() instanceof Rook;
    	}
    }

    //Function to check if king is under threat
    //It checks whether there is any piece of opposite color that can attack king for a given board state
    public boolean isindanger(Cell state[][]) {


        if (attackedHorizontally(state)
                || attackedVertically(state)
                || attackedDiagonally(state, getX(), getY())) {
            return true;
        }

        if (attackedbyKnight(state)
                || attackedbyPawn(state)) {
            return true;
        }

        return false;

    }

    private boolean attackedbyPawn(Cell[][] state) {
        int pox[] = {getX() + 1, getX() + 1, getX() + 1, getX(), getX(), getX() - 1, getX() - 1, getX() - 1};
        int poy[] = {getY() - 1, getY() + 1, getY(), getY() + 1, getY() - 1, getY() + 1, getY() - 1, getY()};
        {
            for (int i = 0; i < 8; i++)
                if ((pox[i] >= 0 && pox[i] < 8 && poy[i] >= 0 && poy[i] < 8))
                    if (state[pox[i]][poy[i]].getpiece() != null && state[pox[i]][poy[i]].getpiece().getcolor() != this.getcolor() && (state[pox[i]][poy[i]].getpiece() instanceof Pawn)) {
                        return true;
                    }
        }
        if (isWhite()) {
            return performValidationOnWhite(state);
        } else {
            return performValidationOnBlack(state);
        }

    }

    private boolean performValidationOnBlack(Cell[][] state) {
        if ((validateCoordsAndPiece(state, (getX() + 1), (getY() - 1))
                && validatePieceTypeandColor(state, 0, (getX() + 1), (getY() - 1))
                || (validateCoordsAndPiece(state, (getX() + 1), (getY() + 1))
                && validatePieceTypeandColor(state, 0, (getX() + 1), (getY() + 1))))) {
            return true;
        }
        return false;
    }

    private boolean performValidationOnWhite(Cell[][] state) {
        if ((validateCoordsAndPiece(state, (getX() - 1), (getY() - 1))
                && validatePieceTypeandColor(state, 1, (getX() - 1), (getY() - 1)))
                || (validateCoordsAndPiece(state, (getX() - 1), (getY() + 1))
                && validatePieceTypeandColor(state, 1, (getX() - 1), (getY() + 1)))) {
            return true;
        }
        return false;
    }

    private boolean isWhite() {
        return getcolor() == Constants.WHITE;
    }

    private boolean validatePieceTypeandColor(Cell[][] state, int color, int tx, int ty) {
        return state[tx][ty].getpiece().getcolor() == color
                && (state[tx][ty].getpiece() instanceof Pawn);
    }

    private boolean validateCoordsAndPiece(Cell[][] state, int tx, int ty) {
        return validateCoords() && validatePiece(state, tx, ty);
    }

    private boolean validatePiece(Cell[][] state, int tx, int ty) {
        return state[tx][ty].getpiece() != null;
    }

    private boolean validateCoords() {
        return getX() > 0 && getY() > 0;
    }

    private boolean attackedbyKnight(Cell[][] state) {
        int posx[] = {getX() + 1, getX() + 1, getX() + 2, getX() + 2, getX() - 1, getX() - 1, getX() - 2, getX() - 2};
        int posy[] = {getY() - 2, getY() + 2, getY() - 1, getY() + 1, getY() - 2, getY() + 2, getY() - 1, getY() + 1};
        for (int i = 0; i < 8; i++)
            if ((posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8))
                if (state[posx[i]][posy[i]].getpiece() != null && state[posx[i]][posy[i]].getpiece().getcolor() != this.getcolor() && (state[posx[i]][posy[i]].getpiece() instanceof Knight)) {
                    return true;
                }
        return false;
    }

    private boolean attackedDiagonally(Cell[][] state, int x, int y) {
        return (attackedfromSE(state, x, y) ||
                attackedfromNW(state, x, y) ||
                attackedfromSW(state, x, y) ||
                attackedfromNE(state, x, y));
    }

    private boolean attackedfromNE(Cell[][] state, int x, int y) {
        int tempx = x + 1;
        int tempy = y + 1;

        while (tempx < 8 && tempy < 8) {
            if (state[tempx][tempy].getpiece() == null) {
                tempx++;
                tempy++;
            } else if (state[tempx][tempy].getpiece().getcolor() == this.getcolor())
                break;
            else {
                if (state[tempx][tempy].getpiece() instanceof Bishop || state[tempx][tempy].getpiece() instanceof Queen)
                    return true;
                else
                    break;
            }
        }
        return false;
    }

    private boolean attackedfromSW(Cell[][] state, int x, int y) {
        int tempx = x - 1;
        int tempy = y - 1;

        while (tempx >= 0 && tempy >= 0) {
            if (state[tempx][tempy].getpiece() == null) {
                tempx--;
                tempy--;
            } else if (state[tempx][tempy].getpiece().getcolor() == this.getcolor())
                break;
            else {
                if (state[tempx][tempy].getpiece() instanceof Bishop || state[tempx][tempy].getpiece() instanceof Queen)
                    return true;
                else
                    break;
            }
        }
        return false;
    }

    private boolean attackedfromNW(Cell[][] state, int x, int y) {
        int tempx = x - 1;
        int tempy = y + 1;
        while (tempx >= 0 && tempy < 8) {
            if (state[tempx][tempy].getpiece() == null) {
                tempx--;
                tempy++;
            } else if (state[tempx][tempy].getpiece().getcolor() == this.getcolor())
                break;
            else {
                if (state[tempx][tempy].getpiece() instanceof Bishop || state[tempx][tempy].getpiece() instanceof Queen)
                    return true;
                else
                    break;
            }
        }
        return false;
    }

    private boolean attackedfromSE(Cell[][] state, int x, int y) {
        int tempx = x + 1;
        int tempy = y - 1;
        while (tempx < 8 && tempy >= 0) {
            if (state[tempx][tempy].getpiece() == null) {
                tempx++;
                tempy--;
            } else if (state[tempx][tempy].getpiece().getcolor() == this.getcolor())
                break;
            else {
                if (state[tempx][tempy].getpiece() instanceof Bishop || state[tempx][tempy].getpiece() instanceof Queen)
                    return true;
                else
                    break;
            }
        }
        return false;
    }

    private boolean attackedVertically(Cell[][] state) {
        return attackedFromUp(state) || attackedFromDown(state);
    }

    private boolean attackedHorizontally(Cell[][] state) {
        return attackedFromLeft(state) || attackedFromRight(state);
    }

    private boolean attackedFromDown(Cell[][] state) {
        for (int i = getY() - 1; i >= 0; i--) {
            if (state[getX()][i].getpiece() == null)
                continue;
            else if (state[getX()][i].getpiece().getcolor() == this.getcolor())
                break;
            else {
                if ((state[getX()][i].getpiece() instanceof Rook) || (state[getX()][i].getpiece() instanceof Queen))
                    return true;
                else
                    break;
            }
        }
        return false;
    }

    private boolean attackedFromUp(Cell[][] state) {
        for (int i = getY() + 1; i < 8; i++) {
            if (state[getX()][i].getpiece() == null)
                continue;
            else if (state[getX()][i].getpiece().getcolor() == this.getcolor())
                break;
            else {
                if ((state[getX()][i].getpiece() instanceof Rook) || (state[getX()][i].getpiece() instanceof Queen))
                    return true;
                else
                    break;
            }
        }
        return false;
    }

    private boolean attackedFromRight(Cell[][] state) {
        for (int i = getX() - 1; i >= 0; i--) {
            if (state[i][getY()].getpiece() == null)
                continue;
            else if (state[i][getY()].getpiece().getcolor() == this.getcolor())
                break;
            else {
                if ((state[i][getY()].getpiece() instanceof Rook) || (state[i][getY()].getpiece() instanceof Queen))
                    return true;
                else
                    break;
            }
        }
        return false;
    }

    private boolean attackedFromLeft(Cell[][] state) {
        for (int i = getX() + 1; i < 8; i++) {
            if (state[i][getY()].getpiece() == null)
                continue;
            else if (state[i][getY()].getpiece().getcolor() == this.getcolor())
                break;
            else {
                if ((state[i][getY()].getpiece() instanceof Rook) || (state[i][getY()].getpiece() instanceof Queen))
                    return true;
                else
                    break;
            }
        }
        return false;
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