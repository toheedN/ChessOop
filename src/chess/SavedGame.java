package chess;

import javax.swing.*;

public class SavedGame {
	static int maxSavedStates = 5;
	public static JFrame saved[] = new JFrame[maxSavedStates];
	
	/**************************************************************************************************
	 * A function to save the current state of the game 'objMain' to 'saved[]' array at location 'index
	 **************************************************************************************************/
	public static boolean save_game(int index, JFrame objMain)
	{
		if (saved[index] == null)
		{
			saved[index] = objMain;
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Save slot is full, choose a free save slot.");
			return false;
		}
	}
	/**********************************************************************************
	 * A function to retrieve a saved game state from location 'index' of array 'saved[]' 
	 **********************************************************************************/
	public static JFrame get_saved_game(int index)
	{
		if(saved[index] == null)
		{
			JOptionPane.showMessageDialog(null, "No saved game exists at this location. Choose a valid saved slot.");
		}
		
		return saved[index];
	}
	/************************************************************************************
	 * A function to delete a saved game state from array 'saved[]' at location 'index'
	 ************************************************************************************/
	public void delete_saved_slot(int index)
	{
		if(saved[index] == null)
		{
			JOptionPane.showMessageDialog(null, "No saved game exists at this location. Choose a valid saved slot.");
		}
		else
		{
			saved[index] = null;
			JOptionPane.showMessageDialog(null, "Saved game at slot " + index + " is deleted.");
		}
	}
}