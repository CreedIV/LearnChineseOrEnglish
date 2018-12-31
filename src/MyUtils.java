import java.net.URL;
import java.util.ArrayList;
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
	
	public static void playSoundFile(Integer lessonNum, String partialPath, String lessonDataString) {
		playSoundFile(lessonNum, partialPath, lessonDataString, null);
	}

	private static String createWavFilePath(Integer lessonNum, String partialPath, String lessonDataString) {
		String filename = lessonDataString.replaceAll("[ .?!,()]", "") + ".wav";
		String path = "./audio/lesson" + lessonNum + "/" + partialPath + "/" + filename;
		return path;
	}
	
	public static void playSoundFile(Integer lessonNum, String partialPath, String lessonDataString, Double rate) {
		String path = createWavFilePath(lessonNum, partialPath, lessonDataString);
		//System.out.println(path);
		URL resource = MyUtils.class.getResource(path);
		
		playSoundFile(resource);
	}
	
	
	// unit Testing functions
	
    static void testSoundFilesLivingLangLesson(int lessonNum) {
    	LanguageLesson currLesson = new LivingLanguageChinese(lessonNum); 
        
        if(currLesson.lessonVocab1.size() > 0) {
	        TestSoundFiles(lessonNum, currLesson.lessonVocab1, "vocab1");
        }
        if(currLesson.lessonVocab2.size() > 0) {
	        TestSoundFiles(lessonNum, currLesson.lessonVocab2, "vocab2");
        }
        
        if(currLesson.grammarBuilder1.size() > 0) {
	        TestSoundFiles(lessonNum, currLesson.grammarBuilder1, "grammarbuilder1");
        }
        
        if(currLesson.grammarBuilder2.size() > 0) {
	        TestSoundFiles(lessonNum, currLesson.grammarBuilder2, "grammarbuilder2");
        }
        
        if(currLesson.lessonWorkOut1.size() > 0) {
	        TestSoundFiles(lessonNum, currLesson.lessonWorkOut1, "workout1");
        }
        
        if(currLesson.lessonWorkOut2.size() > 0) {        
	        TestSoundFiles(lessonNum, currLesson.lessonWorkOut2, "workout2");
        }
    }


    private static void TestSoundFiles(int lessonNum, ArrayList<String[]> lessonData, String partial_path) {
    	for(String[] dataArray : lessonData ) {
    		
    		for(int i = 0; i <= 1; i++) {
    			
    			String lang = "";
    			if(i == 1) {
    				lang = "english/";
    			}else {
    				lang = "chinese/";
    			}
    			String lessonString = dataArray[i];
    			
        		try {
        			MyUtils.playSoundFile(lessonNum, lang + partial_path, lessonString);
        		}catch(Exception e) {
        			String path = createWavFilePath(lessonNum, lang + partial_path, lessonString);
        			System.out.println("problem looking for file : " + path);
        		}    			
    		}

    	}
	}


}
