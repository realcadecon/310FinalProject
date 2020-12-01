package main;

	import java.awt.BorderLayout;
	import java.awt.CardLayout;
	import java.awt.Color;
	import java.awt.Container;
	import java.awt.Dimension;
	import java.awt.Image;
	import java.awt.Toolkit;
	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.awt.event.ItemEvent;
	import java.awt.event.ItemListener;
//Standard Library Imports
	import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener;

	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;

	import java.awt.event.MouseWheelEvent;
	import java.awt.event.MouseWheelListener;

	import java.awt.event.WindowAdapter;
	import java.awt.event.WindowEvent;
	import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
//Swing Imports
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
	import javax.swing.UnsupportedLookAndFeelException;

//Manager Imports
	import database.DatabaseManager;
	import error.ErrorManager;


public class GUIInterface extends JPanel implements MouseListener, MouseWheelListener, KeyListener, ItemListener {
	
	JPanel cards; //a panel that uses CardLayout
    final static String FUNCTIONS = "JDBC Functions";
    final static String DASHBOARD = "Dashboard";
    final static String ADMIN = "Admin";
    final static String USER = "User";
    
    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel menu = new JPanel(); //use FlowLayout, could maybe add more things to this
        String pages[] = { DASHBOARD, FUNCTIONS, ADMIN, USER };
        JComboBox cb = new JComboBox(pages);
        cb.setEditable(false);
        cb.addItemListener(this);
        menu.add(cb);
         
        //Create the "cards". TODO: maybe add a menu card
        JPanel card1 = new JPanel();
        createFunctionsPage(card1);
        JPanel card2 = new JPanel();
        createDashboardPage(card2);
        JPanel card3 = new JPanel();
        createAdminPage(card3);
        JPanel card4 = new JPanel();
        createUserPage(card4);
         
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, FUNCTIONS);
        cards.add(card2, DASHBOARD);
        cards.add(card3, ADMIN);
        cards.add(card4, USER);
         
        pane.add(menu, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }
    
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("CSCE 310 Final Project");
        frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.dispose();
				DatabaseManager.closeConnection();
				System.exit(0);
			}
		});
        frame.setBounds(300,300,300,300);
        //Create and set up the content pane.
        GUIInterface demo = new GUIInterface();
        demo.addComponentToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public static void createDashboardPage(JPanel card) {
    	Dashboard.runDash(card);
    }
    
    public static void createFunctionsPage(JPanel card) {
    	card.setLayout(null);
    	card.setPreferredSize(new Dimension(810, 650));
    	
    }
    
    public static void createAdminPage(JPanel card) {
    	card.setLayout(null);
    	card.setPreferredSize(new Dimension(810, 650));
    	
    	card.add(new JButton("Button 1 - Card 1"));
        card.add(new JButton("Button 2 - Card 1"));
        card.setBackground(Color.blue);
    	
    }
    
    public static void createUserPage(JPanel card) {
    	JCheckBox stormType = new JCheckBox("storm type"); 
    	JCheckBox state = new JCheckBox("state"); 
    	JCheckBox city = new JCheckBox("city"); 
    	JCheckBox property = new JCheckBox("property damage"); 
    	JCheckBox eventNar = new JCheckBox("event narrative"); 
    	JCheckBox beginD = new JCheckBox("begin date"); 
    	JCheckBox endD = new JCheckBox("end date"); 
    	JCheckBox tScale = new JCheckBox("tornado scale"); 
    	
    	stormType.setBounds(0,30,0,0);  
    	state.setBounds(0,60,0,0);  
    	city.setBounds(0,90,0,0);  
    	property.setBounds(0,120,0,0);  
    	eventNar.setBounds(0,150,0,0);  
    	endD.setBounds(0,180,0,0);  
    	tScale.setBounds(0,210,0,0);  
    	
    	card.add(stormType);
    	card.add(state);
    	card.add(city);
    	card.add(property);
    	card.add(eventNar);
    	card.add(beginD);
    	card.add(endD);
    	card.add(tScale);
    	
    	JTextField stateName = new JTextField(15);
    	stateName.setBounds(100,100,100,100);
    	
    	card.add(stateName);
    	
    	JTextField cityName = new JTextField(15);
    	cityName.setBounds(120,120,120,120);
    	
    	card.add(cityName);
    	
    	JCheckBox fatal = new JCheckBox("fatal (check if yes, leave blank if not)"); 
    	card.add(fatal);
    	
 
    	
    	String[] stormT = {"stormType1", "stormType2", "stormType3"};
    	JComboBox stormTy = new JComboBox(stormT);
    	stormTy.setSelectedIndex(2);
    	stormTy.setBounds(0,100,95,30);  
    	card.add(stormTy);
    	
    	/*JButton button = new JButton("button");
    	button.setBounds(50,100,95,30);  
    	card.add(button);
    	card.setLayout(null);
    	card.setPreferredSize(new Dimension(810, 650));*/
    	//OKButton.addActionListener();
    	
    }
	
	public static void main(String[]args) {
		 /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	DatabaseManager.openConnection();
        		DatabaseManager.queryDatabase("use weatherdb;");
                createAndShowGUI();
            }
        });
	}

	//Could make listeners frame-synchronized or based on press & release combo
	@Override
	public void keyPressed(KeyEvent key) {}

	@Override
	public void keyReleased(KeyEvent key) {}

	@Override
	public void keyTyped(KeyEvent key) {
		int keycode = key.getKeyCode();
	}

	@Override
	public void mouseClicked(MouseEvent mouse) {
		//this is where we do all functions
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {}

	@Override
	public void mouseExited(MouseEvent mouse) {}

	@Override
	public void mousePressed(MouseEvent mouse) {}

	@Override
	public void mouseReleased(MouseEvent mouse) {}

	@Override
	public void mouseWheelMoved(MouseWheelEvent mousewheel) {}
}
