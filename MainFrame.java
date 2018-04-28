package guiproject;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


public class MainFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Historical Expert System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.setMinimumSize(new Dimension(800, 600));
		//frame.setIconImage();
	
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		MainPanel mainPanel = new MainPanel("/guiproject/dictionary.txt");
		
		frame.getContentPane().add(mainPanel, c);
		
		frame.pack();
		frame.setVisible(true);
		
	}

}
