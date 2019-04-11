package llama;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Shoot extends Entity {
	
	public Shoot(int x, int y) {
		super(x, y);
	}

	public void update() {
		y -= 7; //Speed of the laser shot
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(getShootImg(), x, y, null); //Draws laser image
	}
	
	public Image getShootImg() {
		ImageIcon ic = new ImageIcon(this.getClass().getResource("/laser.png")); //Finds laser image
		return ic.getImage(); //Returns laser image
	}
	
	public Rectangle Boundaries() {
		return new Rectangle(x, y, getShootImg().getWidth(null), getShootImg().getHeight(null)); //Sets boundary/hitbox of laser
	}
	
}
