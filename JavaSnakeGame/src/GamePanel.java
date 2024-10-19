import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {
	
	static final int screenW = 600;
	static final int screenH = 600;
	static final int unitS = 25;
	static final int gameU = (screenW*screenH)/unitS;
	static final int delay = 155;
	final int x[] = new int[gameU];
	final int y[] = new int[gameU];
	JButton restart;
	int bodyparts = 6; 
	int applesEaten;
	int highScore = 0;
	int appleX;
	int appleY;
	char direction = 'D';
	boolean running = false;
	Timer timer;
	Random random;
	GamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(screenW, screenH));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		
		setLayout(new BorderLayout());

		// Create and configure restart button
		restart = new JButton("Restart");
		restart.setBackground(Color.GREEN);
		restart.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
		restart.setPreferredSize(new Dimension(200, 50));
		restart.addActionListener(this);

	
		// Initially hidden
		restart.setVisible(false);
		this.add(restart, BorderLayout.SOUTH);

		startGame();

	}
	
	
	public void startGame() {
    // Reset game variables
    applesEaten = 0;
    bodyparts = 6;
    direction = 'D';
    running = true;

    // Reset snake position (start at the center)
    x[0] = screenW / 2;
    y[0] = screenH / 2;

    // Create a new apple
    newApple();

    // If there's an existing timer, stop it
    if (timer != null) {
        timer.stop();
    }

    // Start a new timer
    timer = new Timer(delay, this);
    timer.start();

    // Hide the restart button when starting the game
    restart.setVisible(false);

    // Refresh the UI
    this.revalidate();
    this.repaint();
}

	/*
	public void newGame() {
		restart = new JButton("Restart");
		restart.setBackground(Color.GREEN);
		restart.setFont(new Font("Comic Sans MS", Font.BOLD, 35)); 
		restart.setPreferredSize(new Dimension(200, 50)); 
		restart.addActionListener(this);

		this.add(restart); 
		this.revalidate(); 
		this.repaint();

	}
	*/

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
		if(running) {
		/* Grid Lines
		for(int i=0; i < screenH/unitS; i++) {
			g.drawLine(i*unitS, 0, i*unitS, screenH);
			g.drawLine(0, i*unitS, screenW, i*unitS);
		}
		*/
		
		g.setColor(Color.RED);
		g.fillOval(appleX, appleY, unitS, unitS);
		
		for(int i=0; i < bodyparts; i++) {
			if(i == 0) {
				g.setColor(Color.GREEN);
				g.fillRect(x[i], y[i], unitS, unitS);
			}
			else {
				g.setColor(new Color(45,180,0));
				g.fillRect(x[i], y[i], unitS, unitS);
			}
		}
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 35));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten, (screenW - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		
		}
		else {
			gameOver(g);
		}
	}

	public void move() {
		for (int i = bodyparts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}

		switch (direction) {
			case 'W':  // Up
				y[0] = y[0] - unitS;
				break;
			case 'S':  // Down
				y[0] = y[0] + unitS;
				break;
			case 'A':  // Left
				x[0] = x[0] - unitS;
				break;
			case 'D':  // Right
				x[0] = x[0] + unitS;
				break;
		}
	}
	
	
	
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyparts++;
			applesEaten++;
			newApple();
		}
	}

	public void newApple() {
		appleX = random.nextInt((int) (screenW/unitS)) *unitS;
		appleY = random.nextInt((int) (screenH/unitS)) *unitS;
	}
	
	public void checkCollisions() {
		//Checks if Head Touches Left Border
		for(int i = bodyparts; i > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
			
			if(x[0] < 0) {
				running = false;
			}
			if(x[0] > screenW) {
				running = false;
			}
			if(y[0] < 0) {
				running = false;
			}
			if(y[0] > screenH) {
				running = false;
			}
			
			
			if(!running) {
				timer.stop();
			}
		}
	}

	public void gameOver(Graphics g) {
		//Game Over
		g.setColor(Color.red);
		g.setFont( new Font("Comic Sans MS",Font.BOLD, 75));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (screenW - metrics1.stringWidth("Game Over"))/2, screenH/2);
		// Score
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 35));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		if(applesEaten < highScore) {
			g.drawString("Score: "+applesEaten+"\nHigh Score: "+highScore, (screenW - metrics2.stringWidth("Score: "+applesEaten+"\nHigh Score: "+highScore))/2, g.getFont().getSize());
		}
		else {
			highScore = applesEaten;
			g.drawString("New High Score: " + highScore,
					(screenW - metrics2.stringWidth("New High Score: " + highScore)) / 2, g.getFont().getSize());

		}
		
		restart.setVisible(true);
		this.revalidate();
		
		
		
	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if(direction != 'S') {
					direction = 'W';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'W') {
					direction = 'S';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'A') {
					direction = 'D';
				}
			case KeyEvent.VK_LEFT:
				if(direction != 'D') {
					direction = 'A';
				}
				break;		        	
			}
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkApple();
			checkCollisions();
		}
		
		if ( e.getSource() == restart) {
			//this.remove(restart); 
			//this.revalidate(); 
			startGame(); 
		}
		repaint();
		
	}
	
}