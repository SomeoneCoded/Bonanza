package view;

import java.awt.Color;

import javax.swing.*;

// this class visualizes the general game field
public class GameFieldPanel extends JPanel {

	// the game field is composed of two player fields and an offer field in between
	private PlayerFieldPanel playerOneField = new PlayerFieldPanel();
	private PlayerFieldPanel playerTwoField = new PlayerFieldPanel();
	private OfferFieldPanel offerField = new OfferFieldPanel();
	
	// background image
	private JLabel backgroundImage = new JLabel(new ImageIcon("images/Dirt.png"));

	// constructor
	public GameFieldPanel() {
		setSize(1920, 1080);
		setLayout(null);
		
		backgroundImage.setBounds(0,0,1920, 1080);
		add(backgroundImage);

		playerOneField.setBounds(0, 0, 1920, 360);
		offerField.setBounds(0, 360, 1920, 360);
		playerTwoField.setBounds(0, 720, 1920, 360);

		backgroundImage.add(playerOneField);
		backgroundImage.add(offerField);
		backgroundImage.add(playerTwoField);

		setVisible(true);

	}

	// accessors and mutators
	public PlayerFieldPanel getPlayerOneField() {
		return playerOneField;
	}

	public void setPlayerOneField(PlayerFieldPanel playerOneField) {
		this.playerOneField = playerOneField;
	}

	public PlayerFieldPanel getPlayerTwoField() {
		return playerTwoField;
	}

	public void setPlayerTwoField(PlayerFieldPanel playerTwoField) {
		this.playerTwoField = playerTwoField;
	}

	public OfferFieldPanel getOfferField() {
		return offerField;
	}

	public void setOfferField(OfferFieldPanel offerField) {
		this.offerField = offerField;
	}

}
