package view;

import src.model.interfaces.Coin;
import src.model.interfaces.CoinPair;
import src.model.interfaces.GameEngine;
import src.model.interfaces.Player;
import src.view.interfaces.GameEngineCallback;
import view.components.GameFrame;

public class GameEngineCallbackGUI implements GameEngineCallback {
	private GameFrame gameFrame;
	private GameEngine gameEngine;

	// constructor
	public GameEngineCallbackGUI(GameFrame gameFrame, GameEngine gameEngine) {
		this.gameFrame = gameFrame;
		this.gameEngine = gameEngine;

	}

	@Override
	public void playerCoinUpdate(Player player, Coin coin, GameEngine engine) {
		this.gameFrame.getStatusBar().updateStatusBar(player, coin, engine);
		if (coin.getNumber() == 1) {
			this.gameFrame.getCoinPanel().setCoin1(coin.getFace());
		} else {
			this.gameFrame.getCoinPanel().setCoin2(coin.getFace());
		}

	}

	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
		if (coin.getNumber() == 1) {
			this.gameFrame.getCoinPanel().setCoin1(coin.getFace());
		} else {
			this.gameFrame.getCoinPanel().setCoin2(coin.getFace());
		}

	}

	@Override
	public void playerResult(Player player, CoinPair coinPair, GameEngine engine) {

	}

	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) {
		this.gameFrame.getSummaryPanel().updateCoinValues(coinPair);
		for (Player players : engine.getAllPlayers()) {
			if (players.getBet() != 0) {
				this.gameFrame.getSummaryPanel().getPlayerSummaryPanel(players.getPlayerId())
						.updateBalanceInfo(players);
			}
		}

	}

}
