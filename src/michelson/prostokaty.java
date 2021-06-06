package michelson;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class prostokaty extends JPanel implements Runnable
{

	static int delta;
	prostokat pocz, pion;//nie zmieniać!
	boolean active = true;
	double vx = 1;//tego też nie
	double vy = 1;//ani tego
	Color k_pocz, k_pion;
	double dvx, dvy, dv1, dv2;
	
	
	
	int[] xlustro = {384, 386, 466, 464};
	int[] ylustro = {289, 291, 211, 209};
	
	public prostokaty() {
		k_pocz = new Color(255, 0, 0, 127);
		k_pion = new Color(255, 255, 0, 127);
		dvx=0;
		dvy=0;
		
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
		delta = 0;
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
		dvx=0;
		dvy=0;
	}

	public static int getDelta(){
		return delta;
	}
	
	public void odbij(prostokat p) {//odbijamy prostokat (zmieniamy predkosc na przeciwna)
		p.setvx(-p.getvx());
		p.setvy(-p.getvy());
	}
	
	public void modV(double k, double v)//metoda pozwalająca uwzględniać prędkość wiatru eteru
	{
		pocz = new prostokat();
		pion = new prostokat();
		
		pocz.setX(0);
		pocz.setY(this.getHeight()/2);
		pocz.setWidth(40);
		pocz.setHeight(10);
		
		pion.setX(0);
		pion.setY(this.getHeight()/2);
		pion.setWidth(40);
		pion.setHeight(10);
		dv1 = Math.round(1000.0*Math.sin(Math.toRadians(k)))/1000.0;
		dv2 = Math.round(1000.0*Math.cos(Math.toRadians(k)))/1000.0;

		dvx+=(Math.round(1000.0*v*dv1)/1000.0)/100.0;
		dvy+=(Math.round(1000.0*v*dv2)/1000.0)/100.0;;
		vx+=dvx;
		vy+=dvy;
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
		g.fillRect(0, getSize().height/2-5, 80, 20);//emiter
		
		g.setColor(new Color(179, 204, 255));
		g.fillRect(getSize().width/2-35, 0, 80, 10);//lustro na górze
		g.fillRect(getSize().width/2+getSize().height/2, getSize().height/2-50, 20, 80);//lustro z prawej
		g.setColor(new Color(0, 204, 0));
		g.fillRect(getSize().width/2-5, getSize().height-20, 20, 40);//odbiornik
		
		
		g.setColor(new Color(230, 238, 255));//zwierciadło półprzepuszczalne
		g.fillPolygon(xlustro, ylustro, 4);
		
	}
	
	
	public void run()
	{
		int height = 500;
		int width = 850;
		int center = width/2;
		while(active) {
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
			
			if(pocz.getX()+pocz.getWidth() >= center)//aktywacja po przekroczeniu środka przez laser początkowy
			{
				if(pion.getX()+pion.getWidth()>=center && pion.getvx()!=0)//zmiana kierunku lasera pion na pionowy
				{
					flip(pion, -90);

					pion.setvy(vy);

					
					
					pion.setY(height/2);
					pion.setX(center);
					pion.setvx(0);
					repaint();
				}
				
				if(pocz.getX()<635)//przemieszczamy laser początkowy do kontaktu z prawym lustrem
				{
					pocz.setX(pocz.getX()+pocz.getvx());
					repaint();
				}	
				
				if(pocz.getX()>=635)//odbijamy początkowy laser od lustra
				{
					odbij(pocz);
					pocz.setvx(-1);
					pocz.setX(pocz.getX()+pocz.getvx());
					repaint();
				}
				
				if(pocz.getX()<=center && pocz.getvx()<0)//zmieniamy kierunek początkowego laser na pionowy i w dół
				{
					flip(pocz, -90);
					pocz.setvy(pocz.getvx());
					pocz.setY(height/2-pocz.getHeight()-pocz.getvy());
					pocz.setX(center);

					pocz.setvx(0);

					repaint();
				}
				if(pocz.getY()+pocz.getHeight()-25<=height)//przemieszczamy początkowy laser w dół
				{
					pocz.setY(pocz.getY()+pocz.getvy());
					repaint();
				}

				
				if(pocz.getY()>=height)//zatrzymanie początkowego lasera
				{
					pocz.setvy(0);
				}
				
				if(pion.getY()>=0)//przemieszczanie pionowego lasera do góry
				{
					pion.setY(pion.getY()-pion.getvy());
					repaint();
				}
			
				if(pion.getY()<=0) //odbicie lasera na górnej ściance
				{
					odbij(pion);
					pion.setvy(-1);
					pion.setY(pion.getY()-pion.getvy());
					repaint();
				}
				if(pion.getY()+pion.getHeight() == 250){
				}
				
				if(pion.getY()+pion.getHeight()-25-pion.getvy()/2>=height)//zatrzymanie lasera na dolnej krawędzi
				{
					pion.setvy(0);
				}
			}



			if(pion.getY()+pion.getHeight() - 25 > 499  && pocz.getY()+pocz.getHeight() - 25 != 500 && pion.getY() != pocz.getY()){
				delta++;
				System.out.println("jeden " + delta);
			}

			if(pion.getY()+pion.getHeight()-25 > 499  && pocz.getY()+pocz.getHeight() - 25 > 499)//ustawiamy animacje na nieaktywną
			{
				active = false;
			}

			if(pion.getY()+pion.getHeight() - 25 != 500  && pocz.getY()+pocz.getHeight() - 25 > 499 && pion.getY() != pocz.getY()){
				delta++;
			}
			if(dv1==dv2)
				delta = 0;


			try {
			Thread.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	}
}
