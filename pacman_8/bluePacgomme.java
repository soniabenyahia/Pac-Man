package pacman;

import java.awt.Color;
import java.awt.Graphics;

public class bluePacgomme extends Pacgomme {
	public bluePacgomme(Coordinate coordinate) {
		points = 100;
		color = Color.blue;
		this.coordinate = coordinate;
	}
	
	public void draw(Graphics g, Coordinate coordinate, int width, int height) {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
		g.fillOval(width*coordinate.getX()/20 +8, height*coordinate.getY()/20 + 8, 8, 8);
	}
}
