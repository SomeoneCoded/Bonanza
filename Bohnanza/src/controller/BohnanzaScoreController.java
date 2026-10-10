package controller;

import model.PlayerModel;

// this class controls the score
public class BohnanzaScoreController {

	public BohnanzaScoreController() {

	}

	// UTILITY METHODS --------------------
	// add score method
	public void addScore(PlayerModel player, int scoreAmount) {
		player.setCoinAmount(player.getCoinAmount() + scoreAmount);
	}

	// remove score method
	public void removeScore(PlayerModel player, int scoreAmount) {
		if (player.getCoinAmount() - scoreAmount > 0) {
			player.setCoinAmount(player.getCoinAmount() - scoreAmount);
		}
	}

	// check for winners
	public void checkForWinner(PlayerModel player1, PlayerModel player2) {
		if (player1.getCoinAmount() > player2.getCoinAmount())
			System.out.println("Player 1 won!");
		else if (player1.getCoinAmount() < player2.getCoinAmount())
			System.out.println("Player 2 won!");
		else
			System.out.println("TIE");
		return;
	}
}
