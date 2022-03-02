package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import src.model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	private GameToolBar gameToolBar;
	private CoinPanel coinPanel;
	private StatusBar statusBar;
	private SummaryPanel summaryPanel;
	private GameMenu gameMenu;

	public GameFrame(GameEngine gameEngine) {
		super("Coin Game GUI");

		// setting layout
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null); // center position
		this.setMinimumSize(new Dimension(screenSize.width / 2, screenSize.height / 2));

		// adding main sections		
		coinPanel = new CoinPanel();

		gameToolBar = new GameToolBar(gameEngine, this);
		gameToolBar.setPreferredSize(new Dimension(0, 25));

		summaryPanel = new SummaryPanel(gameEngine, this);
		summaryPanel.setPreferredSize(new Dimension(this.getWidth() / 5, 0));

		gameMenu = new GameMenu(gameEngine, this);
		gameMenu.setPreferredSize(new Dimension(0, 25));

		statusBar = new StatusBar();
		statusBar.setPreferredSize(new Dimension(0, 25));

		// adding components
		setJMenuBar(gameMenu);
		this.add(gameToolBar, BorderLayout.NORTH);
		this.add(coinPanel, BorderLayout.CENTER);
		this.add(statusBar, BorderLayout.SOUTH);
		this.add(summaryPanel, BorderLayout.EAST);

		// setting frame specifics
		setBounds(100, 100, 640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// get methods
	public GameToolBar getGameToolBar() {
		return gameToolBar;
	}

	public CoinPanel getCoinPanel() {
		return coinPanel;
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}

	public SummaryPanel getSummaryPanel() {
		return summaryPanel;
	}

	public GameMenu getGameMenu() {
		return gameMenu;
	}
	

}
