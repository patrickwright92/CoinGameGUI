package view.forms;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import controller.CloseWindowListener;
import controller.RemovePlayerSubmitListener;
import src.model.interfaces.GameEngine;
import src.model.interfaces.Player;
import view.components.GameFrame;

@SuppressWarnings("serial")
public class RemovePlayerForm extends JDialog {

	private GameFrame gameFrame;
	private JPanel panel;
	private JComboBox<String> playerComboBox;
	private JButton submitBtn, cancelBtn;

	public RemovePlayerForm(GameEngine gameEngine, GameFrame gameFrame) {
		this.gameFrame = gameFrame;

		setTitle("Remove Player Form");
		ShowContent(gameEngine);
		setSize(500, 150);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	public void ShowContent(GameEngine gameEngine) {

		panel = new JPanel(new GridLayout(2, 2, 20, 20));

		// Select Player
		panel.add(new JLabel("Select Player:"));
		playerComboBox = new JComboBox<String>();
		for (Player player : gameEngine.getAllPlayers()) {
			playerComboBox.addItem(player.getPlayerId());
		}

		panel.add(playerComboBox);

		submitBtn = new JButton("DELETE");
		cancelBtn = new JButton("CANCEL");
		panel.add(cancelBtn);
		panel.add(submitBtn);

		this.add(panel, BorderLayout.CENTER);

		// adding listeners to buttons
		submitBtn.addActionListener(new RemovePlayerSubmitListener(this, gameEngine, gameFrame));
		cancelBtn.addActionListener(new CloseWindowListener(this));
	}

	public JComboBox<String> getPlayerComboBox() {
		return playerComboBox;
	}
}
