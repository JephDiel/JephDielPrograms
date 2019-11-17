package main;

import javax.swing.JFrame;

import display.MinesweeperPanel;

public class Main {
	public static void main(String[] args) {
		
		int difficulty = 0; //0-4
		
		JFrame frame = new JFrame("Minesweeper");	
		MinesweeperPanel panel = new MinesweeperPanel(720, 720, difficulty);
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
