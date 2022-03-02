package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import src.model.enumeration.BetType;
import src.model.interfaces.GameEngine;
import src.model.interfaces.Player;
import view.components.GameFrame;
import view.forms.BetForm;

public class BetSubmitListener implements ActionListener {

	private GameEngine gameEngine;
	private BetForm betForm;
	private GameFrame gameFrame;

	public BetSubmitListener(BetForm betForm, GameEngine gameEngine, GameFrame gameFrame) {
		this.betForm = betForm;
		this.gameEngine = gameEngine;
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			String playerId = this.betForm.getPlayerComboBox().getSelectedItem().toString();
			Player player = this.gameEngine.getPlayer(playerId);
			BetType betType = (BetType) this.betForm.getBetTypeComboBox().getSelectedItem();
			int bet = Integer.parseInt(this.betForm.getBet().getText());
			// placing a bet confirmation and updating summary panel
			if (gameEngine.placeBet(player, bet, betType)) {
				JOptionPane.showMessageDialog(null, "You have successfully placed bet");
				gameFrame.getSummaryPanel().updatePlayerBetInfo(player);
				betForm.dispose();
				// check if all players have placed bets or not
				if (AllPlayersHaveBet()) {
					//if all players have bet, new thread and spin automatically
					new Thread() {
						@Override
						public void run() {
							for (Player players : gameEngine.getAllPlayers()) {
								// disable spin button while player spins
								gameFrame.getGameToolBar().disableSpinButton();
								// spin player
								gameEngine.spinPlayer(players, 100, 1000, 100, 50, 500, 50);
								
							}
							gameEngine.spinSpinner(100, 1000, 200, 50, 500, 25);
							// enable spin button once players and spinner have spun
							gameFrame.getGameToolBar().enableSpinButton();
						}
					}.start();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Invalid BetType", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException x) {
			JOptionPane.showMessageDialog(null, "Invalid format of bet! Try again !", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private boolean AllPlayersHaveBet() {
		//checking that all players have placed a valid bet
		int counter = 0;
		int numPlayers = gameEngine.getAllPlayers().size();
		for (Player player : gameEngine.getAllPlayers()) {
			if (player.getBet() > 0) {
				counter++;
			}
		}
		return counter == numPlayers;

	}

}
