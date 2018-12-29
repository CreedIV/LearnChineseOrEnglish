import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;

public class MatchPane extends MyCenterPane{
   
    MyButton[] srcButtons;  // i decided to had src and dst buttons to make logic easier to follow as expense of a little repeated code
    MyButton[] dstButtons;
    int currWord;
    int numCorrect = 0;
    int numToMatch = 0;
    int srcButtonPressed = -1;
    int dstButtonPressed = -1;
    

    public MatchPane(ArrayList<String[]> lessonData, LearnChinese parent) {
    	super(lessonData, "Match the translations", parent);
        
    	
        EventHandler<ActionEvent> srcButtonHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
            	int btnValue = ((MyButton)ae.getTarget()).getValue();
                if(dstButtonPressed == -1) { // there is no dst button to compare against, just record src button
                	if(srcButtonPressed != -1) // if another src button was pressed reenable it, and disable new button
                		srcButtons[srcButtonPressed].setEffect(null);
                	srcButtonPressed = btnValue;
                	srcButtons[srcButtonPressed].setEffect(new DropShadow()); 
                }else { // a dst button is currently pressed, check if the two selections match
                	if(dstButtonPressed == btnValue) { // they match: clear buttonPress, disable both, and add one to correct total
                    	srcButtons[btnValue].setDisable(true); 
                    	dstButtons[dstButtonPressed].setDisable(true); 
                    	dstButtons[dstButtonPressed].setEffect(null);
                    	numCorrect++;
                    	playSound("right");
                    	dstButtonPressed = -1;
                    	srcButtonPressed = -1;
                    	if(numCorrect == numToMatch) {
                    		next();
                    	}
                	}else { // they dont match, clear both so show wrong answer
                    	dstButtons[dstButtonPressed].setDisable(false); 
                    	dstButtons[dstButtonPressed].setEffect(null);
                    	playSound("wrong");
                    	dstButtonPressed = -1;
                    	srcButtonPressed = -1;
                	}
                }
            }
        };
        
        EventHandler<ActionEvent> dstButtonHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
            	int btnValue = ((MyButton)ae.getTarget()).getValue();
                if(srcButtonPressed == -1) { // there is no src button to compare against, just record dst button
                	if(dstButtonPressed != -1) // if another dst button was pressed reenable it, and disable new button
                		dstButtons[dstButtonPressed].setEffect(null);
                	dstButtonPressed = btnValue;
                	dstButtons[dstButtonPressed].setEffect(new DropShadow()); 
                }else { // a src button is currently pressed, check if the two selections match
                	if(srcButtonPressed == btnValue) { // they match: clear buttonPress, disable both, and add one to correct total
                    	dstButtons[btnValue].setDisable(true); 
                    	srcButtons[srcButtonPressed].setDisable(true); 
                    	srcButtons[srcButtonPressed].setEffect(null);
                    	numCorrect++;
                    	playSound("right");
                    	dstButtonPressed = -1;
                    	srcButtonPressed = -1;
                    	if(numCorrect == numToMatch) {
                    		next();
                    	}
                	}else { // they dont match, clear both so show wrong answer
                    	srcButtons[srcButtonPressed].setDisable(false);
                    	srcButtons[srcButtonPressed].setEffect(null);
                    	playSound("wrong");
                    	dstButtonPressed = -1;
                    	srcButtonPressed = -1;
                	}
                }
            }
        };
        
        GridPane grid = new GridPane();
        
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(20);
        
        srcButtons = new MyButton[5];
        dstButtons = new MyButton[5];
        for(int i = 0; i < 5; i++) {
        	srcButtons[i] = new MyButton(i);
        	dstButtons[i] = new MyButton(i);
        	srcButtons[i].setFont(parent.font);
        	dstButtons[i].setFont(parent.font);
        	srcButtons[i].setStyle("-fx-focus-traversable: false;");
        	dstButtons[i].setStyle("-fx-focus-traversable: false;");
        	srcButtons[i].setOnAction(srcButtonHandler);
        	dstButtons[i].setOnAction(dstButtonHandler);
        }
        
        // shuffle randomly and add to tile
		List<Integer> srcList = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4));
		List<Integer> dstList = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4));
		MyUtils.shuffle(srcList);
		MyUtils.shuffle(dstList);

    	for(int i = 0; i < 5; i++)
    		grid.addRow(i,srcButtons[srcList.get(i)], dstButtons[dstList.get(i)]);

        this.vbox.getChildren().add(grid);

        currWord = 0;
        next();
    }
    
    
    public Boolean next() {
    	numCorrect = 0;
    	if((currWord < lessonData.size() - 1) ) {
    		load5words();
    		return true;
    	}else
    		return false;
	}
    
    public Boolean prev() {
    	if(currWord > 5) {
    		currWord -= 10;
    	}else
    		return false;
    	load5words();
    	return true;
    }
    
    private void load5words() {
    	int i = 0;
    	numToMatch = 0;
    	while(i < 5) {
    		srcButtons[i].setText("");
    		dstButtons[i].setText("");
    		
    		
    		if((currWord < lessonData.size())) {
    			numToMatch++;
	    		String dstWord = lessonData.get(currWord)[dstLang.ordinal()];
	    		if(dstLang == LangEnum.MANDARIN) 
	        		dstWord = AccentTranslator.getTranslator().translate(dstWord);
	    		dstButtons[i].setText(dstWord);
	    		dstButtons[i].setVisible(true);
	    		dstButtons[i].setDisable(false);
	    		
	    		String srcWord = lessonData.get(currWord)[srcLang.ordinal()];
	    		if(srcLang == LangEnum.MANDARIN) 
	        		srcWord = AccentTranslator.getTranslator().translate(srcWord);
	    		srcButtons[i].setText(srcWord);
	    		srcButtons[i].setVisible(true);
	    		srcButtons[i].setDisable(false);
    		}
    		if(srcButtons[i].getText().equals("")) {
    			srcButtons[i].setVisible(false);
    			dstButtons[i].setVisible(false);
    		}
    		currWord++;
    		i++;
    	}    	
    }
    
	public Boolean showAns() {
		if(srcButtonPressed != -1) {
			dstButtons[srcButtonPressed].requestFocus();
		}else if(dstButtonPressed != -1) {
			srcButtons[dstButtonPressed].requestFocus();
		}
    	return true;
    }
}
