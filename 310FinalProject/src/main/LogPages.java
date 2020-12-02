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
	JFrame frame = new JFrame();
	JPanel landing = new JPanel();
	
	JButton submitBtn = new JButton("Submit");
	JButton cancelBtn = new JButton("Cancel");
	JButton adminBtn = new JButton("Administrator");
	JButton clientBtn = new JButton("Client");
	
	JLabel landingLabel = new JLabel("Client or Administrator Log-In?");
	JLabel userLabel = new JLabel("Username: ");
	JLabel passLabel = new JLabel("Password: ");
	JLabel titleLabel;
	JTextField userField = new JTextField(50);
	JPasswordField passField = new JPasswordField(50);
	
	java.util.Map<String, char[]> clients = new java.util.HashMap<>();
	java.util.Map<String, char[]> admins = new java.util.HashMap<>();
	
	
	public void client_proc() {
		char[] exp = {'p', 'a', 's','s'};
		String str = "client";
		clients.put(str, exp);
		char[] exp1 = {'c', 'h', 'a','r'};
		String str1 = "sam";
		clients.put(str1, exp1);
	}
	
	public void admin_proc() {
		char[] exp = {'p', 'a', 's','s'};
		String str = "admin";
		admins.put(str, exp);
		char[] exp1 = {'c', 'h', 'a','r'};
		String str1 = "sam";
		admins.put(str1, exp1);
	}
	
	private boolean clientAuth(String string, char[] cs) {
		if(clients.containsKey(string)) {
			return (new String(clients.get(string))).equals(new String(cs));
		} else {
			return false;
		}
	}
	
	private boolean adminAuth(String string, char[] cs) {
		if(admins.containsKey(string)) {
			return (new String(admins.get(string))).equals(new String(cs));
		} else {
			//System.out.println("here");
			return false;
		}
	}
	
	public void landingPage() {
		landing.setLayout(null);
		landing.setBounds(100, 100, 800, 800);
		
		landingLabel.setBounds(250, 100, 200, 40);
		landing.add(landingLabel);
		clientBtn.setBounds(150, 150, 140, 40);
		adminBtn.setBounds(350, 150, 140, 40);
		landing.add(adminBtn);
		landing.add(clientBtn);
		
		
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
		frame.remove(landing);
		frame.revalidate();
		frame.repaint();
		
		if(client) {
			titleLabel = new JLabel("Client Log-In");
			client_proc();
		} else {
			titleLabel = new JLabel("Admin Log-In");
			admin_proc();
		}
		titleLabel.setBounds(250, 50, 100, 40);
		frame.add(titleLabel);
		
		userLabel.setBounds(100, 100, 100, 40);
		userField.setBounds(200, 100, 200, 40);
		passLabel.setBounds(100, 200, 100, 40);
		passField.setBounds(200, 200, 200, 40);
		
		frame.add(userLabel);
		frame.add(userField);
		frame.add(passLabel);
		frame.add(passField);
		
		submitBtn.setBounds(350, 300, 140, 40);
		cancelBtn.setBounds(150, 300, 140, 40);
		frame.add(submitBtn);
		frame.add(cancelBtn);
		
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		frame.setVisible(true);
		
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
		
		cancelBtn.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent arg0) {
	        	System.exit(0);
	        }          
	      });
	}
	
	
}
