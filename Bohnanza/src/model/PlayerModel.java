package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import view.CardButton;

public class PlayerModel {
	private int coinAmount;
	private Queue<CardModel> playerHand = new LinkedList<>();
	private ArrayList<CardModel>[] fields = new ArrayList[3]; 
	private ArrayList<CardButton>[] visualFields = new ArrayList[3];
	private boolean isThirdFieldUnlocked = false;

	public PlayerModel() {
		for(int field = 0; field < 3; field++) {
			fields[field] = new ArrayList<CardModel>();
		}
	}

	public int getCoinAmount() {
		return coinAmount;
	}

	public void setCoinAmount(int coinAmount) {
		this.coinAmount = coinAmount;
	}

	public Queue<CardModel> getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(Queue<CardModel> playerHand) {
		this.playerHand = playerHand;
	}

	public ArrayList<CardModel>[] getFields() {
		return fields;
	}

	public void setFields(ArrayList<CardModel>[] fields) {
		this.fields = fields;
	}

	public ArrayList<CardButton>[] getVisualFields() {
		return visualFields;
	}

	public void setVisualFields(ArrayList<CardButton>[] visualFields) {
		this.visualFields = visualFields;
	}

	public boolean isThirdFieldUnlocked() {
		return isThirdFieldUnlocked;
	}

	public void setThirdFieldUnlocked(boolean isThirdFieldUnlocked) {
		this.isThirdFieldUnlocked = isThirdFieldUnlocked;
	}

}
