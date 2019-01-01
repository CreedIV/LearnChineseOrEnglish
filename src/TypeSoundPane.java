import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class TypeSoundPane extends MyCenterPane{

    Label feedbackPrompt;
    TextField tf;
    int currWord;
    Button playBtn;
    String partialPath;
    
    public TypeSoundPane( ArrayList<String[]> lessonData, String partialPath, LearnChinese parent) {
    	super(lessonData, "Type what is said", parent);
    	this.partialPath = partialPath;

    	// create labels and textfield with fonts
        tf = createTextField();
        feedbackPrompt = new Label();
        playBtn = new Button("Listen");
        tf.setPrefSize(500, USE_COMPUTED_SIZE);
        tf.setFont(parent.font);
        feedbackPrompt.setFont(parent.font);
        playBtn.setFont(parent.font);    
        playBtn.setOnAction((ActionEvent ae)->{playSound();});
        
        feedbackPrompt.setFont(parent.font);
        tf.setFont(parent.font);
        this.vbox.setAlignment(Pos.CENTER_LEFT);

        this.vbox.getChildren().addAll(playBtn, tf, feedbackPrompt);
        
        currWord = -1;
        next();
    }
    

    protected void playSound() {
        MyUtils.playSoundFile( parent.lessonNum, partialPath, lessonData.get(currWord)[dstLang.ordinal()], null);
        tf.requestFocus();
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
        	String userInput = tf.getText();
    		String correctInput = lessonData.get(currWord)[dstLang.ordinal()];
    		if(dstLang == LangEnum.MANDARIN) 
        		correctInput = AccentTranslator.getTranslator().translate(correctInput);
            if(correctInput.toLowerCase().equals(userInput.toLowerCase())) {
            	playSound("right");
                if(!next())
                	parent.nextExercise();
            }else {
            	playSound("wrong");
            	feedbackPrompt.setText("The correct answer is: " + correctInput); 
            }
        });
        return tf;
    }
    
    public Boolean next() {
    	tf.clear();
    	if(currWord < lessonData.size() - 1) {
    		++currWord;
    	}else {
    		return false;
    	}
    	if(parent.centerPanes[parent.currCenterPane] == this) {
    		playSound();
    		tf.requestFocus();
    	}else {
    		playBtn.requestFocus();
    	}
    	return true;
	}
    
    public Boolean prev() {
    	tf.clear();
    	if(currWord > 0) {
    		--currWord;
    	}else {
    		return false;
    	}
    	playSound();
    	tf.requestFocus();
    	return true;
    }

	public Boolean showAns() {
		String correctInput = lessonData.get(currWord)[dstLang.ordinal()];
		if(dstLang == LangEnum.MANDARIN)
    		correctInput = AccentTranslator.getTranslator().translate(correctInput);
		feedbackPrompt.setText(correctInput);
    	tf.requestFocus();
    	return true;
    }
}
