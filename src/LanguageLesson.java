import java.util.ArrayList;

public class LanguageLesson {
    // the lesson elements are ordered string arrays, all string arrays are size 2
    //    they contain the english and mandarin equivalents
	String lessonText = new String();
	
	
	//* These are used for Melnyks lessons
    ArrayList<String[]> lessonWords = new ArrayList<String[]>();
    ArrayList<String[]> lessonPhrases = new ArrayList<String[]>();
    ArrayList<String[]> lessonDialog1 = new ArrayList<String[]>();
    ArrayList<String[]> lessonDialog2 = new ArrayList<String[]>();
    ArrayList<String[]> lessonDialog3 = new ArrayList<String[]>();
    //
    
    // these are used for Living Language lessons
    ArrayList<String[]> lessonVocab1 = new ArrayList<String[]>();
    ArrayList<String[]> lessonVocab2 = new ArrayList<String[]>();
    ArrayList<String[]> grammarBuilder1 = new ArrayList<String[]>();
    ArrayList<String[]> grammarBuilder2 = new ArrayList<String[]>();
    ArrayList<String[]> lessonWorkOut1 = new ArrayList<String[]>();
    ArrayList<String[]> lessonWorkOut2 = new ArrayList<String[]>();
    //
    
    LanguageLesson(){
        /* I dont know why these dont work, 
           for some reason if I create the vars here they are undefined in subclass constructor
         
        ArrayList<String[]> lessonWords = new ArrayList<String[]>();
        ArrayList<String[]> lessonPhrases = new ArrayList<String[]>();
        ArrayList<String[]> lessonDialog1 = new ArrayList<String[]>();
        ArrayList<String[]> lessonDialog2 = new ArrayList<String[]>();
        ArrayList<String[]> lessonDialog3 = new ArrayList<String[]>();
        */
        
    }
}
