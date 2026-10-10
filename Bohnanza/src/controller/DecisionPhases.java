package controller;

import model.*;
import view.OfferFieldPanel;
import view.PlayerFieldPanel;

public interface DecisionPhases {
	public void offerPhase(OfferFieldPanel offerField, DeckModel discardPile, DeckModel drawPile);

	public void plantingPhase(PlayerModel playerCurrent, PlayerFieldPanel currentPlayerField, DeckModel discardPile);

	public void drawPhase(PlayerModel player, PlayerFieldPanel playerField, DeckModel drawPile);

	public void switchAccessibility(PlayerFieldPanel currentPlayerField, PlayerFieldPanel opposingPlayerField);

}
