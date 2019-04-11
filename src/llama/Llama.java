package llama;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import sun.audio.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Llama extends Entity {
	
	int vX = 0; //Int to set v equal to 0
	int speed = 4; //Speed of player
	
	public Llama(int x, int y) {
	super(x, y); //Utilizes super class
	update(); //From Entity class
	}
	
	public void update(){
		x += vX; //For movement...adds updated x and y values to recalibrate position
		collisionCheck(); //Updates collision check to see if you have hit an enemy
		screenBoundaries(); //Updates boundaries of screen so you and enemies cannot go off screen

	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(getPlayerImg(), x, y, null); //Draw image and calls getPlayerImg() method. Null b/c there's no observer
		//g2d.draw(Boundaries());
	}
	
	public Image getPlayerImg() {
		
		ImageIcon ic = new ImageIcon(this.getClass().getResource("/llama.gif")); //Image of player
		return ic.getImage(); //Returns the image of player
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); //Basic java function to track key input
		if (key == KeyEvent.VK_A) { //If A is pressed
			vX = -speed; //Set velocity equal to negative direction
		} else if (key == KeyEvent.VK_D) { //If D is pressed
			vX = speed; //Set velocity equal to positive direction
		}
	}


	public static void playLaserSound() { //Same function as documented in Main
		ClassLoader classLoader = Llama.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("laser.wav");
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
	
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {
			vX = 0; //Velocity stops when A is released
		} else if (key == KeyEvent.VK_D) {
			vX = 0; //Velocity stops when D is released
		} else if (key == KeyEvent.VK_SPACE) { //Put in keyReleased so the user cannot hold down the space bar
			playLaserSound(); //Plays laser.wav sound effect
			GameFrame.addShoot(new Shoot(x, y)); //Shoots laser programmed in game frame when space is released
			}
	}
	
	public void collisionCheck() {
		ArrayList<Enemies> enemies = GameFrame.EnemyList(); //Sets variable to enemies array list in GameFrame
		for (int i = 0; i < enemies.size(); i++) {
			Enemies tEnemy = enemies.get(i);
			if (Boundaries().intersects(tEnemy.Boundaries())) { //Makes it easier to check for collisions. If hitboxes intersect, an action will occur.
				JOptionPane.showMessageDialog(null, "YOU DIED! " + "LEVELS LASTED: " + ( GameFrame.level - 1 )); //Game over message
				System.exit(0); //Game exits
			}
			
			
		}
		
	}
	
	public void screenBoundaries() {
		if( x >= 565) { //Checks if you are off the screen
			x = 565; //Blocks you from passing coordinate 565
		} else if ( x <= 5 ) { //Sets boundaries for the left side of the screen
			x = 5; //Blocks you from passing coordinate 5 in the negative direction
		}
	}
	
	public Rectangle Boundaries() {
		return new Rectangle(x, y, getPlayerImg().getWidth(null), getPlayerImg().getHeight(null)); //Sets boundary/hitbox of player
	}
	

}