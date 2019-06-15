package mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import mvc.model.game.Game;
import mvc.view.board.ChessBoard;
import mvc.view.board.GUI;

public class StartToolListener implements ActionListener{

	ChessBoard chessBoard;
	Game game;
	
	public StartToolListener(ChessBoard chessBoard, Game game) {
		
		this.chessBoard = chessBoard;
		this.game = game;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (game.checkPlayers()) {
			try {
				String input = (String) JOptionPane.showInputDialog("Enter number of moves");
				int moves = Integer.parseInt(input);
				game.playGame(moves);
				chessBoard.startGame();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Failure to get moves");
				chessBoard.destroy();
			}
		} 
		else {
			JOptionPane.showMessageDialog(null, "Players are not logged in");
			return;
		}
	}
}
