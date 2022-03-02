package view.components;


import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.model.enumeration.CoinFace;

@SuppressWarnings("serial")
public class CoinPanel extends JPanel {

	private JLabel coin1 = new JLabel();
	private JLabel coin2 = new JLabel();

	ImageIcon heads = new ImageIcon("images/heads.png");
	ImageIcon tails = new ImageIcon("images/tails.png");

	public CoinPanel() {

		this.setLayout(new GridLayout());
		coin1.setIcon(heads);
		coin2.setIcon(tails);
		add(coin1);
		add(coin2);

	}

	public void setCoin1(CoinFace face) {
		if (face.equals(CoinFace.HEADS)) {
			coin1.setIcon(heads);
		} else {
			coin1.setIcon(tails);
		}
	}

	public void setCoin2(CoinFace face) {
		if (face.equals(CoinFace.HEADS)) {
			coin2.setIcon(heads);
		} else {
			coin2.setIcon(tails);
		}
	}

}
