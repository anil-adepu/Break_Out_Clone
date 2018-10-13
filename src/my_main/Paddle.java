package my_main;

import java.awt.Color;
import java.awt.Graphics2D;

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
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect( (int) x, YPOS, width, height);
	}

	public void mouseMoved(int mouseXpos) {
		x = mouseXpos;
	}
	
}



