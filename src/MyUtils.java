import java.net.URL;
import java.util.List;
import java.util.Random;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MyUtils {
	static MediaPlayer mediaPlayer;
	
	public static<T> void shuffle(List<T> list)
	{
		Random random = new Random();

		// start from end of the list
		for (int i = list.size() - 1; i >= 1; i--)
		{
			// get a random index j such that 0 <= j <= i
			int j = random.nextInt(i + 1);

			// swap element at i'th position in the list with element at
			// randomly generated index j
			T obj = list.get(i);
			list.set(i, list.get(j));
			list.set(j, obj);
		}
	}
	
	public static void playSoundFile(URL resource) {
		playSoundFile(resource, null);
	}
	
	public static void playSoundFile(URL resource, Double rate) {
        Media media = new Media(resource.toString());
        if(mediaPlayer != null) {
            mediaPlayer.dispose();
        }
        mediaPlayer = new MediaPlayer(media);
        if(rate != null)
        	mediaPlayer.setRate(rate);
        mediaPlayer.play();
	}
}
