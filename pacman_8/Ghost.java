package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost {
	private Coordinate coordinate;
	private Color color;
	private Direction direction;
	private Game game;
	private ArrayList<GhostObserver> observers;
	
	public Ghost(Game game, Coordinate coordinate, Color color) {
		this.coordinate=coordinate;
		this.game=game;
		this.color=color;
		observers = new ArrayList<>();
		//randomDirection();
		direction=Direction.Up;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
	
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public void randomDirection() {
		Random rnd = new Random();
		int n = rnd.nextInt(4);
		if (n==0)
			direction = Direction.Up;
		else if (n==1)
			direction = Direction.Down;
		else if (n==2)
			direction = Direction.Left;
		else
			direction = Direction.Right;
	}
	

	
	public void register(GhostObserver o) {
		observers.add(o);
	}
	
	private void notifyObserver(List<GhostEvent> events)
	{
		for(GhostObserver GhostObserver : observers)
			GhostObserver.notifyGhost(events);
	}
	
	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}
	
	public void move()
	{
		int x = coordinate.getX() + direction.getX();
		int y = coordinate.getY() + direction.getY();
		Coordinate  pos = new Coordinate(x, y);
		if(game.getMaze().getSchemaContent(x, y) != -1
				&& game.getMaze().getSchemaContent(x, y) != -2
				&& game.getMaze().getSchemaContent(x, y) != -3
				&& game.getMaze().getSchemaContent(x, y) != -4
				&& game.getMaze().getSchemaContent(x, y) != -5)
		{
			List<GhostEvent> events = new ArrayList<>();
			coordinate = pos;
			events.add(new GhostEvent(pos));
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
		else if (game.getMaze().getSchemaContent(x, y) == -1) {
			randomDirection();
		}
	}
	
	public void draw(Graphics g, int height, int width) {
		g.setColor(color);
		g.fillRect(width*coordinate.getX()/20+2, (height-25)*coordinate.getY()/20+2, 20, 20);
	}

	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color=color;
	}
}
