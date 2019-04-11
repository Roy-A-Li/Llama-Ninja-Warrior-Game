package llama;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Key extends KeyAdapter { //This class exists to simply extend KeyAdapter

	Llama l;
	
	public Key(Llama llama) {
		l = llama;
	}
	
	public void keyPressed(KeyEvent e) {
		l.keyPressed(e); //Self explanatory...Key pressed from Llama l
	}
	
	public void keyReleased(KeyEvent e) {
		l.keyReleased(e); //Self explanatory...Key released from Llama l
	}
}
