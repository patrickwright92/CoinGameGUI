package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.model.interfaces.GameEngine;
import view.components.GameFrame;
import view.forms.AddPlayerForm;

public class AddPlayerListener implements ActionListener {
	private GameEngine gameEngine;
	private GameFrame gameFrame;

	public AddPlayerListener(GameEngine gameEngine, GameFrame gameFrame) {
		this.gameEngine = gameEngine;
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new AddPlayerForm(gameEngine, gameFrame);
	}
}
