package display;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Game.Grid;

public class MinesweeperPanel extends JPanel{
	
	int difficulty;
	int score;
	
	int mapWidth;
	int mapHeight;
	
	final int[][] SIZES = {{10,10}, {20,30}, {50, 100}, {100, 200}, {200,200}};
	
	int buffer;
	
	Grid grid;
	
	long startTime;
	
	public MinesweeperPanel(int width, int height, int buf, int dif) {
		this.setPreferredSize(new Dimension(width, height));
		this.buffer = buf;
		this.difficulty = dif;
		this.mapWidth = SIZES[this.difficulty][0];
		this.mapHeight = SIZES[this.difficulty][1];
		grid = new Grid(this.mapWidth, this.mapHeight);
		grid.generate();
	}
	
	public void paintComponent(Graphics g) {
		startTime = System.currentTimeMillis();
		grid.draw(g, this.getWidth() - (buffer * 2), this.getHeight() - (buffer*2), buffer);
		try {
			Thread.sleep(((1/60)*1000));
		} catch (InterruptedException e) {
		}
		repaint();
		
	}
}
