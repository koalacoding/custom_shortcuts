package custom_shortcuts;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame extends JFrame implements FocusListener {
	// In this JTextField will be entered the key the user wants to use as a shortcut key.
	JTextField shortcutField = new JTextField(10);
	
	JTextField screenWidthField = new JTextField(10);
	JTextField screenHeightField = new JTextField(10);
	
    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
        		if (shortcutField.isFocusOwner() == true) {
        			shortcutField.setText("" + e.getKeyChar());
        			shortcutField.setBackground(Color.GREEN);
        		}
            }
            
            else if (e.getID() == KeyEvent.KEY_RELEASED) {
            }
            
            else if (e.getID() == KeyEvent.KEY_TYPED) {
            }
            return false;
        }
    }
    
	public Frame() throws AWTException {
		super();
		
		shortcutField.addFocusListener((FocusListener) this);
		screenWidthField.addFocusListener((FocusListener) this);
		screenHeightField.addFocusListener((FocusListener) this);		
		
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());

		build(); // Initializing of the window.
	}
	
	private void build() {
		setTitle("Custom shortcuts");
		setSize(320,240);
		setLocationRelativeTo(null); // Centers the frame in the middle of the screen.
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allow user to close application by clicking on the cross.
		setContentPane(buildContentPanel());
	}
	
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
		
		return panel;
	}

	@Override
	public void focusGained(FocusEvent e) {
		e.getComponent().setBackground(Color.GREEN);
	}

	@Override
	public void focusLost(FocusEvent e) {
		e.getComponent().setBackground(Color.WHITE);
	}
}