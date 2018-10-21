package my_main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {

	//Fields
	private double x;
	private int width , height ;
	
	public final int YPOS = game_main.HEIGHT - 75;
	 
	
	//constructor
	public Paddle() {
	
		width = 120;
		height = 12;
		
		x = game_main.WIDTH / 2 - width / 2;
	}

	//update
	public void update() {
		
	}

	//draw
	public void draw(Graphics2D g) {
		
		g.setColor(Color.RED);
		g.fill3DRect( (int) x, YPOS, width, height,false);
	}

	//for restricting paddle movement to the edges
	public void mouseMoved(int mouseXpos) {
		x = mouseXpos;
			if( x > game_main.WIDTH - width) {
				x = game_main.WIDTH - width;
			}
	}

	public Rectangle getRect() {
		
		return new Rectangle((int) x, YPOS, width, height);
	}

	public int getWidth() { return width; }

}



