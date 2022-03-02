package view.forms;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controller.BetSubmitListener;
import controller.CloseWindowListener;
import src.model.enumeration.BetType;
import src.model.interfaces.GameEngine;
import src.model.interfaces.Player;
import view.components.GameFrame;

@SuppressWarnings("serial")
public class BetForm extends JDialog {
	private GameFrame gameFrame;
	private JPanel panel;
	private JTextField bet;
	private JButton submitBtn, cancelBtn;
	private JComboBox<String> playerComboBox;
	private JComboBox<BetType> betTypeComboBox;

	public BetForm(GameEngine gameEngine, GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		
		setTitle("Set Bet");
		ShowContent(gameEngine);
		setSize(500, 250);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void ShowContent(GameEngine gameEngine) {

		panel = new JPanel(new GridLayout(4, 2, 20, 20));

		// Select player
		panel.add(new JLabel("Player ID:"));
		playerComboBox = new JComboBox<String>();
		for (Player player : gameEngine.getAllPlayers()) {
			playerComboBox.addItem(player.getPlayerId());
		}

		panel.add(playerComboBox);

		// Set Bet Type
		panel.add(new JLabel("Select BetType:"));
		betTypeComboBox = new JComboBox<BetType>();

		for (BetType betType : BetType.values()) {
			betTypeComboBox.addItem(betType);
		}

		panel.add(betTypeComboBox);

		// Set bet
		panel.add(new JLabel("Bet:"));
		bet = new JTextField();
		panel.add(bet);

		// Submit and Cancel Buttons
		submitBtn = new JButton("SUBMIT");
		cancelBtn = new JButton("CANCEL");
		panel.add(cancelBtn);
		panel.add(submitBtn);

		// Layout panel
		this.add(panel, BorderLayout.CENTER);

		// Adding the listeners to buttons
		submitBtn.addActionListener(new BetSubmitListener(this, gameEngine, gameFrame));
		cancelBtn.addActionListener(new CloseWindowListener(this));

	}

	public JComboBox<String> getPlayerComboBox() {
		return playerComboBox;
	}

	public JComboBox<BetType> getBetTypeComboBox() {
		return betTypeComboBox;
	}

	public JTextField getBet() {
		return bet;
	}

}

