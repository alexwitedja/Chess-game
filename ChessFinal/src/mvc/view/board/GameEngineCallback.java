package mvc.view.board;

public class GameEngineCallback {

	private GUI gui;
	
	public GameEngineCallback(GUI gui) {
		this.gui = gui;
	}
	
	public void move(int white, int black, int moves) {
		
		gui.redraw(white, black, moves);
		
	}
}
