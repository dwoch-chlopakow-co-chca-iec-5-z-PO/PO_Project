package testy;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class prostokaty extends JPanel implements Runnable
{

	prostokat pocz, pion;
	boolean active = true;
	int pauza;
	
	public void setActive(boolean b)
	{
		active = b;
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		pocz.paint(g);
		pion.paint(g);
	}

	prostokaty()
	{
		
	}
	
	public void odbij(prostokat p)
	{
		p.setvx(-p.getvx());
		p.setvy(-p.getvy());
	}
	
	public void flip(prostokat p, int k)
	{
		if(k == 180)
		{
			odbij(p);
		}
		
		if(k==90)
		{
			if(p.getvx()>0)
			{
				p.setvy(-p.getvx());
				p.setvx(0);
			}
			if(p.getvx()<0)
			{
				p.setvy(-p.getvx());
				p.setvx(0);
			}
			if(p.getvy()<0)
			{
				p.setvx(p.getvy());
				p.setvy(0);
			}
			if(p.getvy()>0)
			{
				p.setvx(p.getvy());
				p.setvy(0);
			}
			int h = p.getHeight();
			int w = p.getWidth();
			p.setHeight(w);
			p.setWidth(h);
		}
		
		if(k==-90)
		{
			int h = p.getHeight();
			int w = p.getWidth();
			p.setHeight(w);
			p.setWidth(h);
			
			if(p.getvx()<0)
			{
				p.setvy(p.getvx());
				p.setvx(0);
			}
			if(p.getvx()>0)
			{
				p.setvy(p.getvx());
				p.setvx(0);
			}
			if(p.getvy()<0)
			{
				p.setvx(-p.getvy());
				p.setvy(0);
			}
			if(p.getvy()>0)
			{
				p.setvx(-p.getvy());
				p.setvy(0);
			}
		}
	}
	
	
	
	public void run()
	{
		int height = getSize().height;
		int width = getSize().width;
		int center = getSize().width/2;
		
		pocz = new prostokat();
		pion = new prostokat();
		
		pocz.setX(0);
		pocz.setY(height/2);
		pocz.setvx(10);
		pocz.setvy(0);
		pocz.setWidth(20);
		pocz.setHeight(5);
		pocz.setColor(Color.red);
		
		pion.setX(width/2);
		pion.setY(height/2+10);
		pion.setvx(0);
		pion.setvy(10);
		pion.setWidth(5);
		pion.setHeight(20);
		pion.setColor(Color.BLACK);
		
		while(active)
		{
			if(pocz.getX()+pocz.getWidth()<center)//przemieszczanie lasera w lewo
			{
				pocz.setX(pocz.getX()+pocz.getvx());
				repaint();
			}
			
			if(pocz.getX()+pocz.getWidth()>=center)//aktywacja po przekroczeniu środka przez laser początkowy
			{
				if(pocz.getX()+pocz.getWidth()<=center+height/2)//przemieszczamy laser początkowy do kontaktu z prawą ścianką
				{
					pocz.setX(pocz.getX()+pocz.getvx());
					repaint();
				}	
				
				if(pocz.getX()+pocz.getWidth()>=center+height/2)//odbijamy początkowy laser od ścianki
				{
					odbij(pocz);
					pocz.setX(pocz.getX()+pocz.getvx());
					repaint();
					
				}
				
				if(pocz.getX()<center && pocz.getvx()<0)//zmieniamy kierunek początkowego laser na pionowy i w dół
				{
					flip(pocz, -90);
					pocz.setY(pocz.getY()-20);
					pocz.setvy(pocz.getvx());
					pocz.setvx(0);
					pocz.setY(pocz.getY()-pocz.getvy());
					repaint();
				}
				
				if(pocz.getY()+pocz.getHeight()+9<height)//przemieszczamy początkowy laser w dół
				{
					pocz.setY(pocz.getY()+pocz.getvy());
					repaint();
					System.out.println("poziom: "+pocz.getY());
				}
				
				if(pocz.getY()+pocz.getHeight()>=height)
				{
					pocz.setvy(0);
					repaint();
				}
				
				if(pion.getY()>0)//przemieszczanie pionowego lasera do góry
				{
					pion.setY(pion.getY()-pion.getvy());
					repaint();
					System.out.println("pion: "+pion.getY());
				}
			
				if(pion.getY()<=0) //odbicie lasera na górnej ściance
				{
					odbij(pion);
					pion.setY(pion.getY()-pion.getvy());
					repaint();
				}
				
				if(pion.getY()+pion.getHeight()+9>=height)//zatrzymanie lasera na dolnej krawędzi
				{
					pion.setvy(0);
				}
			}
			
			try {//czekanie między kolejymi wykonaniami
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
