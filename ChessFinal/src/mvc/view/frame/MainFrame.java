package mvc.view.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import mvc.model.game.Game;
import mvc.view.board.GUI;

public class MainFrame extends JFrame
{	
	public GUI bg;
	
	public MainFrame(Game gameEngine) 
	{
		super("CHESS sef assignment1");
		bg = new GUI(gameEngine);
		add(bg.getGui());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds(0,0,screenSize.width/2, screenSize.height);
	    pack();
	    addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                bg.logout();
                e.getWindow().dispose();
            }
        });
		setVisible(true);
	}
}