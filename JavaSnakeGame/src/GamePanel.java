import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

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
			this.setPreferredSize(new Dimension(screenW, screenH));
			this.setBackground(Color.black);
			this.setFocusable(true);
			startGame();
		}
		
		
		
		
		
		public void startGame() {
			newApple();
			running = true;
			timer = new Timer(delay, this);
			
			
			
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

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	