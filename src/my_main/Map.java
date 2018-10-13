package my_main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {

	//fields
	private int[][] theMap;
	private double brickHeight, brickWidth;
	
	public final int HOR_PAD = 80 , VERT_PAD = 60;
	
	//constructor
	public Map(int row , int col) {
		
		initMap(row , col);
		
		brickWidth  = (game_main.WIDTH - 2 * HOR_PAD) / col;
		brickHeight = ( game_main.HEIGHT / 2 - 1.5 * VERT_PAD ) /  row;
	}

	public void initMap(int row , int col) {
		
		theMap = new int[row][col];
		
		for(int i = 0; i < theMap.length; i++) {
			
			for(int j = 0; j < theMap[0].length; j++) {
				
				theMap[i][j] = 1;
			}
		}	
	}
	
	public void draw(Graphics2D g) {

		for(int row = 0; row < theMap.length; row++) {
			
			for(int col = 0; col < theMap[0].length; col++) {
				
				if(theMap[row][col] > 0) {
					g.setColor(Color.BLACK);
					g.fillRoundRect(col * (int) brickWidth + HOR_PAD, row * (int) brickHeight + VERT_PAD, (int) brickWidth, (int) brickHeight, 10, 10);
					g.setStroke(new BasicStroke(1));
					g.setColor(Color.CYAN);
					g.drawRoundRect(col * (int) brickWidth + HOR_PAD, row * (int) brickHeight + VERT_PAD, (int) brickWidth, (int) brickHeight , 10,10);				
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

}