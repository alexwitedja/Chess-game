package mvc.view.board;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	
	private JLabel title = new JLabel("This is the Score panel");
	private JLabel w = new JLabel("White score:");
	private JLabel wscore = new JLabel("");
	private JLabel b = new JLabel("Black score:");
	private JLabel bscore = new JLabel("");
	private JLabel move = new JLabel("Moves of both players:");
	private JLabel remainingMoves = new JLabel("");
	
	public ScorePanel() {
		
		title.setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(150,435));
		setBackground(Color.WHITE);
		add(title);
		add(b);
		add(bscore);
		add(w);
		add(wscore);
		add(move);
		add(remainingMoves);
	}
	
	public void updateScore(int white, int black, int moves) {
		wscore.setText(String.format("%d",white));
		bscore.setText(String.format("%d",black));
		remainingMoves.setText(String.format("%d",moves));
	}
}
