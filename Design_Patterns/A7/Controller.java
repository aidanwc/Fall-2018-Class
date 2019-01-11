

//Aidan Weber-Concannon
//260708481

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class Controller extends KeyAdapter {
	
	private GameModel game;
	private GamePanel view;
	
	//Added a game model and game panel to controller 
	public Controller(GameModel g,GamePanel v) {
		game = g;
		view=v;//careful
		updateView();
	}
	//Updates the view 
	public void updateView(){
		view.set(game.getMyTiles(),game.isMyWin(),game.isMyLose(),game.getMyScore());
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.resetGame();
		}
		if (!game.canMove()) {
			game.myLose = true;
		}
		
		if (!game.myWin && !game.myLose) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					game.left();
					break;
				case KeyEvent.VK_RIGHT:
					game.right();
					break;
				case KeyEvent.VK_DOWN:
					game.down();
					break;
				case KeyEvent.VK_UP:
					game.up();
					break;
			}
		}
		
		if (!game.myWin && !game.canMove()) {
			game.myLose = true;
		}
		updateView();
		view.repaint();//needs to be view 
	}
	//Driver method
	public static void main(String[] args) {
		JFrame gameFrame = new JFrame();
		gameFrame.setTitle("2048 Game");
		gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gameFrame.setSize(340, 400);
		gameFrame.setResizable(true);
		
		GameModel game = new GameModel();
		GamePanel view= new GamePanel();
		gameFrame.add(view);
		view.addKeyListener(new Controller(game,view));
		
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
	}
}