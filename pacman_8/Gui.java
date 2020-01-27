package pacman;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JFrame;

import pacman.Pacman.State;



public class Gui extends JFrame {
	private Game game;
	private PacmanListener mylistener;
	
	public Gui() {
		game = new Game(525,500,new Coordinate(1,1), this);
		mylistener= new PacmanListener(game);
		game.getPacman().register(game.getMaze());
		game.getGhost1().register(game.getMaze());
		game.getGhost2().register(game.getMaze());
		game.getGhost3().register(game.getMaze());
		game.getGhost4().register(game.getMaze());
		this.setContentPane(game.getMaze());
		this.setSize(game.getWidth(), game.getHeight());
		this.setLocationRelativeTo(null);
		this.setBackground(Color.GREEN);
		this.setResizable(false);
		this.setTitle("Pacman v0.1");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setBackground(Color.black);
		this.addKeyListener(mylistener);
		while(game.getCompteur().getAlive() == true)
		{
			game.step(); 
			int i=0;
			
			try {
		        Thread.sleep(180);
			} catch (InterruptedException e) {
		        e.printStackTrace();
			}
		}
	}
	
	
}
