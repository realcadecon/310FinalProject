package main;

	import java.awt.BorderLayout;
//Standard Library Imports
	import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener; 
	
	import java.awt.event.MouseEvent; 
	import java.awt.event.MouseListener;
	
	import java.awt.event.MouseWheelEvent;
	import java.awt.event.MouseWheelListener;
	
	import java.awt.event.WindowAdapter;
	import java.awt.event.WindowEvent;
	
	import java.awt.Color;
	
	import java.awt.Font;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.SwingConstants;
	
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.text.DecimalFormat;
	

//Manager Imports
	import database.DatabaseManager;
	import error.ErrorManager;
	
public class Dashboard extends JPanel implements MouseListener, MouseWheelListener, KeyListener {
	
	private static JFrame frame = new JFrame();
	public static JButton yearRev;
	public static JPanel mainPanel;
	
	
	public static void runDash(JPanel card) {
		card.setSize(1100, 1000);
		DecimalFormat df = new DecimalFormat("#.##");
		
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
	public void mouseClicked(MouseEvent mouse) {}
	

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
