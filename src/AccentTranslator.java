import java.util.HashMap;
import java.util.Map;

public class AccentTranslator{
    /**
     * This class is for translating vowels followed by a number into a vowel with the mandarin accent
     *  a vowel followed by 1 is flat tone,
     *   followed by 2 is rising,
     *   followed by 3 is fall rise,
     *   followed by 4 is falling
     */
    
    private static final boolean DEBUG_TRANSLATE = false;
    
    private static AccentTranslator translatorSingleton = new AccentTranslator();
    private static Map<Character, Character> accentMap4;
    private static Map<Character, Character> accentMap1;
    private static Map<Character, Character> accentMap2;
    private static Map<Character, Character> accentMap3;
    private static Map<Character, Character> accentMap5;
    
    private AccentTranslator() {
        // the construct creates the maps
        accentMap1 = new HashMap<Character, Character>(); // flat : 1
        accentMap2 = new HashMap<Character, Character>(); // rising  : 2
        accentMap3 = new HashMap<Character, Character>(); // fall rise : 3
        accentMap4 = new HashMap<Character, Character>(); // falling : 4
        accentMap5 = new HashMap<Character, Character>(); // x as in nu5re2n
        // these are maps between vowels and the (unicode) vowel with an accent mark.
        accentMap4.put('A', '\u00C0'); // falling
        accentMap1.put('A', '\u0100'); // flat
        accentMap2.put('A', '\u00C1'); // rising
        accentMap3.put('A', '\u0102'); // fall rise
        accentMap4.put('E', '\u00C8'); // falling
        accentMap1.put('E', '\u0112'); // flat
        accentMap2.put('E', '\u00C9'); // rising
        accentMap3.put('E', '\u0114'); // fall rise
        accentMap4.put('I', '\u00CC'); // falling
        accentMap1.put('I', '\u012A'); // flat
        accentMap2.put('I', '\u00CD'); // rising
        accentMap3.put('I', '\u012C'); // fall rise
        accentMap4.put('O', '\u00D2'); // falling
        accentMap1.put('O', '\u014C'); // flat
        accentMap2.put('O', '\u00D3'); // rising
        accentMap3.put('O', '\u014E'); // fall rise  
        accentMap4.put('U', '\u00D9'); // falling
        accentMap1.put('U', '\u016A'); // flat
        accentMap2.put('U', '\u00DA'); // rising
        accentMap3.put('U', '\u016C'); // fall rise
        accentMap5.put('U', '\u01D9');
    }
    
    // factory method for singleton
    public static AccentTranslator getTranslator() {
        //if(translatorSingleton == null)
        //    translatorSingleton = new Translator();
        return translatorSingleton;
    }
    
    public Character translate(Character prevChar, Character currChar) {
        /** this is the main function of Translator, 
         it takes in a vowel in prevChar and a number in currChar, 
         returning the unicode character which has the mandarin accent.
         If prevChar is not a vowel or currChar is not {1,2,3,4}, it returns null
         
         our map only has mapping for uppercase characters, change to uppercase,
            and change back to lowercase after map if needed
        */
        if(DEBUG_TRANSLATE) {
            System.out.println("prevChar: " + prevChar);
            System.out.println("currChar: " + currChar);
        }
        Character prevCharUpper = Character.toUpperCase(prevChar);
        Character ret = null;
        switch(currChar) {
        case '1':
            ret = accentMap1.get(prevCharUpper);
            break;
        case '2':
            ret = accentMap2.get(prevCharUpper);
            break;
        case '3':
            ret = accentMap3.get(prevCharUpper);
            break;
        case '4':
            ret = accentMap4.get(prevCharUpper);
            break;
        case '5':
            ret = accentMap5.get(prevCharUpper);
            break;
        }
        if(ret != null && Character.isLowerCase(prevChar)) // change back to lowercase if needed
            ret = Character.toLowerCase(ret);
        return ret;
    }
    
    public String translate(String input) {
        if(DEBUG_TRANSLATE) {
            System.out.println("input : " + input);
            System.out.println("length : " + input.length());
        }
        if(input.length() <= 1) 
            return input;
        
        String ret = "";
        for(int i = 1; i < input.length(); i++){
            if(DEBUG_TRANSLATE) {
                System.out.println("i = " + i);
            }
            Character newChar = translate(input.charAt(i-1), input.charAt(i));
            if(newChar == null) {
                ret += input.charAt(i-1);
            
                if(i == (input.length()-1)) { // if the second to last character is a number for accent, 
                            //include the last character without translation
                    ret += input.charAt(i);
                }
                
            }else {
                ret += newChar;
                i++;
               
                if(i == (input.length()-1)) { // if the second to last character is a number for accent, 
                    //include the last character without translation
                    ret += input.charAt(i);
                }
            }
            if(DEBUG_TRANSLATE){
                System.out.println("ret = " + ret);
            }
        }
        return ret;
    }
    
}