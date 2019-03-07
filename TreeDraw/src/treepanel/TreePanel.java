//-----------------------------------
// Author: Joseph D. Lee
//
// Type: Supporting file/class
// Description: This program randomly generates/draws a tree. 
// It utilizes a recursive method to accomplish this by drawing a limb that branches of into two limbs, 
// which branches of into two limbs, etc. until the width of the branch has breached a certain threshold.
//
// Date Created: 2/9/16
// Date Modified: 3/6/18
//-----------------------------------




package treepanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TreePanel extends JPanel {

	private int height;  //Height and width of window.
	private int width;
	
	private final double WIDTH = 50;          //Initial width and variance thereof for the tree. Width indirectly corresponds with size/height of tree
	private final double WIDTH_VARIANCE = 20;
	
	private final double LENGTH = 3.5;
	private final double LENGTH_VARIANCE = 1.5;  //Multipliers for tree generation, adjust for interesting results/varied tree types.
	private final double SPLIT_LENGTH = 1.4;
	private final double SPLIT_ANGLE = 20;
	private final double SPLIT_LENGTH_VARIANCE = 0.3;
	private final double ANGLE_VARIANCE = 10;
	
	JButton reload = new JButton("Reload");  //Button for reloading window which in turn draws a new tree.
	
	public TreePanel(int w, int h) { //Constructor for custom JPanel
		this.setPreferredSize(new Dimension(w, h));
		this.height = h;
		this.width = w;
		reload.addActionListener(new ButtonListener());
		this.add(reload);
		
	}
	
	public void paintComponent(Graphics g) { //Overrides paintComponent method of JPanel
		super.paintComponent(g);
		
		this.height = this.getHeight();
		this.width = this.getWidth();
		
		g.setColor(new Color(180, 100, 10)); //Sets color to brown
		drawLimb(g, width/2, height - 50, WIDTH + ((Math.random()-0.5) * WIDTH_VARIANCE), 90); //Starts tree drawing with trunk as first limb

	}

	private void drawLimb(Graphics g, double x, double y, double w, double ang) { //Recursive method that draws a tree
		
		int count = (int)(w * LENGTH) + (int)(w * LENGTH*LENGTH_VARIANCE *(Math.random()-0.5)); //For a random length, draw limb straight.
		while (count > 0 && w > 0) {
			
			g.fillRect((int)(x-(w/2)), (int)y, (int)w, 1); //Draw line at point of width "w"
			
			y -= Math.sin(Math.toRadians(ang)); //Shifts point within given direction "äng".
			x += Math.cos(Math.toRadians(ang));
			
			x += Math.random() - 0.5; //Slightly randomizes point for jagged bark effect.
			
			if (w<2 && Math.random()>0.2) { //If small enough branch and random, draw leaf
				Color oldColor = g.getColor();
				g.setColor(new Color(0, 180, 0)); // set color to green
				g.drawLine((int)x, (int)y, (int)x, (int)y); //Draw green dot for leaf
				g.setColor(oldColor); //Revert to previous color.

			}
			w -= Math.random()/50; //Slighty reduces width while drawing straight section.
			count--;
		}
		if (w > 0.5) { //If not at smallest branch(adjust for bigger/smaller end branches)(CAUTION: smaller values may cause infinite recursion/stackoverflow exception:
			
				double new_ang1 = ang - (SPLIT_ANGLE + (ANGLE_VARIANCE * (Math.random())));
				double new_w1 = w/(SPLIT_LENGTH + ((Math.random()-0.5) * SPLIT_LENGTH_VARIANCE));
				drawLimb(g, x + ((new_w1/5)), y, new_w1, new_ang1);   //Draw new limb of smaller width to the left(recursion).
			
				double new_ang2 = ang + (SPLIT_ANGLE + (ANGLE_VARIANCE * (Math.random())));
				double new_w2 = w/(SPLIT_LENGTH + ((Math.random()-0.5) * SPLIT_LENGTH_VARIANCE));
				drawLimb(g, x - ((new_w1/5)), y, new_w2, new_ang2);   //Draw new limb of smaller width to the right(recursion).
		}
	}
	
	private class ButtonListener implements ActionListener { //Listener for button
		public void actionPerformed(ActionEvent e) { 
			repaint(); //Redraw window on button press. (generates new tree).
		} 
	}
}
