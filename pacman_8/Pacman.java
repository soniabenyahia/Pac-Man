package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class Pacman {
	
	private Coordinate coordinate;
	private Direction direction;
	private Game game;
	//private Compteur compteur;
	private ArrayList<PacmanObserver> observers;
	private Color color;
	private State state;
	
	public enum State{
		Default, Super, Invisible
	}
	
	public Pacman (Game game, Coordinate coordinate) {
		observers = new ArrayList<>();
		this.game = game;
		//this.compteur = new Compteur();
		this.coordinate = coordinate;
		this.direction=Direction.Right;
		//this.compteur.setAlive(true);
		this.color = Color.yellow;	
		this.state = State.Default;
	}
	
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate=coordinate;
	}
	
	public void setColor(Color color) {
		this.color=color;
	}
	
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
	
	public void register(PacmanObserver o) {
		observers.add(o);
	}
	
//	public Compteur getCompteur() {
//		return compteur;
//	}
	
	private void notifyObserver(List<PacmanEvent> events)
	{
		for(PacmanObserver pacmanObserver : observers)
			pacmanObserver.notify(events);
	}
	
	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}
	
	public void move()
	{
		int x = coordinate.getX() + direction.getX();
		int y = coordinate.getY() + direction.getY();
		Coordinate  c = new Coordinate(x, y);
		if(game.getMaze().getSchemaContent(x, y) != -1
				&& game.getMaze().getSchemaContent(x, y) != -2
				&& game.getMaze().getSchemaContent(x, y) != -3
				&& game.getMaze().getSchemaContent(x, y) != -4
				&& game.getMaze().getSchemaContent(x, y) != -5)
		{
			List<PacmanEvent> events = new ArrayList<>();
			coordinate = c;
			events.add(new PacmanEvent(c));
			notifyObserver(events);
		}
		else if (game.getMaze().getSchemaContent(x, y) == -2) {
			this.setCoordinate(new Coordinate(19,3));
		}
		else if (game.getMaze().getSchemaContent(x, y) == -3) {
			this.setCoordinate(new Coordinate(0,5));
		}
		else if (game.getMaze().getSchemaContent(x, y) == -4) {
			this.setCoordinate(new Coordinate(12,19));
		}
		else if (game.getMaze().getSchemaContent(x, y) == -5) {
			this.setCoordinate(new Coordinate(1,0));
		}
		game.getCompteur().updateLives();
		//System.out.println(coordinate.getX() + " - " + coordinate.getY() + " *--------------* " + game.getGhost1().getCoordinate().getX() + " - " + game.getGhost1().getCoordinate().getY());
		//System.out.println("Pacman: "+ getDirection() + " - Ghost: "+ game.getGhost4().getDirection());
		if (collision(game.getGhost1()) || collision(game.getGhost2()) || collision(game.getGhost3()) || collision(game.getGhost4()))
		{
			if (state == State.Default) {
				try {
			        Thread.sleep(3000);
				} catch (InterruptedException e) {
			        e.printStackTrace();
				}
				game.getCompteur().incDeaths();
				coordinate.setCoordinate(1, 1);
				game.getGhost1().getCoordinate().setCoordinate(1, 10);
				game.getGhost2().getCoordinate().setCoordinate(1, 18);
				game.getGhost3().getCoordinate().setCoordinate(18, 16);
				game.getGhost4().getCoordinate().setCoordinate(18, 1);
			}
			else if (state == State.Super) {
				if (collision(game.getGhost1()))
					game.getGhost1().getCoordinate().setCoordinate(1, 10);
				if (collision(game.getGhost2()))
					game.getGhost2().getCoordinate().setCoordinate(1, 18);
				if (collision(game.getGhost3()))
					game.getGhost3().getCoordinate().setCoordinate(18, 16);
				if (collision(game.getGhost4()))
					game.getGhost4().getCoordinate().setCoordinate(18, 1);
			}
		}
	}
	
	public State getState() {
		return state;
	}
	
	public boolean collision(Ghost ghost) {
		if (coordinate.equal(ghost.getCoordinate())) {
			if (game.getCompteur().getLives() == 0)
				game.gameover();
			return true;
		}
			
		if ((coordinate.getX() == ghost.getCoordinate().getX() + ghost.getDirection().getX()) && (coordinate.getY() == ghost.getCoordinate().getY() + ghost.getDirection().getY())){
			if (game.getCompteur().getLives() == 0)
				game.gameover();
			return true;
		}
		if ((coordinate.getX()+ direction.getX() == ghost.getCoordinate().getX()) && (coordinate.getY()+ direction.getY() == ghost.getCoordinate().getY())){
			if (game.getCompteur().getLives() == 0)
				game.gameover();
			return true;
		}
		
		return false;
	}
	
	public void setInvisible() {
		setDefault();
		this.state = State.Invisible;
		this.color = new Color(255, 255, 153);
	}
	
	public void setSuper() {
		setDefault();
		this.state = State.Super;
		this.color = Color.orange;
		game.getGhost1().setColor(Color.blue);
		game.getGhost2().setColor(Color.blue);
		game.getGhost3().setColor(Color.blue);
		game.getGhost4().setColor(Color.blue);
	}
	
	public void setDefault() {
		this.state = State.Default;
		game.getGhost1().setColor(Color.red);
		game.getGhost2().setColor(Color.white);
		game.getGhost3().setColor(Color.magenta);
		game.getGhost4().setColor(Color.cyan);
		this.color = Color.yellow;
	}
	
	public void draw(Graphics g, int height, int width) {
		g.setColor(color);
		g.fillOval(width*coordinate.getX()/20+2, (height-25)*coordinate.getY()/20+2, 20, 20);
	}
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}
}