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
	import java.util.ArrayList;
	import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
//Swing Imports
	import javax.swing.JComboBox;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.UIManager;
	import javax.swing.JTextField;
	import javax.swing.UIManager;
	import javax.swing.UnsupportedLookAndFeelException;
	import javax.swing.table.DefaultTableModel;
	import javax.swing.JTextField;
	import javax.swing.JTextArea;
	import javax.swing.ButtonGroup;
	import javax.swing.JRadioButton;

//Manager Imports
	import database.DatabaseManager;
	import error.ErrorManager;


public class GUIInterface extends JPanel implements MouseListener, MouseWheelListener, KeyListener, ItemListener {
	//SWING Variables
	private static JButton buttonEX;
	private static JLabel labelLB;
	
	// Admin Search Variables
	private static JLabel adminQueryLabel;
	private static JTextArea adminQuery;
	private static JButton adminQuerySubmit;
	
	// Admin Table Select Variables
	private static ButtonGroup adminTableSelectGroup;
	private static JRadioButton adminStormTable;
	private static JRadioButton adminLocationTable;
	private static JRadioButton adminFatalityTable;
	private static JRadioButton adminTornadoDetailsTable;
	private static JRadioButton adminStormPathTable;
	
	// Admin Add or Update
	/*
	private static JLabel adminEventIdLabel;
	private static JTextField adminEventId;
	private static JButton admin
	*/
	
	
	JPanel cards; //a panel that uses CardLayout
    final static String FUNCTIONS = "JDBC Functions";
    final static String DASHBOARD = "Dashboard";
    final static String ADMIN = "Admin";
    final static String USER = "User";
    
    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel menu = new JPanel(); //use FlowLayout, could maybe add more things to this
        String pages[] = {FUNCTIONS, DASHBOARD, ADMIN, USER };
        JComboBox cb = new JComboBox(pages);
        cb.setEditable(false);
        cb.addItemListener(this);
        menu.add(cb);
         
        //Create the "cards". TODO: maybe add a menu card
        JPanel card1 = new JPanel();
        createFunctionsPage(card1);
        JPanel card3 = new JPanel();
        createAdminPage(card3);
        JPanel card4 = new JPanel();
        createUserPage(card4);
         
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, FUNCTIONS);
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
    	card.setLayout(null);
    	card.setPreferredSize(new Dimension(810, 650));
    }
    
    public static void createFunctionsPage(JPanel card) {
    	card.setLayout(null);
    	card.setPreferredSize(new Dimension(810, 650));
    	labelLB = new JLabel("jdb-get-view:");
		labelLB.setBounds(10, 450, 100, 25);
		buttonEX = new JButton("jdb-get-view");
		buttonEX.setBounds(10, 480, 200, 25);
		buttonEX.addMouseListener(new GUIInterface());
		card.add(labelLB);
		card.add(buttonEX);
    }
    
    public static void createAdminPage(JPanel card) {
    	card.setLayout(null);
    	card.setPreferredSize(new Dimension(810, 650));
    	
    	// Admin Search
    	adminQueryLabel = new JLabel("Search Query");
    	adminQueryLabel.setBounds(10, 10, 100, 25);
    	adminQuery = new JTextArea("Enter Valid SQL Query");
    	adminQuery.setBounds(10, 40, 300, 100);
    	adminQuery.setLineWrap(true);
    	adminQuerySubmit = new JButton("Search Query");
    	adminQuerySubmit.setBounds(10, 150, 150, 25);
    	adminQuerySubmit.addMouseListener(new GUIInterface());
    	
    	card.add(adminQueryLabel);
    	card.add(adminQuery);
    	card.add(adminQuerySubmit);
    	
    	/*
    	// Admin Table Select Variables
    	private static ButtonGroup adminTableSelectGroup;
    	private static JRadioButton adminStormTable;
    	private static JRadioButton adminLocationTable;
    	private static JRadioButton adminFatalityTable;
    	private static JRadioButton adminTornadoDetailsTable;
    	private static JRadioButton adminStormPathTable;
		*/
    	
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
    	
    	JButton button = new JButton("button");
    	button.setBounds(50,100,95,30);  
    	card.add(button);
    	card.setLayout(null);
    	card.setPreferredSize(new Dimension(810, 650));
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
		if (mouse.getSource() == buttonEX) {
			JFrame frame = new JFrame();
			frame.setVisible(true);
			GUIInterface panel = new GUIInterface();
			DefaultTableModel model = new DefaultTableModel();
			frame.setSize(800, 600);
			frame.add(panel);
			frame.setTitle("test");
			frame.setLocationRelativeTo(null);

			JTable table = new JTable(model);
			table.setShowGrid(true);
			table.setGridColor(Color.black);
			JScrollPane sp = new JScrollPane(table);
			
			//create ArrayList and HashMap here for the parameters of handleStormSearch
			ArrayList<String> columns = null;
			HashMap<String, String> parameters = null;
			
			
			
			String tableName = "test";
			String output = DatabaseManager.handleStormSearch(columns, parameters);
			String line[] = output.split("\n");
			String headers[] = line[1].split(",");

			System.out.println("---------------------");
			for(int i=0; i<headers.length; i++) {
				if(headers[i].indexOf("=") != -1) {
					System.out.println(headers[i].substring(0, headers[i].indexOf("=")));
					model.addColumn(headers[i].substring(0, headers[i].indexOf("=")));
				}
			}
			sp.setPreferredSize(new Dimension(headers.length * 70, 300));
			System.out.println("---------------------");
			for (String token : line) {
				if(!token.isEmpty()) {
					token = token.replace("{", "");
					token = token.replace("}", "");
					String row[] = token.split(","); //FIXME: not work for column has , in their data. can fix by split using regex
					ArrayList<String> single_row = new ArrayList<String>();
					for (String rowToken : row) {
						String elem[] = rowToken.split("=");
						single_row.add(elem[1]);
					}
					model.addRow(single_row.toArray());
				}
			}
			panel.add(sp);
		}
		
		else if (mouse.getSource() == adminQuerySubmit) {
			String contents = adminQuery.getText();
			System.out.println(DatabaseManager.handleSQLCommand(contents));
			System.out.println(contents);
		}
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
