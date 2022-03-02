package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import src.model.SimplePlayer;
import src.model.interfaces.GameEngine;
import src.model.interfaces.Player;
import view.components.GameFrame;
import view.forms.AddPlayerForm;

public class AddPlayerSubmitListener implements ActionListener {

	private GameEngine gameEngine;
	private AddPlayerForm addPlayerForm;
	private GameFrame gameFrame;

	public AddPlayerSubmitListener(AddPlayerForm addPlayerForm, GameEngine gameEngine, GameFrame gameFrame) {
		this.gameEngine = gameEngine;
		this.gameFrame = gameFrame;
		this.addPlayerForm = addPlayerForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			String playerId = addPlayerForm.getPlayerId_text();
			String playerName = addPlayerForm.getPlayerName_text();
			int playerPoints = Integer.parseInt(addPlayerForm.getInitialPoints_text());

			if (gameEngine.getPlayer(playerId) != null) {
				JOptionPane.showMessageDialog(null, "User Already Exists", "Error Message", JOptionPane.ERROR_MESSAGE);
			} else {
				// create and add a new player to the gameEngine
				Player player = new SimplePlayer(playerId, playerName, playerPoints);
				gameEngine.addPlayer(player);
				addPlayerForm.dispose();

				// add player to the summary panel
				gameFrame.getSummaryPanel().addPlayer(gameFrame, player);
				gameFrame.getGameToolBar().addAdditionalPlayer(player);

				// success notification message
				JOptionPane.showMessageDialog(null, "Player Added: Success");

				// enable relevant buttons
				gameFrame.getGameToolBar().enableSpinButton();
				gameFrame.getGameMenu().enableBtnPlayerFound();
			}
			// Catch invalid input values
		} catch (NumberFormatException x) {
			JOptionPane.showMessageDialog(null, "Invalid Points Value!", "Error Message", JOptionPane.ERROR_MESSAGE);
		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, x.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
}
