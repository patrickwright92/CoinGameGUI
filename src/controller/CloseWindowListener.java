package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;


public class CloseWindowListener implements ActionListener {

	private JDialog dialogBox;
	
	public CloseWindowListener(JDialog dialogBox) {
		this.dialogBox = dialogBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dialogBox.dispose();
		
	}

}
