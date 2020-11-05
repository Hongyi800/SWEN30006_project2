package game;

import ch.aplu.jcardgame.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SelectRandomStrategy implements ISelectStrategy {

    private Random random;

    @Override
    public Card makeSelectStrategy(Hand hand, Hand originHand, Card currentWinCard, Whist.Suit trump, String seedProp) {
        // return random Card from Hand
        if(seedProp == null) {
            // and no property
            // so randomise
            this.random = new Random();
        } else { // Use property seed
            int seed = Integer.parseInt(seedProp);
            this.random = new Random(seed);
        }

        int x = this.random.nextInt(originHand.getNumberOfCards());
        return originHand.get(x);
    }

}
