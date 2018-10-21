package my_main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
//import java.awt.Rectangle;

public class Map {

	//fields
	private int[][] theMap;
	private double brickHeight, brickWidth;
	
	public final int HOR_PAD = 80 , VERT_PAD = 60;
	
	//constructor
	public Map(int row , int col) {
		
		initMap(row , col);
		
		brickWidth  = (game_main.WIDTH - 2 * HOR_PAD) / col;
		brickHeight = ( game_main.HEIGHT / 2 - 2 *  VERT_PAD ) /  row;
	}

	public void initMap(int row , int col) {
		theMap = new int[row][col];

		for(int i = 0; i < theMap.length; i++) {			
			for(int j = 0; j < theMap[0].length; j++) {
				
				int r = (int) (Math.random() * 3 + 1);
//				System.out.print(r + "  " );
				theMap[i][j] = r;
	//			theMap[i][j] = 1;
				
			}
		}
		
		theMap[3][1] = 4;	theMap[7][2] = 4;	theMap[7][6] = 4;	theMap[5][4] = 4;	
		theMap[4][4] = 5;	theMap[7][5] = 5; 	theMap[5][4] = 5; 	theMap[6][8] = 5;
		
	}
	
	public void draw(Graphics2D g) {

		for(int row = 0; row < theMap.length; row++) {
			
			for(int col = 0; col < theMap[0].length; col++) {
				
				if(theMap[row][col] > 0) {
				
					if(theMap[row][col] == 1)
						g.setColor(Color.DARK_GRAY);
					if(theMap[row][col] == 2)
						g.setColor(Color.GREEN);
					if(theMap[row][col] == 3)
						g.setColor(Color.BLUE);
									
					if(theMap[row][col] == PowerUp.FASTBALL)
						g.setColor(PowerUp.FASTCOLOR);
					if(theMap[row][col] == PowerUp.WIDEPADDLE)
						g.setColor(PowerUp.WIDECOLOR);						
					
				/*	if(row % 2 == 0)
						if(col % 2 == 0)	
							g.setColor(Color.DARK_GRAY);
						else
						g.setColor(Color.GREEN);
					else
						if(col % 2 == 0)	
							g.setColor(Color.BLUE);
						else
							g.setColor(Color.DARK_GRAY);
				*/
					//g.fillRoundRect(col * (int) brickWidth + HOR_PAD, row * (int) brickHeight + VERT_PAD, (int) brickWidth, (int) brickHeight, 7,7);
					g.fill3DRect(col * (int) brickWidth + HOR_PAD, row * (int) brickHeight + VERT_PAD, (int) brickWidth, (int) brickHeight, true);
					g.setStroke(new BasicStroke(5));
					g.setColor(Color.BLACK);
					g.drawRoundRect(col * (int) brickWidth + HOR_PAD, row * (int) brickHeight + VERT_PAD, (int) brickWidth, (int) brickHeight , 7,7);				
				}
			}
		}
	}
	
	public int[][] getMapArray() { return theMap; }
	
	public void setBrick(int row , int col, int value) {
		theMap[row][col] = value;
	}
	
	public int getBrickWidth() { return (int) brickWidth; }
	public int getBrickHeight() { return (int) brickHeight; }
	
	public void hitBrick(int row, int col) {
		theMap[row][col] -= 1;
		if(theMap[row][col] < 0)
			theMap[row][col] = 0;
	}
	
	
	public boolean checkWin() {
		boolean IsWin = false;
		
		int RemBricks = 0;
		for(int row = 0 ; row < theMap.length ; row++) {
			for(int col = 0 ; col < theMap[0].length ; col++) {
				
				RemBricks += theMap[row][col];
			}
		}
	
		if(RemBricks == 0)
			IsWin = true;
	
		return IsWin;
	}
	
	

}
