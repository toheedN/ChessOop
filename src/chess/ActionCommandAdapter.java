package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionCommandAdapter implements ActionListener {

	private final Command command;
	public ActionCommandAdapter(Command command) {
		this.command = command;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		command.execute();
	}

}
