package pieces;

import java.util.ArrayList;

import chess.Cell;

/**
 * This is the Pawn Class inherited from the piece
 *
 */
public class Pawn extends Piece{
	
	//COnstructors
	public Pawn(String i,String p,int c)
	{
		setAttributes(i, p, c);
	}

	private void setAttributes(String i, String p, int c) {
		setId(i);
		setPath(p);
		setColor(c);
	}
	
	//Move Function Overridden
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		//Pawn can move only one step except the first chance when it may move 2 steps
		//It can move in a diagonal fashion only for attacking a piece of opposite color
		//It cannot move backward or move forward to attact a piece
		
		getPossiblemoves().clear();
		if(isWhite())
		{
			if(x==0)
				return getPossiblemoves();
			
			calculateMovesForWhiteColor(state, x, y);
		}
		else
		{
			if(x==8)
				return getPossiblemoves();
			calculateMovesForBlackColor(state, x, y);
		}
		return getPossiblemoves();
	}

	private boolean isWhite() {
		return getcolor()==0;
	}

	private void calculateMovesForBlackColor(Cell[][] state, int x, int y) {
		if(state[x+1][y].getpiece()==null)
		{
			getPossiblemoves().add(state[x+1][y]);
			if(x==1)
			{
				if(state[3][y].getpiece()==null)
					getPossiblemoves().add(state[3][y]);
			}
		}
		if((y>0)&&(state[x+1][y-1].getpiece()!=null)&&(state[x+1][y-1].getpiece().getcolor()!=this.getcolor()))
			getPossiblemoves().add(state[x+1][y-1]);
		if((y<7)&&(state[x+1][y+1].getpiece()!=null)&&(state[x+1][y+1].getpiece().getcolor()!=this.getcolor()))
			getPossiblemoves().add(state[x+1][y+1]);
	}

	private void calculateMovesForWhiteColor(Cell[][] state, int x, int y) {
		if(state[x-1][y].getpiece()==null)
		{
			getPossiblemoves().add(state[x-1][y]);
			if(x==6)
			{
				if(state[4][y].getpiece()==null)
					getPossiblemoves().add(state[4][y]);
			}
		}
		if((y>0)&&(state[x-1][y-1].getpiece()!=null)&&(state[x-1][y-1].getpiece().getcolor()!=this.getcolor()))
			getPossiblemoves().add(state[x-1][y-1]);
		if((y<7)&&(state[x-1][y+1].getpiece()!=null)&&(state[x-1][y+1].getpiece().getcolor()!=this.getcolor()))
			getPossiblemoves().add(state[x-1][y+1]);
	}
}
