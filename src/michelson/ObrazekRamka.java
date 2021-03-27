package michelson;

import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class ObrazekRamka extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObrazekRamka() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		ObrazekPanel obrazek = new ObrazekPanel();
		setSize(obrazek.getPreferredSize());
		add(obrazek);
	}

	
	

}
