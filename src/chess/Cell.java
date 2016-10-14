package chess;

import java.awt.*;
import javax.swing.*;

import pieces.*;

/**
 * This is the Cell Class. It is the token class of our GUI.
 * There are total of 64 cells that together makes up the Chess Board
 *
 */
public class Cell extends JPanel implements Cloneable{

	private static final int RVAL = 113;
	private static final int GVAL = 198;
	private static final int BVAL = 113;
	//Member Variables
	private static final long serialVersionUID = 1L;
	private boolean ispossibledestination;
	private JLabel content;
	private Piece piece;
	private int x;
	private int y;                             //is public because this is to be accessed by all the other class
	private boolean isSelected=false;
	private boolean ischeck=false;

	//Constructors
	public Cell(int x,int y,Piece p)
	{		
		this.setX(x);
		this.setY(y);

		setLayout(new BorderLayout());

		setBackGroundColor(x, y);

		if(p!=null)
			setPiece(p);
	}

	public static int getRVAL() {
		return RVAL;
	}

	public static int getGVAL() {
		return GVAL;
	}

	public static int getBVAL() {
		return BVAL;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private void setBackGroundColor(int x, int y) {
		if(evenSumofCoordinates(x, y)) {
			setBackground(new Color(getRVAL(), getGVAL(), getBVAL()));
		}
		else
		{
			setBackground(Color.white);}
	}

	private boolean evenSumofCoordinates(int x, int y) {
		return (x+y)%2==0;
	}

	//A constructor that takes a cell as argument and returns a new cell will the same data but different reference
	public Cell(Cell cell) throws CloneNotSupportedException
	{
		this.setX(cell.getX());
		this.setY(cell.getY());
		setLayout(new BorderLayout());

		setBackGroundColor(getX(), getY());

		setPiece(cell);
	}

	private void setPiece(Cell cell) throws CloneNotSupportedException {
		if(cell.getpiece()!=null)
		{
			setPiece(cell.getpiece().getcopy());
		}
		else
			piece=null;
	}

	public void setPiece(Piece p)    //Function to inflate a cell with a piece
	{
		inflateCellWithPiece(p);
	}

	private void inflateCellWithPiece(Piece p) {
		setPiece(p);
		ImageIcon img=new javax.swing.ImageIcon(this.getClass().getResource(p.getPath()));
		setContent(new JLabel(img));
		this.add(getContent());
	}

	public void removePiece()      //Function to remove a piece from the cell
	{

		piece=null;
		this.remove(getContent());

	}


	public Piece getpiece()    //Function to access piece of a particular cell
	{
		return this.getPiece();
	}

	public void select()       //Function to mark a cell indicating it's selection
	{
		this.setBorder(BorderFactory.createLineBorder(Color.red,6));
		this.setSelected(true);
	}

	public boolean isselected()   //Function to return if the cell is under selection
	{
		return this.isSelected();
	}

	public void deselect()      //Function to delselect the cell
	{
		this.setBorder(null);
		this.setSelected(false);
	}

	public void setpossibledestination()     //Function to highlight a cell to indicate that it is a possible valid move
	{
		this.setBorder(BorderFactory.createLineBorder(Color.blue,4));
		this.setIspossibledestination(true);
	}

	public void removepossibledestination()      //Remove the cell from the list of possible moves
	{
		this.setBorder(null);
		this.setIspossibledestination(false);
	}

	public boolean ispossibledestination()    //Function to check if the cell is a possible destination 
	{
		return this.ispossibledestination;
	}

	public void setcheck()     //Function to highlight the current cell as checked (For King)
	{
		this.setBackground(Color.RED);
		this.setIscheck(true);
	}

	public void removecheck()   //Function to deselect check
	{
		this.setBorder(null);
		setBackGroundColor(getX(), getY());
		this.setIscheck(false);
	}

	public boolean ischeck()    //Function to check if the current cell is in check
	{
		return ischeck;
	}

	public void setIspossibledestination(boolean ispossibledestination) {
		this.ispossibledestination = ispossibledestination;
	}

	public JLabel getContent() {
		return content;
	}

	public void setContent(JLabel content) {
		this.content = content;
	}

	public Piece getPiece() {
		return piece;
	}

	@Override
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean selected) {
		isSelected = selected;
	}

	public void setIscheck(boolean ischeck) {
		this.ischeck = ischeck;
	}
}