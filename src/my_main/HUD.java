package my_main;

import java.awt.Color;
import java.awt.Graphics2D;

public class HUD {

	//fields
	private int score;
	
	//constructor
	public HUD() {

		init();
	}

	public void init() {
		score = 0;
	}

	public void draw(Graphics2D g) {
		
		g.setColor(Color.RED);
		g.drawString("SCORE :" + score , 20, 20);
	}

	public int getScore() { return score; }
	
	public void addScore(int ScoreToAdd) {
		score += ScoreToAdd;
	}

}



