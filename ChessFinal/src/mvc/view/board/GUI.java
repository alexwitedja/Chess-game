package mvc.view.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import mvc.controller.ExitToolListener;
import mvc.controller.LoginToolListener;
import mvc.controller.RegisterToolListener;
import mvc.controller.StartToolListener;
import mvc.model.game.Game;
import mvc.view.menu.IntroPanel;
import mvc.view.menu.LoginPanel;

public class GUI {	
	
	Game game;
	private final JPanel gui = new JPanel(new BorderLayout(3,3));
	private ChessBoard chessBoard;
	JToolBar tools = new JToolBar();
	private LoginPanel lp = new LoginPanel();
	private LoginPanel lp1 = new LoginPanel();
	private LoginPanel lp2 = new LoginPanel();
	private IntroPanel ip = new IntroPanel();
	private ScorePanel sp = new ScorePanel();
	
	public GUI(Game gameEngine) {
		sp.setVisible(false);
		this.game = gameEngine;
		this.chessBoard = new ChessBoard(game,ip,sp,this);
		initialize();
	}
	
	public void initialize() {
		
		gui.setBorder(new EmptyBorder(5,5,5,5));
		
		createToolbar();
		
		JPanel boardConstraints = new JPanel(new GridBagLayout());
		
		boardConstraints.add(chessBoard);
		boardConstraints.add(ip);
		boardConstraints.add(sp);
		
		gui.add(boardConstraints);
	}
	
	public JPanel getGui() {
		return gui;
	}
	
	private void createToolbar() {
		
		tools.setFloatable(false);
		gui.add(tools, BorderLayout.PAGE_START);
		
		JButton loginB = new JButton("Login");
		JButton registerB = new JButton("Register");
		JButton startB = new JButton("Start game");
		JButton exitB = new JButton("Exit");
		
		registerB.addActionListener(new RegisterToolListener(this, lp, game));
		loginB.addActionListener(new LoginToolListener(this, lp1, lp2, game));
		startB.addActionListener(new StartToolListener(chessBoard, game));
		exitB.addActionListener(new ExitToolListener(game));
		
		tools.add(registerB);
		tools.add(loginB);
		tools.addSeparator();
		tools.add(startB);
		tools.addSeparator();
		tools.add(exitB);
	}
	
	public void showLogin(String msg) {
		
		if (msg.equals("PLAYER 1 LOGIN")) {
			JOptionPane.showMessageDialog(null, lp1, msg, JOptionPane.OK_CANCEL_OPTION);
		} else if (msg.equals("PLAYER 2 LOGIN")) {
			JOptionPane.showMessageDialog(null, lp2, msg, JOptionPane.OK_CANCEL_OPTION);
		} else {
			JOptionPane.showMessageDialog(null, lp, msg, JOptionPane.OK_CANCEL_OPTION);
		}
	}
	
	public void redraw(int white, int black, int moves) {
		chessBoard.redraw();
		sp.updateScore(white, black, moves);
	}

	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public void logout() {
		game.logout();
	}
}
