package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;

// class for middle offer pile
public class OfferFieldPanel extends JPanel {

	// placeholders for offer piles
	private JButton[] offerPiles = { new JButton("Offer 1"), new JButton("Offer 2"), new JButton("Offer 3") };

	private JLabel[] offerPilesQuantities = { new JLabel("0", SwingConstants.CENTER),
			new JLabel("0", SwingConstants.CENTER), new JLabel("0", SwingConstants.CENTER) };

	private JLabel[] offerPileBackground = { new JLabel(), new JLabel(), new JLabel() };

	// card piles
	private JLabel discardPlaceholder = new JLabel("Discard Pile", SwingConstants.CENTER);
	private JLabel drawPlaceHolder = new JLabel("Draw Pile", SwingConstants.CENTER);

	private ArrayList<CardButton>[] cardPiles = new ArrayList[3];

	// deck piles
	private Stack<CardButton> drawPile = new Stack<CardButton>();
	private Stack<CardButton> discardPile = new Stack<CardButton>();

	// buttons
	private JButton turnButton = new JButton("Next Turn");
	private JButton drawButton = new JButton("Draw Pile");
	private JButton dealOfferButton = new JButton("Add Offer");

	// Font
	private Font font = new Font("Times New Roman", Font.BOLD, 20);

	// constructor method
	public OfferFieldPanel() {
		setSize(1920, 360);
		setOpaque(false);
		setLayout(null);

		for (JButton offerField : offerPiles) {
			offerField.setBackground(Color.ORANGE);
			offerField.setForeground(Color.YELLOW);
			offerField.setFont(font);
			offerField.setBorder(null);
			offerField.setOpaque(true);
		}

		offerPiles[0].setBounds(760, 90, 100, 152);
		offerPiles[1].setBounds(910, 90, 100, 152);
		offerPiles[2].setBounds(1060, 90, 100, 152);

		offerPiles[0].add(offerPileBackground[0]);
		offerPiles[1].add(offerPileBackground[1]);
		offerPiles[2].add(offerPileBackground[2]);

		for (JButton offerField : offerPiles) {
			add(offerField);
		}

		for (JLabel offerQuantity : offerPilesQuantities) {
			offerQuantity.setBackground(Color.CYAN);
			offerQuantity.setForeground(Color.BLACK);
			offerQuantity.setFont(font);
			offerQuantity.setOpaque(true);
		}

		offerPilesQuantities[0].setBounds(760, 242, 100, 50);
		offerPilesQuantities[1].setBounds(910, 242, 100, 50);
		offerPilesQuantities[2].setBounds(1060, 242, 100, 50);

		for (JLabel offerQuantity : offerPilesQuantities) {
			add(offerQuantity);
		}

		discardPlaceholder.setBackground(Color.RED);
		discardPlaceholder.setForeground(Color.WHITE);
		discardPlaceholder.setOpaque(true);

		drawButton.setBackground(Color.GREEN);
		drawButton.setForeground(Color.BLACK);
		drawButton.setFocusPainted(false);
		drawButton.setOpaque(true);

		drawButton.setBounds(1210, 90, 100, 152);
		discardPlaceholder.setBounds(610, 90, 100, 152);

		add(drawButton);
		add(discardPlaceholder);

		turnButton.setBounds(1710, 120, 100, 50);
		turnButton.setFocusPainted(false);
		add(turnButton);

		dealOfferButton.setBounds(1360, 120, 100, 50);
		dealOfferButton.setFocusPainted(false);
		add(dealOfferButton);

		setVisible(true);
	}

	// accessors and mutators
	public ArrayList<CardButton>[] getCardPiles() {
		return cardPiles;
	}

	public void setCardPiles(ArrayList<CardButton>[] cardPiles) {
		this.cardPiles = cardPiles;
	}

	public Stack<CardButton> getDrawPile() {
		return drawPile;
	}

	public void setDrawPile(Stack<CardButton> drawPile) {
		this.drawPile = drawPile;
	}

	public Stack<CardButton> getDiscardPile() {
		return discardPile;
	}

	public void setDiscardPile(Stack<CardButton> discardPile) {
		this.discardPile = discardPile;
	}

	public JButton getTurnButton() {
		return turnButton;
	}

	public void setTurnButton(JButton turnButton) {
		this.turnButton = turnButton;
	}

	public JLabel getDiscardPlaceholder() {
		return discardPlaceholder;
	}

	public void setDiscardPlaceholder(JLabel discardPlaceholder) {
		this.discardPlaceholder = discardPlaceholder;
	}

	public JLabel getDrawPlaceHolder() {
		return drawPlaceHolder;
	}

	public void setDrawPlaceHolder(JLabel drawPlaceHolder) {
		this.drawPlaceHolder = drawPlaceHolder;
	}

	public JButton getDrawButton() {
		return drawButton;
	}

	public void setDrawButton(JButton drawButton) {
		this.drawButton = drawButton;
	}

	public JButton[] getOfferPiles() {
		return offerPiles;
	}

	public void setOfferPiles(JButton[] offerFields) {
		this.offerPiles = offerFields;
	}

	public JLabel[] getOfferPilesQuantities() {
		return offerPilesQuantities;
	}

	public void setOfferPilesQuantities(JLabel[] offerQuantities) {
		this.offerPilesQuantities = offerQuantities;
	}

	public JLabel[] getOfferPileBackground() {
		return offerPileBackground;
	}

	public void setOfferPileBackground(JLabel[] offerPileBackground) {
		this.offerPileBackground = offerPileBackground;
	}

	public JButton getDealOfferButton() {
		return dealOfferButton;
	}

	public void setDealOfferButton(JButton dealOfferButton) {
		this.dealOfferButton = dealOfferButton;
	}

}