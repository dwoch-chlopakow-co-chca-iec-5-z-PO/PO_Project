package michelson;

import java.awt.*;
import java.awt.geom.AffineTransform;


public class Arrow {
	
	int[] x = {74,100,126};
	int[] y = {195,150,195};
	private Color color = new Color(154, 227, 192, 50);
	private int []vX = {1, 1, 1};
	private int []vY = {1, 1, 1};
	private int width = 2;
	
	
	
	
	public void setXs(int[] x) {
		this.x = x;
	}
	    
	public void setYs(int[] y) {
		this.y = y;
	}
	
	public int[] getXs() {
		return x;
	}
	    
	public int[] getYs() {
		return y;
	}
	
	

	public void setvX(int[] vX) {
		this.vX = vX;
	}
	    
	public void setvY(int[] vY) {
		this.vY = vY;
	}
	 
	public int[] getvX() {
		return vX;
	}
	    
	public int[] getvY() {
		return vY;
	}
	
	public int getHeight() {
		return y[2]-y[1];
	}
	
	
	 public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
	

	public void paint(Graphics g){
		g.setColor(color);
		g.fillPolygon(x, y, 3);
		g.fillRect(x[0] + (x[2] - x[1])-width/2, y[2], width, y[2]-y[1]);
    }
}
