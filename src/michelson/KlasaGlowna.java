package michelson;


import static java.util.concurrent.TimeUnit.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class KlasaGlowna extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final ScheduledExecutorService scheduler = 
		       Executors.newScheduledThreadPool(2);
	
	
	JMenuBar pasek_menu;
	JMenu menu;
	JMenu grubosc_menu;
	JMenuItem resize;
	JMenuItem dzialanie;
	JMenuItem dane;
	
	
	
	static ArrowsPanel duzy;
	JPanel prawy;
	JPanel dolny;
	
	
	JButton prawy_jezyk;
	JButton prawy_opis;
	JButton prawy_kolor;
	JButton prawy_eter;
	JButton prawy_zamknij;
	
	JLabel tytul1;
	JLabel tytul2;
	JSlider predkosc;
	JSlider obrot;
	
	Color kolor;
	
	static final int predkosc_min = 0;
	static final int predkosc_max = 10;
	static final int predkosc_init = 0;
	
	static final int obrot_min = -180;
	static final int obrot_max = 180;
	static final int obrot_init = 0;
	
	int czy_wiatr = 0;
	

	
	public KlasaGlowna() throws HeadlessException {
		
		
		
	
		
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		
		
		
		
		//Menu
		
				pasek_menu = new JMenuBar();
				setJMenuBar(pasek_menu);
				
				menu = new JMenu("Witam");
				pasek_menu.add(menu);
				
				resize = new JMenuItem("Ustaw losową wielkość okna");
				dane = new JMenuItem("Dane twórców");
				dzialanie = new JMenuItem("Jak działa program");
				
				resize.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setSize((int)(Math.random()*500)+100,(int)(Math.random()*300)+100);
					}
				}
				);
				menu.add(resize);
				
			
				
				
				
				dane.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null,
								"<html><center>Michał Prędota 305062<br>Kacper Kowerski 305028</center></html>","Dane",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				);
				menu.add(dane);
				
				
				
				
				
				
				dzialanie.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {		
					
						
						
						ObrazekRamka ramka = new ObrazekRamka();
						ramka.setVisible(true);
					}
				}
				);
				
				menu.add(dzialanie);
				
				

				//Koniec menu
		
		
		
		
		//początek panelu z animacją lasera
		prostokaty animacja = new prostokaty();
		this.add(animacja, BorderLayout.CENTER);
		animacja.setBackground(Color.white);
		animacja.setActive(false);
		animacja.modV(90, 2);
		
		
		scheduler.scheduleWithFixedDelay(animacja, 0, 30, MILLISECONDS);
		//koniec panelu z animacją lasera
		
		
				
		
		//Początek dużego panelu
		
		duzy = new ArrowsPanel();
		
		for (int i = 0; i < 233; i++)
			duzy.addArrow();
		animacja.add(duzy);
		duzy.setVisible(false);
		
		
		
		scheduler.scheduleWithFixedDelay(duzy, 0, 5, MILLISECONDS);
		
		
		
		//Koniec dużego panelu
		
		
		
		//Początek prawego panelu
		
		prawy = new JPanel();
		add(prawy, BorderLayout.EAST);
		
	//	prawy.setAlignmentY(Component.CENTER_ALIGNMENT);
		prawy.setLayout(new GridLayout(5, 1));
		prawy.setBackground(Color.gray);
		prawy.setPreferredSize(new Dimension(230,1));
	
		
		prawy_jezyk = new JButton("Kliknij aby zmienić język");
		prawy_jezyk.setFont(new Font("Arial", Font.PLAIN, 15));
		prawy_opis = new JButton("<html><center>Kliknij, aby wyświetlić<br>krótki opis doświadczenia</center></html>");
		prawy_opis.setFont(new Font("Arial", Font.PLAIN, 15));
		prawy_kolor = new JButton("<html><center>Kliknij, aby zmienić<br>kolor tła i/lub promienia</center></html>");
		prawy_kolor.setFont(new Font("Arial", Font.PLAIN, 15));
		prawy_eter = new JButton("<html><center>Kliknij, aby uwzględnić<br>wiatr eteru</center></html>");
		prawy_eter.setFont(new Font("Arial", Font.PLAIN, 15));
		prawy_zamknij = new JButton("Kliknij, aby zamknąć program");
		prawy_zamknij.setFont(new Font("Arial", Font.PLAIN, 15));
		
		
		
		
		prawy_jezyk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Narazie nic bo nwm jak
			}
		}
		);
		
		
		prawy_opis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(KlasaGlowna.this,
						"Eksperyment polega na tym i na tym","Krótki opis",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		);
		
		
		prawy_kolor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] options = {"Kolor wiązki",
						"Kolor tła"};
		        int n = JOptionPane.showOptionDialog(KlasaGlowna.this,
		                        "Pytanie z opcjami wyboru Tak/Nie/Anuluj "
		                        + "z wlasnym opisem przyciskow",
		                        "Tytul okna z pytaniem...",
		                        JOptionPane.YES_NO_OPTION,
		                        JOptionPane.QUESTION_MESSAGE,
		                        null,
		                        options,
		                        null);
		        if (n == JOptionPane.NO_OPTION) {
			        kolor = JColorChooser.showDialog(null, "Wybierz kolor z palety kolorów", Color.darkGray);
		        	duzy.setBackground(kolor);
		        	animacja.setBackgroundColor(kolor);
		        } 
		        else if (n == JOptionPane.YES_OPTION) {
			        kolor = JColorChooser.showDialog(null, "Wybierz kolor z palety kolorów", Color.darkGray);
		        	animacja.setLaserColor(kolor);
		        }
			}
		}
		);
		
		
		prawy_eter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(czy_wiatr == 0)
				{
					czy_wiatr = 1;//zmieniamy zmienną dotyczącą występowania wiatru eteru
					prawy_eter.setText("<html><center>Wybrano opcje <br>uwzględniania wiatru eteru</center></html>");
					predkosc.setVisible(true);//widoczne elementy związane ze sliderami
					obrot.setVisible(true);
					tytul1.setVisible(true);
					tytul2.setVisible(true);
					duzy.setVisible(true);
					revalidate();
				}
				else
				{
					czy_wiatr=0;
					prawy_eter.setText("<html><center>Wybrano opcje <br>nie uwzględniania wiatru eteru</center></html>");
					predkosc.setVisible(false);//niewidoczne elementy związane ze sliderami
					obrot.setVisible(false);
					tytul1.setVisible(false);
					tytul2.setVisible(false);
					duzy.setVisible(false);
					predkosc.setValue(0);
					obrot.setValue(0);
					revalidate();
				}
			}
		}
		);
		
		
		prawy_zamknij.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		}
		);
		
		
		
		prawy.add(prawy_jezyk);
		prawy.add(prawy_opis);
		prawy.add(prawy_kolor);
		prawy.add(prawy_eter);
		prawy.add(prawy_zamknij);
		
		
		
		
		//Koniec prawego panelu
		
		
		
		
		
		
		
		//Początek dolnego panelu
		
		dolny = new JPanel();
		add(dolny, BorderLayout.SOUTH);
		
		dolny.setLayout(new GridLayout(1, 3));
		JButton start = new JButton("Rozpocznij symulacje");// do dodania actionlistener
		start.setFont(new Font("Arial", Font.PLAIN, 20));//ustawienia fonta
		
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
			animacja.reset();
			animacja.setActive(true);
			}
		});
		
		//JPanel na_start = new JPanel(new GridLayout(6, 1)); 
		JPanel na_start = new JPanel(new GridBagLayout()); //decyzja czy lepszy griLayout czy gridbag
		na_start.setBorder(BorderFactory.createLineBorder(Color.black, 1));//ustawienia borderów
		na_start.add(start);

		JPanel t = new JPanel(new GridBagLayout());//GridbagLayout żeby wycentrować JLabel
		JLabel delta_t = new JLabel("\u0394 t= ");
		delta_t.setFont(new Font("Arial", Font.PLAIN, 20));
		t.add(delta_t);
		t.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		
		JPanel slidery = new JPanel();
		slidery.setLayout(new GridLayout(4, 1));
		slidery.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	
		predkosc = new JSlider(JSlider.HORIZONTAL, predkosc_min, predkosc_max, predkosc_init);//do dodania listener
		
		predkosc.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				duzy.setyV(predkosc.getValue());
			}
			
		});
		
		
		
		
		
		obrot = new JSlider(JSlider.HORIZONTAL, obrot_min, obrot_max, obrot_init);//dod dodania listener
		
		obrot.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				duzy.setk(obrot.getValue());
			}
			
		});
		
		
		
		
		tytul1 = new JLabel("Prędkość wiatru eteru (w kilometrach na sekundę)");
		tytul2 = new JLabel("Obrót stolika (w stopniach)");
		
		predkosc.setVisible(false);
		obrot.setVisible(false);
		tytul1.setVisible(false);
		tytul2.setVisible(false);
		
		slidery.add(tytul1);
		slidery.add(predkosc);
		slidery.add(tytul2);
		slidery.add(obrot);
		
		predkosc.setMajorTickSpacing(2);//dodanie upiększeń do slidera
		predkosc.setMinorTickSpacing(1);
		predkosc.setPaintTicks(true);
		predkosc.setPaintLabels(true);
		
		obrot.setMajorTickSpacing(90);//dodanie upiększeń do slidera
		obrot.setMinorTickSpacing(45);
		obrot.setPaintTicks(true);
		obrot.setPaintLabels(true);
		
		
		dolny.add(na_start);
		dolny.add(t);
		dolny.add(slidery);		
		
		
		
	}

	

	public static void main(String[] args) {
		
		KlasaGlowna frame = new KlasaGlowna();
		
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	        		frame.setVisible(true);
	        		frame.setSize(1080,760);
	        		frame.setMinimumSize(new Dimension(960,600));		
	            }
		 });
		 
		
	}

}