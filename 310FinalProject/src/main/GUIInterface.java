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
import javax.swing.JSeparator;
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
	private static JCheckBox stormType;
	private static JCheckBox state;
	private static JCheckBox city;
	private static JCheckBox property;
	private static JCheckBox eventNar;
	private static JCheckBox beginD;
	private static JCheckBox endD;
	private static JCheckBox tScale;
	private static JCheckBox fatalities;
	private static JCheckBox tWidth;
	private static JCheckBox tLength;
	
	JPanel cards; //a panel that uses CardLayout
    final static String FUNCTIONS = "JDBC Functions";
    final static String ADMIN = "Admin";
    final static String USER = "User";
    
    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel menu = new JPanel(); //use FlowLayout, could maybe add more things to this
        String pages[] = {FUNCTIONS, ADMIN, USER };
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
    	/* -------------------first section (check boxes)--------------------------------- */
    	JLabel columnsLB = new JLabel("Select which columns you would like to search for...");
    	columnsLB.setBounds(5, 10, 300, 40);
    	
    	JSeparator hr1 = new JSeparator();
    	hr1.setBounds(6, 49, 700, 3);
    	
    	stormType = new JCheckBox("storm type");
    	stormType.setBounds(10, 50, 90, 30);
    	
    	state = new JCheckBox("state"); 
    	state.setBounds(100, 50, 60, 30);
    	
    	city = new JCheckBox("city"); 
    	city.setBounds(165, 50, 50, 30);  
    	
    	property = new JCheckBox("damage");
    	property.setBounds(220, 50, 80, 30);
    	
    	eventNar = new JCheckBox("event narrative");
    	eventNar.setBounds(305, 50, 120, 30);
    	
    	beginD = new JCheckBox("begin date"); 
    	beginD.setBounds(430, 50, 90, 30);
    	
    	endD = new JCheckBox("end date"); 
    	endD.setBounds(525, 50, 80, 30); 
    	
    	tScale = new JCheckBox("tornado scale"); 
    	tScale.setBounds(610, 50, 100, 30); 
    	
    	fatalities = new JCheckBox("No. fatalities");
    	fatalities.setBounds(10, 80, 100, 30);
    	
    	tWidth = new JCheckBox("tornado width"); 
    	tWidth.setBounds(115, 80, 100, 30); 
    	
    	tLength = new JCheckBox("tornado length"); 
    	tLength.setBounds(220, 80, 120, 30); 
    	
    	card.add(columnsLB);
    	card.add(hr1);
    	card.add(stormType);
    	card.add(state);
    	card.add(city);
    	card.add(property);
    	card.add(eventNar);
    	card.add(beginD);
    	card.add(endD);
    	card.add(tScale);
    	card.add(fatalities);
    	card.add(tWidth);
    	card.add(tLength);
    	/* -------------------end first section--------------------------------- */
    	
    	/* -------------------details section----------------------------------- */
    	JLabel parameterLB = new JLabel("Select which parameters you would like to search by (leave field blank or select N/A if you don't want to use it)...");
    	parameterLB.setBounds(5, 120, 700, 40);
    	card.add(parameterLB);
    	
    	JSeparator hr2 = new JSeparator();
    	hr2.setBounds(6, 150, 700, 3);
    	card.add(hr2);
    	
    	String[] stormT = {"N/A", "Dense Fog", "High Wind", "Flood", "Winter Storm", "Heavy Snow", "Winter Weather", "Strong Wind", "Blizzard", "Marine Thunderstorm Wind", "Thunderstorm Wind",
    						"Lakeshore Flood", "High Surf", "Flash Flood", "Ice Storm", "Heavy Rain", "Tornado", "Hail", "Avalanche", "Drought", "Frost/Freeze", "Debris Flow"};
    	JLabel stormTypeLB = new JLabel("Storm Type: ");
    	stormTypeLB.setBounds(10, 160, 70, 30);
    	JComboBox<String> stormType = new JComboBox<String>(stormT);
    	stormType.setSelectedIndex(0);
    	stormType.setBounds(85, 160, 175, 30);
    	card.add(stormTypeLB);
    	card.add(stormType);
    	
    	JLabel stateLB = new JLabel("State: ");
    	stateLB.setBounds(285, 160, 35, 30);
    	JTextField stateName = new JTextField(15);
    	stateName.setBounds(325, 160, 90, 30);
    	card.add(stateLB);
    	card.add(stateName);
    	
    	JLabel townLB = new JLabel("Town: ");
    	townLB.setBounds(425, 160, 40, 30);
    	JTextField townName = new JTextField(15);
    	townName.setBounds(465, 160, 100, 30);
    	card.add(townLB);
    	card.add(townName);
    	
    	JLabel dmgLB = new JLabel("Damage (low - high): ");
    	dmgLB.setBounds(10, 210, 120, 30);
    	JTextField dmgLowTB = new JTextField(15);
    	dmgLowTB.setBounds(135, 210, 80, 30);
    	JLabel dashLB = new JLabel("-");
    	dashLB.setBounds(220, 210, 10, 30);
    	JTextField dmgHighTB = new JTextField(15);
    	dmgHighTB.setBounds(230, 210, 80, 30);
    	card.add(dashLB);
    	card.add(dmgLB);
    	card.add(dmgLowTB);
    	card.add(dmgHighTB);
    	
    	JCheckBox fatal = new JCheckBox("Fatal (check for storms that resulted in 1 or more deaths)");
    	fatal.setBounds(320, 210, 350, 30);
    	card.add(fatal);
    	
    	JLabel date = new JLabel("Start Date - End Date (yyyy-mm-dd): ");
    	date.setBounds(10, 250, 200, 30);
    	JTextField beginDate = new JTextField(15);
    	beginDate.setBounds(215, 250, 80, 30);
    	JLabel dash2LB = new JLabel("-");
    	dash2LB.setBounds(300, 250, 10, 30);
    	JTextField endDate = new JTextField(15);
    	endDate.setBounds(310, 250, 80, 30);
    	card.add(date);
    	card.add(beginDate);
    	card.add(endDate);
    	card.add(dash2LB);
    	
    	JLabel tornadoDetails = new JLabel("Tornado Details (these will only be used if StormType = Tornado):");
    	tornadoDetails.setBounds(10, 290, 400, 30);
    	card.add(tornadoDetails);
    	
    	/* -------------------end details section--------------------------------- */
    	
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
