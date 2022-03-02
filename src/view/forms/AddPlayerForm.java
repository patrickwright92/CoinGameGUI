package view.forms;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controller.AddPlayerSubmitListener;
import controller.CloseWindowListener;
import src.model.interfaces.GameEngine;
import view.components.GameFrame;

@SuppressWarnings("serial")
public class AddPlayerForm extends JDialog {

	private JLabel label1, label2, label3;
	private JTextField playerId_text, playerName_text, initialPoints_text;
	private JButton cancelBtn, submitBtn;
	private JPanel panel;
	private GameEngine gameEngine;
	private GameFrame gameFrame;

	public AddPlayerForm(GameEngine gameEngine, GameFrame gameFrame) {
		this.gameEngine = gameEngine;
		this.gameFrame = gameFrame;

		setTitle("Add Player");
		ShowContent();
		setSize(400, 250);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void ShowContent() {
		// Player ID
		label1 = new JLabel();
		label1.setText("Player ID: ");
		playerId_text = new JTextField();
		
		// Player Name
		label2 = new JLabel();
		label2.setText("Player Name: ");
		playerName_text = new JTextField();
		
		// Player Initial Points
		label3 = new JLabel();
		label3.setText("Initial Points: ");
		initialPoints_text = new JTextField();
		
		// Submit and Cancel Buttons
		cancelBtn = new JButton("Cancel");
		submitBtn = new JButton("Submit");
		
		// Build panel
		panel = new JPanel(new GridLayout(4,2,20,20));
		panel.add(label1);
		panel.add(playerId_text);
		panel.add(label2);
		panel.add(playerName_text);
		panel.add(label3);
		panel.add(initialPoints_text);
		panel.add(cancelBtn);
		panel.add(submitBtn);
		
		// Add panel
		this.add(panel, BorderLayout.CENTER);
		
		// Add Action Listeners to buttons
		submitBtn.addActionListener(new AddPlayerSubmitListener(this, gameEngine, gameFrame));
		cancelBtn.addActionListener(new CloseWindowListener(this));
	}
	
	public String getPlayerId_text() {
		return playerId_text.getText();
	}
	
	public String getPlayerName_text() {
		return playerName_text.getText();
	}
	
	public String getInitialPoints_text() {
		return initialPoints_text.getText();
	}

}
