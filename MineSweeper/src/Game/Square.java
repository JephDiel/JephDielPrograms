package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Square {
	
	private boolean isBomb;
	
	private int touchCount;
	
	private final Color[] COLORS = {Color.BLUE, Color.GREEN, Color.RED, Color.BLUE.darker(), Color.RED.darker(), Color.MAGENTA, Color.BLACK, Color.GRAY};
	
	public Square(boolean bomb) {
		this.isBomb = bomb;
	}
	
	public void setBomb() {
		this.isBomb = true;
	}
	
	public boolean isBomb() {
		return this.isBomb;
	}

	public int getTouchCount() {
		return touchCount;
	}

	public void setTouchCount(int touchCount) {
		this.touchCount = touchCount;
	}

	public void draw(Graphics g, float width, float height, int x, int y, int buffer) {
		g.setFont(new Font ("TImes New Roman", Font.BOLD, (int)height));
		g.setColor(Color.LIGHT_GRAY);
		int drawX = (int)(x * width) + buffer;
		int drawY = (int)((y+1) * height) + buffer;
		g.drawRect(drawX, drawY-(int)height, (int)width, (int)height);
		if (this.isBomb) {
			g.setColor(Color.BLACK);
			g.drawString("X", drawX, drawY);
		} else {
			g.setColor(COLORS[this.touchCount]);
			g.drawString("" + this.touchCount, drawX, drawY);
		}
		
	}

}
