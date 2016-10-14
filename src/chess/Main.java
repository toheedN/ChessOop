package chess;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pieces.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author Ashish Kedia and Adarsh Mohata
 *
 */


/**
 * This is the Main Class of our project.
 * All GUI Elements are declared, initialized and used in this class itself.
 * It is inherited from the JFrame Class of Java's Swing Library. 
 * 
 */
public class Main extends JFrame implements MouseListener
{
	private static final long serialVersionUID = 1L;
	
	//Variable Declaration
	public static Pieces pieces;
	public static Display display;
	public static  final int Height=700;
	public static final int Width=1110;
	public static Cell c,previous;
	public static int chance=0;
	public static Cell boardState[][];
	public static ArrayList<Cell> destinationlist = new ArrayList<Cell>();
	public static Player White=Player.createPlayer("null"),Black=Player.createPlayer("null");
	public static Time timer;
	public static Main Mainboard;
	public static boolean selected=false,end=false;
	public static ArrayList<Player> wplayer,bplayer;
	public static ArrayList<String> Wnames=new ArrayList<String>();
	public static ArrayList<String> Bnames=new ArrayList<String>();
	public static String wname=null,bname=null,winner=null;
	public static String move;
	public static Player tempPlayer;
	public static String[] WNames={},BNames={};
	public static int timeRemaining=60;
	
	
	public static void main(String[] args){
	
	//variable initialization
	//	Pieces.variableInitialization();
		pieces = new Pieces();
		display = new Display();
	//Setting up the Display.board
		SettingBoard();
		
		}

	private static void SettingBoard(){
		Mainboard = new Main();
		Mainboard.setVisible(true);	
		Mainboard.setResizable(false);
	}
	
	
	//Constructor
	private Main()
    {
		setSize(Width,Height);
		setTitle("Chess");
		timeRemaining=60;
		move="White";
		wname=null;
		bname=null;
		winner=null;
		Wnames=new ArrayList<String>();
		Bnames=new ArrayList<String>();
		ImageIcon img = new ImageIcon(this.getClass().getResource("icon.png"));
		this.setIconImage(img.getImage());
		
		//Time Slider Details
		display.timeSliderInit();
		
		
		//Fetching Details of all Players
		wplayer= Player.fetch_players();
		Iterator<Player> witr=wplayer.iterator();
		while(witr.hasNext())
			Wnames.add(witr.next().name());
				
		bplayer= Player.fetch_players();
		Iterator<Player> bitr=bplayer.iterator();
		while(bitr.hasNext())
			Bnames.add(bitr.next().name());
	    WNames=Wnames.toArray(WNames);	
		BNames=Bnames.toArray(BNames);
		
		display.setContent(this);
		
		//Defining the Player Box in Control Panel
		display.playerBoxInit();

		//Defining all the Cells
		display.cellsInit(this);
		
		display.playerDisplayInit();
	
		//The Left Layout When Game is inactive
		display.setLeftLayout();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	

	// A function to change the chance from White Player to Black Player or vice verse
	// It is made public because it is to be accessed in the Time Class
	public void changechance()
	{
		if (boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck())
		{
			chance^=1;
			gameend();
		}
		if(!destinationlist.isEmpty())
			cleandestinations(destinationlist);
		if(previous!=null)
			previous.deselect();
		previous=null;
		chance^=1;
		if(!end && timer!=null)
		{
			timer.reset();
			timer.start();
			display.removePlayer();
			changeTurn();

		}
	}
	
	private void changeTurn(){
		if(move=="White")
			move="Black";
		else
			move="White";
		
		display.changeTurn(move);
	}
	//A function to retrieve the Black King or White King
	private King getKing(int color)
	{
		boolean isWhite=color==0;
		return pieces.returnKing(isWhite);
	}
	
	//A function to clean the highlights of possible destination cells
    private void cleandestinations(ArrayList<Cell> destlist)      //Function to clear the last Display.move's destinations
    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
    		it.next().removepossibledestination();
    }
    
    //A function that indicates the possible Display.moves by highlighting the Cells
    private void highlightdestinations(ArrayList<Cell> destlist)
    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
    		it.next().setpossibledestination();
    }
    
  private Cell[][] exceptionCheckKing(Cell newboardstate[][] , boolean kingInDanger){
    	
    	for(int i=0;i<8;i++)
    		for(int j=0;j<8;j++)
    		{	
    			try
    				{ 
    				newboardstate[i][j] = new Cell(boardState[i][j]);
    				} 
    			catch (CloneNotSupportedException e){
	    			e.printStackTrace(); 
	    			if (kingInDanger)
	    				System.out.println("There is a problem with cloning !!");
	    			}
    		}
       	return newboardstate;
    }
  
  private Cell[][] checkForKingDanger(Cell newboardstate[][] ,Cell tempc , Cell fromcell)
  {
		if(newboardstate[tempc.x][tempc.y].getpiece()!=null)
			newboardstate[tempc.x][tempc.y].removePiece();
		newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
		return newboardstate;
  }


  //Function to check if the king will be in danger if the given Display.move is made
  
  private boolean willkingbeindanger(Cell fromcell,Cell tocell)
  {
  	Cell newboardstate[][] = new Cell[8][8];
  	newboardstate = exceptionCheckKing(newboardstate , true);
  	newboardstate = checkForKingDanger(newboardstate ,tocell , fromcell);

  	if(newboardstate[tocell.x][tocell.y].getpiece() instanceof King)
		{
			((King)(newboardstate[tocell.x][tocell.y].getpiece())).setx(tocell.x);
			((King)(newboardstate[tocell.x][tocell.y].getpiece())).sety(tocell.y);
		}
		newboardstate[fromcell.x][fromcell.y].removePiece();
		if (((King) (newboardstate[getKing(chance).getx()][getKing(chance).gety()].getpiece())).isindanger(newboardstate))
			return true;
		else
			return false;
  }

  //A function to eliminate the possible moves that will put the King in danger
  private ArrayList<Cell> filterdestination (ArrayList<Cell> destlist, Cell fromcell)
  {
  	ArrayList<Cell> newlist = new ArrayList<Cell>();
  	Cell newboardstate[][] = new Cell[8][8];
  	ListIterator<Cell> it = destlist.listIterator();
  	int x,y;
  	while (it.hasNext())
  	{
  	   	newboardstate = exceptionCheckKing(newboardstate , false);
  	    		
  		Cell tempc = it.next();
      	newboardstate = checkForKingDanger(newboardstate ,tempc , fromcell);

      	x=getKing(chance).getx();
  		y=getKing(chance).gety();
  		if(newboardstate[fromcell.x][fromcell.y].getpiece() instanceof King)
  		{
  			((King)(newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
  			((King)(newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
  			x=tempc.x;
  			y=tempc.y;
  		}
  		newboardstate[fromcell.x][fromcell.y].removePiece();		
  		if ((!((King) (newboardstate[x][y].getpiece())).isindanger(newboardstate)))
  			newlist.add(tempc);
  	}
  	return newlist;
  }
  
  //A Function to filter the possible moves when the king of the current player is under Check 
  private ArrayList<Cell> incheckfilter (ArrayList<Cell> destlist, Cell fromcell, int color)
  {
  	ArrayList<Cell> newlist = new ArrayList<Cell>();
  	Cell newboardstate[][] = new Cell[8][8];
  	ListIterator<Cell> it = destlist.listIterator();
  	int x,y;
  	while (it.hasNext())
  	{
  	   	newboardstate = exceptionCheckKing(newboardstate , false);
  	    
  		Cell tempc = it.next();
      	newboardstate = checkForKingDanger(newboardstate ,tempc , fromcell);

      	x=getKing(color).getx();
  		y=getKing(color).gety();
  		if(newboardstate[tempc.x][tempc.y].getpiece() instanceof King)
  		{
  			((King)(newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
  			((King)(newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
  			x=tempc.x;
  			y=tempc.y;
  		}
  		newboardstate[fromcell.x][fromcell.y].removePiece();
  		if ((!((King) (newboardstate[x][y].getpiece())).isindanger(newboardstate)))
  			newlist.add(tempc);
  	}
  	return newlist;
  }
   
    //A function to check if the King is check-mate. The Game Ends if this function returns true.
    public boolean checkmate(int color)
    {
    	ArrayList<Cell> dlist = new ArrayList<Cell>();
    	for(int i=0;i<8;i++)
    	{
    		for(int j=0;j<8;j++)
    		{
    			if (boardState[i][j].getpiece()!=null && boardState[i][j].getpiece().getcolor()==color)
    			{
    				dlist.clear();
    				dlist=boardState[i][j].getpiece().move(boardState, i, j);
    				dlist=incheckfilter(dlist,boardState[i][j],color);
    				if(dlist.size()!=0)
    					return false;
    			}
    		}
    	}
    	return true;
    }
	
    
    @SuppressWarnings("deprecation")
	private void gameend()
    {
    	cleandestinations(destinationlist);
    	display.disabledTime();
    	timer.stop();
    	if(previous!=null)
    		previous.removePiece();
    	if(chance==0)
		{	White.updateGamesWon();
			White.Update_Player();
			winner=White.name();
		}
		else
		{
			Black.updateGamesWon();
			Black.Update_Player();
			winner=Black.name();
		}
    	display.endDisplay(winner);
    	
    	end=true;
		Mainboard.disable();
		Mainboard.dispose();
		Mainboard = new Main();
		Mainboard.setVisible(true);
		Mainboard.setResizable(false);
    }
    
    
    public void initializeClick()
    {
		c.select();
		previous=c;
		destinationlist.clear();
		destinationlist=c.getpiece().move(boardState, c.x, c.y);
		if(c.getpiece() instanceof King)
			destinationlist=filterdestination(destinationlist,c);
		else
		{
			if(boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck())
				destinationlist = new ArrayList<Cell>(filterdestination(destinationlist,c));
			else if(!destinationlist.isEmpty() && willkingbeindanger(c,destinationlist.get(0)))
				destinationlist.clear();
		}
		highlightdestinations(destinationlist);
    }

    
    public void  setKingPieceCoordinates(){
    	
    	((King)c.getpiece()).setx(c.x);
		((King)c.getpiece()).sety(c.y);
    	
    }
   

    
    //These are the abstract function of the parent class. Only relevant method here is the On-Click Fuction
    //which is called when the user clicks on a particular cell
	@Override
	public void mouseClicked(MouseEvent arg0){
		// TODO Auto-generated method stub
		c=(Cell)arg0.getSource();
		if (previous==null)
		{
			if(c.getpiece()!=null)
			{
				if(c.getpiece().getcolor()!=chance)
					return;
				initializeClick();
			}
		}
		else
		{
			if(c.x==previous.x && c.y==previous.y)
			{
				c.deselect();
				cleandestinations(destinationlist);
				destinationlist.clear();
				previous=null;
			}
			else if(c.getpiece()==null||previous.getpiece().getcolor()!=c.getpiece().getcolor())
			{
				if(c.ispossibledestination())
				{
					if(c.getpiece()!=null)
						c.removePiece();
					c.setPiece(previous.getpiece());
					if (previous.ischeck())
						previous.removecheck();
					previous.removePiece();
					if(getKing(chance^1).isindanger(boardState))
					{
						boardState[getKing(chance^1).getx()][getKing(chance^1).gety()].setcheck();
						if (checkmate(getKing(chance^1).getcolor()))
						{
							previous.deselect();
							if(previous.getpiece()!=null)
								previous.removePiece();
							gameend();
						}
					}
					if(!getKing(chance).isindanger(boardState))
						boardState[getKing(chance).getx()][getKing(chance).gety()].removecheck();
					if(c.getpiece() instanceof King)
					{
						setKingPieceCoordinates();
					}
					changechance();
					if(!end)
					{
						timer.reset();
						timer.start();
					}
				}
				if(previous!=null)
				{
					previous.deselect();
					previous=null;
				}
				cleandestinations(destinationlist);
				destinationlist.clear();
			}
			
				else if(previous.getpiece().getcolor()==c.getpiece().getcolor())
				{
					previous.deselect();
					cleandestinations(destinationlist);
					initializeClick();
				}

			
		}
		if(c.getpiece()!=null && c.getpiece() instanceof King)
		{
			setKingPieceCoordinates();
		}
	}
    
    //Other Irrelevant abstract function. Only the Click Event is captured.
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}
	
	
	
	
		
		
		 
}