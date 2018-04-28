package guiproject;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginPanel extends JPanel{
	
	
	protected JButton userButton;
	protected JButton adminButton;
	
	private JLabel logo;
	private JLabel welcomeLogo;
	private GridBagConstraints c;
	
	LoginPanel()
	{
		
		//Set layout to GridBagLayout
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] {1.0};
		setLayout(gridBagLayout);
		
		
		
		//Create each component
		logo = new JLabel("");
		logo.setIcon(new ImageIcon(this.getClass().getResource("resources/expertSystem.png")));
		
		welcomeLogo = new JLabel("Welcome to the Historical Expert System.");
		welcomeLogo.setFont(new Font("Eras Light ITC", Font.PLAIN, 30));
		
		userButton = new JButton("Login as User");
		adminButton = new JButton("Login as Admin");
		userButton.setFocusable(false);
		adminButton.setFocusable(false);
		

		//Define the GridBgLayout constraints for each component
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,0,5,0);
		add(logo, c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5,0,50,0);
		add(welcomeLogo, c);
		
		c = new GridBagConstraints();
		c.gridy = 3;
		c.gridx = 0;
		c.insets = new Insets(5,0,5,0);
		add(adminButton, c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5,0,5,0);
		add(userButton, c);
		
		
		
		
		//Set the button behaviors for userButton and adminButton
		

		
		userButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				e.getComponent().getParent().setVisible(false); //sets the buttons parent (loginpanel) to be not visble
				((MainPanel)e.getComponent().getParent().getParent()).userPanel.setVisible(true); //sets the mainpanels userpanel to be visible
			}
		});
		
		
		
		adminButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				e.getComponent().getParent().setVisible(false); //sets the buttons parent (loginpanel) to be not visble
				((MainPanel)e.getComponent().getParent().getParent()).adminPanel.setVisible(true); //sets the mainPanel's admin panel to be visible
			}
		});
		
		
	}

}
