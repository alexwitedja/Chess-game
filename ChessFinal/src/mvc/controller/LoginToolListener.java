package mvc.controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import mvc.model.database.PlayerException;
import mvc.model.game.Game;
import mvc.view.board.GUI;
import mvc.view.menu.LoginPanel;

public class LoginToolListener implements ActionListener {

	GUI gui;
	LoginPanel lp1;
	LoginPanel lp2;
	Game game;
	
	public LoginToolListener(GUI gui, LoginPanel lp1, LoginPanel lp2, Game game) {
		
		this.gui = gui;
		this.lp1 = lp1;
		this.lp2 = lp2;
		this.game = game;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		gui.showLogin("PLAYER 1 LOGIN");
		gui.showLogin("PLAYER 2 LOGIN");
		
		String username1 = lp1.getUser();
		String password1 = lp1.getPass();
		try {
			game.login(username1, password1);
		} catch (PlayerException e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
		String username2 = lp2.getUser();
		String password2 = lp2.getUser();
		try {
			game.login(username2, password2);
			JOptionPane.showMessageDialog(null, "player one and two logged in");
		}  catch (PlayerException e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
