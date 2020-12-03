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
	
	// Table Names
	private static String databaseTables[] = {"fatalities", "location", "storm", "stormpath", "tornadodetails"};
	
	// Admin Update/Insert Variables
	private static JLabel adminLabel = new JLabel("Update or Insert Data - Select Table");
	private static JButton adminUpdateButton = new JButton("Update Row");
	private static JButton adminInsertButton = new JButton("Insert Row");
	private static JComboBox adminDDBox = new JComboBox(databaseTables);
	
	// Update/Insert and Search Separator
	private static JSeparator adminSeparator = new JSeparator(JSeparator.VERTICAL);
	
	// Admin Search Variables
		private static JLabel adminQueryLabel = new JLabel("Search Query");
		private static JTextArea adminQuery;
		private static JButton adminQuerySubmit = new JButton("Submit Search");
	
		
	// "Fatalities" Fields
	private static JLabel adminFatFatalityIDLabel = new JLabel("FatalityID");
	private static JTextField adminFatFatalityID = new JTextField();
	private static JLabel adminFatEventIDLabel = new JLabel("EventID");
	private static JTextField adminFatEventID = new JTextField();
	private static JLabel adminFatAgeLabel = new JLabel("Age");
	private static JTextField adminFatAge = new JTextField();
	private static JLabel adminFatGenderLabel = new JLabel("Gender");
	private static JTextField adminFatGender = new JTextField();
	private static JLabel adminFatTimeLabel = new JLabel("Time");
	private static JTextField adminFatTime = new JTextField();
	private static JLabel adminFatLocationLabel = new JLabel("Location");
	private static JTextField adminFatLocation = new JTextField();
	private static JLabel adminFatTypeLabel = new JLabel("Type");
	private static JTextField adminFatType = new JTextField();
	
	private static JLabel adminFatalityLabels[] = {
			adminFatFatalityIDLabel,
			adminFatEventIDLabel,
			adminFatAgeLabel,
			adminFatGenderLabel,
			adminFatTimeLabel,
			adminFatLocationLabel,
			adminFatTypeLabel
		};
	
	private static JTextField adminFatalityTextFields[] = {
			adminFatFatalityID,
			adminFatEventID,
			adminFatAge,
			adminFatGender,
			adminFatTime,
			adminFatLocation,
			adminFatType
	};
	
	
	// "Location" Fields
	private static JLabel adminLocEventIDLabel = new JLabel("EventID");
	private static JTextField adminLocEventID = new JTextField();;
	private static JLabel adminLocLocationIndexLabel = new JLabel("LocationIndex");
	private static JTextField adminLocLocationIndex = new JTextField();;
	private static JLabel adminLocEpisodeIDLabel = new JLabel("EpisodeID");
	private static JTextField adminLocEpisodeID = new JTextField();;
	private static JLabel adminLocTownLabel = new JLabel("Town");
	private static JTextField adminLocTown = new JTextField();;
	private static JLabel adminLocLatitudeLabel = new JLabel("Latitude");
	private static JTextField adminLocLatitude = new JTextField();;
	private static JLabel adminLocLongitudeLabel = new JLabel("Longitude");
	private static JTextField adminLocLongitude = new JTextField();;
	
	private static JLabel adminLocationLabels[] = {
			adminLocEventIDLabel,
			adminLocLocationIndexLabel,
			adminLocEpisodeIDLabel,
			adminLocTownLabel,
			adminLocLatitudeLabel,
			adminLocLongitudeLabel
	};
	
	private static JTextField adminLocationTextFields[] = {
		adminLocEventID,
		adminLocLocationIndex,
		adminLocEpisodeID,
		adminLocTown,
		adminLocLatitude,
		adminLocLongitude
	};
	
	
	// "Storm" Fields
	private static JLabel adminStormEventIDLabel = new JLabel("EventID");
	private static JTextField adminStormEventID = new JTextField();
	private static JLabel adminStormEpisodeIDLabel = new JLabel("EpisodeID");
	private static JTextField adminStormEpisodeID = new JTextField();
	private static JLabel adminStormStormTypeLabel = new JLabel("StormType");
	private static JTextField adminStormStormType = new JTextField();
	private static JLabel adminStormBeginDateLabel = new JLabel("BeginDate");
	private static JTextField adminStormBeginDate = new JTextField();
	private static JLabel adminStormEndDateLabel = new JLabel("EndDate");
	private static JTextField adminStormEndDate = new JTextField();
	private static JLabel adminStormStateLabel = new JLabel("State");
	private static JTextField adminStormState = new JTextField();
	private static JLabel adminStormPropertyDamageLabel = new JLabel("PropertyDamage");
	private static JTextField adminStormPropertyDamage = new JTextField();
	private static JLabel adminStormCropDamageLabel = new JLabel("CropDamage");
	private static JTextField adminStormCropDamage = new JTextField();
	private static JLabel adminStormInjuriesDirectLabel = new JLabel("InjuriesDirect");
	private static JTextField adminStormInjuriesDirect = new JTextField();
	private static JLabel adminStormDeathsDirectLabel = new JLabel("DeathsDirect");
	private static JTextField adminStormDeathsDirect = new JTextField();
	private static JLabel adminStormMagnitudeLabel = new JLabel("Magnitude");
	private static JTextField adminStormMagnitude = new JTextField();
	private static JLabel adminStormMagnitudeTypeLabel = new JLabel("MagnitudeType");
	private static JTextField adminStormMagnitudeType = new JTextField();
	private static JLabel adminStormEventNarrativeLabel = new JLabel("event_narrative");
	private static JTextField adminStormEventNarrative = new JTextField();
	private static JLabel adminStormEpisodeNarrativeLabel = new JLabel("EpisodeID");
	private static JTextField adminStormEpisodeNarrative = new JTextField();
	
	private static JLabel adminStormLabels[] = {
		adminStormEventIDLabel,
		adminStormEpisodeIDLabel,
		adminStormStormTypeLabel,
		adminStormBeginDateLabel,
		adminStormEndDateLabel,
		adminStormStateLabel,
		adminStormPropertyDamageLabel,
		adminStormCropDamageLabel,
		adminStormInjuriesDirectLabel,
		adminStormDeathsDirectLabel,
		adminStormMagnitudeLabel,
		adminStormMagnitudeTypeLabel,
		adminStormEventNarrativeLabel,
		adminStormEpisodeNarrativeLabel
	};
	
	private static JTextField adminStormTextFields[] = {
		adminStormEventID,
		adminStormEpisodeID,
		adminStormStormType,
		adminStormBeginDate,
		adminStormEndDate,
		adminStormState,
		adminStormPropertyDamage,
		adminStormCropDamage,
		adminStormInjuriesDirect,
		adminStormDeathsDirect,
		adminStormMagnitude,
		adminStormMagnitudeType,
		adminStormEventNarrative,
		adminStormEpisodeNarrative
	};
	
	
	
	// Storm Path Fields
	private static JLabel adminPathEventIDLabel = new JLabel("EventID");
	private static JTextField adminPathEventID = new JTextField();
	private static JLabel adminPathRangeLabel = new JLabel("begin_range");
	private static JTextField adminPathRange = new JTextField();
	private static JLabel adminPathAzimuthLabel = new JLabel("begin_azimuth");
	private static JTextField adminPathAzimuth = new JTextField();
	private static JLabel adminPathLocationLabel = new JLabel("begin_location");
	private static JTextField adminPathLocation = new JTextField();
	private static JLabel adminPathBeginLatLabel = new JLabel("begin_lat");
	private static JTextField adminPathBeginLat = new JTextField();
	private static JLabel adminPathBeginLonLabel = new JLabel("begin_lon");
	private static JTextField adminPathBeginLon = new JTextField();
	private static JLabel adminPathEndLatLabel = new JLabel("end_lat");
	private static JTextField adminPathEndLat = new JTextField();
	private static JLabel adminPathEndLonLabel = new JLabel("end_lon");
	private static JTextField adminPathEndLon = new JTextField();
	
	private static JLabel adminStormPathLabels[] = {
			adminPathEventIDLabel,
			adminPathRangeLabel,
			adminPathAzimuthLabel,
			adminPathLocationLabel,
			adminPathBeginLatLabel,
			adminPathBeginLonLabel,
			adminPathEndLatLabel,
			adminPathEndLonLabel
	};
	
	private static JTextField adminStormPathTextFields[] = {
			adminPathEventID,
			adminPathRange,
			adminPathAzimuth,
			adminPathLocation,
			adminPathBeginLat,
			adminPathBeginLon,
			adminPathEndLat,
			adminPathEndLon
	};
	
	
	
	// Tornado Details Fields
	private static JLabel adminTornadoEventIDLabel = new JLabel("EventID");
	private static JTextField adminTornadoEventID = new JTextField();
	private static JLabel adminTornadoFScaleLabel = new JLabel("tor_f_scale");
	private static JTextField adminTornadoFScale = new JTextField();
	private static JLabel adminTornadoLengthLabel = new JLabel("tor_length");
	private static JTextField adminTornadoLength = new JTextField();
	private static JLabel adminTornadoWidthLabel = new JLabel("tor_width");
	private static JTextField adminTornadoWidth = new JTextField();
	

	private static JLabel adminTornadoDetailsLabels[] = {
			adminTornadoEventIDLabel,
			adminTornadoFScaleLabel,
			adminTornadoLengthLabel,
			adminTornadoWidthLabel
	};
	
	private static JTextField adminTornadoDetailsTextFields[] = {
			adminTornadoEventID,
			adminTornadoFScale,
			adminTornadoLength,
			adminTornadoWidth
	};
	

	// User variables?
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
	
	//Search parameter variables
	private static JButton searchButton;
	private static JLabel parameterLB;
	private static JLabel columnsLB;
	private static JSeparator hr1;
	private static JSeparator hr2;
	private static JLabel stormTypeLB;
	private static JComboBox<String> stormTypeDropDown;
	private static JLabel stateLB;
	private static JTextField stateName;
	private static JLabel townLB;
	private static JTextField townName;
	private static JLabel dmgLB;
	private static JTextField dmgLowTB;
	private static JLabel dashLB;
	private static JTextField dmgHighTB;
	private static JCheckBox fatal;
	private static JLabel date;
	private static JTextField beginDate;
	private static JLabel dash2LB;
	private static JTextField endDate;
	private static JLabel tornadoDetails;
	private static JLabel tornadoMagLB;
	private static JComboBox<String> efScaleDropDown;
	private static JLabel tornadoWidthLB;
	private static JTextField tornadoWidth;
	private static JLabel tornadoLengthLB;
	private static JTextField tornadoLength;
	private static JSeparator hr3;
	
	JPanel cards; //a panel that uses CardLayout
    final static String ADMIN = "Admin";
    final static String USER = "User";
    
    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel menu = new JPanel(); //use FlowLayout, could maybe add more things to this
        String pages[] = {USER, ADMIN};
        JComboBox cb = new JComboBox(pages);
        cb.setEditable(false);
        cb.addItemListener(this);
        menu.add(cb);
         
        //Create the "cards". TODO: maybe add a menu card
        JPanel card1 = new JPanel();
        createUserPage(card1);
        JPanel card2 = new JPanel();
        createAdminPage(card2);
         
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, USER);
        cards.add(card2, ADMIN);
         
        pane.add(menu, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }
    
    public void itemStateChanged(ItemEvent evt) {
    	if (evt.getSource() == adminDDBox && evt.getStateChange() == ItemEvent.SELECTED) {
    		if (evt.getItem() == "fatalities") {
    			showItems(adminFatalityLabels, adminFatalityTextFields);
    		}
    		else if (evt.getItem() == "location") {
    			showItems(adminLocationLabels, adminLocationTextFields);
    		}
    		else if (evt.getItem() == "storm") {
    			showItems(adminStormLabels, adminStormTextFields);
    		}
    		else if (evt.getItem() == "stormpath") {
    			showItems(adminStormPathLabels, adminStormPathTextFields);
    		}
    		else if (evt.getItem() == "tornadodetails") {
    			showItems(adminTornadoDetailsLabels, adminTornadoDetailsTextFields);
    		}
    	}
    	else if (evt.getSource() == adminDDBox && evt.getStateChange() == ItemEvent.DESELECTED) {
    		if (evt.getItem() == "fatalities") {
    			hideItems(adminFatalityLabels, adminFatalityTextFields);
    		}
    		else if (evt.getItem() == "location") {
    			hideItems(adminLocationLabels, adminLocationTextFields);
    		}
    		else if (evt.getItem() == "storm") {
    			hideItems(adminStormLabels, adminStormTextFields);
    		}
    		else if (evt.getItem() == "stormpath") {
    			hideItems(adminStormPathLabels, adminStormPathTextFields);
    		}
    		else if (evt.getItem() == "tornadodetails") {
    			hideItems(adminTornadoDetailsLabels, adminTornadoDetailsTextFields);
    		}
    	}
    	else {
    		CardLayout cl = (CardLayout)(cards.getLayout());
    		cl.show(cards, (String)evt.getItem());
    	}
    }
    
    public void showItems(JLabel[] labels, JTextField[] textFields) {
    	for (int i = 0; i < labels.length; i++) {
    		labels[i].setVisible(true);
    		textFields[i].setVisible(true);
    	}
    }
    
    public void hideItems(JLabel[] labels, JTextField[] textFields) {
    	for (int i = 0; i < labels.length; i++) {
    		labels[i].setVisible(false);
    		textFields[i].setVisible(false);
    	}
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
    
    public void createAdminPage(JPanel card) {
    	card.setLayout(null);
    	card.setPreferredSize(new Dimension(810, 650));
    	
    	// Admin Update Label
    	adminLabel.setBounds(10, 10, 250, 25);
    	
    	// Admin Update Dropdown Box
    	adminDDBox.setBounds(10, 40, 100, 25);
    	adminDDBox.setEditable(false);
    	adminDDBox.addItemListener(this);
    	
    	// Admin Update Submit Button;
    	adminUpdateButton.setBounds(125, 40, 125, 25);
    	adminUpdateButton.addMouseListener(new GUIInterface());
    	
    	// Admin Insert Submit Button
    	adminInsertButton.setBounds(260, 40, 125, 25);
    	adminInsertButton.addMouseListener(new GUIInterface());
    	
    	card.add(adminLabel);
    	card.add(adminDDBox);
    	card.add(adminUpdateButton);
    	card.add(adminInsertButton);
    	
    	
    	
    	// Fatality Fields (update + insert)
    	for (int i = 0; i < adminFatalityLabels.length; i++) {
    		adminFatalityLabels[i].setBounds(10, 80 + (i * 30), 100, 25);
    		adminFatalityTextFields[i].setBounds(150, 80 + (i * 30), 100, 25);
    		
    		card.add(adminFatalityLabels[i]);
    		card.add(adminFatalityTextFields[i]);
    		
    		//adminUpdateFatalityLabels[i].setVisible(false);
    		//adminUpdateFatalityTextFields[i].setVisible(false);
    	}
    	
    	// Location Fields
    	for (int i = 0; i < adminLocationLabels.length; i++) {
    		adminLocationLabels[i].setBounds(10, 80 + (i * 30), 100, 25);
    		adminLocationTextFields[i].setBounds(150, 80 + (i * 30), 100, 25);
    		
    		card.add(adminLocationLabels[i]);
    		card.add(adminLocationTextFields[i]);
    		
    		adminLocationLabels[i].setVisible(false);
    		adminLocationTextFields[i].setVisible(false);
    	}
    	
    	// Storm Fields
    	for (int i = 0; i < adminStormLabels.length; i++) {
    		adminStormLabels[i].setBounds(10, 80 + (i * 30), 100, 25);
    		adminStormTextFields[i].setBounds(150, 80 + (i * 30), 100, 25);
    		
    		card.add(adminStormLabels[i]);
    		card.add(adminStormTextFields[i]);
    		
    		adminStormLabels[i].setVisible(false);
    		adminStormTextFields[i].setVisible(false);
    	}
    	
    	// Storm Path Fields
    	for (int i = 0; i < adminStormPathLabels.length; i++) {
    		adminStormPathLabels[i].setBounds(10, 80 + (i * 30), 100, 25);
    		adminStormPathTextFields[i].setBounds(150, 80 + (i * 30), 100, 25);
    		
    		card.add(adminStormPathLabels[i]);
    		card.add(adminStormPathTextFields[i]);
    		
    		adminStormPathLabels[i].setVisible(false);
    		adminStormPathTextFields[i].setVisible(false);
    	}
    	
    	// Storm Path Fields
    	for (int i = 0; i < adminTornadoDetailsLabels.length; i++) {
    		adminTornadoDetailsLabels[i].setBounds(10, 80 + (i * 30), 100, 25);
    		adminTornadoDetailsTextFields[i].setBounds(150, 80 + (i * 30), 100, 25);
    		
    		card.add(adminTornadoDetailsLabels[i]);
    		card.add(adminTornadoDetailsTextFields[i]);
    		
    		adminTornadoDetailsLabels[i].setVisible(false);
    		adminTornadoDetailsTextFields[i].setVisible(false);
    	}
    	
    	
    	
    	// Admin Separator
    	adminSeparator.setBounds(425, 10, 3, 300);
    	card.add(adminSeparator);
    	
    	// Admin Search
    	adminQueryLabel.setBounds(475, 10, 100, 25);
    	
    	adminQuery = new JTextArea("Enter Valid SQL Query");
    	adminQuery.setBounds(475, 40, 300, 100);
    	adminQuery.setLineWrap(true);
    	
    	adminQuerySubmit.setBounds(475, 150, 150, 25);
    	adminQuerySubmit.addMouseListener(new GUIInterface());
    	
    	card.add(adminQueryLabel);
    	card.add(adminQuery);
    	card.add(adminQuerySubmit);
    	
    	
    }
    
    public static void createUserPage(JPanel card) {
    	/* -------------------first section (check boxes)--------------------------------- */
    	columnsLB = new JLabel("Select which columns you would like to search for...");
    	columnsLB.setBounds(5, 10, 300, 40);
    	
    	hr1 = new JSeparator();
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
    	parameterLB = new JLabel("Select which parameters you would like to search by (leave field blank or select N/A if you don't want to use it)...");
    	parameterLB.setBounds(5, 120, 700, 40);
    	card.add(parameterLB);
    	
    	hr2 = new JSeparator();
    	hr2.setBounds(6, 150, 700, 3);
    	card.add(hr2);
    	
    	stormTypeLB = new JLabel("Storm Type: ");
    	stormTypeLB.setBounds(10, 160, 70, 30);
    	String[] stormT = {"N/A", "Dense Fog", "High Wind", "Flood", "Winter Storm", "Heavy Snow", "Winter Weather", "Strong Wind", "Blizzard", "Marine Thunderstorm Wind", "Thunderstorm Wind",
    			"Lakeshore Flood", "High Surf", "Flash Flood", "Ice Storm", "Heavy Rain", "Tornado", "Hail", "Avalanche", "Drought", "Frost/Freeze", "Debris Flow"};
    	stormTypeDropDown = new JComboBox<String>(stormT);
    	stormTypeDropDown.setSelectedIndex(0);
    	stormTypeDropDown.setBounds(85, 160, 175, 30);
    	card.add(stormTypeLB);
    	card.add(stormTypeDropDown);
    	
    	stateLB = new JLabel("State: ");
    	stateLB.setBounds(285, 160, 35, 30);
    	stateName = new JTextField(15);
    	stateName.setBounds(325, 160, 90, 30);
    	card.add(stateLB);
    	card.add(stateName);
    	
    	townLB = new JLabel("Town: ");
    	townLB.setBounds(425, 160, 40, 30);
    	townName = new JTextField(15);
    	townName.setBounds(465, 160, 100, 30);
    	card.add(townLB);
    	card.add(townName);
    	
    	dmgLB = new JLabel("Damage (low - high): ");
    	dmgLB.setBounds(10, 210, 120, 30);
    	dmgLowTB = new JTextField(15);
    	dmgLowTB.setBounds(135, 210, 80, 30);
    	dashLB = new JLabel("-");
    	dashLB.setBounds(220, 210, 10, 30);
    	dmgHighTB = new JTextField(15);
    	dmgHighTB.setBounds(230, 210, 80, 30);
    	card.add(dashLB);
    	card.add(dmgLB);
    	card.add(dmgLowTB);
    	card.add(dmgHighTB);
    	
    	fatal = new JCheckBox("Fatal (check for storms that resulted in 1 or more deaths)");
    	fatal.setBounds(320, 210, 350, 30);
    	card.add(fatal);
    	
    	date = new JLabel("Start Date - End Date (yyyy-mm-dd): ");
    	date.setBounds(10, 250, 200, 30);
    	beginDate = new JTextField(15);
    	beginDate.setBounds(215, 250, 80, 30);
    	dash2LB = new JLabel("-");
    	dash2LB.setBounds(300, 250, 10, 30);
    	endDate = new JTextField(15);
    	endDate.setBounds(310, 250, 80, 30);
    	card.add(date);
    	card.add(beginDate);
    	card.add(endDate);
    	card.add(dash2LB);
    	
    	tornadoDetails = new JLabel("---------- Tornado Details will only be used if StormType = Tornado ----------");
    	tornadoDetails.setBounds(170, 380, 400, 30);
    	card.add(tornadoDetails);
    	
    	String[] efScale = {"N/A","EF0","EF1","EF2","EF3","EF4","EF5"};
    	tornadoMagLB = new JLabel("Tornado Magnitude (EF Scale): ");
    	tornadoMagLB.setBounds(10, 300, 175, 30);
    	efScaleDropDown = new JComboBox<String>(efScale);
    	efScaleDropDown.setSelectedIndex(0);
    	efScaleDropDown.setBounds(185, 300, 100, 30);
    	card.add(tornadoMagLB);
    	card.add(efScaleDropDown);
    	
    	tornadoWidthLB = new JLabel("Tornado Width (e.g. <, >, <=): ");
    	tornadoWidthLB.setBounds(305, 300, 170, 30);
    	tornadoWidth = new JTextField();
    	tornadoWidth.setBounds(475, 300, 100, 30);
    	card.add(tornadoWidthLB);
    	card.add(tornadoWidth);
    	
    	tornadoLengthLB = new JLabel("Tornado Length (e.g. <, >, <=): ");
    	tornadoLengthLB.setBounds(305, 335, 170, 30);
    	tornadoLength = new JTextField();
    	tornadoLength.setBounds(475, 335, 100, 30);
    	card.add(tornadoLengthLB);
    	card.add(tornadoLength);
    	
    	
    	/* -------------------end details section--------------------------------- */
    	
    	hr3 = new JSeparator();
    	hr3.setBounds(6, 410, 700, 3);
    	card.add(hr3);
    	
    	searchButton = new JButton("Search");
    	searchButton.setBounds(280, 460, 200, 30);
    	searchButton.addMouseListener(new GUIInterface());
    	card.add(searchButton);
    	
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
		if (mouse.getSource() == searchButton) {
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
			ArrayList<String> columns = new ArrayList<String>();
			HashMap<String, String> parameters = new HashMap<String, String>();
			
//-------------------------------------------create columns----------------------------------------------------------------------------
			if(stormType.isSelected()) {
				columns.add("StormType");
			}
			if(state.isSelected()) {
				columns.add("State");
			}
			if(city.isSelected()) {
				columns.add("Town");
			}
			if(property.isSelected()) {
				columns.add("PropertyDamage");
				columns.add("CropDamage");
			}
			if(eventNar.isSelected()) {
				columns.add("event_narrative");
			}
			if(beginD.isSelected()) {
				columns.add("BeginDate");
			}
			if(endD.isSelected()) {
				columns.add("EndDate");
			}
			if(tScale.isSelected()) {
				columns.add("tor_f_scale");
			}
			if(fatalities.isSelected()) {
				columns.add("DeathsDirect");
			}
			if(tWidth.isSelected()) {
				columns.add("tor_width");
			}
			if(tLength.isSelected()) {
				columns.add("tor_length");
			}
			
//-----------------------------------------create parameters-----------------------------------------------------------
			//handle stormType
			if(!stormTypeDropDown.getSelectedItem().equals("N/A")) {
				parameters.put("StormType", (String)stormTypeDropDown.getSelectedItem());
			}
			else {
				parameters.put("StormType", null);
			}
			
			//handle State
			if(!stateName.getText().equals("")) {
				parameters.put("State", stateName.getText());
			}
			else {
				parameters.put("State", null);
			}
			
			//handle Town
			if(!stateName.getText().equals("")) {
				parameters.put("Town", townName.getText());
			}
			else {
				parameters.put("State", null);
			}
			
			//handle damage
			if(dmgLowTB.getText().equals("") || dmgHighTB.getText().equals("")) {
				parameters.put("Damage", null);
			}
			else {
				parameters.put("Damage", dmgLowTB.getText().equals("")+"-"+dmgHighTB.getText().equals(""));
			}
		
			//handle fatal
			if(fatal.isSelected()) {
				parameters.put("Fatal", "yes");
			}
			else {
				parameters.put("Fatal", "no");
			}
			
			//handle date
			if(beginDate.getText().equals(null) || endDate.getText().equals(null)) {
				parameters.put("BeginDate", null);
			}
			else {
				parameters.put("BeginDate", beginDate.getText());
				parameters.put("EndDate", endDate.getText());
			}
			
			//handle tornado details
			if(stormTypeDropDown.getSelectedItem().equals("Tornado")) {
				if(!efScaleDropDown.getSelectedItem().equals("N/A")) {
					parameters.put("torScale", (String)efScaleDropDown.getSelectedItem());
				}
				else {
					parameters.put("torScale", null);
				}
				
				if(!tornadoWidth.getText().equals("")) {
					parameters.put("torWidth", tornadoWidth.getText());
				}
				else {
					parameters.put("torWidth", null);
				}
				
				if(!tornadoLength.getText().equals("")) {
					parameters.put("torWidth", tornadoLength.getText());
				}
				else {
					parameters.put("torLength", null);
				}
			}
			
			String tableName = "Search Results";
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
			
			JFrame frame = new JFrame();
			frame.setVisible(true);
			GUIInterface panel = new GUIInterface();
			DefaultTableModel model = new DefaultTableModel();
			frame.setSize(800, 600);
			frame.add(panel);
			frame.setTitle("Admin table");
			frame.setLocationRelativeTo(null);

			JTable table = new JTable(model);
			table.setShowGrid(true);
			table.setGridColor(Color.black);
			JScrollPane sp = new JScrollPane(table);
			
			String tableName = "Admin table";
			String output = DatabaseManager.handleSQLCommand(contents);
			String line[] = output.split("\n");
			String headers[] = line[1].split(",");
			


			for(int i=0; i<headers.length; i++) {
				if(headers[i].indexOf("=") != -1) {
					model.addColumn(headers[i].substring(0, headers[i].indexOf("=")));
				}
			}
			sp.setPreferredSize(new Dimension(headers.length * 70, 300));
			
			for (String token : line) {
				if(!token.isEmpty()) {
					token = token.replace("{", "");
					token = token.replace("}", "");
					String row[] = token.split(","); //FIXME: not work for column has , in their data. can fix by split using regex
					ArrayList<String> single_row = new ArrayList<String>();
					for (String rowToken : row) {
						String elem[] = rowToken.split("=");
						if (elem.length == 2) {
							single_row.add(elem[1]);
						}
						else {
							single_row.add("-1");
						}
					}
					model.addRow(single_row.toArray());
				}
			}
			panel.add(sp);
			
			
		}
		
		else if (mouse.getSource() == adminUpdateButton) {
			System.out.println("FIXME: formulate update query");
		}
		
		else if (mouse.getSource() == adminInsertButton) {
			System.out.println("FIXME: formulate insert query");
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
