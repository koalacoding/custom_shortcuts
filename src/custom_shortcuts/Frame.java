package custom_shortcuts;

import java.awt.AWTException;

import java.awt.Color;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static custom_shortcuts.PixelRobot.*;

public class Frame extends JFrame implements FocusListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int frameWidth = 0;
	private int frameHeight = 0;
	
	// In this JTextField will be entered the key the user wants to use as a shortcut key.
	JTextField shortcutField = new JTextField(10);
	
	JTextField screenWidthField = new JTextField(10);
	JTextField screenHeightField = new JTextField(10);
	
	JButton startButton = new JButton("Start");
 
	/*-------------------------------------------------
	---------------------------------------------------
	--------------CONSTRUCTOR------------
	---------------------------------------------------
	------------------------------------------------*/	
	
	public Frame(int width, int height) throws AWTException {
		super();
		
		this.frameWidth = width;
		this.frameHeight = height;
		
		shortcutField.addFocusListener((FocusListener) this);
		screenWidthField.addFocusListener((FocusListener) this);
		screenHeightField.addFocusListener((FocusListener) this);
		
		startButton.addActionListener(this);
		
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());

		build(); // Initializing of the window.
	}
	
	/*---------------------------------------------------------------
	-----------------------------------------------------------------
	--------------KEY EVENT DISPATCHER------------
	-----------------------------------------------------------------
	---------------------------------------------------------------*/	
	
    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
        		if (shortcutField.isFocusOwner() == true) {
        			shortcutField.setText("" + e.getKeyChar());
        		}
        		
        		else { // If the focus is not on the shortcut field.
        			 // If a character has been typed in the shortcut field.
    				if (shortcutField.getText().length() > 0 ) {
        				String strScreenWidth = screenWidthField.getText();
        				String strScreenHeight = screenHeightField.getText();
        				
            		    // If both screen dimension fields have numeric values.
	        			if (isStringNumeric(strScreenWidth) && isStringNumeric(strScreenHeight)) {
	        				int intScreenWidth = Integer.parseInt(strScreenWidth);
	        				int intScreenHeight =Integer.parseInt(strScreenHeight);
        			
	        				if (intScreenWidth > 0 && intScreenHeight > 0) {
			        			// If the key pressed is the same as the key entered in the shortcut field.
			        			if (e.getKeyChar() == shortcutField.getText().charAt(0)) {
			        				try {
			        					PixelRobot myRobot = new PixelRobot();
										myRobot.findPixelsAndClick(intScreenWidth, intScreenHeight);
									}
			        				
			        				catch (AWTException e1) {
										e1.printStackTrace();
									}
			        			}
	        				}
	        			}
    				}
        		}
            }
 
            else if (e.getID() == KeyEvent.KEY_RELEASED) {
            }
            
            else if (e.getID() == KeyEvent.KEY_TYPED) {
            }
            return false;
        }
    }
    
	/*----------------------------------------------
	------------------------------------------------
	--------------BUILD FRAME------------
	-----------------------------------------------
	---------------------------------------------*/	
    
	private void build() {
		setTitle("Custom shortcuts");
		setSize(this.frameWidth, this.frameHeight);
		setLocationRelativeTo(null); // Centers the frame in the middle of the screen.
		setResizable(false);
		setAlwaysOnTop (true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allow user to close application by clicking on the cross.
		setContentPane(buildContentPanel());
	}
	
	/*--------------------------------------------------------------
	----------------------------------------------------------------
	--------------BUILD FRAME CONTENT------------
	---------------------------------------------------------------
	-------------------------------------------------------------*/	
	
	private JPanel buildContentPanel(){
		JPanel panel = new JPanel();
		FlowLayout flow = new FlowLayout();
		flow.setHgap(20);
		panel.setLayout(flow);
		
		shortcutField.setEditable(false);
		shortcutField.setBackground(Color.WHITE);

		JLabel screenWidthLabel = new JLabel("Screen width : ");
			
		JLabel screenHeightLabel = new JLabel("Screen height : ");	
		
		JLabel shortcutLabel = new JLabel("Shortcut  : ");	
		
		panel.add(screenWidthLabel);
		panel.add(screenWidthField);
		
		panel.add(screenHeightLabel);
		panel.add(screenHeightField);
		
		panel.add(shortcutLabel);
		panel.add(shortcutField);
		
		panel.add(Box.createRigidArea(new Dimension(this.frameWidth, this.frameHeight / 50)));
		panel.add(startButton);
		
		
		return panel;
	}
	
	/*---------------------------------------------------
	-----------------------------------------------------
	--------------FOCUS HANDLER------------
	-----------------------------------------------------
	---------------------------------------------------*/	
	
	@Override
	public void focusGained(FocusEvent e) {
		e.getComponent().setBackground(Color.GREEN);
	}

	@Override
	public void focusLost(FocusEvent e) {
		e.getComponent().setBackground(Color.WHITE);
	}
	
	/*----------------------------------------------------------
	------------------------------------------------------------
	--------------IS STRING NUMERIC ?------------
	------------------------------------------------------------
	----------------------------------------------------------*/	
	
	public static boolean isStringNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.setExtendedState(JFrame.ICONIFIED);
		this.setExtendedState(this.getExtendedState() | JFrame.ICONIFIED);
	}	
}