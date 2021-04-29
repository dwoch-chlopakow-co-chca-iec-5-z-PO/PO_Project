package michelson;



import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


public class ArrowsPanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	List<Arrow> arrows = new ArrayList<Arrow>();
	

	private int a = 10;
	private int h = (int)(a * Math.sqrt(3) / 2);
	private int w = 2;
	

	private int x = 0, y = 40;
	
	private int[] xV = {0, 0, 0, 0};
	private int[] yV = {0, 0, 0, 0};
	private boolean zero = true;
	
	
	public void addArrow(){
			
		
	
		
	//	Color col1 = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		
		
		

		int[] xt = {x , x + (int)(a/2), x + a};
		int[] yt = {y + h, y, y + h};
		
		int[] xr = {xt[1] - w/2, xt[1] + w/2 + 1, xt[1] + w/2 + 1                 , xt[1] - w/2};
		int[] yr = {yt[0]      , yt[0]	    , yt[2] + (yt[2] - yt[1])     , yt[2] + (yt[2] - yt[1])};
		
		
		Arrow p = new Arrow();
		p.setXts(xt);
		p.setYts(yt);
		p.setXrs(xr);
		p.setYrs(yr);

		

		
	//	p.setvX(xV);
		p.setvY(yV);
	//	p.setColor(col1);
		
		arrows.add(p);	
		
		x += 80;
		
		
		if(xt[2] >= 840) {
			if(zero == true) {
				x = 40;
				zero = false;
			}
			else {
				x = 0;
				zero = true;
			}
			y = y + 50;
			
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		for (Arrow pr : arrows)
			pr.paint(g);
	}
	
	@Override
	public void run() {
		for (Arrow ar : arrows) {
			
				
				ar.setYts(sub(ar.getYts(), yV));
				ar.setYrs(sub(ar.getYrs(), yV));

				
				if((ar.getYts()[1] + h + ar.getHeight()) <= 0) {
					ar.setYts(addnum(ar.getYts()));
					ar.setYrs(addnum(ar.getYrs()));
				}
				
				
	
		}
		repaint();
	}
	
	int[] add(int []x1, int []x2){
		int[] ret = new int[x1.length];
			
		for(int i = 0; i < x1.length; i++)
			ret[i] = x1[i] + x2[i];
			
		return ret;
	}
		
	int[] addnum(int []y1){
		int[] ret = new int[y1.length];
			
		for(int i = 0; i < y1.length; i++)
			ret[i] = y1[i] + 600;
			
		return ret;
	}
		
	int[] sub(int []x1, int []x2){
		int[] ret = new int[x1.length];
			
		for(int i = 0; i<x1.length; i++)
			ret[i] = x1[i] - x2[i];
			
		return ret;
	}
	
	void setyV(int a) {
		int[] yv = {a, a, a, a};
		yV = yv;
	}
}
