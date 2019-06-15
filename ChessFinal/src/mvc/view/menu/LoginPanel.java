package mvc.view.menu;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	
	private JTextField tfUsername;
    private JPasswordField tfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JLabel title;
    private JButton btnLogin;
    private JButton btnCancel;
	private GridBagConstraints cs = new GridBagConstraints();

	private final int SPACING = 10;
	
	public LoginPanel() {	
		
		setLayout(new GridBagLayout());
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.insets = new Insets(SPACING,SPACING,SPACING,SPACING);
		
		title = new JLabel("Enter your details");
		lbUsername = new JLabel("Username: ");
        tfUsername = new JTextField(20);
        lbPassword = new JLabel("Password: ");
        tfPassword = new JPasswordField(20);
		
		addComponent(title, 0, 0);
		addComponent(lbUsername, 0, 1);
		addComponent(tfUsername, 0, 2);
		addComponent(lbPassword, 1, 1);
		addComponent(tfPassword, 1, 2);
       
	}
	
	protected void addComponent(Component component, int x, int y) 
	{
		cs.gridx = x;
		cs.gridy = y;
		cs.fill = GridBagConstraints.HORIZONTAL;
		add(component, cs);
	}
	
	public String getUser() {
		return tfUsername.getText();
	}
	
	public String getPass() {
		return String.valueOf(tfPassword.getPassword());
	}
}
