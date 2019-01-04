import java.net.URL;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class MyCenterPane extends FlowPane{

	LearnChinese parent;
	Label title = new Label();
	VBox vbox = new VBox();
	ArrayList<String[]> lessonData;
	LangEnum srcLang;
    LangEnum dstLang;
    
    public void loadLesson(ArrayList<String[]> lessonData) {
    	this.lessonData = lessonData;
    }
    
    protected void playSound(String rightOrWrong) {
    	String filename = "./audio/" + rightOrWrong + ".mp3";
    	//System.out.println(filename);
    	URL resource = getClass().getResource(filename);
        MyUtils.playSoundFile(resource);
	}
    
    
    public MyCenterPane(ArrayList<String[]> lessonData, String titleString, LearnChinese parent) {
    	super(10,10);
    	
    	title.setText(titleString);
    	title.setFont(parent.titleFont);
    	title.setPadding(new Insets(20,20,20,20));
        title.setAlignment(Pos.CENTER);
        
    	this.parent = parent;
    	this.lessonData = lessonData;
    	srcLang = parent.srcLang;
        dstLang = parent.dstLang;
        
        this.getChildren().add(title);
        this.setVgap(50);
        this.setAlignment(Pos.TOP_CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.getChildren().add(vbox);
    }
    
    public Boolean next() {
    	return false;
	}
    
    public Boolean prev() {
    	return false;
    }
    
    public Boolean showAns() {
    	return false;
    }
}
