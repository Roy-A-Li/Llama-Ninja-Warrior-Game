package llama;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFrame extends JPanel implements ActionListener {

	Timer mainTimer;
	Llama llama;
		
	int enemyNum = 5; //Base number of enemies (in level 1)
	public static int level = 1; //Beginning level
	int score = 0; //Beginning score
	
	
	static ArrayList<Enemies> enemies = new ArrayList<Enemies>(); //Holds enemy classes
	static ArrayList<Shoot> shoot = new ArrayList<Shoot>(); //Needed to allow for collision to occur with the enemies
	Random r = new Random(); //Allows for random spawning of enemies
	
	public GameFrame() {
		setFocusable(true); //Set to true to gain focus if requested to do so
		
		llama = new Llama(300, 500); //Sets the location of the player llama
		addKeyListener(new Key(llama)); //Key listener implemented for basic functions

		
		mainTimer = new Timer(10, this); //Timer used to update the game every 10 ms
		mainTimer.start(); //Starts the timer
		
		gameStart(); //Calls gameStart function
		
	}
	
	public void paint(Graphics g) {
		super.paint(g); //Part of Java Swing JPanel
		Graphics2D g2d = (Graphics2D) g; //Used to illustrate graphics
				
		ImageIcon ic = new ImageIcon(this.getClass().getResource("/bg.gif")); //Background gif
		g2d.drawImage(ic.getImage(), 0, 0, null); //Gets image from icon, then begins drawing at top left corner of screen
		
		llama.draw(g2d); //Llama
		
		Font myFont = new Font ("Century Gothic", 1, 20);
		Font subFont = new Font ("Century Gothic", 1, 12);
		g2d.setFont(myFont);
		g2d.drawString("Score: " + score, 10, 30); //Score
		g2d.setFont(myFont);
		g2d.drawString("Level: " + level, 10, 50);//Level
		g2d.setFont(subFont);
		g2d.drawString("Complete level to earn score!", 410, 20);//Tip
		
		for (int i = 0; i < enemies.size(); i++) { //Runs based on size of enemies array list
			Enemies tEnemy = enemies.get(i); //Gets location of value i the array list
			tEnemy.draw(g2d); //Enemies
		}
		
		for (int i = 0; i < shoot.size(); i++) {
			Shoot s = shoot.get(i); //Function for lasers
			s.draw(g2d); //Displays laser image
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
				
		llama.update(); //Updates llama functions
		
		for (int i = 0; i < enemies.size(); i++) { //Checks enemy array list
			Enemies tEnemy = enemies.get(i); //Must be under enemies.size
			tEnemy.update();
		}
		
		for (int i = 0; i < shoot.size(); i++) { //Similar to enemy
			Shoot s = shoot.get(i); //Used to update and verify the shoot function
			s.update();
		}
				
		end(); //Calls end method
		
		repaint(); //Calls repaint method
		
		if(level == 11) { //End game, if you defeat level 10...the final level
			JOptionPane.showMessageDialog(null, "YOU HAVE ELIMINATED THE LLAMA SPECIES! YOU'RE SAFE FROM CANNIBALISM, CONGRATS!"); //Confirmation message after level complete
			System.exit(0); //Exits application
		}
	}
	
	public static void addEnemies(Enemies e) { //Simple function to add enemies
		enemies.add(e);
	}
	
	public static void removeEnemy(Enemies e) { //Simple function to remove enemies
		enemies.remove(e);
	}
	
	public static ArrayList<Enemies> EnemyList() { //Use array list because we returned enemies
		return enemies; //Returns the enemy array list
	}
	
	public static void addShoot(Shoot s) { //Simple function to add lasers
		shoot.add(s);
	}
	
	public static void removeShoot(Shoot s) { //Simple function to remove lasers
		shoot.remove(s);
	}
	
	public static ArrayList<Shoot> ShootList() { //Use array list because we returned enemies
		return shoot; //Returns the enemy array list
	}
	
	public void gameStart() {
		enemyNum = level * 5;
		
		for (int i = 0; i < enemyNum; i++) {
			addEnemies(new Enemies(r.nextInt(600), -15 + -r.nextInt(600))); //Runs command enemyNum amount of times and adds a new enemy to array list between dimensions of GameFrame
		}
	}
	
	public void gameRetry() {
		enemyNum = 5;
		level = 1;
		
		for (int i = 0; i < enemyNum; i++) {
			addEnemies(new Enemies(r.nextInt(600), -15 + -r.nextInt(600))); //Runs command enemyNum amount of times and adds a new enemy to array list between dimensions of GameFrame
		}
	}
	
	public void end() {
		if (enemies.size() == 0) { //Checks if enemies are all destroyed
			level++; //Increases level, which increases enemy number
			enemies.clear(); //Clears enemies from the array
			shoot.clear(); //Clears lasers from the array
			JOptionPane.showMessageDialog(null, "LEVEL " + (level - 1) + " COMPLETE!"); //Confirmation message after level complete
			score = score + enemyNum; //Updates score every level completed
			gameStart(); //Calls gameStart() method
		}
	}	
}
