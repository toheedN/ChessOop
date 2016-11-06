package chess;

import javax.swing.JOptionPane;

public class SavedGame {
	boolean savedStates[]={false, false, false, false, false};
	int maxSavedStates = 5;
	public Main saved[] = {Main.get_new_main(), Main.get_new_main(), Main.get_new_main(), Main.get_new_main(), Main.get_new_main()};
	
	/**************************************************************************************************
	 * A function to save the current state of the game 'objMain' to 'saved[]' array at location 'arg'
	 **************************************************************************************************/
	public boolean save_game(int arg, Main objMain)
	{
		if (savedStates[arg] == false)
		{
			saved[arg] = objMain;
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Save slot is full, choose a free save slot.");
			return false;
		}
	}
	/**********************************************************************************
	 * A function to retrieve a saved game state from location 'arg' of array 'saved[]' 
	 **********************************************************************************/
	public Main get_saved_game(int arg)
	{
		if(savedStates[arg])
		{
			return saved[arg];
		}
		JOptionPane.showMessageDialog(null, "No saved game exists at this location. Choose a valid saved slot.");
		return null;
	}
	/************************************************************************************
	 * A function to delete a saved game state from array 'saved[]' at location 'arg'
	 ************************************************************************************/
	public void delete_saved_slot(int arg)
	{
		savedStates[arg] = false;
		saved[arg] = null;
		JOptionPane.showMessageDialog(null, "Saved game at slot "+arg+" is deleted.");
	}
}
