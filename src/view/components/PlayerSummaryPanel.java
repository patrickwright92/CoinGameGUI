package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import src.model.interfaces.Player;

@SuppressWarnings("serial")
public class PlayerSummaryPanel extends JPanel {

	private JLabel playerId_text = new JLabel();
	private JLabel playerName_text = new JLabel();
	private JLabel points_text = new JLabel();
	private JLabel betType_text = new JLabel();
	private JLabel bet_text = new JLabel();
	private JLabel winLoss_text = new JLabel();

	public PlayerSummaryPanel(Player player) {
		//layout of the panel
		this.setLayout(new BorderLayout(5, 5));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		//setup the player panel
		JPanel panelContent = new JPanel(new GridLayout(2, 3));
		panelContent = ShowPanelContent(player);
		//add components to the panel
		add(this.playerName_text, BorderLayout.NORTH);
		add(panelContent, BorderLayout.CENTER);
	}

	private JPanel ShowPanelContent(Player player) {
		JPanel panelContent = new JPanel(new GridLayout(2, 3));

		String betType = String.valueOf(player.getBetType());
		String playerId = player.getPlayerId();
		String playerName = player.getPlayerName();
		String point = String.valueOf(player.getPoints());
		String bet = String.valueOf(player.getBet());
		String winLoss = " ";
		// assigning values
		this.playerId_text.setText(playerId);
		this.playerName_text.setText(playerName);
		this.points_text.setText(point);
		this.betType_text.setText(betType.equals("null") ? "Not Bet" : betType);
		this.bet_text.setText(bet);
		this.winLoss_text.setText(winLoss);
		// add to frame
		panelContent.add(new JLabel("ID"));
		panelContent.add(new JLabel("Point"));
		panelContent.add(new JLabel("BetType"));
		panelContent.add(new JLabel("Bet"));
		panelContent.add(new JLabel("Win/Loss"));
		panelContent.add(this.playerId_text);
		panelContent.add(this.points_text);
		panelContent.add(this.betType_text);
		panelContent.add(this.bet_text);
		panelContent.add(this.winLoss_text);
		// return panel
		return panelContent;
	}

	public void updateBetInfo(Player player) {
		String betType = String.valueOf(player.getBetType());
		this.betType_text.setText(betType.equals("null") ? "Not Bet" : betType);
		this.bet_text.setText(String.valueOf(player.getBet()));

	}

	public void updateBalanceInfo(Player player) {
		int currentPoint = player.getPoints();
		this.updateWINLOSS(player);
		this.points_text.setText(String.valueOf(currentPoint));
	}

	public void updateWINLOSS(Player player) {
		int initialPoints = Integer.parseInt(this.points_text.getText());
		int currentPoints = player.getPoints();
		if(currentPoints > initialPoints) {
			this.winLoss_text.setText("WIN");
		} else {
			this.winLoss_text.setText("LOSS");
		}

	}
	
}
