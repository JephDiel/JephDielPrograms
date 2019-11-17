package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Square {
	
	private boolean isBomb;
	private boolean isRevealed;
	
	private int touchCount;
	
	private final Color[] COLORS = {
			Color.BLUE, 
			Color.GREEN.darker(),
			Color.RED, 
			Color.BLUE.darker(), 
			Color.RED.darker().darker(), 
			new Color(90,200,180), 
			Color.BLACK, 
			Color.GRAY};
	
	public Square(boolean bomb) {
		this.isBomb = bomb;
		this.isRevealed=false;
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
	
	public boolean[] reveal() {
		boolean[] outputs =  {this.isBomb, this.isRevealed, this.touchCount==0};
		this.isRevealed = true;
		return outputs;
	}

	public void draw(Graphics g, float size, int x, int y, int xBuffer,int yBuffer) {
		g.setFont(new Font ("MonoCode", Font.BOLD, (int)size));
		
		int drawX = (int)(x * size) + xBuffer;
		int drawY = (int)((y+1) * size) + yBuffer;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(drawX-(int)(size / 4), drawY - (int)(size * (4.5 / 5)), (int)size, (int)size);
		g.setColor(Color.GRAY);
		g.drawRect(drawX-(int)(size / 4), drawY - (int)(size * (4.5 / 5)), (int)size, (int)size);
		
		if (this.isRevealed) {
			if (this.isBomb) {
				g.setColor(Color.BLACK);
				g.drawString("x", drawX, drawY); //Solid circle Unicode (Too big) \u25CF
			} else if (this.touchCount !=0 ){
				g.setColor(COLORS[this.touchCount-1]);
				g.drawString("" + this.touchCount, drawX, drawY);
			}
		} else {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(drawX-(int)((size / 4) - (9.0 * size / 10.0)), drawY - (int)(size * (4.5 / 5)), (int)(size/10), (int)size);
			g.fillRect(drawX-(int)(size / 4), drawY - (int)((size * (4.5 / 5)) - (9.0 * size / 10.0)), (int)(size), (int)size/10);
			g.setColor(Color.WHITE);
			g.fillRect(drawX-(int)(size / 4), drawY - (int)(size * (4.5 / 5)), (int)(size/10), (int)size);
			g.fillRect(drawX-(int)(size / 4), drawY - (int)(size * (4.5 / 5)), (int)(size), (int)size/10);
			

			
		}
		
	}

}
