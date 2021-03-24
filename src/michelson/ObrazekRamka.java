package michelson;

import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class ObrazekRamka extends JFrame {

	public ObrazekRamka() throws HeadlessException {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		ObrazekPanel obrazek = new ObrazekPanel();
		setSize(obrazek.getPreferredSize());
		add(obrazek);
	}

	
	

}
