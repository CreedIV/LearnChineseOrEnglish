import java.net.URL;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ListenToPane extends MyCenterPane{

    Label[] srcLangPrompt;        
    Label[] dstLangPrompt;        
    MyButton[] playBtn;
    MyButton[] srcPlay;
    int currWord;
    String partialPath;
    
    public ListenToPane(ArrayList<String[]> lessonData, String partialPath, LearnChinese parent) {
    	super(lessonData, "Listen to the translation", parent);
    	this.partialPath = partialPath;
    	
    	String destLangString = (dstLang == LangEnum.MANDARIN) ? "chinese" : "english";
    	String srcLangString = (srcLang == LangEnum.MANDARIN) ? "chinese" : "english";

        
        EventHandler<ActionEvent> ButtonHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                int value = ((MyButton)ae.getTarget()).getValue() + currWord - 5;
            	String filename = lessonData.get(value)[dstLang.ordinal()].replaceAll("[ .?!,]", "") + ".wav";
            	String path = "./lesson" + parent.lessonNum + "/" + destLangString + partialPath + "/" + filename;
            	//System.out.println(path);
            	URL resource = getClass().getResource(path);
                MyUtils.playSoundFile(resource);
            }
        };
        
        EventHandler<ActionEvent> ButtonHandlerSrc = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                int value = ((MyButton)ae.getTarget()).getValue() + currWord - 5;
            	String filename = lessonData.get(value)[srcLang.ordinal()].replaceAll("[ .?!,]", "") + ".wav";
            	String path = "./lesson" + parent.lessonNum + "/" + srcLangString + partialPath + "/" + filename;
            	//System.out.println(path);
            	URL resource = getClass().getResource(path);
                MyUtils.playSoundFile(resource);
            }
        };
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(20);
        srcLangPrompt = new Label[5];
        dstLangPrompt = new Label[5];
        playBtn = new MyButton[5];
        srcPlay = new MyButton[5];
        for(int i = 0; i < 5; i++) {
        	dstLangPrompt[i] = new Label();
        	dstLangPrompt[i].setFont(parent.font);
        	srcLangPrompt[i] = new Label();
        	srcLangPrompt[i].setFont(parent.font);
        	playBtn[i] = new MyButton(i,"Listen");
            playBtn[i].setOnAction(ButtonHandler);
            playBtn[i].setFont(parent.font);
            srcPlay[i] = new MyButton(i,"Listen");
            srcPlay[i].setOnAction(ButtonHandlerSrc);
            srcPlay[i].setFont(parent.font);

            grid.addRow(i, srcPlay[i] ,srcLangPrompt[i], dstLangPrompt[i], playBtn[i]);
        	
        }

        this.vbox.getChildren().add(grid);
        currWord = 0;
        next();
    }
    
    
    public Boolean next() {
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
    	while(i < 5) {
    		dstLangPrompt[i].setText("");
    		srcLangPrompt[i].setText("");
    		
    		if((currWord < lessonData.size())) {
	    		String srcLangText = lessonData.get(currWord)[srcLang.ordinal()];
	    		if(srcLang == LangEnum.MANDARIN) 
	    			srcLangText = AccentTranslator.getTranslator().translate(srcLangText);
	    		srcLangPrompt[i].setText(srcLangText);
	    		
	    		String dstLangText = lessonData.get(currWord)[dstLang.ordinal()];
	    		if(dstLang == LangEnum.MANDARIN) 
	    			dstLangText = AccentTranslator.getTranslator().translate(dstLangText);
	    		dstLangPrompt[i].setText(dstLangText);
    			playBtn[i].setVisible(true);
    		}
    		if(dstLangPrompt[i].getText().equals("")) {
    			playBtn[i].setVisible(false);
    		}
    		currWord++;
    		i++;
    	}    	
    }
}
