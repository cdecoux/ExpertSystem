package guiproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;



public class UserMainPanel extends JPanel
{
	protected Dictionary data;
	protected GridBagConstraints c;
	
	//Panels
	protected JPanel controlPanel;
	protected JPanel searchPanel;
	protected ContentPanel contentPanel;
	
	
	public UserMainPanel(Dictionary d)
	{
		data = d;
		
		
		setLayout(new GridBagLayout());
	
		
		//User panels: search panel, button panel, output panel
		contentPanel = new ContentPanel();
		contentPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		contentPanel.setLayout(new GridBagLayout());
		
		searchPanel = new JPanel();
		searchPanel.setBackground(Color.WHITE);
		searchPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		searchPanel.setLayout(new GridBagLayout());
		
		//Logout Button
		JButton logout = new JButton("Logout");
		JButton search = new JButton("Search");
		JTextField userInput = new JTextField();
		
		
		//Logout implementation and design
		logout.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				e.getComponent().getParent().setVisible(false);
				((MainPanel)e.getComponent().getParent().getParent()).loginPanel.setVisible(true);
			}
		});
		
		//TextField userinput implementation and design
		userInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.search(userInput.getText());
				requestFocusInWindow();
			}
		});
		userInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		//Search button implemenation
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPanel.search(userInput.getText());
			}
		});
		search.setVisible(true);
		search.setBorderPainted(true);
		search.setOpaque(false);
		search.setContentAreaFilled(false);
		search.setFocusable(false);
		
		
		c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 15, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		add(searchPanel, c);
		//Button and Text-Field in searchPanel
			//Search Bar
			c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weighty = 1.0;
			c.weightx = 1.0;
			c.gridx = 0;
			c.gridy = 0;
			searchPanel.add(userInput, c);
			
			//Search Button
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 0;
			c.weighty = 1.0;
			c.anchor = GridBagConstraints.EAST;
			c.fill = GridBagConstraints.VERTICAL;
			searchPanel.add(search, c);

		
		
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 15, 10);
		c.anchor = GridBagConstraints.NORTHEAST;
		//c.fill = GridBagConstraints.BOTH;
		add(logout, c);

		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 15, 10);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		add(contentPanel, c);
	}
	

	
	
	private class ContentPanel extends JPanel
	{
		//Search algorithm attributes
		private String input;
		private ArrayList<String> keywords;
		private String inputKeyWord;
		private String[] parsed;
		private GridBagConstraints c;
		
		//comonents
		private JLabel title;
		private JLabel nation;
		private JTextArea descriptions;
		private JLabel vs;
		JLabel aggs;
		JLabel defs;
		
		public ContentPanel() 
		{
			GridBagLayout gbl = new GridBagLayout();
			//gbl.columnWeights = new double[] {1.0, 1.0};
			//gbl.rowWeights = new double[] {1.0};
			setLayout(gbl);
			parsed = new String[3];
			input = null;
			this.keywords = new ArrayList<String>(Arrays.asList(new String []{"HELP", "SHOW", "WHAT", "HOW", "WHEN", "WHO", "WHY", "WHERE"}));
			
		}
		
		private void parseText(String input) 
		{
			input = input.toUpperCase();

			//Iterate through dictionary keys and look if input contains a key
			for(String key : data.getMapKeys()) 
			{
				//Case - Dictionary key found
				if(input.contains(key)) 
				{
					parsed[2] = key;
					//Snip input to narrow down
					input = input.replaceAll(key, "");
				}
			}
			
			//Iterate through keywords and save to parsed[]
			for(String element : keywords) 
			{
				if(input.contains(element)) 
				{
					//Save keyword
					parsed[0] = element;
					//Snip input
					input = input.replaceAll(element, "");
				}
			}
			
			parsed[1] = input.replaceAll("[^\\d]", "");
			if (parsed[1].equals("")) {parsed[1] = null;}
		}
		
		public void search(String input) 
		{
			this.input = input;
			parseText(input);

			/*
			 * parsed0 = keywords
			 * parsed1 = numbers
			 * parsed2 = dictionary keys
			 */

			if(parsed[0] == null) 
			{
				parsed[0] = "DEFAULT";
			}

			switch (this.parsed[0]) {
			case "WHAT":
			case "HOW":
			case "WHEN":
				if(parsed[2] != null) 
				{
					displayEvent(data.get(parsed[2]));
				}
				else if (parsed[1] != null) {printDictionaryYears();}
				else 
				{
					System.out.println("Result could not be found, here are some suggestions");
					printDictionaryNations();
				}
				break;
			case "WHO":
				if(parsed[2] == null){System.out.println("Result could not be found."); break;}
			case "WHY":
				if(parsed[2] != null) 
				{
					System.out.println(data.get(parsed[2]) + "\n");
				}
				else System.out.println("Result could not be found");
				break;

			case "WHERE":
				System.out.println("Here are some suggestions: ");
				printDictionaryNations();
				break;
			case "HELP":
				System.out.println("Search for a historical event, year, or country");
				break;
			case "SHOW":
				printKeys();
				break;
			
			default:
				if(parsed[2] != null) 
				{
					displayEvent(data.get(parsed[2]));
				}
				else 
				{
					System.out.println("Here are some suggestions: ");
					printDictionaryNations();
					printDictionaryYears();

				}
				break;
			}
			parsed[0] = null;
			parsed[1] = null;
			parsed[2] = null;
		}
		private void printKeys() 
		{
			String result = "[";
			for(String key : data.getMapKeys()) 
			{
				result = result + key + ", ";
			}
			result = result.substring(0, result.length()-2);
			result = result + "]";
			System.out.println(result);

		}


		//Prints related entries determined by a year
		private void printDictionaryYears() 
		{

			if(parsed[1] == null) return;



			for(String keys : data.getMapKeys())
			{
				if(data.get(keys).isInDate(parsed[1])) 
				{
					System.out.println(data.get(keys).getName());
				}
			}

		}
		//Prints related entries determined by a nation
		private void printDictionaryNations() 
		{

			for(String keys : data.getMapKeys())
			{
				if(data.get(keys).containsNation(this.input)) 
				{
					System.out.println(data.get(keys).getName());
				}
			}

		}	
		
		private void displayEvent(Event event) 
		{
			if(event instanceof WarEvent) 
			{
				this.removeAll();
				//Set the Aggressors and Defenders string
				String aggsString = "";
				String defsString = "";
				for(String a : ((WarEvent) event).getAggressors()) {aggsString += ", " + a;}
				for(String b : ((WarEvent) event).getDefenders()) {defsString += ", " + b;}
				aggsString = aggsString.substring(2);
				defsString = defsString.substring(2);
				

				
				title = new JLabel(event.getName() + " (" + event.getDate() + ")");
				aggs = new JLabel(aggsString);
				defs = new JLabel(defsString);
				descriptions = new JTextArea(event.getDetail());
				vs = new JLabel("VS");
					descriptions.setEditable(false);
					descriptions.setOpaque(false);
					descriptions.setWrapStyleWord(true);
					descriptions.setLineWrap(true);
					descriptions.setFont(new Font("Arial", Font.PLAIN, 22));
				
				//Add using gridbaglayout
				c = new GridBagConstraints();
				c.gridy = 0;
				c.gridx = 0;
				c.weightx = 1.0;
				c.anchor = GridBagConstraints.CENTER;
				add(title, c);
				
				c = new GridBagConstraints();
				c.gridy = 1;
				c.gridx = 0;
				c.weightx = 1.0;
				c.gridwidth = GridBagConstraints.REMAINDER;
				c.anchor = GridBagConstraints.CENTER;
				add(aggs, c);
				
				c = new GridBagConstraints();
				c.gridy = 2;
				c.gridx = 0;
				c.weightx = 1.0;
				c.gridwidth = GridBagConstraints.REMAINDER;
				c.anchor = GridBagConstraints.CENTER;
				add(vs, c);
				 
				c = new GridBagConstraints();
				c.gridy = 3;
				c.gridx = 0;
				c.weightx = 1.0;
				c.gridwidth = GridBagConstraints.REMAINDER;
				c.anchor = GridBagConstraints.CENTER;
				add(defs, c);
				
				c = new GridBagConstraints();
				c.gridy = 4;
				c.gridx = 0;
				c.weightx = 1;
				c.weighty = 1;
				c.gridwidth = 5;
				c.gridheight = 5;
				c.insets = new Insets(30,30,30,30);
				c.fill = GridBagConstraints.BOTH;
				c.anchor = GridBagConstraints.NORTHWEST;
				add(descriptions, c);
				
				validate();
				repaint();
				
				
			}
			else if(event instanceof NationalEvent) 
			{
				this.removeAll();
				//Set the Aggressors and Defenders string
				
				
				
				title = new JLabel(event.getName() + " (" + event.getDate() + ")");
				nation = new JLabel(((NationalEvent) event).getNation());
				descriptions = new JTextArea(event.getDetail());
				vs = new JLabel("VS");
					descriptions.setEditable(false);
					descriptions.setOpaque(false);
					descriptions.setWrapStyleWord(true);
					descriptions.setLineWrap(true);
					Font font = descriptions.getFont();
					FontMetrics fontMetrics = descriptions.getFontMetrics(font);
					descriptions.setFont(new Font("Arial", Font.PLAIN, 22));
					
					//int width = fontMetrics.getWidths();
				
				//Add using gridbaglayout
				c = new GridBagConstraints();
				c.gridy = 0;
				c.gridx = 0;
				c.weightx = 1.0;
				c.anchor = GridBagConstraints.CENTER;
				add(title, c);
				
				c = new GridBagConstraints();
				c.gridy = 1;
				c.gridx = 0;
				c.weightx = 1.0;
				c.gridwidth = GridBagConstraints.REMAINDER;
				c.anchor = GridBagConstraints.CENTER;
				add(nation, c);
				
				c = new GridBagConstraints();
				c.gridy = 2;
				c.gridx = 0;
				c.weightx = 1;
				c.weighty = 1;
				c.gridwidth = 5;
				c.gridheight = 5;
				c.insets = new Insets(30,30,30,30);
				c.fill = GridBagConstraints.BOTH;
				c.anchor = GridBagConstraints.NORTHWEST;
				add(descriptions, c);
				
				validate();
				repaint();
			}
		}
	}

}
