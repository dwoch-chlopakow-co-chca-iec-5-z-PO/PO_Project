package testy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class prostokaty extends JPanel implements Runnable
{

	prostokat pocz, pion;
	boolean active = true;
	int pauza;
	int vx = 5;
	int vy = 5;
	
	public void setActive(boolean b)//ustawiamy czy symulacja jest aktywna
	{
		active = b;
	}

	prostokaty()
	{
	}
	
	public void odbij(prostokat p)//odbijamy prostokat (zmieniamy predkosc na przeciwna)
	{	
		p.setvx(-p.getvx());
		p.setvy(-p.getvy());
	}
	
	public void modV(int k, int v)//metoda pozwalająca uwzględniać prędkość wiatru eteru
	{
		vx+=(int)(v*Math.cos(Math.toRadians(k)));
		vy+=(int)(v*Math.sin(Math.toRadians(k)));
	}
	
	public void flip(prostokat p, int k)//metoda przekręcająca prostokąt o dany kąt
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
	
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		pocz.paint(g);
		pion.paint(g);
		
		g.setColor(Color.blue);
		g.fillRect(0, getSize().height/2-5, 80, 10);//emiter
		g.setColor(new Color(179, 204, 255));
		g.fillRect(getSize().width/2-35, 0, 80, 10);//lustro na górze
		g.fillRect(getSize().width/2+getSize().height/2, getSize().height/2-35, 20, 80);//lustro z prawej
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(230, 238, 255));
		g2d.rotate(Math.toRadians(45), getSize().width/2, getSize().height/2);//lustro półprzepuszczające
		g2d.fillRect(getSize().width/2, getSize().height/2-45, 5, 100);
		
	}
	
	
	public void run()
	{
		int height = getSize().height;
		int width = getSize().width;
		int center = width/2;
		
		pocz = new prostokat();
		pion = new prostokat();
		
		pocz.setX(0);
		pocz.setY(height/2);
		pocz.setvx(vx);
		pocz.setvy(0);
		pocz.setWidth(40);
		pocz.setHeight(10);
		pocz.setColor(Color.red);
		
		pion.setX(width/2);
		pion.setY(height/2+10);
		pion.setvx(0);
		pion.setvy(vy);
		pion.setWidth(10);
		pion.setHeight(40);
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
					pocz.setvy(pocz.getvx());
					pocz.setY(pocz.getY()-50+pocz.getvy());
					
					pocz.setvx(0);
					pocz.setY(pocz.getY()-pocz.getvy());
					repaint();
				}
				
				if(pocz.getY()+pocz.getHeight()<height)//przemieszczamy początkowy laser w dół
				{
					pocz.setY(pocz.getY()+pocz.getvy());
					repaint();
					System.out.println("poziom: "+pocz.getY());
				}
				
				if(pocz.getY()+pocz.getHeight()+5>=height)
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
				
				if(pion.getY()+pion.getHeight()+5>=height)//zatrzymanie lasera na dolnej krawędzi
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
