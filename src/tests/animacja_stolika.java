package tests;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;

import michelson.Arrow;

public class animacja_stolika extends JPanel implements Runnable
{
	Arrow pocz, pion, poziom;
	boolean active = true;
	int pause;
	double obrot;
	int width = 20;
	int k;
	int[] xts = {74, 100, 126};
	int[] yts = {195, 150, 195};
	int[] xrs = {xts[1] - width/2, xts[1] + width/2, xts[1] + width/2, xts[1] - width/2};
	int[] yrs = {yts[0], yts[0], yts[2] + (yts[2] - yts[1]), yts[2] + (yts[2] - yts[1])};
	int[] v = {10, 10, 10, 10};
	Color kolor = Color.black;
	
	int[] v0 = {0, 0, 0, 0};
	
	
	animacja_stolika()
	{
		pocz = new Arrow();
		pocz.setXts(xts);
		pocz.setYts(yts);
		pocz.setXrs(xrs); 
		pocz.setYrs(yrs);
		pocz.setvX(v);
		pocz.setvY(v0);
		pocz.setColor(kolor);
		
		
		int[] pixts = {pocz.getXts()[0]+200, pocz.getXts()[1]+200, pocz.getXts()[2]+200};//wyciągamy kolejne współrzędne strzałki początkowej i używamy ich do zdefiniowania nowej strzałki
		int[] piyts = {pocz.getYts()[0], pocz.getYts()[1], pocz.getYts()[2]};
		int[] pixrs = {pocz.getXrs()[0]+200, pocz.getXrs()[1]+200, pocz.getXrs()[2]+200, pocz.getXrs()[3]+200};
		int[] piyrs = {pocz.getYrs()[0], pocz.getYrs()[1], pocz.getYrs()[2], pocz.getYrs()[3]};
		int[] piv = pocz.getvX();
		
		pion = new Arrow();
		pion.setXts(pixts);//ustawiamy wszystkie parametry nowej strzałki
		pion.setYts(piyts);
		pion.setXrs(pixrs);
		pion.setYrs(piyrs);
		pion.setvY(odbij(piv));//strzałka będzie poruszała się w pionie z prędkością początkowej strzałki
		pion.setvX(v0);//prędkość strzałki w poziomie jest równa 0
		pion.setColor(Color.red);
	}
	
	public void setPause(int i)
	{
		pause = i;
	}
	
	public void setActive(boolean b)
	{
		active = b;
	}
	
	public void vEteru(int i)
	{
		for(int a = 0; a<v.length; a++)
		{
			v[a]+=i;
		}
	}
	
	Arrow flipLewoDol(Arrow a)
	{
		Arrow ret = new Arrow();
		
		ret.setvY(a.getvX());
		
		int x_0 = (a.getXrs()[1]+a.getXrs()[3])/2;
		int y_0 = (a.getYts()[1]+a.getYrs()[3])/2;
		int[] xts = {x_0+(a.getYts()[0]-y_0), x_0, x_0-(a.getYts()[0]-y_0)};
		int[] yts = {y_0, y_0+(a.getXts()[1]-x_0), y_0};
		int[] xrs = {x_0-((a.getYrs()[0]-a.getYrs()[1])/2), x_0+((a.getYrs()[0]-a.getYrs()[1])/2), x_0+((a.getYrs()[0]-a.getYrs()[1])/2), x_0-((a.getYrs()[0]-a.getYrs()[1])/2)};
		int[] yrs = {y_0, y_0, y_0-(x_0-a.getXrs()[2]), y_0-(x_0-a.getXrs()[2])};
		
		ret.setXts(xts);
		ret.setYts(yts);
		ret.setXrs(xrs);
		ret.setYrs(yrs);
		
		return ret;
	}
	
	Arrow flipPrawoLewo(Arrow a)
	{
		Arrow ret = new Arrow();
		ret.setvX(odbij(a.getvX()));
		
		int x_0 = (a.getXrs()[1]+a.getXrs()[3])/2;
		int y_0 = (a.getYts()[1]+a.getYrs()[3])/2;
		int[] xts = {a.getXts()[2], x_0+(x_0-a.getXts()[1]), a.getXts()[0]};
		int[] yts = {a.getYts()[2], y_0, a.getYts()[0]};
		int[] xrs = {a.getXrs()[0], a.getXrs()[1], x_0-(a.getXrs()[2]-x_0), x_0-(a.getXrs()[3]-x_0)};
		int[] yrs = {y_0+(y_0-a.getYrs()[0]), y_0-(a.getYrs()[1]-y_0), y_0-(a.getYrs()[2]-y_0), y_0+(y_0-a.getYrs()[3])};
		
		ret.setXts(xts);
		ret.setYts(yts);
		ret.setXrs(xrs);
		ret.setYrs(yrs);
		return ret;
	}
	
	Arrow fliGoraDol(Arrow a)
	{
		Arrow ret = new Arrow();
		ret.setvY(odbij(a.getvY()));
		
		
		return ret;
	}
	
	public static boolean compareArrays(int[] array1, int[] array2) {
        boolean b = true;
        if (array1 != null && array2 != null){
          if (array1.length != array2.length)
              b = false;
          else
              for (int i = 0; i < array2.length; i++) {
                  if (array2[i] != array1[i]) {
                      b = false;    
                  }                 
            }
        }else{
          b = false;
        }
        
        return b;
    }
	
	int[] add(int []x1, int []x2)//metoda która będzie wykorzystywana do poruszania strzałki
	{
		int[] ret = new int[x1.length];
			
		for(int i = 0; i<x1.length; i++)
			ret[i] = x1[i] + x2[i];
		return ret;
	}
	
	int[] sub(int[] x1, int[] x2){
		int[] ret = new int[x1.length];
			
		for(int i = 0; i<x1.length; i++)
			ret[i] = x1[i] - x2[i];
			
		return ret;
	}
	
	int[] odbij(int[] x)//metoda służąca do odbijania strzałki
	{
		int[] ret = new int[x.length];
		for(int i = 0; i<x.length; i++)
		{
			ret[i] = -x[i];
		}
		
		return ret;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		pocz.paint(g2d);
		//g2d.rotate(Math.toRadians(90), 850/2, 500/2);
		pion.paint(g2d);
		//poziom.paint(g2d);
	}
	

	@Override
	public void run()
	{
		int x_srodek = getSize().width;
		
		while(active) 
		{
			/*Random r = new Random();
			Color col1 = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
			setBackground(col1);*/
			
			
			
			if(pocz.getXts()[0]+pocz.getHeight()+14<x_srodek) //dopóki początkowa strzałka nie przekracza środka to przmieszczamy ją w prawo
			{
				pocz.setXts(add(pocz.getXts(), pocz.getvX()));
				pocz.setYts(add(pocz.getYts(), pocz.getvY()));
				pocz.setXrs(add(pocz.getXrs(), pocz.getvX()));
				pocz.setYrs(add(pocz.getYrs(), pocz.getvY()));
				repaint();
			}
			if(pocz.getXts()[0]+pocz.getHeight()+14>x_srodek)
			{
				pocz = flipPrawoLewo(pocz);
				
				pocz.setXts(add(pocz.getXts(), pocz.getvX()));
				pocz.setYts(add(pocz.getYts(), pocz.getvY()));
				pocz.setXrs(add(pocz.getXrs(), pocz.getvX()));
				pocz.setYrs(add(pocz.getYrs(), pocz.getvY()));
				repaint();
			}
		//	pion = new Arrow();//degfiniujemy pionową strzałkę w punkcie w którym strzałka początkowa zakończyła swój ruch
			
			
			
			
			/*poziom = new Arrow();
			int[] poxts = {pocz.getXts()[0], pocz.getXts()[1], pocz.getXts()[2]};
			int[] poyts = {pocz.getYts()[0], pocz.getYts()[1], pocz.getYts()[2]};
			int[] poxrs = {pocz.getXts()[1] - width/2, pocz.getXts()[1] + width/2, pocz.getXts()[1] + width/2, pocz.getXts()[1] - width/2};
			int[] poyrs = {pocz.getYts()[0], pocz.getYts()[0], pocz.getYts()[2] + (pocz.getYts()[2] - pocz.getYts()[1]), pocz.getYts()[2] + (pocz.getYts()[2] - pocz.getYts()[1])};
			int[] pov = pocz.getvX();
			
			poziom.setXts(poxts);
			poziom.setYts(poyts);
			poziom.setXrs(poxrs);
			poziom.setYrs(poyrs);
			poziom.setvX(pov);
			poziom.setvY(v0);*/
			
			if(pion.getYrs()[0]+pion.getHeight()>45)//strzałka znajduje się poniżej górnej krawędzi
			{
			//przesuwamy każdą współrzędną o współczynnik równy prędkości w danym kierunku
				pion.setYts(add(pion.getYts(), pion.getvY()));
				pion.setYrs(add(pion.getYrs(), pion.getvY()));
				repaint();
				System.out.println("y: "+pion.getYrs()[0]);
				int x = pion.getHeight()+pion.getYrs()[0];
				System.out.println("Y+wysokość: "+ x);
			}
			
			if(pion.getYrs()[0]+pion.getHeight()<90)//strzałka wyjdzie ponad górną krawędz
			{
				pion.setvY(odbij(pion.getvY()));//zmieniamy prędkość na ujemną w celu przemieszczania się strzałki w dół
				//zmieniamy współzędne tak jak w poprzedniej pętli if 
				pion.setYts(add(pion.getYts(), pion.getvY()));
				pion.setYrs(add(pion.getYrs(), pion.getvY()));
				repaint();
			}
			
			if(pion.getYts()[0]>getSize().height-45)//jeżeli strzałka znajdzie się poniżej krawędzi to zatrzymujemy animację związaną z tą strzałką(nie będzie się przemieszczała)
			{
			}
			
			/*if(poziom.getXts()[0]+poziom.getHeight()<getSize().width)//robimy to samo co ze strzałką "pion"
			{
				poziom.setXts(add(poziom.getXts(), poziom.getvX()));
				poziom.setYts(add(poziom.getYts(), poziom.getvY()));
				poziom.setXrs(add(poziom.getXrs(), poziom.getvX()));
				poziom.setYrs(add(poziom.getYrs(), poziom.getvY()));
				repaint();
			}
			
			if(poziom.getXts()[0]+poziom.getHeight()>getSize().width)
			{
				poziom.setvX(odbij(poziom.getvX()));
				poziom.setXts(add(poziom.getXts(), poziom.getvX()));
				poziom.setYts(add(poziom.getYts(), poziom.getvY()));
				poziom.setXrs(add(poziom.getXrs(), poziom.getvX()));
				poziom.setYrs(add(poziom.getYrs(), poziom.getvY()));
				repaint();
			}
			
			if(poziom.getXts()[0]-poziom.getHeight()<x_srodek)//przestajemy poruszać strzałką w poziomie, zmieniamy ją na ruch w pionie(w dół)
			{
				poziom.setvY(poziom.getvX());
				poziom.setvX(v0);
				
				poziom.setXts(add(poziom.getXts(), poziom.getvX()));
				poziom.setYts(add(poziom.getYts(), poziom.getvY()));
				poziom.setXrs(add(poziom.getXrs(), poziom.getvX()));
				poziom.setYrs(add(poziom.getYrs(), poziom.getvY()));
				
				//metoda która obróci strzałkę o 90 stopni tak, aby zwrócona była w dół
			}
			
			if(poziom.getYts()[0]-poziom.getHeight()>0 && compareArrays(poziom.getvX(), v0)==true)//dopóki położenie strzałka jest nad dolną krawędzią
			{																				      // i nie przemieszcza się w poziomie to przemieszczamy
				poziom.setXts(add(poziom.getXts(), poziom.getvX()));							  // strzałkę w dół
				poziom.setYts(add(poziom.getYts(), poziom.getvY()));
				poziom.setXrs(add(poziom.getXrs(), poziom.getvX()));
				poziom.setYrs(add(poziom.getYrs(), poziom.getvY()));
			}
			
			if(poziom.getYts()[0]-poziom.getHeight()<0 && compareArrays(poziom.getvX(), v0)==true)
			{
				break;//strzałka znalazła się pod zerem, przerywamy pętle
			}*/
			
			
			try {//czekanie między kolejymi wykonaniami
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}
