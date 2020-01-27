package pacman;

import java.awt.Color;
import java.awt.Graphics;

public class orangePacgomme extends Pacgomme {
	public orangePacgomme(Coordinate coordinate) {
		points = 500;
		color = Color.orange;
		this.coordinate = coordinate;
	}
	
	public void draw(Graphics g, Coordinate coordinate, int width, int height) {
		// TODO Auto-generated method stub
		g.setColor(color);
		g.fillOval(width*coordinate.getX()/20 +8, height*coordinate.getY()/20 + 8, 10, 10);
	}
}
