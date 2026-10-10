package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.ImageIcon;

import model.CardModel;
import model.DeckModel;
import model.OfferModel;
import model.PlayerModel;
import view.CardButton;
import view.OfferFieldPanel;
import view.PlayerFieldPanel;

// this class controls the deck
public class BohnanzaCardController {

	private BohnanzaGameController gameControl;

	// constructor method
	public BohnanzaCardController(BohnanzaGameController gameControl) {
		this.gameControl = gameControl;
	}

	// UTILITY METHODS ---------------------------------
	// input bean card data into card model class
	public ArrayList<CardModel> createCards(ArrayList<CardModel> cards) {
		try {
			File cardFile = new File("data/Cards.txt");
			Scanner fileReader = new Scanner(cardFile).useDelimiter(",|\r\n");
			while (fileReader.hasNextLine()) {
				String name = fileReader.next();
				int cardNumber = fileReader.nextInt();
				int[] coinOutput = new int[4];
				for (int i = 0; i < coinOutput.length; i++) {
					coinOutput[i] = fileReader.nextInt();
				}
				cards.add(new CardModel(name, cardNumber, coinOutput));

			}

			fileReader.close();
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}

		return cards;
	}

	// creates a draw pile
	public DeckModel fillDrawPile(ArrayList<CardModel> cardData, DeckModel drawPile) {
		System.out.println("Drawing pile");
		for (CardModel card : cardData) {
			int cardNumber = card.getCardNumber();
			for (int i = 0; i < cardNumber; i++) {
				drawPile.getDeck().add(card);
			}
		}

		shufflePile(drawPile);
		for (CardModel card : drawPile.getDeck())
			System.out.println(card.toString());

		return drawPile;

	}

	// shuffle a pile
	public void shufflePile(DeckModel pile) {
		Collections.shuffle(pile.getDeck());
	}

	// dealing out cards to players
	public void dealCards(PlayerModel player, PlayerFieldPanel playerField, DeckModel drawPile,
			OfferFieldPanel offerField, int cardAmount) {
		System.out.println("Dealing cards");
		// deal cards to player 1
		for (int i = 0; i < cardAmount; i++) {
			drawPlayerCard(player, playerField, drawPile);
		}

		playerField.getPlayerHandPane().repaint();
		playerField.getPlayerHandPane().revalidate();

	}

	// drawing card method to player hand
	public void drawPlayerCard(PlayerModel player, PlayerFieldPanel playerField, DeckModel drawPile) {
		System.out.println("Adding cards to player field");
		if (!drawPile.getDeck().isEmpty()) {
			CardModel card = drawPile.getDeck().pop();
			CardButton cardButton = new CardButton(card.getName());
			// add action listener
			cardButton.addActionListener(gameControl);
			// take a card out of the draw pile and put it into the player's hand
			player.getPlayerHand().add(card);
			playerField.getPlayerHand().add(cardButton);
			refreshPlayerHand(playerField, playerField.getPlayerHand());

		}

	}

	// discard player card method
	public void discardPlayerCard(PlayerModel player, DeckModel discardPile) {
		if (!player.getPlayerHand().isEmpty()) {
			discardPile.getDeck().push(player.getPlayerHand().poll());
		}

	}

	// discard offer card methods
	public void discardOfferCard(OfferModel offerModel, DeckModel discardPile, OfferFieldPanel offerField) {
		for (ArrayList<CardModel> cardPile : offerModel.getOfferFields()) {
			for (CardModel card : cardPile) {
				discardPile.getDeck().push(card);
				cardPile.remove(card);
			}
		}

		for (int field = 0; field < 3; field++) {
			// update look of each field
		}
	}

	// planting cards from playerHand
	public void plantCardfromPlayerHand(PlayerModel player, PlayerFieldPanel playerField, int fieldNumber) {
		CardModel card = player.getPlayerHand().poll();
		player.getFields()[fieldNumber - 1].add(card);
		playerField.getPlayerHand().poll();

		// updating field background to show the planted card
		playerField.getFieldsBackground()[fieldNumber - 1].setIcon(new ImageIcon("images/" + card.getName() + ".png"));
		playerField.getFieldsBackground()[fieldNumber - 1].setOpaque(true);
		playerField.getFields()[fieldNumber - 1].repaint();
		playerField.getFields()[fieldNumber - 1].revalidate();

		playerField.getFieldQuantities()[fieldNumber - 1]
				.setText(Integer.toString(player.getFields()[fieldNumber - 1].size()));
		playerField.getFieldQuantities()[fieldNumber - 1].repaint();

		// refresh the player hand
		refreshPlayerHand(playerField, playerField.getPlayerHand());

	}

	// planting card from offerHand
	public void plantCardfromOfferHand(PlayerModel player, OfferModel offerModel, PlayerFieldPanel playerField,
			OfferFieldPanel offerField, int offerPileNumber, int fieldNumber) {

		String name = offerModel.getOfferFields()[offerPileNumber - 1].get(0).getName(); // name of card

		for (CardModel card : offerModel.getOfferFields()[offerPileNumber - 1]) {
			player.getFields()[fieldNumber - 1].add(card);

		}

		offerModel.getOfferFields()[offerPileNumber - 1].clear();

		// updating field background to show the planted card
		offerField.getOfferPileBackground()[offerPileNumber - 1].setIcon(null);
		offerField.getOfferPileBackground()[offerPileNumber - 1].repaint();
		offerField.getOfferPileBackground()[offerPileNumber - 1].revalidate();

		offerField.getOfferPilesQuantities()[offerPileNumber - 1].setText("0");
		offerField.getOfferPilesQuantities()[offerPileNumber - 1].repaint();

		playerField.getFieldsBackground()[fieldNumber - 1].setIcon(new ImageIcon("images/" + name + ".png"));
		playerField.getFieldsBackground()[fieldNumber - 1].setOpaque(true);
		playerField.getFields()[fieldNumber - 1].repaint();
		playerField.getFields()[fieldNumber - 1].revalidate();

		playerField.getFieldQuantities()[fieldNumber - 1]
				.setText(Integer.toString(player.getFields()[fieldNumber - 1].size()));
		playerField.getFieldQuantities()[fieldNumber - 1].repaint();

	}

	// method to refresh player hand panel
	public void refreshPlayerHand(PlayerFieldPanel playerField, Queue<CardButton> playerHand) {
		playerField.getPlayerHandPanel().removeAll();
		for (CardButton card : playerHand) {
			playerField.getPlayerHandPanel().add(card);
		}

		playerField.getPlayerHandPanel().revalidate();
		playerField.getPlayerHandPanel().repaint();
	}

	// method to harvest field
	public void harvestField(PlayerModel player, DeckModel coinPile, DeckModel discardPile,
			PlayerFieldPanel playerField, int fieldNumber) {
		// coin counting
		int cardAmount = player.getFields()[fieldNumber - 1].size();
		int coinAmount = 0;
		for (int i = 0; i < 4; i++) {
			if (cardAmount > player.getFields()[fieldNumber - 1].get(0).getCoinOutput()[i]) {
				coinAmount = i + 1;
			} else
				break;
		}

		// card distribution
		for (int i = 0; i < cardAmount - 1; i++) {
			coinPile.getDeck().add(player.getFields()[fieldNumber - 1].get(i));
			player.getFields()[fieldNumber - 1].remove(i);
		}

		playerField.getCoinQuantity().setText(Integer.toString(coinAmount));
		playerField.getCoinQuantity().repaint();

		for (int i = 0; i < player.getFields()[fieldNumber - 1].size(); i++) {
			discardPile.getDeck().add(player.getFields()[fieldNumber - 1].get(i));
			player.getFields()[fieldNumber - 1].remove(i);
		}

		// updating field background to show the planted card
		playerField.getFieldsBackground()[fieldNumber - 1].setIcon(null);
		playerField.getFields()[fieldNumber - 1].repaint();
		playerField.getFields()[fieldNumber - 1].revalidate();

		playerField.getFieldQuantities()[fieldNumber - 1].setText("0");
		playerField.getFieldQuantities()[fieldNumber - 1].repaint();
	}

	// dealing out three cards
	public void addToOfferField(OfferModel offerModel, DeckModel drawPile, DeckModel discardPile,
			OfferFieldPanel offerField) {
		System.out.println("Adding cards to offer field");
		// draw from the draw pile 3 times
		for (int i = 0; i < 3; i++) {
			if (!drawPile.getDeck().isEmpty()) {
				CardModel card = drawPile.getDeck().pop();
				offerModel.getOfferFields()[i].add(card);
				offerField.getOfferPileBackground()[i].setIcon(new ImageIcon("images/" + card.getName() + ".png"));
				offerField.getOfferPileBackground()[i].repaint();
				offerField.getOfferPileBackground()[i].revalidate();
				offerField.getOfferPilesQuantities()[i]
						.setText(Integer.toString(offerModel.getOfferFields()[i].size()));
				offerField.getOfferPilesQuantities()[i].repaint();
			} else
				continue;
		}

		// check discard pile
		boolean isSame = true;
		while (isSame) {
			isSame = false;
			for (int i = 0; i < 3; i++) {
				if(discardPile.getDeck().isEmpty()) break;
				else if (offerModel.getOfferFields()[i].contains(discardPile.getDeck().peek())) {
					isSame = true;
					offerModel.getOfferFields()[i].add(discardPile.getDeck().pop());
					offerField.getOfferPilesQuantities()[i]
							.setText(Integer.toString(offerModel.getOfferFields()[i].size()));
					offerField.getOfferPilesQuantities()[i].repaint();
					break;
				}
			}
		}

	}

}
