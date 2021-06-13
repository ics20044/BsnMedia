package GUI;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import GUI.BackendProfileEmployeeGUI.ButtonListener;
import entities.*;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;

public class BackendProfileChiefGUI {

	private JFrame frmStartingPage;
	private JTextField textField;
	private JTextField searchField;
	private static Chief chief;
	private JPanel panel, picturePanel;
	private JLabel lblNewLabel;
	private JButton searchButton, helpButton, requestsButton, messagesButton, notifsButton, editAccountButton,createProjectButton, 
	postButton, checkprofileButton, sendMessageButton, sendRequestButton, disconnectButton;
	private JLabel emailLabel;
	private JRadioButton connectionsRadio, PublicRadio;
	private JTextArea writePostArea;
	private JList<String> connectionsList, suggestedList;
	ArrayList<User> listOfConnections;
	TreeSet<User> suggestedListConnections = new TreeSet<>();
	ButtonGroup radioGroup;
	TreeSet<Post> allPosts = new TreeSet<>();
	
	private JTextArea textArea;
	private JButton btnNewButton_1;
	private JSeparator separator;
	
	
	public BackendProfileChiefGUI(User theChief) throws IOException {
		chief = (Chief) theChief;
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frmStartingPage = new JFrame();
		frmStartingPage.setTitle("Starting Page");
		frmStartingPage.setBounds(100, 100, 893, 1020);
		frmStartingPage.setLocation(500, 0);
		frmStartingPage.setVisible(true);
		frmStartingPage.setResizable(false);
		frmStartingPage.getContentPane().setLayout(null);
		
	    panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 887, 985);
		frmStartingPage.getContentPane().add(panel);
		panel.setLayout(null);
		
		picturePanel = new JPanel();
		picturePanel.setBounds(49, 76, 181, 152);
		panel.add(picturePanel);
		picturePanel.setLayout(null);
		
	    lblNewLabel = new JLabel();
		BufferedImage imageicon;
		try {
			imageicon = ImageIO.read(new File(chief.getImage()));
			ImageIcon image = new ImageIcon(imageicon);
			Image imagerisize = image.getImage().getScaledInstance(181, 152, 170);
			lblNewLabel.setIcon(new ImageIcon(imagerisize));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblNewLabel.setBounds(0, 0, 181, 152);
		picturePanel.add(lblNewLabel);
		
		Icon search = new ImageIcon("Buttons_backgrounds/search_30px.png");
		searchButton= new JButton(search);
		searchButton.setContentAreaFilled(false); 
		searchButton.setFocusPainted(false); 
		searchButton.setOpaque(false);
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String searchedText = searchField.getText();
				if(!searchedText.isEmpty()) {
					boolean result;
					try {
						result = chief.getMyAccount().getMyCompany().searchObject(searchedText, chief);
						
						if (!result) {
							ArrayList<String> suggestedOptions = new ArrayList<String>();
							new SearchSuggestionsGUI(suggestedOptions, chief);
						}else {
							frmStartingPage.setVisible(false);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					 String message = "Type something in the Search field";
						JOptionPane.showMessageDialog(new JFrame(), message, "Message",
						        JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		searchButton.setBounds(612, 38, 55, 44);
		panel.add(searchButton);
		
		JLabel nameLabel = new JLabel();
		nameLabel.setText(chief.getFirstName()+" "+chief.getLastName());
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		nameLabel.setBounds(49, 254, 322, 30);
		panel.add(nameLabel);
		
		JLabel companyPostLabel = new JLabel("Chief, ");
		companyPostLabel.setBounds(49, 297, 37, 16);
		panel.add(companyPostLabel);
		
		JLabel specializationLabel = new JLabel(chief.getCompanyPost());
		specializationLabel.setBounds(86, 295, 124, 16);
		panel.add(specializationLabel);
		
		emailLabel = new JLabel(chief.getMyAccount().getEmail());
		emailLabel.setBounds(49, 324, 125, 16);
		panel.add(emailLabel);
		
		JLabel lblNewLabel_5 = new JLabel("Currently supervising:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(49, 359, 155, 16);
		panel.add(lblNewLabel_5);
		
		
		Icon help = new ImageIcon("Buttons_backgrounds/customer_support_40px.png");
		helpButton = new JButton(help);
		helpButton.setContentAreaFilled(false); 
		helpButton.setFocusPainted(false); 
		helpButton.setOpaque(false);
		helpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		helpButton.setBounds(814, 922, 63, 58);
		panel.add(helpButton);
		
		searchField = new JTextField();
		searchField.setBackground(new Color(255, 250, 240));
		searchField.setColumns(10);
		searchField.setBounds(324, 42, 275, 30);
		panel.add(searchField);
		
		Icon friends = new ImageIcon("Buttons_backgrounds/friends_30px.png");
		requestsButton= new JButton(friends);
		requestsButton.setContentAreaFilled(false); 
		requestsButton.setFocusPainted(false); 
		requestsButton.setOpaque(false);
		requestsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		requestsButton.setBounds(714, 27, 37, 30);
		panel.add(requestsButton);
		
		Icon messages = new ImageIcon("Buttons_backgrounds/Messages_30px.png");
		messagesButton = new JButton(messages);
		messagesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		messagesButton.setContentAreaFilled(false); 
		messagesButton.setFocusPainted(false); 
		messagesButton.setOpaque(false);
		messagesButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		messagesButton.setBounds(763, 27, 37, 30);
		panel.add(messagesButton);
		
		Icon bell = new ImageIcon("Buttons_backgrounds/bell_30px.png");
		notifsButton = new JButton(bell);
		notifsButton.setContentAreaFilled(false); 
		notifsButton.setFocusPainted(false); 
		notifsButton.setOpaque(false);
		notifsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		notifsButton.setBounds(814, 27, 37, 30);
		panel.add(notifsButton);
		
		editAccountButton = new JButton("Edit Account Info");
		editAccountButton.setContentAreaFilled(false); 
		editAccountButton.setFocusPainted(false); 
		editAccountButton.setOpaque(false);
		editAccountButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editAccountButton.setBounds(216, 293, 155, 25);
		panel.add(editAccountButton);
		
		
		
		connectionsList = new JList<String>();
		DefaultListModel<String> model = new DefaultListModel<String>();
		listOfConnections = chief.getListOfConnections(); //Get all his Connections
		for (User theUser: listOfConnections) {
			model.addElement(theUser.getFirstName()+" "+theUser.getLastName()); 
		}
		connectionsList.setModel(model);
		connectionsList.setBackground(new Color(255, 250, 240));
		connectionsList.setBounds(43, 594, 116, 152);
		panel.add(connectionsList);
		
		suggestedListConnections = chief.suggestedConnections(); //Get all Suggested Connections
		suggestedList = new JList<String>();
		DefaultListModel<String> model2 = new DefaultListModel<String>();
		for (User suggestedUser: suggestedListConnections) {
			model2.addElement(suggestedUser.getFirstName()+" "+suggestedUser.getLastName());
		}
		suggestedList.setModel(model2);
		suggestedList.setBackground(new Color(255, 250, 240));
		suggestedList.setBounds(220, 594, 116, 152);
		panel.add(suggestedList);
		
		JLabel lblNewLabel_9 = new JLabel("Connections (" + chief.getListOfConnections().size() + ")");
		lblNewLabel_9.setBounds(48, 565, 99, 16);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_9_1 = new JLabel("Suggested Connections");
		lblNewLabel_9_1.setBounds(215, 565, 139, 16);
		panel.add(lblNewLabel_9_1);
		
		writePostArea = new JTextArea();
		writePostArea.setLineWrap(true);
		writePostArea.setWrapStyleWord(true);
		writePostArea.setBackground(new Color(255, 250, 240));
		writePostArea.setBounds(427, 688, 424, 49);
		panel.add(writePostArea);
		
		connectionsRadio = new JRadioButton("Connections");
		connectionsRadio.setActionCommand("Connections");
		connectionsRadio.setOpaque(false);
		connectionsRadio.setBackground(Color.WHITE);
		connectionsRadio.setBounds(441, 746, 112, 25);
		panel.add(connectionsRadio);
		
		PublicRadio = new JRadioButton("Public");
		PublicRadio.setActionCommand("Public");
		PublicRadio.setOpaque(false);
		PublicRadio.setBackground(Color.WHITE);
		PublicRadio.setBounds(557, 746, 78, 25);
		panel.add(PublicRadio);
		
		JRadioButton rdbtnGroup = new JRadioButton("Group");
		rdbtnGroup.setActionCommand("Group");
		rdbtnGroup.setOpaque(false);
		rdbtnGroup.setBackground(Color.WHITE);
		rdbtnGroup.setBounds(639, 746, 78, 25);
		panel.add(rdbtnGroup);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setText("");
		textArea.setLineWrap(true);
		for( Post post : chief.returnAllPosts())
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formatDateTime = post.getTimestamp().format(formatter);
			textArea.append("-----------------------------------------------------------------------------------------------------"+ "\n\r");
			textArea.append(post.getContent()+" | "+post.getCreator().getFirstName()+" | "+post.getPostScope()+" | "+formatDateTime+ "\n\r");
		}
		textArea.setBackground(new Color(255, 250, 240));
		textArea.setBounds(427, 237, 424, 409);
		panel.add(textArea);
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 250, 240));
		textField.setColumns(10);
		textField.setBounds(639, 780, 64, 25);
		panel.add(textField);
		
		postButton = new JButton("Post");
		postButton.setContentAreaFilled(false); 
		postButton.setFocusPainted(false); 
		postButton.setOpaque(false);
		postButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		postButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 if(connectionsRadio.isSelected() || PublicRadio.isSelected()){
				        
						//Putting post on boss' and others Users' wall
							String myText = writePostArea.getText();
							Post myPost = new Post(chief,myText,radioGroup.getSelection().getActionCommand());
							chief.addPost(myPost);
							textArea.setText(""); 
						    for( Post post : chief.returnAllPosts())
							{
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						        String formatDateTime = post.getTimestamp().format(formatter);
						        textArea.append("-----------------------------------------------------------------------------------------------------"+ "\n\r");
								textArea.append(post.getContent()+" | "+post.getCreator().getFirstName()+" | "+post.getPostScope()+" | "+formatDateTime+ "\n\r");
							}
							
					   }
				 else if(rdbtnGroup.isSelected())
				 {
					 String myText = writePostArea.getText();
						Post myPost = new Post(chief,myText,radioGroup.getSelection().getActionCommand());
						chief.addPost(myPost);
						textArea.setText(""); 
					    for( Post post : chief.returnAllPosts())
						{
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					        String formatDateTime = post.getTimestamp().format(formatter);
					        textArea.append("-----------------------------------------------------------------------------------------------------"+ "\n\r");
							textArea.append(post.getContent()+" | "+post.getCreator().getFirstName()+" | "+post.getPostScope()+" | "+formatDateTime+ "\n\r");
						}
					 String groupToPost=textField.getText();
					 boolean found=false;
					 for(int i=0;i<chief.getGroups().size();i++)
					 {
						 if(chief.getGroups().get(i).getName().equals(groupToPost))
						 {
							 found=true;
							 chief.getGroups().get(i).addPostToGroupList(myPost);
						 
						 String message = "You successfully posted on "+chief.getGroups().get(i).getName()+"'s wall!";
							JOptionPane.showMessageDialog(new JFrame(), message, "Message",
							  JOptionPane.INFORMATION_MESSAGE);
						 }
					 }
					 if(found==false)
					 {
						 String message = "Group not found...Please check your spelling!";
							JOptionPane.showMessageDialog(new JFrame(), message, "Message",
							  JOptionPane.ERROR_MESSAGE);
					 }
				 }
				else
				{
					String message = "Select Post scope before posting!";
					JOptionPane.showMessageDialog(new JFrame(), message, "Message",
					  JOptionPane.ERROR_MESSAGE);
				}
				 textArea.setBackground(new Color(255, 250, 240));
				 textArea.setBounds(427, 213, 424, 409);	
			}
		});
		postButton.setBounds(754, 750, 97, 25);
		panel.add(postButton);
		
		
		checkprofileButton = new JButton("Check profile");
		checkprofileButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkprofileButton.setContentAreaFilled(false); 
		checkprofileButton.setFocusPainted(false); 
		checkprofileButton.setOpaque(false);
		checkprofileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkprofileButton.setBounds(43, 759, 116, 25);
		panel.add(checkprofileButton);
		
		// create button group for the radio button to know which one was selected
		radioGroup = new ButtonGroup();
	    radioGroup.add(connectionsRadio);
		radioGroup.add(PublicRadio);
		radioGroup.add(rdbtnGroup);
		
		sendMessageButton = new JButton("Send Message");
		sendMessageButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sendMessageButton.setContentAreaFilled(false); 
		sendMessageButton.setFocusPainted(false); 
		sendMessageButton.setOpaque(false);
		sendMessageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sendMessageButton.setBounds(43, 797, 116, 25);
		panel.add(sendMessageButton);
		
		sendRequestButton = new JButton("Send request");
		sendRequestButton.setContentAreaFilled(false); 
		sendRequestButton.setFocusPainted(false); 
		sendRequestButton.setOpaque(false);
		sendRequestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sendRequestButton.setBounds(220, 759, 116, 25);
		panel.add(sendRequestButton);
		
		Icon logout = new ImageIcon("Buttons_backgrounds/exit_50px.png");
		disconnectButton = new JButton(logout);
		disconnectButton.setContentAreaFilled(false); 
		disconnectButton.setFocusPainted(false); 
		disconnectButton.setOpaque(false);
		disconnectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		disconnectButton.setBounds(796, 169, 55, 54);
		panel.add(disconnectButton);
		
		createProjectButton = new JButton("Create Project");
		createProjectButton.setContentAreaFilled(false); 
		createProjectButton.setFocusPainted(false); 
		createProjectButton.setOpaque(false);
		createProjectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		createProjectButton.setBounds(216, 332, 155, 25);
		panel.add(createProjectButton);
		
		JList<String> list = new JList<String>();
		DefaultListModel<String> listModelForGroups = new DefaultListModel<String>();
		for (Group theSupervisingGroup: chief.getGroups()) {
			listModelForGroups.addElement(theSupervisingGroup.getName());
		}
		list.setModel(listModelForGroups);
		list.setBounds(49, 386, 98, 110);
		panel.add(list);
		
		Icon check = new ImageIcon("Buttons_backgrounds/takealook_32px.png");
		JButton checkGroup = new JButton(check);
		checkGroup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		checkGroup.setBackground(new Color(248, 248, 255));
		checkGroup.setBorder(null);
		checkGroup.setOpaque(false);
		checkGroup.setBounds(49, 495, 48, 36);
		checkGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String selectedGroupString = list.getSelectedValue()+"";
				Group selectedGroup = null;
				
				for (Group supervisingGroup: chief.getGroups()) {
					if (selectedGroupString.equalsIgnoreCase(supervisingGroup.getName())) {
						selectedGroup = supervisingGroup;
						break;
					}
				}
				
				if (selectedGroup != null) {
					try {
						new GroupProfileGUI(chief, selectedGroup);
						frmStartingPage.dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					String message = "You havent select any Group!";
					JOptionPane.showMessageDialog(new JFrame(), message, "Message",
					  JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(checkGroup);
		
		Icon edit = new ImageIcon("Buttons_backgrounds/edit_20px.png");
		btnNewButton_1 = new JButton(edit);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setBackground(new Color(248, 248, 255));
		btnNewButton_1.setBounds(99, 495, 48, 36);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String selectedGroupString = list.getSelectedValue()+"";
				Group selectedGroup = null;
				
				for (Group supervisingGroup: chief.getGroups()) {
					if (selectedGroupString.equalsIgnoreCase(supervisingGroup.getName())) {
						selectedGroup = supervisingGroup;
						break;
					}
				}
				
				if (selectedGroup != null) {
					new EditGroupProjectGUI(selectedGroup);
				}else {
					String message = "You havent select any Group!";
					JOptionPane.showMessageDialog(new JFrame(), message, "Message",
					  JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		panel.add(btnNewButton_1);
		
		separator = new JSeparator();
		separator.setForeground(new Color(255, 250, 250));
		separator.setBounds(49, 549, 287, 5);
		panel.add(separator);
		
		JLabel editGroup = new JLabel("");
		BufferedImage imagebackground = ImageIO.read(new File("label_backgrounds/background.jpg"));
		ImageIcon imageb = new ImageIcon(imagebackground);
		Image imagerisizeb = imageb.getImage().getScaledInstance(887, 991, 140) ;
		editGroup.setIcon(new ImageIcon(imagerisizeb));
		editGroup.setBounds(0, 0, 887, 991);
		panel.add(editGroup);
		

		ButtonListener listener = new ButtonListener();
		requestsButton.addActionListener(listener);
		messagesButton.addActionListener(listener);
		notifsButton.addActionListener(listener);
		editAccountButton.addActionListener(listener);
		createProjectButton.addActionListener(listener);
		helpButton.addActionListener(listener);
		disconnectButton.addActionListener(listener);
		checkprofileButton.addActionListener(listener);
		sendMessageButton.addActionListener(listener);
		sendRequestButton.addActionListener(listener);
		postButton.addActionListener(listener);
	}
	
	public void disconnectUser() throws IOException {
		frmStartingPage.setVisible(false);
		new WelcomeScreen_GUI(chief.getMyAccount().getMyCompany());
	}
	
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(requestsButton)) {
				try {
					new ConnectionRequestsGUI(chief);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource().equals(messagesButton)) {
				try {
					new NewMessagesGUI(chief);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource().equals(notifsButton)) {
				try {
					new NotificationsGUI(chief);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource().equals(editAccountButton)) {
				
				new EditAccountGUI(chief, frmStartingPage);
			}
			
			else if(e.getSource().equals(createProjectButton)) {
				 new CreateProjectGUI(chief);
			}
			
			else if(e.getSource().equals(helpButton)) {
				try {
					frmStartingPage.setVisible(false);
					new HelpGUI(chief);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource().equals(disconnectButton)) {
				try {
					disconnectUser();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource().equals(checkprofileButton)) {
				
				String selectedUserString = connectionsList.getSelectedValue();
				User selectedUser = null;
				
				for(User theUser: listOfConnections) {
					String userFullName = theUser.getFirstName()+" "+theUser.getLastName();
					
					if (userFullName.equalsIgnoreCase(selectedUserString)) {
						selectedUser = theUser;
						break;
					}
				}
				
				if (selectedUser == null) {
					 String message = "You have not selected any user!";
						JOptionPane.showMessageDialog(new JFrame(), message, "Message",
						        JOptionPane.INFORMATION_MESSAGE);
				}else {
					try {
						new FrontEndProfileGUI(chief, selectedUser);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frmStartingPage.setVisible(false);
				}
			}
			else if(e.getSource().equals(sendMessageButton)) {
				
				String selectedUserString = connectionsList.getSelectedValue();
				User selectedUser = null;
				
				for(User theUser: listOfConnections) {
					String userFullName = theUser.getFirstName()+" "+theUser.getLastName();
					
					if (userFullName.equalsIgnoreCase(selectedUserString)) {
						selectedUser = theUser;
						break;
					}
				}
				
				if (selectedUser == null) {
					 String message = "You have not selected any user!";
						JOptionPane.showMessageDialog(new JFrame(), message, "Message",
						        JOptionPane.INFORMATION_MESSAGE);
				}else {
					ArrayList<Conversation> listOfConversation = chief.getListOfConversations();
					Conversation selectedUserToChat = null;
					
					for (Conversation theConversation: listOfConversation) {
						
						if ((((privateConversation)theConversation).getDiscussant1().equals(chief) && ((privateConversation)theConversation).getDiscussant2().equals(selectedUser)) ||
							(((privateConversation)theConversation).getDiscussant2().equals(chief) && ((privateConversation)theConversation).getDiscussant1().equals(selectedUser))) {
							
							selectedUserToChat = theConversation;
							break;
						}
					}
					
					if(selectedUserToChat == null) {
						 String message = "Something went Wrong!";
							JOptionPane.showMessageDialog(new JFrame(), message, "Message",
							        JOptionPane.INFORMATION_MESSAGE);
					}else {
						new PrivateChatGUI(chief, selectedUser, selectedUserToChat);
					}
				}
			}
			else if(e.getSource().equals(sendRequestButton)) {
				
				String selectedUserString = suggestedList.getSelectedValue();
				User selectedUser = null;
				
				for(User suggestedUser: suggestedListConnections) {
					String usersFullName = suggestedUser.getFirstName()+" "+suggestedUser.getLastName();
					
					if (usersFullName.equalsIgnoreCase(selectedUserString)) {
						selectedUser = suggestedUser;
						break;
					}
				}
				
				if (selectedUser == null) {
					 String message = "You have not selected any user!";
						JOptionPane.showMessageDialog(new JFrame(), message, "Message",
						        JOptionPane.INFORMATION_MESSAGE);
				}else {
					Connection possibleConnection = new Connection(chief, selectedUser);
					possibleConnection.sendConnectionRequest();
				}
				
			}
		}
	}
}
