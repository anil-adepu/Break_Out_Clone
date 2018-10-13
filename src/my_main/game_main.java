package my_main;

import javax.swing.JFrame;

public class game_main {
	
	public static final int WIDTH = 800 , HEIGHT = 560;
	
	public static void main( String[] args ) {
		
		JFrame theFrame =  new JFrame("MINIMUM_BREAK_OUT_GAME");

		game_panel PanelObj = new game_panel();
		
		Thread ThreadObj = new Thread(PanelObj);
		
		theFrame.setSize(WIDTH , HEIGHT);
		theFrame.setLocationRelativeTo(null);
		theFrame.setResizable(false);
		
		theFrame.add(PanelObj);
		ThreadObj.start();
		
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setVisible(true);		
	}
}
