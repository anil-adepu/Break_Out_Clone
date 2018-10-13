package my_main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {

	//fields
	private int[][] theMap;
	private int brickHeight, brickWidth;
	
	public final int HOR_PAD = 100 , VERT_PAD = 50;
	
	//constructor
	public Map(int row , int col) {
		
		initMap(row , col);
		
		brickWidth  = (game_main.WIDTH - 2 * HOR_PAD) / col;
		brickHeight = ( game_main.HEIGHT /2 - VERT_PAD ) / row;
	}

	public void initMap(int row , int col) {
		
		theMap = new int[row][col];
		
		for(int i = 0; i < theMap.length; i++) {
			
			for(int j = 0; j < theMap[0].length; j++) {
				
				theMap[i][j] = 1;
			}
		}	
	}
	
	public void graw(Graphics2D g) {

		for(int row = 0; row < theMap.length; row++) {
			
			for(int col = 0; col < theMap[0].length; col++) {
		
				g.setColor(Color.DARK_GRAY);
				g.fillRect(col * brickWidth + HOR_PAD, row * brickHeight + VERT_PAD, brickWidth, brickHeight);
				g.setStroke(new BasicStroke(2));
				g.setColor(Color.WHITE);
				g.fillRect(col * brickWidth + HOR_PAD, row * brickHeight + VERT_PAD, brickWidth, brickHeight);				
				
				
			}
		}	

	}
	
	
	
}


