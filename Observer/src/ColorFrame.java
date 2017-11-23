import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class ColorFrame extends JFrame implements Observer {
	Color color;
	String colorName = "black";
	JPanel p = new JPanel(true);

	public ColorFrame(Subject s) {
		super("Colors"); //set frame caption
		getContentPane().add("Center", p);
		s.registerInterest(this); //register with Subject
		setBounds(100, 100, 100, 100);
		setVisible(true);
	}

	public void sendNotify(String s) {
		//  Observer is notified of change here
		colorName = s; //save color name
		//  set background to that color
		if (s.toUpperCase().equals("RED"))	
			color = Color.red;
		if (s.toUpperCase().equals("BLUE"))
			color = Color.blue;
		if (s.toUpperCase().equals("GREEN"))
			color = Color.green;
		setBackground(color);
	}

	public void paint(Graphics g) {
		g.drawString(colorName, 20, 50);
	}
} // class ColorFrame
