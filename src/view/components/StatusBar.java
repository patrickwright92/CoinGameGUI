package view.components;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import src.model.interfaces.Coin;
import src.model.interfaces.GameEngine;
import src.model.interfaces.Player;

@SuppressWarnings("serial")
public class StatusBar extends JPanel {

	private JLabel statusLabel1;
	private JLabel statusLabel2;

	public StatusBar() {
		// create labels
		statusLabel1 = new JLabel("Coin 1: ");
		statusLabel2 = new JLabel("Coin 2: ");
		// set layout
		setLayout(new GridLayout(1, 3));
		// set border
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		statusLabel1.setBorder(blackBorder);
		statusLabel2.setBorder(blackBorder);
		// add labels to bar
		add(statusLabel1);
		add(statusLabel2);

	}

	public void updateStatusBar(Player player, Coin coin, GameEngine gameEngine) {
		statusLabel1.setText("Coin 1: " + player.getResult().getCoin1().getFace());
		statusLabel2.setText("Coin 2: " + player.getResult().getCoin2().getFace());

	}
}

