package model;

import java.util.ArrayList;
import java.util.Arrays;

public class OfferModel {
	private ArrayList<CardModel>[] offerFields = new ArrayList[3];

	public OfferModel() {
		for(int i = 0; i < 3; i++) {
			offerFields[i] = new ArrayList<CardModel>();
		}
	}

	public ArrayList<CardModel>[] getOfferFields() {
		return offerFields;
	}

	public void setOfferFields(ArrayList<CardModel>[] offerFields) {
		this.offerFields = offerFields;
	}

	@Override
	public String toString() {
		return "OfferModel [offerFields=" + Arrays.toString(offerFields) + "]";
	}

}
