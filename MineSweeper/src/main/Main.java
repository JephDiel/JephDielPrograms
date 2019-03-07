package main;

import javax.swing.JFrame;

import display.MinesweeperPanel;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Minesweeper");
		MinesweeperPanel panel = new MinesweeperPanel(720, 720, 50, 0);
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
