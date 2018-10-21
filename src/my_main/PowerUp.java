package my_main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PowerUp {

	//fields
	private int x, y, dy, type, width, height;
	
	private boolean isOnScreen;
	
	private Color color;
	
	public final static int WIDEPADDLE = 4;
	public final static int FASTBALL = 5;
	public final static Color WIDECOLOR = Color.CYAN;
	public final static Color FASTCOLOR = Color.yellow;

	//constructor
	public PowerUp(int xStart, int yStart, int theType, int theWidth, int theHeight) {
		
		x = xStart;
		y = yStart;
		type = theType;
		width = theWidth;
		height = theHeight;
		
		if(type < 4)	type = 4;
		if(type > 5)	type = 5;
		
		if(type == WIDEPADDLE)	color = WIDECOLOR;
		if(type == FASTBALL)	color = FASTCOLOR;
		
		dy = (int) (Math.random() * 6 + 1);
		
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(color);
	}

	public void update() {
		y += dy;
		
		if(y > game_main.HEIGHT)
			isOnScreen = false;
	}
	
	public int getX() { return x; }

	public void setX(int newX) { x = newX; }

	public int getY() { return y; }

	public void setY(int newY) { y = newY; }

	public int getDY() { return dy; }

	public void setDY(int dy) { this.dy = dy; }

	public int getType() { return type; }
	
	public boolean getIsOnScreen() { return isOnScreen; }
	
	public void setIsOnScreen(boolean onScreen) { isOnScreen = onScreen; }

	public Rectangle getRect() {
		
		return new Rectangle(x, y, width, height);
	}






}
