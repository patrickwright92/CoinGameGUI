package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.model.interfaces.GameEngine;
import src.model.interfaces.Player;
import view.components.GameFrame;

public class SpinButtonListener implements ActionListener {

	private GameEngine gameEngine;
	private GameFrame gameFrame;

	public SpinButtonListener(GameEngine gameEngine, GameFrame gameFrame) {
		this.gameEngine = gameEngine;
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player currentPlayer = gameFrame.getGameToolBar().getSelectedPlayer();

		new Thread() {
			@Override
			public void run() {
				if (gameFrame.getSummaryPanel().getBetStatus() == true) {
					// disable spin button while player spins
					gameFrame.getGameToolBar().disableSpinButton();
					// spin player
					gameEngine.spinPlayer(currentPlayer, 100, 1000, 100, 50, 500, 50);
					// spin spinner
					gameEngine.spinSpinner(100, 1000, 200, 50, 500, 25);
					// enable spin button once player has spun
					gameFrame.getGameToolBar().enableSpinButton();
				}
			}
		}.start();

	}

}
