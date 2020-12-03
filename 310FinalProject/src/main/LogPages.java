package main;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LogPages
{
	// frame for all areas
	JFrame frame = new JFrame();
	// panel for the landing page
	JPanel landing = new JPanel();
	// panel for log-in page
	JPanel log = new JPanel();
	
	// all buttons
	JButton submitBtn = new JButton("Submit");
	JButton cancelBtn = new JButton("Cancel");
	JButton adminBtn = new JButton("Administrator");
	JButton clientBtn = new JButton("Client");
	
	// all labels
	JLabel landingLabel = new JLabel("Welcome to the Data Simps' Storm Page");
	JLabel descLabel = new JLabel("Here you will be able to browse, update, and display storm data through a variety of attributes");
	JLabel userLabel = new JLabel("Username: ");
	JLabel passLabel = new JLabel("Password: ");
	JLabel titleLabel;
	
	// all fields
	JTextField userField = new JTextField(50);
	JPasswordField passField = new JPasswordField(50);
	
	// username and password HashMaps
	java.util.Map<String, char[]> clients = new java.util.HashMap<>();
	java.util.Map<String, char[]> admins = new java.util.HashMap<>();
	
	// filling the client with usernames and passwords
	public void client_proc() {
		char[] exp = {'p', 'a', 's','s'};
		String str = "client";
		clients.put(str, exp);
		char[] exp1 = {'c', 'h', 'a','r'};
		String str1 = "sam";
		clients.put(str1, exp1);
	}
	
	// filling the admin with usernames and passwords
	public void admin_proc() {
		char[] exp = {'p', 'a', 's','s'};
		String str = "admin";
		admins.put(str, exp);
		char[] exp1 = {'c', 'h', 'a','r'};
		String str1 = "sam";
		admins.put(str1, exp1);
	}
	
	// Authenticator for client login
	private boolean clientAuth(String string, char[] cs) {
		// checks for existence of key
		if(clients.containsKey(string)) {
			// check password
			return (new String(clients.get(string))).equals(new String(cs));
		} else {
			return false;
		}
	}
	
	// Authenticator for admin login
	private boolean adminAuth(String string, char[] cs) {
		// checks for existence of key
		if(admins.containsKey(string)) {
			// check password
			return (new String(admins.get(string))).equals(new String(cs));
		} else {
			//System.out.println("here");
			return false;
		}
	}
	
	// creation of landing page
	public void landingPage() {
		// sets panel settings
		landing.setLayout(null);
		landing.setBounds(100, 100, 800, 800);
		
		// creates title and btns
		descLabel.setBounds(250, 100, 200, 40);
		landing.add(descLabel);
		landingLabel.setBounds(250, 50, 200, 40);
		landing.add(landingLabel);
		clientBtn.setBounds(150, 150, 140, 40);
		adminBtn.setBounds(350, 150, 140, 40);
		landing.add(adminBtn);
		landing.add(clientBtn);
		
		// adds panel and sets frame properties
		frame.add(landing);
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		frame.setVisible(true);
		
		clientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
        		logIn(true);
            }          
          });
			
		adminBtn.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent arg0) {
	        	logIn(false);
	        }          
	      });
    }   
	
	public void logIn(boolean client) {
		// cleans frame
		frame.remove(landing);
		frame.revalidate();
		frame.repaint();
		
		// sets panel settings
		log.setLayout(null);
		log.setBounds(100, 100, 800, 800);
		
		// sets title
		if(client) {
			titleLabel = new JLabel("Client Log-In");
			client_proc();
		} else {
			titleLabel = new JLabel("Admin Log-In");
			admin_proc();
		}
		titleLabel.setBounds(250, 50, 100, 40);
		log.add(titleLabel);
		
		// creates login prompt and fields
		userLabel.setBounds(100, 100, 100, 40);
		userField.setBounds(200, 100, 200, 40);
		passLabel.setBounds(100, 200, 100, 40);
		passField.setBounds(200, 200, 200, 40);
		log.add(userLabel);
		log.add(userField);
		log.add(passLabel);
		log.add(passField);
		
		// creates btns
		submitBtn.setBounds(350, 300, 140, 40);
		cancelBtn.setBounds(150, 300, 140, 40);
		log.add(submitBtn);
		log.add(cancelBtn);
		
		// adds panel and sets frame properties
		frame.add(log);
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		frame.setVisible(true);
		
		// submit btn that authenticates username and password
		submitBtn.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent arg0) {
	        	if(client) {
	    			if(clientAuth(userField.getText(), passField.getPassword())) {
	    				System.out.println("Success");
	    			} else {
	    				System.out.println("Fail");
	    			}
	    		} else {
	    			if (adminAuth(userField.getText(), passField.getPassword())) {
	    				System.out.println("Success");
	    			} else {
	    				System.out.println("Fail");
	    			}
	    		}
	        }          
	      });
		
		// cancel btn for exiting program
		cancelBtn.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent arg0) {
	        	System.exit(0);
	        }          
	      });
	}
	
	
}
