import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

public class Watch extends JFrame
			implements ActionListener, ItemListener, Subject {
	Button Close;
	JRadioButton red, green, blue;
	Vector observers;

	public Watch() {
		super("Change 2 other frames");
		//  list of observing frames
		observers = new Vector();
		//  add panel to content pane
		JPanel p = new JPanel(true);
		p.setLayout(new BorderLayout());
		getContentPane().add("Center", p);
		//  vertical box layout
		Box box = new Box(BoxLayout.Y_AXIS);

		p.add("Center", box);
		//  add 3 radio buttons
		box.add(red = new JRadioButton("Red"));
		box.add(green = new JRadioButton("Green"));
		box.add(blue = new JRadioButton("Blue"));
		//  listen for clicks on radio buttons
		blue.addItemListener(this);
		red.addItemListener(this);
		green.addItemListener(this);
		//  make all part of same button group
		ButtonGroup bgr = new ButtonGroup();
		bgr.add(red);
		bgr.add(green);
		bgr.add(blue);
		pack();
		setVisible(true);
		// create observers
		ColorFrame cframe = new ColorFrame(this);
		ListFrame lframe = new ListFrame(this);
		
	}
	public void itemStateChanged(ItemEvent e) {
		//  responds to radio button clicks
		//  if the button is selected
			if (e.getStateChange() == ItemEvent.SELECTED)
				notifyObservers((JRadioButton) e.getSource());
		}

		private void notifyObservers(JRadioButton rad) {
		//  sends text of selected button to all observers
			String color = rad.getText();
			for (int i = 0; i < observers.size(); i++) 
				((Observer) (observers.elementAt(i))).sendNotify(color);
		}

		public void registerInterest(Observer obs) {
			observers.addElement(obs);
		}
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		public static void main(String[] arg){
			Watch w = new Watch();
		}
		

	} // class Watch