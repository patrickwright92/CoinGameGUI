package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import src.model.interfaces.CoinPair;
import src.model.interfaces.GameEngine;
import src.model.interfaces.Player;

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel {

	private JLabel winCoin1, winCoin2;
	private GameEngine gameEngine;
	private GameFrame gameFrame;
	private JPanel playerInfoSection;
	private Map<String, PlayerSummaryPanel> playerList = new HashMap<String, PlayerSummaryPanel>();
	private boolean hasBet;

	public SummaryPanel(GameEngine gameEngine, GameFrame gameFrame) {
		this.gameEngine = gameEngine;
		this.gameFrame = gameFrame;
		this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		this.setLayout(new BorderLayout());
		this.renderScoreInfo();
	}

	private void renderScoreInfo() {
		// Coin Outcome Section
		JPanel coinOutcome = new JPanel();
		coinOutcome.setLayout(new GridLayout(2, 2, 5, 5));
		coinOutcome.setBorder(BorderFactory.createTitledBorder("Spinner Result"));
		winCoin1 = new JLabel("");
		winCoin2 = new JLabel("");
		coinOutcome.add(new JLabel("C1"));
		coinOutcome.add(new JLabel("C2"));
		coinOutcome.add(winCoin1);
		coinOutcome.add(winCoin2);

		// Summary balance 
		playerInfoSection = new JPanel();
		playerInfoSection.setBorder(new EmptyBorder(0, 5, 0, 0));

		// Load initial players into summary pannel
		for (Player player : gameEngine.getAllPlayers()) {
			addPlayer(gameFrame, player);
		}

		// Add two sections into the summary panel
		this.add(coinOutcome, BorderLayout.PAGE_START);
		this.add(playerInfoSection, BorderLayout.CENTER);

		// creating scroll pane
		JScrollPane scrollPane = new JScrollPane(playerInfoSection);
		scrollPane.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
		this.add(scrollPane);

	}

	public void addPlayer(GameFrame gameFrame, Player player) {
		PlayerSummaryPanel playerInfo = new PlayerSummaryPanel(player);
		playerList.put(player.getPlayerId(), playerInfo);
		playerInfoSection.add(playerInfo);
		updateLayout();
	}

	public void deletePlayer(Player player) {
		PlayerSummaryPanel selectedPlayerInfo = getPlayerSummaryPanel(player.getPlayerId());
		playerList.remove(player.getPlayerId());
		playerInfoSection.remove(selectedPlayerInfo);
		updateLayout();
	}

	public void updatePlayerBetInfo(Player player) {
		PlayerSummaryPanel selectedPlayerInfo = getPlayerSummaryPanel(player.getPlayerId());
		selectedPlayerInfo.updateBetInfo(player);
		hasBet = true;
	}

	public void updateCoinValues(CoinPair coinpair) {
		winCoin1.setText(String.valueOf(coinpair.getCoin1().getFace()));
		winCoin2.setText(String.valueOf(coinpair.getCoin2().getFace()));
	}

	public void updateBalance(Player player) {
		PlayerSummaryPanel selectedPlayerInfo = getPlayerSummaryPanel(player.getPlayerId());
		selectedPlayerInfo.updateBalanceInfo(player);
	}

	public void updatePlayerBalances(CoinPair coinpair) {
		gameEngine.applyBetResults(coinpair);
		for (Player player : gameEngine.getAllPlayers()) {
			this.updateBalance(player);
		}
	}

	public void resetPlayerBet() {
		for (Player player : gameEngine.getAllPlayers()) {
			player.resetBet();
			this.updatePlayerBetInfo(player);
		}
	}

	private void updateLayout() {
		int size = gameEngine.getAllPlayers().size() < 15 ? 15 : gameEngine.getAllPlayers().size() + 1;
		playerInfoSection.setLayout(new GridLayout(size, 5, 4, 4));
		playerInfoSection.setSize(new Dimension(250, 20));
		playerInfoSection.invalidate();
		this.validate();
	}

	public PlayerSummaryPanel getPlayerSummaryPanel(String id) {
		// Return the player if id exists in the player list hash map
		return playerList.get(id);
	}

	public boolean getBetStatus() {
		return hasBet;
	}

}
