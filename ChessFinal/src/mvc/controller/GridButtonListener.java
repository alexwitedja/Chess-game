package mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import mvc.model.game.Game;
import mvc.view.board.ChessBoard;
import mvc.view.board.GUI;

public class GridButtonListener implements ActionListener {

	private JButton b;
	private JButton[][] bgroup;
	Game game;
	private GUI gui;
	
	public GridButtonListener (JButton[][] bgroup, JButton b, Game game, GUI gui) {
		this.bgroup = bgroup;
		this.b = b;
		this.game = game;
		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		int x = 0;
		int y = 0;
		
		if(game.getGameOver()) {
			gui.showMessage("Game over");
			return;
		}
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if (b == bgroup[i][j]) {
					x = i;
					y = j;
				}
			}		
		}
		
		if(!game.checkSelected()) {
			game.selectPiece(y, x);
		}
		else {
			game.movePiece(y, x);
		}
	}
}
