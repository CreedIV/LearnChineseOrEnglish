import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class TypeTranslationPane extends MyCenterPane{

    Label prompt;
    Label feedbackPrompt;
    TextField tf;
    int currWord;
    
    public TypeTranslationPane( ArrayList<String[]> lessonData, LearnChinese parent) {
    	super(lessonData, "Type the translation", parent);

    	// create labels and textfield with fonts
        tf = createTextField();
        prompt = new Label();
        feedbackPrompt = new Label();
        
        prompt.setFont(parent.font);
        feedbackPrompt.setFont(parent.font);
        tf.setFont(parent.font);
        tf.setPrefSize(500, USE_COMPUTED_SIZE);

        this.vbox.setAlignment(Pos.CENTER_LEFT);
        this.vbox.getChildren().addAll(prompt,tf, feedbackPrompt);

        currWord = -1;
        next();
    }
    

    private  TextField createTextField() {
        TextField tf = new TextField();
        tf.setPrefColumnCount(10);
        
        // the textfield will process words you type in order to add accents
        tf.setOnKeyReleased((ke)->{ 
        	if(ke.getCode() != KeyCode.ENTER)
        		feedbackPrompt.setText("");
        	String userInput = tf.getText();
        	int origLen = userInput.length();
            int pos = tf.caretPositionProperty().intValue();
    		if(dstLang == LangEnum.MANDARIN) 
    			userInput = AccentTranslator.getTranslator().translate(userInput);
    		tf.setText(userInput);
    		// if length changed because of translation, assume all changes occur to left of cursor and move cursor to the same relative position as before translation
            tf.positionCaret(pos - (origLen-userInput.length()));
        });
        
        
        // when the user hits return, we check what he typed against the expected translation
        tf.setOnAction((ae)->{ 
        	String userInput = tf.getText().toLowerCase();
    		String correctInput = lessonData.get(currWord)[dstLang.ordinal()].toLowerCase();
    		if(dstLang == LangEnum.MANDARIN) 
        		correctInput = AccentTranslator.getTranslator().translate(correctInput);
            if(correctInput.equals(userInput)) {
            	playSound("right");
                if(!next())
                	parent.nextExercise();
            }else {
            	playSound("wrong");
                int lastmistake = MyUtils.lastMistakePoint(userInput, correctInput);
            	feedbackPrompt.setText("The correct answer is: " + correctInput); 
                tf.selectRange(lastmistake-1, lastmistake);
            }
        });
        return tf;
    }
    
    public Boolean next() {
    	tf.clear();
    	if(currWord < lessonData.size() - 1) {
    		String question = lessonData.get(++currWord)[srcLang.ordinal()];
    		if(srcLang == LangEnum.MANDARIN)
    			question = AccentTranslator.getTranslator().translate(question);
    		prompt.setText(question);
    		//chinesePrompt.setText(AWSTranslate.translate(question));
    	}else {
    		return false;
    	}
    	tf.requestFocus();
    	return true;
	}
    
    public Boolean prev() {
    	tf.clear();
    	if(currWord > 0) {
    		String question = lessonData.get(--currWord)[srcLang.ordinal()];
    		if(srcLang == LangEnum.MANDARIN)
    			question = AccentTranslator.getTranslator().translate(question);
    		prompt.setText(question);
    		//chinesePrompt.setText(AWSTranslate.translate(question));
    	}else {
    		return false;
    	}
    	tf.requestFocus();
    	return true;
    }

	public Boolean showAns() {
		String correctInput = lessonData.get(currWord)[dstLang.ordinal()];
		if(dstLang == LangEnum.MANDARIN)
    		correctInput = AccentTranslator.getTranslator().translate(correctInput);
    	tf.setText(correctInput);
    	tf.requestFocus();
    	return true;
    }
}
