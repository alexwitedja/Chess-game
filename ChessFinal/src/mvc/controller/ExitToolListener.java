package mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mvc.model.game.Game;

public class ExitToolListener implements ActionListener {

	Game game;
	
	public ExitToolListener(Game game) {
		this.game = game;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		game.logout();
		System.exit(0);
	}

}
