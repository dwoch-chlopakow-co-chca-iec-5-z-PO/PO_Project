package michelson;


import static java.util.concurrent.TimeUnit.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class KlasaGlowna extends JFrame {
	private static final long serialVersionUID = 1L;
	
	
	final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	final ExecutorService exec = Executors.newSingleThreadExecutor();
	JLabel delta_t = new JLabel();


	Locale currentLocale;
	ResourceBundle messages;
	

	
	
	
	JMenuBar pasek_menu;
	JMenu menu;
	JMenu grubosc_menu;
	JMenuItem dzialanie;
	JMenuItem dane;
	
	
	
	static ArrowsPanel duzy;
	static prostokaty animacja;
	JPanel prawy;
	JPanel dolny;
	
	
	JButton prawy_jezyk;
	JButton prawy_opis;
	JButton prawy_kolor;
	JButton prawy_eter;
	JButton prawy_zamknij;
	JButton start;
	
	JLabel tytul1;
	JLabel tytul2;
	JSlider predkosc;
	JSlider obrot;
	
	Color kolor;
	
	static final int predkosc_min = 0;
	static final int predkosc_max = 50;
	static final int predkosc_init = 0;
	
	static final int obrot_min = -180;
	static final int obrot_max = 180;
	static final int obrot_init = 0;
	
	int czy_wiatr = 0;
	
	int jezyk = 0;
	

	
	public KlasaGlowna() throws HeadlessException {
		
		
	currentLocale = new Locale("en");
	
	messages = ResourceBundle.getBundle("michelson.dictionaries.MessagesBundle", currentLocale);

		
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//slidery
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
		//koniec sliderów
		
		//Menu
		
				pasek_menu = new JMenuBar();
				setJMenuBar(pasek_menu);
				
				menu = new JMenu(messages.getString("Menu"));
				pasek_menu.add(menu);
				
				dane = new JMenuItem(messages.getString("Menu_item1"));
				dzialanie = new JMenuItem(messages.getString("Menu_item2"));
			
				
			
				
				
				
				dane.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null,
								messages.getString("Info_data"), messages.getString("Info_title"), JOptionPane.INFORMATION_MESSAGE);
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
		animacja = new prostokaty();
		this.add(animacja, BorderLayout.CENTER);
		animacja.setBackground(Color.white);
		animacja.setActive(false);
		
		
		exec.execute(animacja);
		exec.shutdown();
		//koniec panelu z animacją lasera
		
		
				
		
		//Początek dużego panelu
		
		duzy = new ArrowsPanel();
		
		for (int i = 0; i < 233; i++)
			duzy.addArrow();
		animacja.add(duzy);
		duzy.setVisible(false);
		
		
		
		scheduler.scheduleWithFixedDelay(duzy, 0, 30, MILLISECONDS);
		
		
		
		//Koniec dużego panelu
		
		
		
		//Początek prawego panelu
		
		prawy = new JPanel();
		add(prawy, BorderLayout.EAST);
		
	//	prawy.setAlignmentY(Component.CENTER_ALIGNMENT);
		prawy.setLayout(new GridLayout(5, 1));
		prawy.setBackground(Color.gray);
		prawy.setPreferredSize(new Dimension(230,1));
	
		
		prawy_jezyk = new JButton(messages.getString("Button1"));
		prawy_jezyk.setFont(new Font("Arial", Font.PLAIN, 15));
		prawy_opis = new JButton(messages.getString("Button2"));
		prawy_opis.setFont(new Font("Arial", Font.PLAIN, 15));
		prawy_kolor = new JButton(messages.getString("Button3"));
		prawy_kolor.setFont(new Font("Arial", Font.PLAIN, 15));
		prawy_eter = new JButton(messages.getString("Button42"));
		prawy_eter.setFont(new Font("Arial", Font.PLAIN, 15));
		prawy_zamknij = new JButton(messages.getString("Button5"));
		prawy_zamknij.setFont(new Font("Arial", Font.PLAIN, 15));
		
		
		
		
		prawy_jezyk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(jezyk == 0) {
					jezyk = 1;
					currentLocale = new Locale("pl");
					messages = ResourceBundle.getBundle("michelson.dictionaries.MessagesBundle", currentLocale);
					}
				
				else if(jezyk == 1) {
					jezyk = 0;
					currentLocale = new Locale("us");
					messages = ResourceBundle.getBundle("michelson.dictionaries.MessagesBundle", currentLocale);
					}
				
				prawy_jezyk.setText(messages.getString("Button1"));
				prawy_opis.setText(messages.getString("Button2"));
				prawy_kolor.setText(messages.getString("Button3"));
				
				if(czy_wiatr == 1)
					prawy_eter.setText(messages.getString("Button41"));
				if(czy_wiatr == 0)
					prawy_eter.setText(messages.getString("Button42"));
				
				prawy_zamknij.setText(messages.getString("Button5"));
				start.setText(messages.getString("Button_down"));
				tytul1.setText(messages.getString("Slider1"));
				tytul2.setText(messages.getString("Slider2"));
				menu.setText(messages.getString("Menu"));
				dzialanie.setText(messages.getString("Menu_item2"));
				dane.setText(messages.getString("Menu_item1"));
				}
		}
		);
		
		
		prawy_opis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(KlasaGlowna.this,
						messages.getString("Description"),messages.getString("Description_title"),JOptionPane.INFORMATION_MESSAGE);
			}
		}
		);
		
		
		prawy_kolor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] options = {messages.getString("ColorChooserPaneopt2"),
									messages.getString("ColorChooserPaneopt1")};
				
		        int n = JOptionPane.showOptionDialog(KlasaGlowna.this,
		        				messages.getString("ColorChooserPaneText"),
		                        messages.getString("ColorChooserPaneTitle"),
		                        JOptionPane.YES_NO_OPTION,
		                        JOptionPane.QUESTION_MESSAGE,
		                        null,
		                        options,
		                        null);
		        if (n == JOptionPane.NO_OPTION) {
			        kolor = JColorChooser.showDialog(null, messages.getString("ColorChooser"), Color.darkGray);
		        	duzy.setBackground(kolor);
		        	animacja.setBackgroundColor(kolor);
		        } 
		        else if (n == JOptionPane.YES_OPTION) {
			        kolor = JColorChooser.showDialog(null, messages.getString("ColorChooser"), Color.darkGray);
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
					prawy_eter.setText(messages.getString("Button41"));
					predkosc.setVisible(true);//widoczne elementy związane ze sliderami
					obrot.setVisible(true);
					tytul1.setVisible(true);
					tytul2.setVisible(true);
					duzy.setVisible(true);
					animacja.reset();
					animacja.setActive(false);
					revalidate();
				}
				else
				{
					czy_wiatr=0;
					prawy_eter.setText(messages.getString("Button42"));
					predkosc.setVisible(false);//niewidoczne elementy związane ze sliderami
					obrot.setVisible(false);
					tytul1.setVisible(false);
					tytul2.setVisible(false);
					duzy.setVisible(false);
					predkosc.setValue(0);
					obrot.setValue(0);
					animacja.reset();
					animacja.setActive(false);
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
		start = new JButton(messages.getString("Button_down"));// do dodania actionlistener
		start.setFont(new Font("Arial", Font.PLAIN, 20));//ustawienia fonta
		
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
			animacja.modV(obrot.getValue(), predkosc.getValue());
			animacja.reset();
			animacja.setActive(true);
			obrot.setEnabled(false);
			predkosc.setEnabled(false);
			prawy_eter.setEnabled(false);
			delta_t.setText("");


			delta_t.setFont(new Font("Arial", Font.PLAIN, 20));

			Timer timer = new Timer(true);


	        timer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                SwingUtilities.invokeLater(new Runnable() {
	                    @Override
	                    public void run() {
	                    	obrot.setEnabled(true);
	        				predkosc.setEnabled(true);
	        				prawy_eter.setEnabled(true);
	        				animacja.setv(1);
							delta_t.setText("\u0394 t = " + prostokaty.getDelta()+" ms");
							dolny.revalidate();
							dolny.repaint();
	                    }
	                    });
	                }}, 9000);
			}
		});
		
		//JPanel na_start = new JPanel(new GridLayout(6, 1)); 
		JPanel na_start = new JPanel(new GridBagLayout()); //decyzja czy lepszy griLayout czy gridbag
		na_start.setBorder(BorderFactory.createLineBorder(Color.black, 1));//ustawienia borderów
		na_start.add(start);

		JPanel t = new JPanel(new GridBagLayout());//GridbagLayout żeby wycentrować JLabel




		t.add(delta_t);
		t.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		
		JPanel slidery = new JPanel();
		slidery.setLayout(new GridLayout(4, 1));
		slidery.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	
		
		
		
		
		
		tytul1 = new JLabel(messages.getString("Slider1"));
		tytul2 = new JLabel(messages.getString("Slider2"));
		
		predkosc.setVisible(false);
		obrot.setVisible(false);
		tytul1.setVisible(false);
		tytul2.setVisible(false);
		
		slidery.add(tytul1);
		slidery.add(predkosc);
		slidery.add(tytul2);
		slidery.add(obrot);
		
		predkosc.setMajorTickSpacing(10);//dodanie upiększeń do slidera
		predkosc.setMinorTickSpacing(1);
		predkosc.setPaintTicks(true);
		predkosc.setPaintLabels(true);
		predkosc.setSnapToTicks(true);
		
		obrot.setMajorTickSpacing(90);//dodanie upiększeń do slidera
		obrot.setMinorTickSpacing(10);
		obrot.setPaintTicks(true);
		obrot.setPaintLabels(true);
		obrot.setSnapToTicks(true);
		
		
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