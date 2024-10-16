import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	GameFrame() {
		
		
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		
		
		this.add(new GamePanel());
		
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		
	}
}
