package view.components;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;
import controller.SpinButtonListener;
import src.model.interfaces.GameEngine;
import src.model.interfaces.Player;

@SuppressWarnings("serial")
public class GameToolBar extends JToolBar {
	private GameEngine gameEngine;
	private GameFrame gameFrame;
	private JButton spinBtn;
	private JComboBox<String> cb;

	public GameToolBar(GameEngine gameEngine, GameFrame gameFrame) {
		this.gameEngine = gameEngine;
		this.gameFrame = gameFrame;
		// create items
		spinBtn = new JButton("  spin  ");

		cb = new JComboBox<String>();
		for (Player players : gameEngine.getAllPlayers()) {
			addPlayer(players);
		}
		// add listeners
		addListeners();
		// add items to tool bar
		this.add(spinBtn);
		this.add(cb);
		
		
	
	}
	public void addPlayer(Player player) {
		cb.addItem(player.getPlayerId() + ": " + player.getPlayerName());
	}
	
	public void addAdditionalPlayer(Player player) {
		cb.addItem(player.getPlayerId() + ": " + player.getPlayerName());
	}
	
	public void removePlayer(Player player) {
		cb.removeItem(player.getPlayerId() + ": " + player.getPlayerName());
	}

	public void addListeners() {
		spinBtn.addActionListener(new SpinButtonListener(gameEngine, gameFrame));
	}

	public void disableSpinButton() {
		spinBtn.setEnabled(false);
	}

	public void enableSpinButton() {
		spinBtn.setEnabled(true);
	}

	public Player getSelectedPlayer() {
		String playerID = this.cb.getSelectedItem().toString().substring(0, 1);
		return gameEngine.getPlayer(playerID);
	}
	
	
	public String getSelectedPlayerID() {
		String playerID = this.cb.getSelectedItem().toString().substring(0, 1);
		return playerID;
	}
}
