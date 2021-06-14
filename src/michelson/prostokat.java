package michelson;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class prostokat
{

    private double xPos;
	private double yPos;
    private double width;
    private double height;
    private Color color = Color.BLACK;
    private double vx, vy;
     
    public void setvx(double i)
    {
    	vx = i;
    }
    
    public double getvx()
    {
    	return vx;
    }
    
    public void setvy(double i)
    {
    	vy = i;
    }
    
    public double getvy()
    {
    	return vy;
    }
    
    public double getX() 
    {
		return xPos;
	}

	public void setX(double xPos) 
	{
		this.xPos = xPos;
	}

    public void setY(double yPos)
    {
        this.yPos = yPos;
    }

    public double getY()
    {
        return yPos;
    }

    public double getWidth()
    {
        return width;
    } 

    public double getHeight()
    {
        return height;
    }


	public void setWidth(double width) 
	{
		this.width = width;
	}

	public void setHeight(double height) 
	{
		this.height = height;
	}

    public Color getColor() 
    {
		return color;
	}

	public void setColor(Color color) 
	{
		this.color = color;
	}

	public void paint(Graphics g)
	{
        g.setColor(getColor());
        Rectangle2D rect = new Rectangle2D.Double(xPos, yPos, width, height);//prostokąt definiowany na double-ach
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(rect); 
    }
}