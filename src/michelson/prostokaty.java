package michelson;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class prostokaty extends JPanel implements Runnable
{

	prostokat pocz, pion;//nie zmieniać!
	boolean active = true;
	double vx = 5;//tego też nie
	double vy = 5;//ani tego
	Color k_pocz, k_pion;
	
	
	
	int[] xlustro = {384, 386, 466, 464};
	int[] ylustro = {289, 291, 211, 209};
	
	public prostokaty() {
		k_pocz = new Color(255, 0, 0, 127);
		k_pion = new Color(255, 255, 0, 127);
		
		pocz = new prostokat();
		pion = new prostokat();
		
		pocz.setX(0);
		pocz.setY(250);
		pocz.setvx(vx);
		pocz.setvy(0);
		pocz.setWidth(40);
		pocz.setHeight(10);
		pocz.setColor(k_pocz);
		
		pion.setX(0);
		pion.setY(250);
		pion.setvx(vx);
		pion.setvy(0);
		pion.setWidth(40);
		pion.setHeight(10);
		
		pion.setColor(k_pion);	
	}
	
	public void setv(double v)
	{
		vx=v;
		vy=v;
	}

	
	
	public void setActive(boolean b)//ustawiamy czy symulacja jest aktywna
	{
		active = b;
	}
	
	public void setBackgroundColor(Color k)
	{
		setBackground(k);
	}
	
	public void setLaserColor(Color k)
	{
		pocz = new prostokat();
		pion = new prostokat();
		
		pocz.setX(0);
		pocz.setY(250);
		pocz.setWidth(40);
		pocz.setHeight(10);
		
		pion.setX(0);
		pion.setY(250);
		pion.setWidth(40);
		pion.setHeight(10);
		
		
		pocz.setColor(k);
		pion.setColor(k);
		repaint();
		this.k_pocz = k;
		this.k_pion = k;
	}

	public void reset()
	{
		
		
		pocz = new prostokat();
		pion = new prostokat();
		
		pocz.setX(0);
		pocz.setY(250);
		pocz.setvx(vx);
		pocz.setvy(0);
		pocz.setWidth(40);
		pocz.setHeight(10);
		pocz.setColor(k_pocz);
		
		pion.setX(0);
		pion.setY(250);
		pion.setvx(vx);
		pion.setvy(0);
		pion.setWidth(40);
		pion.setHeight(10);
		
		pion.setColor(k_pion);	
	}
	
	
	public void odbij(prostokat p)//odbijamy prostokat (zmieniamy predkosc na przeciwna)
	{	
		p.setvx(-p.getvx());
		p.setvy(-p.getvy());
	}
	
	public void modV(double k, double v)//metoda pozwalająca uwzględniać prędkość wiatru eteru
	{
		pocz = new prostokat();
		pion = new prostokat();
		
		pocz.setX(0);
		pocz.setY(250);
		pocz.setWidth(40);
		pocz.setHeight(10);
		
		pion.setX(0);
		pion.setY(250);
		pion.setWidth(40);
		pion.setHeight(10);
		
		vx+=(v*Math.sin(Math.toRadians(k)))/100;
		vy+=(v*Math.cos(Math.toRadians(k)))/100;
		pocz.setvx(vx);
		pocz.setvy(vy);
		pion.setvx(vx);
		pion.setvy(vy);
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
			double h = p.getHeight();
			double w = p.getWidth();
			p.setHeight(w);
			p.setWidth(h);
		}
		
		if(k==-90)
		{
			double h = p.getHeight();
			double w = p.getWidth();
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
		g.fillRect(0, getSize().height/2-17, 80, 20);//emiter
		
		g.setColor(new Color(179, 204, 255));
		g.fillRect(getSize().width/2-35, 0, 80, 10);//lustro na górze
		g.fillRect(getSize().width/2+getSize().height/2-20, getSize().height/2-50, 20, 80);//lustro z prawej
		
		g.setColor(new Color(0, 204, 0));
		g.fillRect(getSize().width/2-3, getSize().height-20, 21, 40);//odbiornik
		
		
		g.setColor(new Color(230, 238, 255));//zwierciadło półprzepuszczalne
		g.fillPolygon(xlustro, ylustro, 4);
		
	}
	
	
	public void run()
	{
		int height = 500;
		int width = 850;
		int center = width/2;
		while(pion.getX() != 850) {
		if(active)
		{
			if(pocz.getX()+pocz.getWidth()<center)//przemieszczanie lasera w prawo
			{
				pocz.setX(pocz.getX()+pocz.getvx());
				repaint();
			}
			
			if(pion.getX()+pion.getWidth()<center)
			{
				pion.setX(pion.getX()+pion.getvx());
				repaint();
			}
			
			if(pocz.getX()+pocz.getWidth()>=center)//aktywacja po przekroczeniu środka przez laser początkowy
			{
				
				if(pion.getX()+pion.getWidth()>=center && pion.getvx()!=0)//zmiana kierunku lasera pion na pionowy
				{
					flip(pion, -90);
					if(vy<vx)
					{
						pion.setvy(5);
					}
					else
					{
						pion.setvy(vy);
					}
					
					
					pion.setY(height/2);
					pion.setX(center-5);
					System.out.println("y1 = "+pion.getY());
					System.out.println("x1 = "+pocz.getX());
					pion.setvx(0);
					repaint();
				}
				
				if(pocz.getX()<630)//przemieszczamy laser początkowy do kontaktu z prawym lustrem
				{
					pocz.setX(pocz.getX()+pocz.getvx());
					repaint();
				}	
				
				if(pocz.getX()>=630)//odbijamy początkowy laser od lustra
				{
					odbij(pocz);
					pocz.setX(pocz.getX()+pocz.getvx());
					repaint();
					System.out.println("x2 = "+pocz.getX());
				}
				
				if(pocz.getX()<center && pocz.getvx()<0)//zmieniamy kierunek początkowego laser na pionowy i w dół
				{
					flip(pocz, -90);
					pocz.setvy(pocz.getvx());
					pocz.setY(height/2-pocz.getHeight()-pocz.getvy());
					pocz.setX(center-5);
					
					pocz.setvx(0);
					pocz.setY(pocz.getY()-pocz.getvy());
					repaint();
					
					if(vx==vy&&vx!=5)
					{
						pocz.setY(pion.getY());
					}
				}
				
				if(pocz.getY()+pocz.getHeight()-25<height)//przemieszczamy początkowy laser w dół
				{
					pocz.setY(pocz.getY()+pocz.getvy());
					repaint();
				}
				
				if(pocz.getY()>=height)//zatrzymanie początkowego lasera
				{
					pocz.setvy(0);
				}
				
				if(pion.getY()>0)//przemieszczanie pionowego lasera do góry
				{
					pion.setY(pion.getY()-pion.getvy());
					repaint();
				}
			
				if(pion.getY()<=0) //odbicie lasera na górnej ściance
				{
					odbij(pion);
					pion.setY(pion.getY()-pion.getvy());
					repaint();
					System.out.println("y2 = "+pion.getY());
				}
				
				if(pion.getY()+pion.getHeight()-25-pion.getvy()/2>=height)//zatrzymanie lasera na dolnej krawędzi
				{
					pion.setvy(0);
				}
				
				if(pion.getY()+pion.getHeight()-25>=height && pocz.getY()>=height )//ustawiamy animacje na nieaktywną
				{
					active = false;
				}
			}
		}
		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	}
}
