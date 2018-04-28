package guiproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminPanel extends JPanel{

	protected Dictionary data;

	private GridBagConstraints c;

	protected JPanel sideButtons;
	protected AdminNewEventPanel createEvent;
	
	protected JFrame frame;
	protected JComboBox<String> comboBox;
	

	AdminPanel(Dictionary d)
	{
		data = d;

		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWeights = new double[] {1.0 ,0};
		gbl.rowWeights = new double[] {1.0};
		setLayout(gbl);


		JButton logout = new JButton("Logout");
		JButton viewDictionary = new JButton("View Dictionary");
		logout.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				e.getComponent().getParent().getParent().setVisible(false);
				((MainPanel)e.getComponent().getParent().getParent().getParent()).loginPanel.setVisible(true);
			}
		});
		viewDictionary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openDictionary();
			}
		});

		
		sideButtons = new JPanel();
		createEvent = new AdminNewEventPanel();
			GridBagLayout gbl2 = new GridBagLayout();
			gbl2.rowWeights = new double[] {0,0,1.0};
			sideButtons.setLayout(gbl2);


		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(sideButtons, c);
		
		//Buttons in sideButton panel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1;
		c.insets = new Insets(5,5,5,5);
		c.anchor = GridBagConstraints.CENTER;
		sideButtons.add(logout, c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		c.insets = new Insets(5,5,5,5);
		c.anchor = GridBagConstraints.CENTER;
		sideButtons.add(viewDictionary, c);
		
		

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.BOTH;
		add(createEvent, c);


	}


	private class AdminNewEventPanel extends JPanel{


		private Event newEvent;
		private GridBagConstraints c;

		AdminNewEventPanel()
		{

			GridBagLayout gbl = new GridBagLayout();
			//gbl.rowWeights = new double[] {};
			setLayout(gbl);


			JRadioButton warEventRB = new JRadioButton("War Event");
				warEventRB.setSelected(true);
			JRadioButton nationalEventRB = new JRadioButton("National Event");
			ButtonGroup typeGroup = new ButtonGroup();				
				typeGroup.add(warEventRB);
				typeGroup.add(nationalEventRB);

			JButton createButton = new JButton("Create");
			JLabel createButtonText = new JLabel();
				
			//Creating the event sub Panels
			JPanel newEventPanel = new JPanel();
			JPanel warEventPanel = new JPanel();
			JPanel nationalEventPanel = new JPanel();
				newEventPanel.setLayout(gbl);
				warEventPanel.setLayout(gbl);
				nationalEventPanel.setLayout(gbl);
				nationalEventPanel.setVisible(false);

				
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1.0;
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.anchor = GridBagConstraints.NORTH;
			add(newEventPanel, c);
			
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1.0;
			c.weighty = 1.0;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.BOTH;
			c.anchor = GridBagConstraints.LINE_START;
			add(warEventPanel, c);

			
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1.0;
			c.weighty = 1.0;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.BOTH;
			c.anchor = GridBagConstraints.LINE_START;
			add(nationalEventPanel, c);
			
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 2;
			c.insets = new Insets(10,10,10,10);
			c.gridwidth = 1;
			c.weightx = 1;
			c.anchor = GridBagConstraints.LINE_END;
			add(createButton, c);
			
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 2;
			c.insets = new Insets(10,0,10,0);
			c.gridwidth = 1;
			c.weightx = 1;
			c.anchor = GridBagConstraints.LINE_START;
			add(createButtonText, c);
			
			//Main Control Panel for new event			
			c = new GridBagConstraints();
			c.insets = new Insets(10,10,10,10);
			c.gridx = 0;
			c.gridy = 0;
			//c.gridwidth = GridBagConstraints.REMAINDER;
			newEventPanel.add(warEventRB, c);
			
			c = new GridBagConstraints();
			c.insets = new Insets(10,10,10,10);
			c.gridx = 1;
			c.gridy = 0;
			//c.gridwidth = GridBagConstraints.REMAINDER;
			newEventPanel.add(nationalEventRB, c);
			
			JLabel eventName = new JLabel("Name: ");
			JLabel eventYear = new JLabel("Year: ");
			JLabel eventDescription = new JLabel("Description: ");
			JLabel eventAggressors = new JLabel("Aggressors: ");
			JLabel eventDefenders = new JLabel("Defenders: ");
			JTextField eventNameText = new JTextField(20);
			JTextField eventYearText = new JTextField(8);
			JTextArea eventDescriptionText = new JTextArea();
			JTextField eventAggressorsText = new JTextField(35);
			JTextField eventDefendersText = new JTextField(35);
			JScrollPane eventDescriptionPane = new JScrollPane(eventDescriptionText);
				eventDescriptionText.setWrapStyleWord(true);
				eventDescriptionText.setLineWrap(true);
				
			
			
				//Add to War Event SubPanel, has name, year, aggressors, defenders, description
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 0;
				c.gridy = 0;
				c.anchor = GridBagConstraints.LINE_END;
				warEventPanel.add(eventName, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 1;
				c.gridy = 0;
				c.anchor = GridBagConstraints.LINE_START;
				warEventPanel.add(eventNameText, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 0;
				c.gridy = 1;
				c.anchor = GridBagConstraints.LINE_END;
				warEventPanel.add(eventYear, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 1;
				c.gridy = 1;
				c.anchor = GridBagConstraints.LINE_START;
				warEventPanel.add(eventYearText, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 0;
				c.gridy = 2;
				c.anchor = GridBagConstraints.LINE_END;
				warEventPanel.add(eventAggressors, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 1;
				c.gridy = 2;
				c.anchor = GridBagConstraints.LINE_START;
				warEventPanel.add(eventAggressorsText, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 0;
				c.gridy = 3;
				c.anchor = GridBagConstraints.LINE_END;
				warEventPanel.add(eventDefenders, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 1;
				c.gridy = 3;
				c.anchor = GridBagConstraints.LINE_START;
				warEventPanel.add(eventDefendersText, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 0;
				c.gridy = 4;
				c.anchor = GridBagConstraints.LINE_END;
				warEventPanel.add(eventDescription, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 1;
				c.gridy = 4;
				c.gridheight = 2;
				c.weightx = 1.0;
				c.weighty = 1.0;
				c.anchor = GridBagConstraints.LINE_END;
				c.fill = GridBagConstraints.BOTH;
				warEventPanel.add(eventDescriptionPane, c);
				
				JLabel eventName2 = new JLabel("Name: ");
				JLabel eventYear2 = new JLabel("Year: ");
				JLabel eventDescription2 = new JLabel("Description: ");
				JLabel eventNation = new JLabel("Nation: ");
				JTextField eventNameText2 = new JTextField(20);
				JTextField eventYearText2 = new JTextField(8);
				JTextArea eventDescriptionText2 = new JTextArea();
				JTextField eventNationText = new JTextField(15);
				JScrollPane eventDescriptionPane2 = new JScrollPane(eventDescriptionText2);
					eventDescriptionText2.setWrapStyleWord(true);
					eventDescriptionText2.setLineWrap(true);
				
				//Add to National Event SubPanel, has name, year, aggressors, defenders, description
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 0;
				c.gridy = 0;
				c.anchor = GridBagConstraints.LINE_END;
				nationalEventPanel.add(eventName2, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 1;
				c.gridy = 0;
				c.anchor = GridBagConstraints.LINE_START;
				nationalEventPanel.add(eventNameText2, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 0;
				c.gridy = 1;
				c.anchor = GridBagConstraints.LINE_END;
				nationalEventPanel.add(eventYear2, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 1;
				c.gridy = 1;
				c.anchor = GridBagConstraints.LINE_START;
				nationalEventPanel.add(eventYearText2, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 0;
				c.gridy = 2;
				c.anchor = GridBagConstraints.LINE_END;
				nationalEventPanel.add(eventNation, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 1;
				c.gridy = 2;
				c.anchor = GridBagConstraints.LINE_START;
				nationalEventPanel.add(eventNationText, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 0;
				c.gridy = 3;
				c.anchor = GridBagConstraints.LINE_END;
				nationalEventPanel.add(eventDescription2, c);
				
				c = new GridBagConstraints();
				c.insets = new Insets(10,10,10,10);
				c.gridx = 1;
				c.gridy = 3;
				c.gridheight = 2;
				c.weightx = 1.0;
				c.weighty = 1.0;
				c.anchor = GridBagConstraints.LINE_END;
				c.fill = GridBagConstraints.BOTH;
				//warEventPanel.add(eventDescriptionText, c);
				nationalEventPanel.add(eventDescriptionPane2, c);
			
			
			
			
			//Radio buttin implementations -> switches info panels for a war or national event
			warEventRB.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					warEventPanel.setVisible(true);
					nationalEventPanel.setVisible(false);
				}
			});
			nationalEventRB.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					warEventPanel.setVisible(false);
					nationalEventPanel.setVisible(true);
				}
			});
			
			//Create button implementation -> pulls text from the forms and calls data's addEvent method
			createButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(warEventPanel.isVisible()) 
					{
						//If fields are filled 
						if(!eventNameText.getText().isEmpty() && !eventYearText.getText().isEmpty() && !eventAggressorsText.getText().isEmpty() && !eventDefendersText.getText().isEmpty() && !eventDescriptionText.getText().isEmpty()) 
						{
							String description = eventDescriptionText.getText().replaceAll("\n", " ");
							ArrayList<String> aggressors = new ArrayList<String>(Arrays.asList(eventAggressorsText.getText().split(", ")));
							ArrayList<String> defenders = new ArrayList<String>(Arrays.asList(eventDefendersText.getText().split(", ")));
							newEvent = new WarEvent(eventNameText.getText(), eventYearText.getText(), description, aggressors, defenders);
							data.addEvent(newEvent);

							eventNameText.setText(""); eventYearText.setText(""); eventAggressorsText.setText(""); eventDefendersText.setText(""); eventDescriptionText.setText("");
							
							createButtonText.setText("Added new event");
							createButton.setVisible(true);
						}
						else 
						{
							createButtonText.setText("Complete each field before adding");
							createButton.setVisible(true);
						}
					}
					
					else if (nationalEventPanel.isVisible()) 
					{
						//If fields are filled 
						if(!eventNameText2.getText().isEmpty() && !eventYearText2.getText().isEmpty() && !eventNationText.getText().isEmpty() && !eventDescriptionText2.getText().isEmpty()) 
						{
							String description = eventDescriptionText2.getText().replaceAll("\n", " ");
							newEvent = new NationalEvent(eventNameText2.getText(), eventYearText2.getText(), description, eventNationText.getText());
							data.addEvent(newEvent);
							
							eventNameText2.setText(""); eventYearText2.setText(""); eventNationText.setText(""); eventDescriptionText2.setText("");
							
							createButtonText.setText("Added new event");
							createButton.setVisible(true);
						}
						else 
						{
							createButtonText.setText("Complete each field before adding");
							createButton.setVisible(true);
						}
					}

				}
			});
			
			

		}
	}
	
	public void openDictionary() 
	{
		
		//Subframe for View Dictionary Button
		frame = new JFrame("View Dictionary");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setMinimumSize(new Dimension(450, 70));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		
		
		JPanel comboPanel = new JPanel();
		frame.getContentPane().add(comboPanel);


		comboPanel.setLayout(new BorderLayout());
		comboPanel.setBackground(Color.GRAY);
		
		comboBox = new JComboBox(data.getMapKeys().toArray());

		
		comboPanel.add(comboBox, BorderLayout.CENTER);

		frame.setVisible(true);
		
	}
}
