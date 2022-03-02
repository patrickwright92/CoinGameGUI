package view.components;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.AddPlayerListener;
import controller.BetListener;
import controller.RemovePlayerListener;
import src.model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class GameMenu extends JMenuBar {

	private JMenuItem addPlayer;
	private JMenuItem removePlayer;
	private JMenuItem setBet;

	public GameMenu(GameEngine gameEngine, GameFrame gameFrame) {
		// creating Menu
		JMenu menu = new JMenu("Game Menu");
		menu.setMnemonic(KeyEvent.VK_TAB);
		// create menu items
		// add player
		addPlayer = new JMenuItem("Add Player");
		addPlayer.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.SHIFT_DOWN_MASK));
		addPlayer.addActionListener(new AddPlayerListener(gameEngine, gameFrame));
		// remove player
		removePlayer = new JMenuItem("Remove Player");
		removePlayer.setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.SHIFT_DOWN_MASK));
		removePlayer.addActionListener(new RemovePlayerListener(gameEngine, gameFrame));
		// setting a bet
		setBet = new JMenuItem("Set Bet");
		setBet.setAccelerator(KeyStroke.getKeyStroke('B', InputEvent.SHIFT_DOWN_MASK));
		setBet.addActionListener(new BetListener(gameEngine, gameFrame));
		// add Menu Items to Menu
		menu.add(addPlayer);
		menu.add(removePlayer);
		menu.add(setBet);
		// add Menu to Menu Bar
		this.add(menu);

	}

	// disable buttons when no players in gameEngine
	public void disableBtnNoPlayerFound() {
		setBet.setEnabled(false);
		removePlayer.setEnabled(false);

	}

	// enable buttons when players in gameEngine
	public void enableBtnPlayerFound() {
		setBet.setEnabled(true);
		removePlayer.setEnabled(true);
	}

}
