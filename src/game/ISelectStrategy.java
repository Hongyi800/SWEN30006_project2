package game;

import ch.aplu.jcardgame.*;

public interface ISelectStrategy {
    Card makeSelectStrategy(Hand filteredHand, Hand originHand);

    void getWinCardAndTrump(Card currentWinCard, Whist.Suit trump);
}
