
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.event.*;
import javafx.geometry.*;

public class LearnChinese extends Application {
    
    public static void main(String args[]) {
        launch(args);
    }
    
    BorderPane rootNode;
    MyCenterPane[] centerPanes;
    int currCenterPane = 0;
    Integer lessonNum = 1;
	ProgressBar pbar = new ProgressBar();
	Label exerciseLabel = new Label();

    Font font;
    Font titleFont;
    LangEnum srcLang = LangEnum.ENGLISH;
    LangEnum dstLang = LangEnum.MANDARIN;
    
    static int MaxPanes = 0;
    final double GOLDRATIO = 1.61803;
    final int HEIGHT = 400;
    final int WIDTH = (int) (GOLDRATIO*HEIGHT);


    private static final int MAXLESSON = 8;
    
    // Override the start() method 
    public void start(Stage myStage) {
    	//testSoundAllLessons(); // for testing/checking if all sound files can be found
    	
        myStage.setTitle("Learning Mandarin");
        rootNode = new BorderPane();
        Scene myScene = new Scene(rootNode, WIDTH, HEIGHT);
        myStage.setScene(myScene);
        
        // Add the menu bar to the top of the border pane and
        // the response label to the center position
        MenuBar mb = createMenus();
        font = new Font("Arial", 16);
        titleFont = new Font("Arial", 30);
        
//        loadMelnyksLesson(lessonNum);
        loadLivingLangLesson(lessonNum);

        pbar.setProgress(0);
        Pane bottomPane = createBottomPane();
        
        rootNode.setTop(mb);
        rootNode.setCenter(centerPanes[currCenterPane]);
        rootNode.setBottom(bottomPane);
        
        myStage.show();
    }
    
    private void loadMelnyksLesson(int lessonNum) {
        LanguageLesson currLesson = new MelnykLessons(lessonNum); 
        centerPanes = new MyCenterPane[5];
        centerPanes[0] = new LessonExplanationPane(currLesson, this);
		centerPanes[1] = new ListenToPane(currLesson.lessonWords, "chinese/vocab1", this);
        centerPanes[2] = new MatchPane(currLesson.lessonWords, this);
        centerPanes[3] = new TypeTranslationPane(currLesson.lessonWords, this);
        centerPanes[4] = new TypeSoundPane(currLesson.lessonWords, "chinese/vocab1", this);
    }
    
    
    private void loadLivingLangLesson(int lessonNum) {
    	LanguageLesson currLesson = new LivingLanguageChinese(lessonNum); 
    	int numPanes = 0;
    	String destLangString = (dstLang == LangEnum.MANDARIN) ? "chinese" : "english";
    	
        centerPanes = new MyCenterPane[20];

        if(currLesson.lessonText.length() > 0) {
        	centerPanes[numPanes++] = new LessonExplanationPane(currLesson, this);
        }
        
        if(currLesson.lessonVocab1.size() > 0) {
			centerPanes[numPanes++] = new ListenToPane(currLesson.lessonVocab1, "vocab1", this);
	        centerPanes[numPanes++] = new MatchPane(currLesson.lessonVocab1, this);
	        centerPanes[numPanes++] = new TypeTranslationPane(currLesson.lessonVocab1, this);
	        centerPanes[numPanes++] = new TypeSoundPane(currLesson.lessonVocab1, destLangString + "/vocab1", this);
        }
        
        if(currLesson.grammarBuilder1.size() > 0) {
	        centerPanes[numPanes++] = new TypeTranslationPane(currLesson.grammarBuilder1, this);
	        centerPanes[numPanes++] = new TypeSoundPane(currLesson.grammarBuilder1, destLangString + "/grammarbuilder1", this);
        }
        
        if(currLesson.lessonVocab2.size() > 0) {
			centerPanes[numPanes++] = new ListenToPane(currLesson.lessonVocab2, "vocab2", this);
	        centerPanes[numPanes++] = new MatchPane(currLesson.lessonVocab2, this);
	        centerPanes[numPanes++] = new TypeTranslationPane(currLesson.lessonVocab2, this);
	        centerPanes[numPanes++] = new TypeSoundPane(currLesson.lessonVocab2, destLangString + "/vocab2", this);
        }
        
        if(currLesson.grammarBuilder2.size() > 0) {
	        centerPanes[numPanes++] = new TypeTranslationPane(currLesson.grammarBuilder2, this);
	        centerPanes[numPanes++] = new TypeSoundPane(currLesson.grammarBuilder2, destLangString + "/grammarbuilder2", this);
        }
        
        if(currLesson.lessonWorkOut1.size() > 0) {
	        centerPanes[numPanes++] = new TypeTranslationPane(currLesson.lessonWorkOut1, this);
	        centerPanes[numPanes++] = new TypeSoundPane(currLesson.lessonWorkOut1, destLangString + "/workout1", this);
        }
        
        if(currLesson.lessonWorkOut2.size() > 0) {        
	        centerPanes[numPanes++] = new TypeTranslationPane(currLesson.lessonWorkOut2, this);
	        centerPanes[numPanes++] = new TypeSoundPane(currLesson.lessonWorkOut2, destLangString + "/workout2", this);
        }
        
        MaxPanes = numPanes;
        currCenterPane = 0;
        rootNode.setCenter(centerPanes[currCenterPane]);
    }

    private void testSoundAllLessons() {
    	for(int i = 1; i < MAXLESSON; i++ ) {
    		MyUtils.testSoundFilesLivingLangLesson(i);
    	}
    }
    
	private Pane createBottomPane() {
        FlowPane bottomPane = new FlowPane(10,10);
        bottomPane.setAlignment(Pos.CENTER);
        Button backBtn = new Button("Back");
        Button ansBtn = new Button("Answer");
        Button nextBtn = new Button("Next");
        Button nextExerciseBtn = new Button("Next Exercise");

        bottomPane.getChildren().addAll(pbar, backBtn, ansBtn, nextBtn, nextExerciseBtn);
        
        // note that button actions are determined by current centerPane
        EventHandler<ActionEvent> ButtonHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                String name = ((Button)ae.getTarget()).getText();
                
                // perform the appropriate action for the clicked button
                if(name.equals("Answer")) 
                	centerPanes[currCenterPane].showAns();
                if(name.equals("Next")) 
                	if(!centerPanes[currCenterPane].next()) 
                		nextExercise();
                if(name.equals("Back")) 
                	if(!centerPanes[currCenterPane].prev()) 
                		prevExercise();
                if(name.equals("Next Exercise"))
                	nextExercise();
            }
        };
        ansBtn.setOnAction(ButtonHandler);
        backBtn.setOnAction(ButtonHandler);
        nextBtn.setOnAction(ButtonHandler);
        nextExerciseBtn.setOnAction(ButtonHandler);
        
        return bottomPane;
    }

	public void prevExercise() {
		if(currCenterPane > 0) {			
	        rootNode.setCenter(centerPanes[--currCenterPane]);
	        pbar.setProgress(((double)currCenterPane)/MaxPanes);
		}
	}

	public void nextExercise() {
		if(currCenterPane < MaxPanes - 1) {
	        rootNode.setCenter(centerPanes[++currCenterPane]);
	        pbar.setProgress(((double)currCenterPane)/MaxPanes);
		}else { // I should probably just add another centerPane for this...
			VBox vbox = new VBox();
			pbar.setProgress(1.0);
			Button repeat = new Button("Repeat Lesson");
			Button nextLesson = new Button("Next Lesson");
			repeat.setOnAction((ActionEvent ae)->{
				currCenterPane = -1;
				nextExercise();
			});
			nextLesson.setOnAction((ActionEvent ae)->{
				//currCenterPane = 0;
				if(++lessonNum <= MAXLESSON)
					loadLivingLangLesson(lessonNum);
				currCenterPane = -1;
				nextExercise();

			});
			HBox hbox = new HBox();
			hbox.getChildren().addAll(repeat, nextLesson);
			vbox.getChildren().addAll(new Label("Congradulations! You finished lesson " + lessonNum), hbox);
	        hbox.setAlignment(Pos.CENTER);
	        vbox.setAlignment(Pos.CENTER);
			rootNode.setCenter(vbox);
		}
	}

	private MenuBar createMenus() {
        MenuBar mb = new MenuBar();
        
        // Create the File Menu
        Menu fileMenu = new Menu("_File");
        MenuItem exit = new MenuItem("_Exit");
        
        exit.setAccelerator(KeyCombination.keyCombination("shortcut+X"));
        
        fileMenu.getItems().addAll( exit);
        
        // Add File menu to the menu bar
        mb.getMenus().add(fileMenu);
        
        Menu lessonMenu = new Menu("Lesson");
        for(Integer i = 1; i <= MAXLESSON; i++) {
        	MenuItem lessoni = new MenuItem("Lesson " + i);
        	lessoni.setId(i.toString());
        	lessoni.setOnAction((ActionEvent ae)->
        	{
        		if(!((MenuItem)ae.getTarget()).getId().equals(lessonNum.toString())) {
        			lessonNum = Integer.parseInt(((MenuItem)ae.getTarget()).getId());
    				loadLivingLangLesson(lessonNum);
        		}
        	});
        	lessonMenu.getItems().add(lessoni);
        }
        mb.getMenus().add(lessonMenu);
        
        Menu languageMenu = new Menu("Language");
        MenuItem english = new MenuItem("Learn English");
        MenuItem chinese = new MenuItem("Learn Mandarin");
        languageMenu.getItems().addAll(english, chinese);
        english.setOnAction((ActionEvent ae) -> 
	        {
	        	if(dstLang != LangEnum.ENGLISH) {
	        		dstLang = LangEnum.ENGLISH;
			        srcLang = LangEnum.MANDARIN;
					loadLivingLangLesson(lessonNum);
	        	}
	        });
        chinese.setOnAction((ActionEvent ae) -> 
        {
        	if(dstLang != LangEnum.MANDARIN) {
        		dstLang = LangEnum.MANDARIN;
		        srcLang = LangEnum.ENGLISH;
				loadLivingLangLesson(lessonNum);
        	}
        });
        mb.getMenus().add(languageMenu);
        
        // Create the Help menu.
        Menu helpMenu = new Menu("_Help");
        Menu accents = new Menu("Accents");
        MenuItem a1 = new MenuItem(AccentTranslator.getTranslator().translate("for a1, e1, i1, o1, u1 type") + " : vowel + 1");
        MenuItem a2 = new MenuItem(AccentTranslator.getTranslator().translate("for a2, e2, i2, o2, u2 type") + " : vowel + 2");
        MenuItem a3 = new MenuItem(AccentTranslator.getTranslator().translate("for a3, e3, i3, o3, u3 type") + " : vowel + 3");
        MenuItem a4 = new MenuItem(AccentTranslator.getTranslator().translate("for a4, e4, i4, o4, u4 type") + " : vowel + 4");
        MenuItem a5 = new MenuItem(AccentTranslator.getTranslator().translate("for u5 type") + " : u + 5");
        
        accents.getItems().addAll(a1,a2,a3,a4,a5);
        helpMenu.getItems().add(accents);
        mb.getMenus().add(helpMenu);
        
        // Create one event handler that will handle menu action events.
        EventHandler<ActionEvent> MEHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                String name = ((MenuItem)ae.getTarget()).getText();
                
                // If Exit is choosen, the program is terminated
                if(name.equals("Exit")) Platform.exit();  
            }
        };
        
        exit.setOnAction(MEHandler);
        accents.setOnAction(MEHandler);
        return mb;
    }

	public void stop() {
        System.out.println("Stop called");
    }
}