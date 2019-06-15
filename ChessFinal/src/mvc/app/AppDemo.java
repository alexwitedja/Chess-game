package mvc.app;

import mvc.model.game.Game;
import mvc.view.board.GameEngineCallback;
import mvc.view.frame.MainFrame;

public class AppDemo {

	private static MainFrame frame;
		
	public static void main(String[] args) 
	{
		final Game gameEngine = new Game();
		frame = new MainFrame(gameEngine);
		gameEngine.addGameEngineCallback(new GameEngineCallback(frame.bg));
		//gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
	}
}
