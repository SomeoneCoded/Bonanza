package model;

import java.util.Stack;

// Class that represents the model of the deck pile (draw pile, discard pile)
public class DeckModel {
	private Stack<CardModel> deck = new Stack<CardModel>();

	public DeckModel() {
		super();
	}

	public Stack<CardModel> getDeck() {
		return deck;
	}

	public void setDeck(Stack<CardModel> deck) {
		this.deck = deck;
	}

}
