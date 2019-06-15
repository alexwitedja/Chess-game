package mvc.view.menu;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroPanel extends JPanel{
	
	public IntroPanel() {
		JLabel title = new JLabel("This is the introduction panel");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(600,500));
		add(title);
//		addAButton("Login", this);
//		addAButton("Register", this);
//		addAButton("Settings", this);
	}
	
	private void addAButton(String text, JPanel container) {

		JButton button = new JButton(text);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
	    container.add(button);
	    
	}

}
