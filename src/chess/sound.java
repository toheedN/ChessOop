package chess;


import javax.sound.sampled.*;

public class sound {
	//"move_piece.wav"
	//"kill_piece.wav"
	//"checkmate.wav"
	public static synchronized void playSound(final String name) {
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
}
