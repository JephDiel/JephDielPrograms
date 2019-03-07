package Game;

import java.awt.Graphics;

public class Grid {
	
	private int width;
	private int height;
	
	private int bombCount;
	
	private Square[][] squares;
	
	
	public Grid(int w, int h) {
		this.width = w;
		this.height = h;
		squares = new Square[this.width][this.height];

	}
	
	public void generate() {
		this.bombCount = this.width * this.height / 10;
		for (int x=0; x<this.width; x++) {
			for (int y=0; y<this.height; y++) {
				this.squares[x][y] = new Square(false);
			}
		}
		for (int i=0; i<this.bombCount; i++) {
			Square randomSquare = squares[(int)(Math.random() * width)][(int)(Math.random() * height)];
			if (!randomSquare.isBomb()) {
				randomSquare.setBomb();
			} else {
				i--;
			}
		}
		for (int x=0; x<this.width; x++) {
			for (int y=0; y<this.width; y++) {
				this.squares[x][y].setTouchCount(this.getTouching(x, y));
			}
		}
		
	}
	
	public int getTouching(int x, int y) {
		int touching = 0;
		if (x<width-1 && squares[x+1][y].isBomb()) {
			touching++;
		}
		if (x<width-1 && y>0 && squares[x+1][y-1].isBomb()) {
			touching++;
		}
		if (y>0 && squares[x][y-1].isBomb()) {
			touching++;
		}
		if (x>0 && y>0 && squares[x-1][y-1].isBomb()) {
			touching++;
		}
		if (x>0 && squares[x-1][y].isBomb()) {
			touching++;
		}
		if (x>0 && y<height-1 && squares[x-1][y+1].isBomb()) {
			touching++;
		}
		if (y<height-1 && squares[x][y+1].isBomb()) {
			touching++;
		}
		if (x<width-1 && y<height-1 && squares[x+1][y+1].isBomb()) {
			touching++;
		}
		return touching;
	}
	
	public void draw(Graphics g, int screenWidth, int screenHeight, int buffer) {
		
		float squareWidth = (screenWidth - (buffer * 2)) / width;
		float squareHeight = (screenHeight - (buffer * 2)) / height;
		System.out.println(screenWidth);
		
		for (int x=0; x<this.width; x++) {
			for (int y=0; y<this.height; y++) {
				squares[x][y].draw(g, squareWidth, squareHeight, x, y, buffer);
			}
		}
	}
}
