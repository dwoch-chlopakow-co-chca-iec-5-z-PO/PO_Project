package michelson;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class KlasaGlowna extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String s="cokolwiek";
	JMenuBar pasek_menu;
	JMenu menu;
	JMenu grubosc_menu;
	JMenuItem resize;
	JMenuItem dzialanie;
	JMenuItem dane;
	
	
	
	JPanel duzy;
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
	JSlider Obrot;
	
	Color kolor;
	
	static final int predkosc_min = 0;
	static final int predkosc_max = 100;
	static final int predkosc_init = 0;
	
	static final int obrot_min = 0;
	static final int obrot_max = 360;
	static final int obrot_init = 0;
	
	int czy_wiatr = 0;
	
	public KlasaGlowna() throws HeadlessException {
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		
		
		
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
						JOptionPane.showMessageDialog(null,
								"Program działa tak i tak","Działanie programu",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				);
				menu.add(dzialanie);
				
				

				//Koniec menu
		
		
		
		
		
		
		//Początek dużego panelu
		
		duzy = new JPanel();
		add(duzy, BorderLayout.CENTER);
		duzy.setBackground(Color.white);
		
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
		        } else if (n == JOptionPane.YES_OPTION) {
			        kolor = JColorChooser.showDialog(null, "Wybierz kolor z palety kolorów", Color.darkGray);
		        //	wiazka.setBackground(kolor);
		        }
			}
		}
		);
		
		
		prawy_eter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(czy_wiatr == 0)
				{
					czy_wiatr=1;//zmieniamy zmienną dotyczącą występowania wiatru eteru
					prawy_eter.setText("<html><center>Wybrano opcje <br>uwzględniania wiatru eteru</center></html>");
					predkosc.setVisible(true);//widoczne elementy związane ze sliderami
					Obrot.setVisible(true);
					tytul1.setVisible(true);
					tytul2.setVisible(true);
					revalidate();
				}
				else
				{
					czy_wiatr=0;
					prawy_eter.setText("<html><center>Wybrano opcje <br>nie uwzględniania wiatru eteru</center></html>");
					predkosc.setVisible(false);//niewidoczne elementy związane ze sliderami
					Obrot.setVisible(false);
					tytul1.setVisible(false);
					tytul2.setVisible(false);
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
		
		
		
		
		
		
		
		//Pocz�tek dolnego panelu
		
		dolny = new JPanel();
		add(dolny, BorderLayout.SOUTH);
		
		dolny.setLayout(new GridLayout(1, 3));
		JButton start = new JButton("Rozpocznij symulacje");// do dodania actionlistener
		start.setFont(new Font("Arial", Font.PLAIN, 20));//ustawienia fonta
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
		Obrot = new JSlider(JSlider.HORIZONTAL, obrot_min, obrot_max, obrot_init);//dod dodania listener
		tytul1 = new JLabel("Prędkość wiatru eteru (w kilometrach na sekundę)");
		tytul2 = new JLabel("Obrót stolika (w stopniach)");
		
		predkosc.setVisible(false);
		Obrot.setVisible(false);
		tytul1.setVisible(false);
		tytul2.setVisible(false);
		
		slidery.add(tytul1);
		slidery.add(predkosc);
		slidery.add(tytul2);
		slidery.add(Obrot);
		
		predkosc.setMajorTickSpacing(10);//dodanie upiększeń do slidera
		predkosc.setMinorTickSpacing(5);
		predkosc.setPaintTicks(true);
		predkosc.setPaintLabels(true);
		
		Obrot.setMajorTickSpacing(90);//dodanie upiększeń do slidera
		Obrot.setMinorTickSpacing(45);
		Obrot.setPaintTicks(true);
		Obrot.setPaintLabels(true);
		
		
		dolny.add(na_start);
		dolny.add(t);
		dolny.add(slidery);		
	}

	

	public static void main(String[] args) {
		KlasaGlowna frame = new KlasaGlowna();
		frame.setVisible(true);
		frame.setSize(1280,960);
		frame.setMinimumSize(new Dimension(960,600));
	}

}