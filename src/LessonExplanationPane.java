
import javafx.geometry.Insets;
import javafx.scene.text.Text;


public class LessonExplanationPane extends MyCenterPane{
	
    public LessonExplanationPane(LanguageLesson lesson, LearnChinese parent) {
    	super(null, "", parent);
    	
        Text lessonText = new Text(AccentTranslator.getTranslator().translate(lesson.lessonText));
        this.setPadding(new Insets(10,10,10,10));
        
        lessonText.setFont(parent.font);
        lessonText.wrappingWidthProperty().set(parent.WIDTH - 20);
        
        
        
        this.vbox.getChildren().add(lessonText);
        this.getChildren().add(vbox);
        
        this.vbox.getChildren().add(lessonText);

        next();
    }
    
    public Boolean next() {
    	return true;
	}
    
    public Boolean prev() {
    	return true;
    }
    
	public Boolean showAns() {
    	return true;
    }
}
