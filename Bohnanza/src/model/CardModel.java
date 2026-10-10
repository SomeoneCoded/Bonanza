package model;

import java.util.Arrays;

public class CardModel {
	private String name;
	private int cardNumber;
	private int[] coinOutput;

	// bean cards
	public CardModel() {

	}

	public CardModel(String name, int cardNumber, int[] coinOutput) {
		super();
		this.name = name;
		this.cardNumber = cardNumber;
		this.coinOutput = coinOutput;
	}

	// coin card
	public CardModel(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int[] getCoinOutput() {
		return coinOutput;
	}

	public void setCoinOutput(int[] coinOutput) {
		this.coinOutput = coinOutput;
	}

	@Override
	public String toString() {
		return "CardModel [name=" + name + ", cardNumber=" + cardNumber + ", coinOutput=" + Arrays.toString(coinOutput)
				+ "]";
	}

}
