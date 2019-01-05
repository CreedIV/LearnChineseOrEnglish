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
	
	public static Integer lastMistakePoint(String userString, String correctString) {
		// find the last incorrect character of user input, derived from minEdit algorithm...
		int m = userString.length();
		int n = correctString.length();
		
		// user string along second axis, correct string along first
		int[][] distMatrix = new int[n+1][m+1];
		Character[][] backpoint = new Character[n+1][m+1]; // B is below(insertion), L is left(deletion), D is diag (sub)
		
		distMatrix[0][0] = 0;
		backpoint[0][0] = ' ';
		for(int i = 1; i < m+1; i++) {
			distMatrix[0][i] = distMatrix[0][i-1] + 1; // cost of deleteing from user string is 1
			backpoint[0][i] = 'L';
		}
		
		for(int i = 1; i < n+1; i++) {
			distMatrix[i][0] = distMatrix[i-1][0] + 1; // cost of inserting chars of correct string is 1
			backpoint[i][0] = 'B';
		}
		for(int i = 1; i < n+1; i++) { // each correct char
			for(int j = 1; j < m+1; j++) { // each user supplied char
				if(userString.charAt(j-1) != correctString.charAt(i-1)) {
					int belowdist =distMatrix[i-1][j] +1; // insertion cost is 1
					int leftdist = distMatrix[i][j-1] +1; // deletion cost is 1
					int diagdist = distMatrix[i-1][j-1] + 1; // substitution cost is 1
					if(diagdist <= leftdist && diagdist <= belowdist) {
						distMatrix[i][j] = diagdist;
						backpoint[i][j] = 'D';
					}else if(leftdist <= belowdist) {
						distMatrix[i][j] = leftdist;
						backpoint[i][j] = 'L';
					}else {
						distMatrix[i][j] = belowdist;
						backpoint[i][j] = 'B';
					}
				}else { // correctly matching chars have no cost.
					distMatrix[i][j] = distMatrix[i-1][j-1];
					backpoint[i][j] = 'D';
				}
			}
		}
		
		List<Integer> mistakePoints = new ArrayList<Integer>();
		int i = n;
		int j = m;
		while(i > 0 || j > 0) {

			if(backpoint[i][j] == 'D') {
				if(distMatrix[i][j] != distMatrix[i-1][j-1])
					mistakePoints.add(j);
				i--;
				j--;
			}else if(backpoint[i][j] == 'L') {
				mistakePoints.add(j);
				j--;
			}else if(backpoint[i][j] == 'B') {
				mistakePoints.add(j);
				i--;
			}
		}

		if(!mistakePoints.isEmpty())
			return mistakePoints.get(0);
		return null;
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
		System.out.println("testing sounds from lesson : " + lessonNum + " section : " + partial_path);

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
