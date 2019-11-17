package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Game.Grid;

public class MinesweeperPanel extends JPanel{

	int difficulty;
	int score;

	int mapWidth;
	int mapHeight;

	final int[][] SIZES = {{10,10}, {20,30}, {50, 100}, {100, 200}, {200,200}};

	int Buffer;

	Grid grid;

	long startTime;
	long changeTime;

	boolean isAlive;

	public MinesweeperPanel(int width, int height, int dif) {
		this.setPreferredSize(new Dimension(width, height));
		this.difficulty = dif;
		this.isAlive = true;
		this.mapWidth = SIZES[this.difficulty][0];
		this.mapHeight = SIZES[this.difficulty][1];
		grid = new Grid(this.mapWidth, this.mapHeight);
		grid.generate();		
		this.addMouseListener(new Mouse());
	}

	public void paintComponent(Graphics g) {

		if (!this.isAlive) {
			g.setColor(Color.RED);
			g.setFont(new Font ("MonoCode", Font.BOLD, (int)this.getWidth()/10));
			g.drawString("Game Over", (int)(this.getWidth()/2 - (3 * this.getWidth()/10.0)), this.getHeight()/10);
		}
		startTime = System.nanoTime();


		float squareSize;
		if (this.getHeight() < (mapHeight / mapWidth) * this.getWidth()) {
			squareSize = this.getHeight() / mapHeight * 0.8f;
		} else {
			squareSize = this.getWidth() / mapWidth * 0.8f;
		}

		//System.out.println(this.getWidth());
		int xBuffer = (int)((this.getWidth() - (squareSize * mapWidth)) / 2.0);
		int yBuffer = (int)((this.getHeight() - (squareSize * mapHeight)) / 1.8);


		grid.draw(g, squareSize, xBuffer, yBuffer);



		changeTime = System.nanoTime() - startTime;
		try {
			Thread.sleep((int)((1000000000 - changeTime) / 60000000.0));
		} catch (InterruptedException e) {
		}
		repaint();


	}

	private void click(MouseEvent mouse) {

		if (this.isAlive) {
			float squareSize;
			if (this.getHeight() < (mapHeight / mapWidth) * this.getWidth()) {
				squareSize = this.getHeight() / mapHeight * 0.8f;
			} else {
				squareSize = this.getWidth() / mapWidth * 0.8f;
			}

			int xBuffer = (int)((this.getWidth() - (squareSize * mapWidth)) / 2.0);
			int yBuffer = (int)((this.getHeight() - (squareSize * mapHeight)) / 1.8);

			int x = (int)((mouse.getX() - xBuffer)/squareSize);
			int y = (int)((mouse.getY() - yBuffer)/squareSize);


			this.isAlive = !grid.reveal(x, y);
		}
	}

	private class Mouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			click(arg0);
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
