
package my_main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import java.awt.Graphics;
//import java.awt.event.*;


public class game_panel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;

	//Fields
	private boolean running;
	private boolean ScreenShakeActive;
	private long screenShakeTimer;
	
	private BufferedImage image;
	private Graphics2D g;
	private MyMouseMotionListener theMouseListener;
	
	private int mousex;
	
	//entities
	private Ball theBall;
	private Paddle thePaddle;
	private Map theMap;
	private HUD theHud;
	private ArrayList<PowerUp> powerUps;
		
	//constructor
	public game_panel() {
	
		init();

	}

public void init() {
	mousex = 0;
	theBall = new Ball();
	thePaddle = new Paddle(120,12);
	theMap = new Map(8,9);
	theHud = new HUD();
	theMouseListener = new MyMouseMotionListener();
	powerUps = new ArrayList<PowerUp>();
	
	addMouseMotionListener(theMouseListener);
	
	running = true;
	ScreenShakeActive = false;
	screenShakeTimer = System.nanoTime(); 
	
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
	
	for(int i = 0; i < powerUps.size(); i++) {
		
		Rectangle puRect = powerUps.get(i).getRect();
		
		if(paddleRect.intersects(puRect)) {
			
			if(powerUps.get(i).getType() == PowerUp.WIDEPADDLE && powerUps.get(i).getWasUsed() == false) {
				thePaddle.setWidth(thePaddle.getWidth() * 1.5);
//				thePaddle.setWidthTimer();
				powerUps.get(i).setWasUsed(true);
			}
			if(powerUps.get(i).getType() == PowerUp.FASTBALL && powerUps.get(i).getWasUsed() == false) {
				theBall.alterBallSize(theBall.getBallSize() * 2/3);
				powerUps.get(i).setWasUsed(true);
			}
		}
	}
	
	
	if(ballRect.intersects(paddleRect)) {
		
		playSound("file:./resources/paddle_ball_intersect.wav",0);
		
		theBall.setY(thePaddle.YPOS - theBall.getBallSize()); //avoiding interference b/w bar and ball
		theBall.setDY(-theBall.getDY());
	
		if(theBall.getX() + theBall.getBallSize() <  mousex - thePaddle.getWidth() / 3) {
			theBall.setDX(theBall.getDX() -  1.5);
		}

		if(theBall.getX() >=  mousex + thePaddle.getWidth() / 3 ) {
			theBall.setDX(theBall.getDX() +  1.5 );
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
					
					if(theMap.getMapArray()[row][col] > 1 && theMap.getMapArray()[row][col] < 4 )
						playSound("file:./resources/brick_touch.wav",0);
						
					//theMap.setBrick(row, col, 0);
					if(theMap.getMapArray()[row][col] ==1 ) {
						ScreenShakeActive = true;
						screenShakeTimer = System.nanoTime();
						playSound("file:./resources/brick_break.wav",0);
					}
					
					if(theMap.getMapArray()[row][col] > 3 ) {
						if(theMap.getMapArray()[row][col]== 5)
							playSound("file:./resources/ball_speed_increase.wav",0);
						else	
							playSound("file:./resources/bar_size_increase.wav",0);
						
						powerUps.add(new PowerUp(brickx, bricky, theMap.getMapArray()[row][col], brickWidth, brickHeight));
						theMap.setBrick(row, col, 0);
					}
					else {
						theMap.hitBrick(row, col);
					}

					//theMap.hitBrick(row, col);

					theBall.setDY(-theBall.getDY());
					//if(theMap.getMapArray()[row][col] == 0)
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

	thePaddle.update();
	
	for(PowerUp pu : powerUps) {
		pu.update();
	}
	
	if(((System.nanoTime() - screenShakeTimer) / 1000000 > 300) && ScreenShakeActive) {
		ScreenShakeActive = false;
	}
	
}
	
private void draw() {
	// draws the background of the image in g object 
	g.setColor(Color.BLACK);
	
	g.fillRect(0, 0, game_main.WIDTH, game_main.HEIGHT);
	
	theBall.draw(g);
	
	thePaddle.draw(g);

	theMap.draw(g);
		
	theHud.draw(g);
	
	drawPowerUps();
	
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

	private void drawPowerUps() {
		for(PowerUp pu : powerUps) {
			pu.draw(g);
		}
	}

	
	
	//paintComponent is an inbuilt fn. which is overridden here
	public void paintComponent(Graphics g) {

		int x = 0;
		int y = 0;
		
		if(ScreenShakeActive == true) {
			x = (int) (Math.random() * 10 - 5);
			y = (int) (Math.random() * 10 - 5);
		}
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(image, x, y, game_main.WIDTH, game_main.HEIGHT, null);
	
		g2.dispose();
	}

	public void playSound(String file , int times) {
		
		try {
			URL filelocation = new URL(file);
			AudioInputStream audio = AudioSystem.getAudioInputStream(filelocation);
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
			clip.loop(times);
			clip.start();
			
		} catch(UnsupportedAudioFileException uae) {
			System.out.println(uae);	
		} catch(IOException ioe) {
			System.out.println(ioe);
		} catch(LineUnavailableException lua) {
			System.out.println(lua);
		}
		
		
	}
	
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

