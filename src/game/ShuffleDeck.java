package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.Random;

public class ShuffleDeck {

    private Random random;

    // shuffle the deck with seed (if seed exists)
    public ShuffleDeck(Random random) {
        this.random = random;
    }

    public void toRand(Hand deck, int i){
        Card temp = deck.get(i);
        deck.remove(i,false);
        deck.insert(temp,false);
    }

    public Hand shuffle(Hand deck) {
        int length = deck.getNumberOfCards();
        for ( int i = length; i > 0; i-- ){
            int randInd = random.nextInt(i);
            toRand(deck, randInd);
        }
        return deck;
    }
}
