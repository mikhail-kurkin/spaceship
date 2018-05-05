package spaceship;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioThread implements Runnable {

	@Override
	public void run() {
		
		try {
			FileInputStream fis = new FileInputStream("res/na_vzlet.mp3");
			Player playMP3 = new Player(fis);
			playMP3.play();


//		Player p = new Player("res/na_vzlet.mp3");
//		Player p = new Player(new FileInputStream(getClass().getClassLoader().getResource("res/na_vzlet.mp3").getPath()));
//		p.play();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
	}
		
	}

}
