package pacman;

import java.awt.Color;

import javax.swing.JOptionPane;

import pacman.Pacman.State;

public class Game {
	private Pacman pacman;
	private Maze maze;
	private Compteur compteur;
	private int height;
	private int width;
	private Gui gui;
	private Ghost ghost1;
	private Ghost ghost2;
	private Ghost ghost3;
	private Ghost ghost4;
	
	public Game(int height, int width, Coordinate coordinate, Gui gui) {
		pacman = new Pacman(this, new Coordinate(1,1));
		ghost1 = new Ghost(this, new Coordinate(1,10), Color.red);
		ghost2 = new Ghost(this, new Coordinate(1,18), Color.white);
		ghost3 = new Ghost(this, new Coordinate(18,16), Color.magenta);
		ghost4 = new Ghost(this, new Coordinate(18,1), Color.cyan);
		maze = new Maze(this);
		compteur = new Compteur();
		this.height=height;
		this.width=width;
		this.gui=gui;
	}

	public int getHeight() {
		return height;
	}
	
	public Ghost getGhost1() {
		return ghost1;
	}
	public Ghost getGhost2() {
		return ghost2;
	}
	public Ghost getGhost3() {
		return ghost3;
	}
	public Ghost getGhost4() {
		return ghost4;
	}
	
	public void gameover() {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, "Game over ! \n Start a new game ?", "Game over !!", dialogButton);
		if(dialogResult == 0) {
			gui.setVisible(false);
			gui.dispose();
			gui = new Gui();
		} else {
		  System.exit(0);
		} 
	  }
	
	public int getWidth() {
		return width;
	}
	
	public Pacman getPacman() {
		return this.pacman;
	}

	public Maze getMaze() {
		// TODO Auto-generated method stub
		return maze;
	}

	public void step() {
		// TODO Auto-generated method stub
		ghost1.move();
		ghost2.move();
		ghost3.move();
		ghost4.move();
		pacman.move();
		System.out.println(compteur.getDeaths() + " - " + compteur.getLives());
	}
	
	public Compteur getCompteur() {
		return compteur;
	}
	
	
	
}
