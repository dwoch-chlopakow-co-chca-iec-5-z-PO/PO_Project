package testy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

public class stolik extends JFrame 
{
	stolik()
	{
	this.setSize(640, 480);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setLayout(new BorderLayout());
	}

	public static void main(String[] args)
	{
		stolik frame = new stolik();
    	
    	prostokaty panel = new prostokaty();
    	panel.setBackground(Color.white);
    	frame.add(panel);
    	frame.setVisible(true);
    	panel.modV(180, 1);
    	
    	ExecutorService exec = Executors.newFixedThreadPool(1);
    	exec.execute(panel);
    	exec.shutdown();
	}
}
