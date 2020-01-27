package pacman;

import java.awt.Color;
import java.awt.Graphics;

public class greenPacgomme extends Pacgomme {
	public greenPacgomme(Coordinate coordinate, Game game) {
		points = 1000;
		color = Color.green;
		this.coordinate = coordinate;
		this.game = game;
	}
	
	public void draw(Graphics g, Coordinate coordinate, int width, int height) {
		// TODO Auto-generated method stub
		g.setColor(color);
		g.fillOval(width*coordinate.getX()/20 +8, height*coordinate.getY()/20 + 8, 12, 12);
	}
}
