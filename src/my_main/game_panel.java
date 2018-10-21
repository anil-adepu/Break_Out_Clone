
package my_main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
//import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import java.awt.Graphics;
//import java.awt.event.*;


public class game_panel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;

	//Fields
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private MyMouseMotionListener theMouseListener;
	
	private int mousex;
	
	//entities
	private Ball theBall;
	private Paddle thePaddle;
	private Map theMap;
	private HUD theHud;
//private ArrayList<PowerUp> powerUps;
		
	//constructor
	public game_panel() {
	
		init();

	}

public void init() {
	mousex = 0;
	theBall = new Ball();
	thePaddle = new Paddle();
	theMap = new Map(3,4);
	theHud = new HUD();
	theMouseListener = new MyMouseMotionListener();
	//powerUps = new ArrayList<PowerUp>();
	
	addMouseMotionListener(theMouseListener);
	
	running = true;
	image = new BufferedImage(game_main.WIDTH, game_main.HEIGHT, BufferedImage.TYPE_INT_BGR);
	
	g =  (Graphics2D) image.getGraphics();
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
}

	@Override
	public void run() {
		// this is the main game loop called by the thread in game_main class over and over
		//Game loop
		
/*	try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		while(running == true) {

			//update
			update();
			
			// render or draw
			draw();
			
			//display
			repaint();
			
			try {
				Thread.sleep(12);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

public void checkCollisions() {
	Rectangle ballRect = theBall.getRect();
	Rectangle paddleRect = thePaddle.getRect();
	
	if(ballRect.intersects(paddleRect)) {
		theBall.setDY(-theBall.getDY());
	
		if(theBall.getX() <  mousex + thePaddle.getWidth() / 4) {
			theBall.setDX(theBall.getDX() -  1.5);
		}

		if(theBall.getX() <  mousex + thePaddle.getWidth() && theBall.getX() >  mousex + thePaddle.getWidth() / 4) {
			theBall.setDX(theBall.getDX() +  1.5 );
        //		theBall.setDX(-theBall.getDX());
		}
	}

	A: for( int row = 0; row < theMap.getMapArray().length; row++ ) {
		for( int col = 0; col < theMap.getMapArray()[0].length; col++ ) {
			
			if(theMap.getMapArray()[row][col] > 0) {
				int brickx = col * theMap.getBrickWidth() + theMap.HOR_PAD;
				int bricky = row * theMap.getBrickHeight() + theMap.VERT_PAD;
				int brickWidth = theMap.getBrickWidth();
				int brickHeight = theMap.getBrickHeight();
			
				Rectangle brickRect = new Rectangle(brickx, bricky, brickWidth, brickHeight);
				
				if(ballRect.intersects(brickRect))  {
					theMap.setBrick(row, col, 0);
					//theMap.hitBrick(row, col);
					
					theBall.setDY(-theBall.getDY());
//					if(theMap.getMapArray()[row][col] == 0)
						theHud.addScore(50);
					break A;
				}
			}
		}
	}
}

//theMap.checkMap();

public void update() {
	
	checkCollisions();
	theBall.update();
}
	
private void draw() {
	// draws the background of the image in g object 
	g.setColor(Color.BLACK);
	
	g.fillRect(0, 0, game_main.WIDTH, game_main.HEIGHT);
	
	theBall.draw(g);
	
	thePaddle.draw(g);

	theMap.draw(g);
		
	theHud.draw(g);
	
	if(theMap.checkWin() == true) {
		printWin();
		running = false;
	}	

	if(theBall.YouLose() == true) {
		running = false;
		printLose();
	}
}

	private void printWin() {
		g.setColor(Color.ORANGE);
		g.setFont(new Font("Courier New",Font.BOLD,30));
		g.drawString("YOU WIN !!", 310 , 270);
	}

	private void printLose() {
		g.setColor(Color.RED);
		g.setFont(new Font("Courier New",Font.BOLD,30));
		g.drawString("YOU LOSE :(", 310 , 270);
	}

	//paintComponent is an inbuilt fn. which is overridden here
public void paintComponent(Graphics g) {

	Graphics2D g2 = (Graphics2D) g;
	
	g2.drawImage(image, 0, 0, game_main.WIDTH, game_main.HEIGHT, null);
	
	g2.dispose();
}

/*private void display() {
	// TODO Auto-generated method stub
	
	
}
*/

private class MyMouseMotionListener implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mousex = e.getX();
		thePaddle.mouseMoved(e.getX());	
	}
}



}

