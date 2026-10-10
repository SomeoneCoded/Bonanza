package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.*;
import view.BohnanzaGameFrame;
import view.CardButton;
import view.OfferFieldPanel;
import view.PlayerFieldPanel;

// this class is the general game controller
public class BohnanzaGameController implements ActionListener, ItemListener {
	// sub-controllers
	private BohnanzaTurnController playerTurnControl;
	private BohnanzaCardController cardControl;
	private BohnanzaScoreController scoreControl;

	// game frame
	private BohnanzaGameFrame gameFrame = new BohnanzaGameFrame();

	// models
	private ArrayList<CardModel> cards = new ArrayList<CardModel>();
	private DeckModel drawPile = new DeckModel();
	private DeckModel discardPile = new DeckModel();
	private DeckModel coinPilePlayerOne = new DeckModel();
	private DeckModel coinPilePlayerTwo = new DeckModel();

	// player model
	private PlayerModel player1 = new PlayerModel();
	private PlayerModel player2 = new PlayerModel();
	private OfferModel offers = new OfferModel();

	public BohnanzaGameController() {
		// Create Controllers
		cardControl = new BohnanzaCardController(this);
		scoreControl = new BohnanzaScoreController();
		playerTurnControl = new BohnanzaTurnController(cardControl, scoreControl);

		// Initial Setup of the main menu
		gameFrame.getMainMenu().getPvPButton().addActionListener(this);
		gameFrame.getMainMenu().getPvESelection().addItemListener(this);

		// Initial Setup of the Game Field -------------------------------
		// Add action listeners
		gameFrame.getGameField().getOfferField().getDrawButton().addActionListener(this);
		gameFrame.getGameField().getOfferField().getTurnButton().addActionListener(this);
		gameFrame.getGameField().getOfferField().getDealOfferButton().addActionListener(this);

		gameFrame.getGameField().getPlayerOneField().getPlantButton().addActionListener(this);
		gameFrame.getGameField().getPlayerTwoField().getPlantButton().addActionListener(this);

		for (JButton playerField : gameFrame.getGameField().getPlayerOneField().getFields()) {
			playerField.addActionListener(this);
		}

		for (JButton playerField : gameFrame.getGameField().getPlayerTwoField().getFields()) {
			playerField.addActionListener(this);
		}

		for (JButton offerField : gameFrame.getGameField().getOfferField().getOfferPiles()) {
			offerField.addActionListener(this);
		}

		// Create the cards
		cards = cardControl.createCards(cards);
		// Create a draw pile
		drawPile = cardControl.fillDrawPile(cards, drawPile);
		// Distribute the cards to each player
		cardControl.dealCards(player1, gameFrame.getGameField().getPlayerOneField(), drawPile,
				gameFrame.getGameField().getOfferField(), 5);
		cardControl.dealCards(player2, gameFrame.getGameField().getPlayerTwoField(), drawPile,
				gameFrame.getGameField().getOfferField(), 5);

		// start the turns
		// player 1 will have access to their field
		playerTurnControl.switchAccessibility(gameFrame.getGameField().getPlayerOneField(),
				gameFrame.getGameField().getPlayerTwoField());

	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		playerTurnControl.setDifficulty(event.getSource().toString());
		gameFrame.getCardLayout().show(gameFrame.getCardLayoutPanel(), "1");

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (playerTurnControl.getTurnNumber() % 2 == 1) { // Player one's turn

			// If player decides to draw a card
			if (event.getSource() == gameFrame.getGameField().getOfferField().getDrawButton()) {
				if (playerTurnControl.getDrawAmount() >= 2) {
					JOptionPane.showMessageDialog(null, "You cannot draw anymore cards");
				} else {
					System.out.println("Adding card to player 1");
					cardControl.drawPlayerCard(player1, gameFrame.getGameField().getPlayerOneField(), drawPile);
					playerTurnControl.setDrawAmount(playerTurnControl.getDrawAmount() + 1);
				}
			}

			if (event.getSource() == gameFrame.getGameField().getPlayerOneField().getPlantButton()) {
				if (playerTurnControl.getPlantAmount() >= 2) {
					JOptionPane.showMessageDialog(gameFrame, "You cannot plant anymore beans.");
				} else {
					Object[] possibleFields = { "Field 1", "Field 2", "Field 3" };

					Object selectedField = JOptionPane.showInputDialog(null,
							"Which field do you want to plant the card?", "GAME", JOptionPane.INFORMATION_MESSAGE, null,
							possibleFields, possibleFields[0]);
					if (selectedField == "Field 1") {
						if (!player1.getFields()[0].isEmpty()) {
							if (!player1.getFields()[0].contains(player1.getPlayerHand().peek())) {

								JOptionPane.showMessageDialog(gameFrame,
										"Please harvest the field or choose another field");
							}
						} else {
							cardControl.plantCardfromPlayerHand(player1, gameFrame.getGameField().getPlayerOneField(),
									1);
							playerTurnControl.setPlantAmount(playerTurnControl.getPlantAmount() + 1);
						}

					}
					if (selectedField == "Field 2") {
						if (!player1.getFields()[1].isEmpty()) {
							if (!player1.getFields()[1].contains(player1.getPlayerHand().peek())) {

								JOptionPane.showMessageDialog(gameFrame,
										"Please harvest the field or choose another field");
							}
						} else {
							cardControl.plantCardfromPlayerHand(player1, gameFrame.getGameField().getPlayerOneField(),
									2);
							playerTurnControl.setPlantAmount(playerTurnControl.getPlantAmount() + 1);
						}

					}
					if (selectedField == "Field 3") {
						if (!player1.getFields()[2].isEmpty()) {
							if (!player1.getFields()[2].contains(player1.getPlayerHand().peek())) {

								JOptionPane.showMessageDialog(gameFrame,
										"Please harvest the field or choose another field");
							}
						} else {
							cardControl.plantCardfromPlayerHand(player1, gameFrame.getGameField().getPlayerOneField(),
									3);
							playerTurnControl.setPlantAmount(playerTurnControl.getPlantAmount() + 1);
						}

					}

				}

			}

			// if the player decides to harvest field
			if (event.getSource() == gameFrame.getGameField().getPlayerOneField().getFields()[0]) {
				int choice = JOptionPane.showOptionDialog(gameFrame, "Are you sure you want to harvest this field?",
						"Game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
						JOptionPane.NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					cardControl.harvestField(player1, coinPilePlayerOne, discardPile,
							gameFrame.getGameField().getPlayerOneField(), 1);
				}

			} else if (event.getSource() == gameFrame.getGameField().getPlayerOneField().getFields()[1]) {
				int choice = JOptionPane.showOptionDialog(gameFrame, "Are you sure you want to harvest this field?",
						"Game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
						JOptionPane.NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					cardControl.harvestField(player1, coinPilePlayerOne, discardPile,
							gameFrame.getGameField().getPlayerOneField(), 2);
				}
			} else if (event.getSource() == gameFrame.getGameField().getPlayerOneField().getFields()[2]) {
				int choice = JOptionPane.showOptionDialog(gameFrame, "Are you sure you want to harvest this field?",
						"Game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
						JOptionPane.NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					cardControl.harvestField(player1, coinPilePlayerOne, discardPile,
							gameFrame.getGameField().getPlayerOneField(), 3);
				}
			}

			// card button action listener
			if (event.getSource() instanceof CardButton) {
				promptCardButtons(player1, gameFrame.getGameField().getPlayerOneField());

			}

			if (event.getSource() == gameFrame.getGameField().getOfferField().getOfferPiles()[0]) {
				if (offers.getOfferFields()[0].size() == 0) {
					JOptionPane.showMessageDialog(gameFrame, "There is nothing to take here.");
				} else {
					int choice = JOptionPane.showOptionDialog(gameFrame, "Do you want to plant the beans?", "Game",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						promptOfferPlant(player1, gameFrame.getGameField().getPlayerOneField(), 1);
					}
				}
			} else if (event.getSource() == gameFrame.getGameField().getOfferField().getOfferPiles()[1]) {
				if (offers.getOfferFields()[1].size() == 0) {
					JOptionPane.showMessageDialog(gameFrame, "There is nothing to take here.");
				} else {
					int choice = JOptionPane.showOptionDialog(gameFrame, "Do you want to plant the beans?", "Game",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						promptOfferPlant(player1, gameFrame.getGameField().getPlayerOneField(), 2);
					}
				}
			} else if (event.getSource() == gameFrame.getGameField().getOfferField().getOfferPiles()[2]) {
				if (offers.getOfferFields()[2].size() == 0) {
					JOptionPane.showMessageDialog(gameFrame, "There is nothing to take here.");
				} else {
					int choice = JOptionPane.showOptionDialog(gameFrame, "Do you want to plant the beans?", "Game",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						promptOfferPlant(player1, gameFrame.getGameField().getPlayerOneField(), 3);
					}
				}
			}

			// if a player decides to display offers
			if (event.getSource() == gameFrame.getGameField().getOfferField().getDealOfferButton()) {
				if (playerTurnControl.getDrawOfferAmount() >= 1) {
					JOptionPane.showMessageDialog(gameFrame, "Already dealt the cards.");
				} else {
					cardControl.addToOfferField(offers, drawPile, discardPile,
							gameFrame.getGameField().getOfferField());
					playerTurnControl.setDrawOfferAmount(playerTurnControl.getDrawOfferAmount() + 1);
				}

			}

		} else { // Player two's turn -------------------------------------------

			// If player decides to draw a card
			if (event.getSource() == gameFrame.getGameField().getOfferField().getDrawButton()) {
				if (playerTurnControl.getDrawAmount() >= 2) {
					JOptionPane.showMessageDialog(null, "You cannot draw anymore cards");
				} else {
					System.out.println("Adding card to player 2");
					cardControl.drawPlayerCard(player2, gameFrame.getGameField().getPlayerTwoField(), drawPile);
					playerTurnControl.setDrawAmount(playerTurnControl.getDrawAmount() + 1);
				}

			}

			else if (event.getSource() == gameFrame.getGameField().getPlayerTwoField().getPlantButton()) {
				if (playerTurnControl.getPlantAmount() >= 2) {
					JOptionPane.showMessageDialog(gameFrame, "You cannot plant anymore beans.");
				} else {
					Object[] possibleFields = { "Field 1", "Field 2", "Field 3" };

					Object selectedField = JOptionPane.showInputDialog(null,
							"Which field do you want to plant the card?", "GAME", JOptionPane.INFORMATION_MESSAGE, null,
							possibleFields, possibleFields[0]);
					if (selectedField == "Field 1") {
						if (!player2.getFields()[0].isEmpty()) {
							if (!player2.getFields()[0].contains(player2.getPlayerHand().peek())) {

								JOptionPane.showMessageDialog(gameFrame,
										"Please harvest the field or choose another field");
							}
						} else {
							cardControl.plantCardfromPlayerHand(player2, gameFrame.getGameField().getPlayerTwoField(),
									1);
							playerTurnControl.setPlantAmount(playerTurnControl.getPlantAmount() + 1);
						}

					}
					if (selectedField == "Field 2") {
						if (!player2.getFields()[1].isEmpty()) {
							if (!player2.getFields()[1].contains(player2.getPlayerHand().peek())) {

								JOptionPane.showMessageDialog(gameFrame,
										"Please harvest the field or choose another field");
							}
						} else {
							cardControl.plantCardfromPlayerHand(player2, gameFrame.getGameField().getPlayerTwoField(),
									2);
							playerTurnControl.setPlantAmount(playerTurnControl.getPlantAmount() + 1);
						}

					}
					if (selectedField == "Field 3") {
						if (!player2.getFields()[2].isEmpty()) {
							if (!player2.getFields()[2].contains(player2.getPlayerHand().peek())) {

								JOptionPane.showMessageDialog(gameFrame,
										"Please harvest the field or choose another field");
							}
						} else {
							cardControl.plantCardfromPlayerHand(player2, gameFrame.getGameField().getPlayerTwoField(),
									3);
							playerTurnControl.setPlantAmount(playerTurnControl.getPlantAmount() + 1);
						}

					}

				}

			}

			// if the player decides to harvest field
			else if (event.getSource() == gameFrame.getGameField().getPlayerTwoField().getFields()[0]) {
				int choice = JOptionPane.showOptionDialog(gameFrame, "Are you sure you want to harvest this field?",
						"Game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
						JOptionPane.NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					cardControl.harvestField(player2, coinPilePlayerTwo, discardPile,
							gameFrame.getGameField().getPlayerTwoField(), 1);
				}
			} else if (event.getSource() == gameFrame.getGameField().getPlayerTwoField().getFields()[1]) {
				int choice = JOptionPane.showOptionDialog(gameFrame, "Are you sure you want to harvest this field?",
						"Game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
						JOptionPane.NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					cardControl.harvestField(player2, coinPilePlayerTwo, discardPile,
							gameFrame.getGameField().getPlayerTwoField(), 2);
				}
			} else if (event.getSource() == gameFrame.getGameField().getPlayerTwoField().getFields()[2]) {
				int choice = JOptionPane.showOptionDialog(gameFrame, "Are you sure you want to harvest this field?",
						"Game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
						JOptionPane.NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					cardControl.harvestField(player2, coinPilePlayerTwo, discardPile,
							gameFrame.getGameField().getPlayerTwoField(), 3);
				}
			}

			// card button action listener
			else if (event.getSource() instanceof CardButton) {
				promptCardButtons(player2, gameFrame.getGameField().getPlayerTwoField());
			}

			// if the player tries to get something from the offer field
			else if (event.getSource() == gameFrame.getGameField().getOfferField().getOfferPiles()[0]) {
				if (offers.getOfferFields()[0].size() == 0) {
					JOptionPane.showMessageDialog(gameFrame, "There is nothing to take here.");
				} else {
					int choice = JOptionPane.showOptionDialog(gameFrame, "Do you want to plant the beans?", "Game",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						promptOfferPlant(player2, gameFrame.getGameField().getPlayerTwoField(), 1);
					}
				}
			} else if (event.getSource() == gameFrame.getGameField().getOfferField().getOfferPiles()[1]) {
				if (offers.getOfferFields()[1].size() == 0) {
					JOptionPane.showMessageDialog(gameFrame, "There is nothing to take here.");
				} else {
					int choice = JOptionPane.showOptionDialog(gameFrame, "Do you want to plant the beans?", "Game",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						promptOfferPlant(player2, gameFrame.getGameField().getPlayerTwoField(), 2);
					}
				}
			} else if (event.getSource() == gameFrame.getGameField().getOfferField().getOfferPiles()[2]) {
				if (offers.getOfferFields()[2].size() == 0) {
					JOptionPane.showMessageDialog(gameFrame, "There is nothing to take here.");
				} else {
					int choice = JOptionPane.showOptionDialog(gameFrame, "Do you want to plant the beans?", "Game",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						promptOfferPlant(player2, gameFrame.getGameField().getPlayerTwoField(), 3);
					}
				}
			}

			// if a player decides to display offers
			else if (event.getSource() == gameFrame.getGameField().getOfferField().getDealOfferButton()) {
				if (playerTurnControl.getDrawOfferAmount() >= 1) {
					JOptionPane.showMessageDialog(gameFrame, "Already dealt the cards.");
				} else {
					cardControl.addToOfferField(offers, drawPile, discardPile,
							gameFrame.getGameField().getOfferField());
					playerTurnControl.setDrawOfferAmount(playerTurnControl.getDrawOfferAmount() + 1);
				}

			}

		}

		// player main menu button
		if(event.getSource() == gameFrame.getMainMenu().getPvPButton()) {
			gameFrame.getCardLayout().show(gameFrame.getCardLayoutPanel(), "1");
		}
		
		// next turn button
		else if (event.getSource() == gameFrame.getGameField().getOfferField().getTurnButton()) {
			if (playerTurnControl.getPlantAmount() < 1) {
				JOptionPane.showMessageDialog(gameFrame, "You must at least plant one card from your player hand.");
			} else if (playerTurnControl.getDrawOfferAmount() < 1) {
				JOptionPane.showMessageDialog(gameFrame, "You must draw the offer cards at least once.");
			} else {
				playerTurnControl.setDiscardAmount(0);
				playerTurnControl.setPlantAmount(0);
				playerTurnControl.setDrawAmount(0);
				playerTurnControl.setDrawOfferAmount(0);
				playerTurnControl.setTurnNumber(playerTurnControl.getTurnNumber() + 1);
				if (playerTurnControl.getTurnNumber() % 2 == 1) {
					playerTurnControl.switchAccessibility(gameFrame.getGameField().getPlayerOneField(),
							gameFrame.getGameField().getPlayerTwoField());
				} else {
					playerTurnControl.switchAccessibility(gameFrame.getGameField().getPlayerTwoField(),
							gameFrame.getGameField().getPlayerOneField());
				}
			}

		}

	}

	// method to ask user if they want to take from the offer pile
	public void promptOfferPlant(PlayerModel player, PlayerFieldPanel gameField, int offerFieldNumber) {
		System.out.println("Prompt offer plant: " + offerFieldNumber);
		Object[] possibleFields = { "Field 1", "Field 2", "Field 3" };

		Object selectedField = JOptionPane.showInputDialog(null, "Which field do you want to plant the card?", "GAME",
				JOptionPane.INFORMATION_MESSAGE, null, possibleFields, possibleFields[0]);
		if (selectedField == "Field 1") {
			if (!player.getFields()[offerFieldNumber - 1].isEmpty()) {
				if (!player.getFields()[0].contains(offers.getOfferFields()[offerFieldNumber - 1].get(0))) {
					JOptionPane.showMessageDialog(gameField, "Please harvest the field or choose another field");
				}
			} else {
				cardControl.plantCardfromOfferHand(player, offers, gameField, gameFrame.getGameField().getOfferField(),
						offerFieldNumber, 1);

			}

		}
		else if (selectedField == "Field 2") {
			if (!player.getFields()[offerFieldNumber - 1].isEmpty()) {
				if (!player.getFields()[1].contains(offers.getOfferFields()[offerFieldNumber - 1].get(0))) {
					JOptionPane.showMessageDialog(gameField, "Please harvest the field or choose another field");
				}
			} else {
				cardControl.plantCardfromOfferHand(player, offers, gameField, gameFrame.getGameField().getOfferField(),
						offerFieldNumber, 2);

			}

		}
		else if (selectedField == "Field 3") {

			if (!player.getFields()[2].contains(offers.getOfferFields()[offerFieldNumber - 1].get(0))
					&& !player.getFields()[offerFieldNumber - 1].isEmpty()) {
				JOptionPane.showMessageDialog(gameField, "Please harvest the field or choose another field");
			} else {
				cardControl.plantCardfromOfferHand(player, offers, gameField, gameFrame.getGameField().getOfferField(),
						offerFieldNumber, 3);

			}

		}

	}

	// Card button prompts
	public void promptCardButtons(PlayerModel player, PlayerFieldPanel gameField) {
		// Coded by Fred
		int choice = JOptionPane.showOptionDialog(gameFrame, "Would you like to discard this card?", "GAME",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			if (playerTurnControl.getDiscardAmount() >= 1) {
				JOptionPane.showMessageDialog(gameFrame, "You cannot discard anymore cards");
			} else {
				playerTurnControl.setDiscardAmount(playerTurnControl.getDiscardAmount() + 1);

			}
		}
	}

}
