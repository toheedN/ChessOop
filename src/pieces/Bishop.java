package pieces;

import java.util.ArrayList;

import chess.Cell;


/**
 * This is the Bishop Class.
 * The Move Function defines the basic rules for movement of Bishop on a chess board
 * 
 *
 */
public class Bishop extends Piece{
	


	//Constructor
	public Bishop(String id,String path,int color)
	{
		setAttributes(id, path, color);
	}

	private void setAttributes(String i, String p, int c) {
		setId(i);
		setPath(p);
		setColor(c);
	}
	
	//move function defined. It returns a list of all the possible destinations of a Bishop
	//The basic principle of Bishop Movement on chess board has been implemented
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		//Bishop can Move diagonally in all 4 direction (NW,NE,SW,SE)
		//This function defines that logic
		resetPossibleMoves();
		
		BishopNWdirectionCheck(state, x,y);
		
		
		bishopNEdirectionCheck(state, x,y);
		

		bishopSWdirectionCheck(state, x,y);

		bishopSEdirectionCheck(state, x,y);
		
		return possiblemoves;
	}

	private void resetPossibleMoves() {
		possiblemoves.clear();
	}

	private void bishopSEdirectionCheck(Cell[][] state, int x, int y) {
		int tempx = x+1;
		int tempy = y+1;
		while(tempx<MAX_CORD && tempy<MAX_CORD)
		{
			if(state[tempx][tempy].getpiece()==null)
				possiblemoves.add(state[tempx][tempy]);
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx++;
			tempy++;
		}
	}

	private void bishopSWdirectionCheck(Cell[][] state, int x, int y) {
		int tempx = x-1;
		int tempy = y-1;
		
		while(tempx>=MIN_CORD && tempy>=MIN_CORD)
		{
			if(state[tempx][tempy].getpiece()==null)
				possiblemoves.add(state[tempx][tempy]);
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx--;
			tempy--;
		}
	}

	private void bishopNEdirectionCheck(Cell[][] state, int x, int y) {
		int tempx = x-1;
		int tempy = y+1;
		
		while(tempx>=MIN_CORD&&tempy<MAX_CORD)
		{
			if(state[tempx][tempy].getpiece()==null)
				possiblemoves.add(state[tempx][tempy]);
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx--;
			tempy++;
		}
	}

	private void BishopNWdirectionCheck(Cell[][] state, int x, int y) {
		int tempx = x+1;
		int tempy = y-1;
		while(tempx<MAX_CORD && tempy>=MIN_CORD)
		{
			if(state[tempx][tempy].getpiece()==null)
			{
				possiblemoves.add(state[tempx][tempy]);
			}
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx++;
			tempy--;
		}
	}
}