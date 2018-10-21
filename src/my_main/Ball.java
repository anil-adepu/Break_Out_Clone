package my_main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	
	//fields
	private double x, y, dx, dy;
	private int ballSize = 16;
	
	
	
	public Ball() {
		
		x = game_main.WIDTH/2;
		y = game_main.HEIGHT/2;
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
	
	public Rectangle getRect() {
		
		return new Rectangle((int) x, (int) y, ballSize, ballSize);
	}
	
	public double getDY() { return dy;	}	
	
	public void setDY(double theDY) {
		dy = theDY;
	}
	
	public double getDX() { return dx;	}
	
	public void setDX(double theDX) {
		dx = theDX;
	}
	
	public double getX() { return x;	}
	
	public void draw(Graphics2D g) {

		//g.setColor(Color.DARK_GRAY);
		g.setStroke(new BasicStroke(4));
		//g.drawOval((int) x,(int) y,(int) ballSize,(int) ballSize);
		g.setColor(Color.BLUE);
		g.fillOval((int) x,(int) y,(int) ballSize,(int) ballSize);
	} 

	public boolean YouLose() {
		boolean loser = false;
		
		if(y > game_main.HEIGHT - 50)
			loser = true;

	   return loser;
	}

}



