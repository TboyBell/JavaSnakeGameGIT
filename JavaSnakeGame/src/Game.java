import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


	
	
	public class GameFrame extends JFrame {
		
	GameFrame() {
		
		this.add(new GamePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		
	}
	
	}
	
	
	
	
	
	
	
	
	
	public class GamePanel extends JPanel implements ActionListener {
		
		static final int screenW = 600;
		static final int screenH = 600;
		static final int unitS = 25;
		static final int gameU = (screenW*screenH)/unitS;
		static final int delay = 75;
		final int x[] = new int[gameU];
		final int y[] = new int[gameU];
		int bodyparts = 6;
		int appleEaten;
		int appleX;
		int appleY;
		char direction = 'R';
		boolean running = false;
		Timer timer;
		Random random;
		GamePanel() {
			random = new Random();
			this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
			this.setBackground(Color.black);
			this.setFocusable(true);
			this.addKeyListener(new MyKeyAdapter());
			startGame();
		}
		
		public void startGame() {
			newApple();
			running = true;
			timer = new Timer(delay, null);
			
			
		}
		public void paintComponents(Graphics g) {
			super.paintComponents(g);
			draw(g);
		}
		public void draw(Graphics g) {
			
		}
		public void checkApple() {
			
		}
		public void newApple() {
			
		}
		public void move() {
			
		}
		
		public void checkCollisons() {
			
		}
		public void gameOver(Graphics g) {
			
		}
	}
	
	
	public class MyKeyAdapter extends KeyAdapter {
		
		
		
		
	}
	
	
	
	
	public class Game {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameFrame();
	}

}
