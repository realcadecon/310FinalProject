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

//Swing Imports
	import javax.swing.JComboBox;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.UIManager;
	import javax.swing.UnsupportedLookAndFeelException;
	import javax.swing.table.DefaultTableModel;

//Manager Imports
	import database.DatabaseManager;
	import error.ErrorManager;


public class GUIInterface extends JPanel implements MouseListener, MouseWheelListener, KeyListener, ItemListener {
	//SWING Variables
	private static JButton buttonEX;
	private static JLabel labelLB;
	
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
    	
    }
    
    public static void createUserPage(JPanel card) {
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
