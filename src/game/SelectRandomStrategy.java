package game;

import ch.aplu.jcardgame.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SelectRandomStrategy implements ISelectStrategy {

    static final Random random = ThreadLocalRandom.current();
    /*
    if(seedProp == null) {
        // and no property
        // so randomise
        this.random = new Random();
    } else { // Use property seed
        int seed = Integer.parseInt(seedProp);
        this.random = new Random(seed);
    }

     */
    @Override
    public Card makeSelectStrategy(Hand hand) {
        // return random Card from Hand
        int x = random.nextInt(hand.getNumberOfCards());
        return hand.get(x);
    }

    @Override
    public void getWinCardAndTrump(Card currentWinCard, Whist.Suit trump) {

    }
}
