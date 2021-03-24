package michelson;

import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class ObrazekRamka extends JFrame {

	public ObrazekRamka() throws HeadlessException {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		ObrazekPanel obrazek = new ObrazekPanel();
		setSize(obrazek.getPreferredSize());
		add(obrazek);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObrazekRamka okno = new ObrazekRamka();
		okno.setVisible(true);
	}

}
