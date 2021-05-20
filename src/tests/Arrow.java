package tests;

import java.awt.*;

public class Arrow {
	
	private int width = 2;
	
	
	
	int[] x = {74, 100, 126};
	int[] y = {195, 150, 195};
	
	int[] x1 = {x[1] - width/2, x[1] + width/2, x[1] + width/2, x[1] - width/2};
	int[] y1 = {y[0]		  , y[0]		  , y[2] + (y[2] - y[1])     , y[2] + (y[2] - y[1])};
	
	private Color color = new Color(154, 227, 192, 100);
	private int []vX = {1, 1, 1, 1};
	private int []vY = {1, 1, 1, 1};
	
	
	
	
	
	public void setXts(int[] x) {
		this.x = x;
	}
	    
	public void setYts(int[] y) {
		this.y = y;
	}
	
	public int[] getXts() {
		return x;
	}
	    
	public int[] getYts() {
		return y;
	}
	
	
	
	public void setXrs(int[] x1) {
		this.x1 = x1;
	}
	    
	public void setYrs(int[] y1) {
		this.y1 = y1;
	}
	
	public int[] getXrs() {
		return x1;
	}
	    
	public int[] getYrs() {
		return y1;
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
	//	g.fillRect(x[0] + (x[2] - x[1])-width/2, y[2], width, y[2]-y[1]);
		g.fillPolygon(x1, y1, 4);
    }
}