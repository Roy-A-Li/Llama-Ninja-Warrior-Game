package llama;

import sun.audio.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.applet.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame {
	
	public static void main(String[] args) {
		playBackgroundMusic(); //Background music
		JOptionPane.showMessageDialog(null, "The universe is tearing apart and all species have become cannibals, except you hero! It is your duty to eliminate all other llamas with your laser vision power or die trying.");
		JOptionPane.showMessageDialog(null, "HOW TO PLAY: Move left and right with 'A' and 'D'. Shoot lasers with the space bar.");
		JOptionPane.showMessageDialog(null, "There is a set amount of llamas every level, those you miss will reappear at the top, but don't get hit!");
		JOptionPane.showMessageDialog(null, "Every level completed will add an increasing number to your score. Shoot all llamas and don't get hit. Llamas will increase in number every level.");
		JOptionPane.showMessageDialog(null, "LEVEL 10 IS THE FINAL LEVEL.");
		JFrame frame = new JFrame("Llama Ninja Warrior");
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Allows closing of the app
		frame.setResizable(false); //Cannot resize
		frame.add(new GameFrame());
		frame.setVisible(true);

}
	
	public static void playBackgroundMusic() {
		ClassLoader classLoader = Main.class.getClassLoader(); //Needed get resource and location
		InputStream inputStream = classLoader.getResourceAsStream("Megalovania.wav"); //Locates file
		try {
		  Clip clip = AudioSystem.getClip();
		  AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
		  clip.open(ais);
		  clip.loop(Clip.LOOP_CONTINUOUSLY); //Loops continuously
		  clip.start();
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
		  System.err.println("ERROR: Playing sound has failed"); //Tells if error occurs in the console
		  e.printStackTrace();
		}
		}
	
	}
	
	

