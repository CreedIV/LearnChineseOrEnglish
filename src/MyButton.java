import javafx.scene.control.Button;

class MyButton extends Button{
		private final int value;
		MyButton(int value, String text){
			super(text);
			this.value = value;
		}
		
		MyButton(int value){
			this.value = value;
		}
		
		MyButton(String text){
			super(text);
			this.value = -1;
		}
		
		int getValue() { return value; }
	}