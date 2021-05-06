package testy;

import java.awt.Color;
import java.awt.Graphics;

public class prostokat
{

    private int xPos;
	private int yPos;
    private int width;
    private int height;
    private Color color = Color.BLACK;
    private int vx, vy;
     
    public void setvx(int i)
    {
    	vx = i;
    }
    
    public int getvx()
    {
    	return vx;
    }
    
    public void setvy(int i)
    {
    	vy = i;
    }
    
    public int getvy()
    {
    	return vy;
    }
    
    public int getX() 
    {
		return xPos;
	}

	public void setX(int xPos) 
	{
		this.xPos = xPos;
	}

    public void setY(int yPos)
    {
        this.yPos = yPos;
    }

    public int getY()
    {
        return yPos;
    }

    public int getWidth()
    {
        return width;
    } 

    public int getHeight()
    {
        return height;
    }


	public void setWidth(int width) 
	{
		this.width = width;
	}

	public void setHeight(int height) 
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
        g.fillRect(xPos,yPos,getWidth(),getHeight());
    }
}