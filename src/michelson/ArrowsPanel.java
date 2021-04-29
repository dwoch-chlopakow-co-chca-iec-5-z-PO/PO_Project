package michelson;



import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


public class ArrowsPanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	List<Arrow> arrows = new ArrayList<Arrow>();
	
	int i = 0;

	private int h = 10;

	private int a = (int)(2 * h / Math.sqrt(3));
	private int x = 0, y = 40;
	
	private int[] xV = {0, 0, 0};
	private int[] yV = {0, 0, 0};
	
	
	public void addArrow(){
			
		
	
		
	//	Color col1 = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		
		
		

		int[] xs = {x , x + (int)(a/2), x + a};
		int[] ys = {y + h, y, y + h};
		
		
		Arrow p = new Arrow();
		p.setXs(xs);
		p.setYs(ys);

		

		
	//	p.setvX(xV);
	//	p.setvY(yV);
	//	p.setColor(col1);
		
		arrows.add(p);	
		
		x += 40;
		
		
		if(xs[2] >= 840) {
			x = 0;
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
		for (Arrow pr : arrows) {
				pr.setYs(subtract(pr.getYs(), pr.getvY()));
			
				if((pr.getYs()[1] + h + pr.getHeight()) == 0)
					pr.setYs(addnum(pr.getYs()));
		}
		
		repaint();
	}
	
	int[] add(int []x1, int []x2){
		int[] ret = new int[x1.length];
			
		for(int i = 0; i<x1.length; i++)
			ret[i] = x1[i] + x2[i];
			
		return ret;
	}
		
	int[] addnum(int []y1){
		int[] ret = new int[y1.length];
			
		for(int i = 0; i < y1.length; i++)
			ret[i] = y1[i] + 500;
			
		return ret;
	}
		
	int[] subtract(int []x1, int []x2){
		int[] ret = new int[x1.length];
			
		for(int i = 0; i<x1.length; i++)
			ret[i] = x1[i] - x2[i];
			
		return ret;
	}
}
