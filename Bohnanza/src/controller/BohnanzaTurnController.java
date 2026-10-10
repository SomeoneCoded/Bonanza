package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import model.CardModel;
import model.DeckModel;
import model.OfferModel;
import model.PlayerModel;
import view.CardButton;
import view.OfferFieldPanel;
import view.PlayerFieldPanel;

// this class controls the player's turn
public class BohnanzaTurnController implements ActionListener, ItemListener, DecisionPhases {

	private int turnNumber = 1;
	private int discardAmount = 0, plantAmount = 0, drawAmount = 0, drawOfferAmount = 0;
	private String difficulty;
	private BohnanzaCardController cardControl;
	private BohnanzaScoreController scoreControl;

	public BohnanzaTurnController(BohnanzaCardController cardControl, BohnanzaScoreController scoreControl) {
		this.cardControl = cardControl;
		this.scoreControl = scoreControl;
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public void offerPhase(OfferFieldPanel offerField, DeckModel discardPile, DeckModel drawPile) {

	}

	@Override
	public void plantingPhase(PlayerModel playerCurrent, PlayerFieldPanel currentPlayerField, DeckModel discardPile) {

	}

	@Override
	public void drawPhase(PlayerModel player, PlayerFieldPanel playerField, DeckModel drawPile) {
		for (int i = 0; i < 2; i++) {
			cardControl.drawPlayerCard(player, playerField, drawPile);
		}

	}

	@Override
	public void switchAccessibility(PlayerFieldPanel currentPlayerField, PlayerFieldPanel opposingPlayerField) {
		// Unlock the correct player's ability to interact with their own cards
		// disable the opposing player's ability to interact with their card

		// Lock opposing
		System.out.println("Switching");
		for (Component c : opposingPlayerField.getComponents()) {
			c.setEnabled(false);
		}

		for (Component c : opposingPlayerField.getPlayerHandPanel().getComponents()) {
			c.setEnabled(false);
		}

		opposingPlayerField.repaint();
		opposingPlayerField.revalidate();

		// Unlock current player
		for (Component c : currentPlayerField.getComponents()) {
			c.setEnabled(true);
		}

		for (Component c : currentPlayerField.getPlayerHandPanel().getComponents()) {
			c.setEnabled(true);
		}

		currentPlayerField.repaint();
		currentPlayerField.revalidate();

	}

	public int getDiscardAmount() {
		return discardAmount;
	}

	public void setDiscardAmount(int discardAmount) {
		this.discardAmount = discardAmount;
	}

	public int getPlantAmount() {
		return plantAmount;
	}

	public void setPlantAmount(int plantAmount) {
		this.plantAmount = plantAmount;
	}

	public int getDrawAmount() {
		return drawAmount;
	}

	public void setDrawAmount(int drawAmount) {
		this.drawAmount = drawAmount;
	}

	public int getDrawOfferAmount() {
		return drawOfferAmount;
	}

	public void setDrawOfferAmount(int drawOfferAmount) {
		this.drawOfferAmount = drawOfferAmount;
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

	}

}
