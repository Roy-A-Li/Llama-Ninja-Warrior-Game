package llama;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Enemies extends Entity {

	private int sY;
	Random r = new Random();
	
	public Enemies(int x, int y) {
		super(x, y);
		sY = y; //Will not change y coordinate
		
}
	
	public void update() {
		y += 5; //Speed of enemies
		collisionCheck();
		screenBoundaries();
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(getEnemyImg(), x, y, null); //Takes in x and y parameters, uses entity class	
	}
	
	public Image getEnemyImg() { //Gets the enemy images
			ImageIcon ic = new ImageIcon(this.getClass().getResource("/enemy1.gif")); //Enemy gif
			return ic.getImage();
	}
	
	public static void playExplosionSound() { //Same function as documented in Main
		ClassLoader classLoader = Enemies.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("explosion.wav");
		try {
		  Clip clip = AudioSystem.getClip();
		  AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
		  clip.open(ais);
		  clip.start();
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
		  System.err.println("ERROR: Playing sound has failed");
		  e.printStackTrace();
		}
		}
	
	public void collisionCheck() {
		for (int i = 0; i < GameFrame.ShootList().size(); i++) { //Gets shoot array list and its size
			Shoot s = GameFrame.ShootList().get(i);
			
			if (Boundaries().intersects(s.Boundaries())) { //Check for collision with laser
			GameFrame.removeEnemy(this); //Removes enemy hit
			GameFrame.removeShoot(s); //Removes the laser that hits
			playExplosionSound(); //Plays explosion.wav
			
			}
		}
	}
	
	
	public void screenBoundaries() {
		if( y >= 650) { //Checks if enemies are off the screen
			y = -r.nextInt(300) - 15; //Randomizes y coordinate, so they come at random intervals
			x = r.nextInt(555) + 10; //Sets enemy llamas to another random location on the x-axis
		}
		if( x >= 565) { //Checks if enemies are off the screen
			x = r.nextInt(555) + 10;//Sets enemy llamas to another random location on the x-axis
		} else if( x <= 10 ) {
			x = r.nextInt(555) + 10; //Sets enemy llamas to another random location on the x-axis
		}
	}
	
	public Rectangle Boundaries() {
		return new Rectangle(x, y, getEnemyImg().getWidth(null), getEnemyImg().getHeight(null)); //Sets boundary/hitbox of player
	}
}