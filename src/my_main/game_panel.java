package my_main;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
//import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JPanel;

import java.awt.Graphics;
//import java.awt.event.*;
public class game_panel extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Fields
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private MyMouseMotionListener theMouseListener;
	
//	private int mousex;
	
	//entities
	private Ball theBall;
	private Paddle thePaddle;
	Map theMap;
	//private HUD theHud;
//private ArrayList<PowerUp> powerUps;
	
	
	//constructor
	public game_panel() {
	
		init();

	}

public void init() {
	//mousex = 0;
//	theBall = new Ball(200, 200, 1, 4, 20);
	theBall = new Ball();
	thePaddle = new Paddle();
//	thePaddle = new Paddle(100, 20);
	theMap = new Map(4, 8);
	//theHud = new HUD();
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

		while(running) {
			

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

public void update() {
	
	theBall.update();
}
	
private void draw() {
	// draws the background of the image in g object 
	g.setColor(Color.WHITE);
	
	g.fillRect(0, 0, game_main.WIDTH, game_main.HEIGHT);
	
	theBall.draw(g);
	
	thePaddle.draw(g);

	//theMap.draw(g);

}

public void paintComponent(Graphics g) {

	Graphics2D g2 = (Graphics2D) g;
			
	//g2.setColor(Color.BLACK);
	
	//g2.fillOval(20, 20, 20, 60);
 	
	g2.drawImage(image, 0, 0, game_main.WIDTH, game_main.HEIGHT, null);
	
	g2.dispose();
} 

private void display() {
	// TODO Auto-generated method stub
	
	
}

private class MyMouseMotionListener implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		thePaddle.mouseMoved(e.getX());	
	}
	
}



}
