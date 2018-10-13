package my_main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {

	private double x, y, dx, dy;
	private int ballSize = 16;
	
	
	
	public Ball() {
		
		x = 200;
		y = 200;
		dx = 1;
		dy = 3;
		
	}
	
	public void update() {
		setPosition();
		
	}
	
	public void setPosition() {
		
		x += dx;
		y += dy;
	
		if( x < 0 ) {
			
			dx = -dx;
		}

		if( y < 0 ) {
			
			dy = -dy;
		}

		if(x > game_main.WIDTH - ballSize) {
			
			dx = -dx;
		}
		
		if(y > game_main.HEIGHT - ballSize) {
			
			dy = -dy;
		}
		


	}
	
	public void draw(Graphics2D g) {

		g.setColor(Color.DARK_GRAY);
		g.setStroke(new BasicStroke(4));
		g.drawOval((int) x,(int) y,(int) ballSize,(int) ballSize);
	} 
}


