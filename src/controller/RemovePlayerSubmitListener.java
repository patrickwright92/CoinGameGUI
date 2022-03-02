package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import src.model.interfaces.GameEngine;
import src.model.interfaces.Player;
import view.components.GameFrame;
import view.forms.RemovePlayerForm;

public class RemovePlayerSubmitListener implements ActionListener {

	private GameEngine gameEngine;
	private RemovePlayerForm removePlayerForm;
	private GameFrame gameFrame;

	public RemovePlayerSubmitListener(RemovePlayerForm removePlayer, GameEngine gameEngine, GameFrame gameFrame) {
		this.gameEngine = gameEngine;
		this.removePlayerForm = removePlayer;
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			String playerId = this.removePlayerForm.getPlayerComboBox().getSelectedItem().toString();
			Player player = this.gameEngine.getPlayer(playerId);
			// remove player confirmation and updating summary panel
			if (gameEngine.removePlayer(player)) {
				JOptionPane.showMessageDialog(null, "Delete Outcome: Success");
				gameFrame.getSummaryPanel().deletePlayer(player);
				gameFrame.getGameToolBar().removePlayer(player);
				removePlayerForm.dispose();

				// disable buttons if there's no player left after the current one is removed
				if (gameEngine.getAllPlayers() == null || gameEngine.getAllPlayers().size() == 0) {
					gameFrame.getGameToolBar().disableSpinButton();
					gameFrame.getGameMenu().disableBtnNoPlayerFound();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Delete Outcome: Failed", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, x.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
		}

	}

}
