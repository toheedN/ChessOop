package chess;


import java.util.Observer;

import javax.sound.sampled.*;

class Sound implements Observer {
	
	private static Sound instance;
	
	public static Sound getInstance () {

			instance = new Sound();
		return instance;
	}
	
	/**
	 * made con private so no one can call it from outside of this class
	 */
	private Sound () {
		
	}
	
	private synchronized void playSound(final String name) {
		new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(name));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
	}

	@Override
	public void update(java.util.Observable o, Object arg) {
		System.out.println("SoundUpdate called");
		if (arg instanceof Event) {
			System.out.println("SoundUpdate called");
			Event event = (Event) arg;
			if (event == Event.PIECE_KILLED) {
				playSound("kill_piece.wav");
			} else if (event == Event.PIECE_MOVED) {
				playSound("move_piece.wav");
			} else if (event == Event.CHECK_MATE) {
				playSound("checkmate.wav");
			}
		}
	}
}
