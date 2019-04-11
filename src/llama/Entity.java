package llama;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Entity extends JPanel {

	int x, y;
	
	public Entity(int x, int y) {
		this.x = x; //Differentiates the variables
		this.y = y;
	}
	
	public void update() { //Needed to prevent specific errors
		
	}
	
	public void draw(Graphics2D g2d) { //Needed to prevent specific errors
		
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
