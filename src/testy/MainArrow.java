package testy;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.awt.Color;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.*;

public class MainArrow {
	

	public static void main(String[] args) {
		
		final ScheduledExecutorService scheduler = 
			       Executors.newScheduledThreadPool(1);
		
		
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	
	            	
	            	
	            	JFrame frame = new JFrame("Rysowanie strzałki");
	            	frame.setSize(640, 480); 
	            	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        		
	            	
	            	
	        		PanelArrow pa = new PanelArrow();
	        		for (int i = 0; i < 400; i++)
	        				pa.addArrow();
	        	
	        		
	        		frame.add(pa);
	        		
	        		frame.setVisible(true);
	        		
	        		
					scheduler.scheduleWithFixedDelay(pa, 0, 5, MILLISECONDS);

	    
	            }
		 });

	}

}
