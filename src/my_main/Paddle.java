package my_main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {

	//Fields
	private double x;
	private int width , height , startWidth, startHeight;
	private long widthTimer;
	//private boolean altWidth;
	
	private double targetx; //for sync b/w pointer & paddle
	public final int YPOS = game_main.HEIGHT - 75;
	 
	
	//constructor
	public Paddle(int theWidth, int theHeight) {
	
		width = theWidth;
		height = theHeight;
		startWidth = theWidth;
		startHeight = height;
		//altWidth = false;

		x = game_main.WIDTH / 2 - width / 2;
	}

	//update
	public void update() {
		
		if((System.nanoTime() - widthTimer) / 1000 > 6000000) {
			width = startWidth;
			//altWidth = false;
		}
			
		x += (targetx - x) * .2;

		int diff = (int) Math.abs(targetx - x) / 5;
		height = startHeight - diff;
		if(height < 2) { height = 2; }
		
	}

	//draw
	public void draw(Graphics2D g) {
		
		int yDraw = YPOS + (startHeight - height) / 2;
		
		g.setColor(Color.CYAN);
		g.fill3DRect( (int) x, yDraw, width, height,false);
		
		/*if(altWidth == true) {
			g.setColor(Color.white);
			g.setFont(new Font("Courier New",Font.BOLD,18));
			g.drawString("Shrinking in " + (6 - ( System.nanoTime() - widthTimer ) / 1000000000), (int)x, YPOS + 18);
		}*/
		
	}

	//for restricting paddle movement to the edges
	public void mouseMoved(int mouseXpos) {
		targetx = mouseXpos - width / 2;
			if( targetx > game_main.WIDTH - width) {
				targetx = game_main.WIDTH - width;
			}
		if(targetx < 0) {
			targetx = 0;
		}
	}

	public Rectangle getRect() {
		
		return new Rectangle((int) x, YPOS, width, height);
	}

	public int getWidth() { return width; }

	public void setWidth( double theWidth) {
		//altWidth = true;
		width = (int) theWidth;
		setWidthTimer();
	}
	
	public void setWidthTimer() {
		widthTimer = System.nanoTime();
	}


	
}



