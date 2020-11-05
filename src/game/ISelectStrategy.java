package game;

import ch.aplu.jcardgame.*;

public interface ISelectStrategy {
    Card makeSelectStrategy(Hand filteredHand, Hand originHand, Card currentWinCard, Whist.Suit trump, String seedProp);
}
