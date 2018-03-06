//-----------------------------------
// Author: Joseph D. Lee
//
// Type: Main file/class
// Description: This program randomly generates/draws a tree. 
// It utilizes a recursive method to accomplish this by drawing a limb that branches of into two limbs, 
// which branches of into two limbs, etc. until the width of the branch has breached a certain threshold.
//
// Date Created: 2/9/16
// Date Modified: 3/6/18
//-----------------------------------

package treemain;

import javax.swing.JFrame;

import treepanel.TreePanel;

public class TreeMain {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		TreePanel tree = new TreePanel(640, 640); //Creates TreePanel with initial size of 640 x 480 pixels
		window.add(tree);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

	}

}
