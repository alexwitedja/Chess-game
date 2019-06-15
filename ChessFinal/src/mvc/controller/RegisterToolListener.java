package mvc.controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import mvc.model.database.PlayerException;
import mvc.model.game.Game;
import mvc.view.board.GUI;
import mvc.view.menu.LoginPanel;

public class RegisterToolListener implements ActionListener{

	GUI gui;
	LoginPanel lp = new LoginPanel();
	Game game;
	
	public RegisterToolListener(GUI gui, LoginPanel lp, Game game) {
		
		this.gui = gui;
		this.lp = lp;
		this.game = game;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		gui.showLogin("CREATE ACCOUNT");
		
		String username = lp.getUser();
		String password = lp.getPass();
		try {
			game.register(username, password);
			JOptionPane.showMessageDialog(null, "player created");
		} catch (PlayerException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1);
		}
	}

}
