package guiproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JPanel;



public class MainPanel extends JPanel
{
	protected Dictionary data;
	protected final int WIDTH = 800, HEIGHT = 600;
	protected GridBagConstraints c;
	
	//Panels
	protected UserMainPanel userPanel;
	protected LoginPanel loginPanel;
	protected AdminPanel adminPanel;
	
	
	public MainPanel(String filename)
	{
		data = new Dictionary(filename);
		setLayout(new GridBagLayout());
		
		
		userPanel = new UserMainPanel(data);
		userPanel.setVisible(false);
		
		loginPanel = new LoginPanel();
		loginPanel.setVisible(true);
		
		adminPanel = new AdminPanel(data);
		adminPanel.setVisible(false);
		
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		
		add(userPanel, c);
		add(loginPanel,c);
		add(adminPanel, c);
		
		


	}
}
