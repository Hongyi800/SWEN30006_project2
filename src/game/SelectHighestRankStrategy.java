package game;

import ch.aplu.jcardgame.*;

public class SelectHighestRankStrategy implements ISelectStrategy{
    @Override
    public Card makeSelectStrategy(Hand handAfterFilter) {
        // return Card with highest rank from Hand
        int highest = 0;
        for(int i=0;i<handAfterFilter.getNumberOfCards();i++){
            if(rankGreater(handAfterFilter.get(i),handAfterFilter.get(highest))){
                highest = i;
            }
        }
        return handAfterFilter.get(highest);
    }

    @Override
    public void getWinCardAndTrump(Card currentWinCard, Whist.Suit trump){

    }

    public boolean rankGreater(Card card1, Card card2) {
        return card1.getRankId() < card2.getRankId(); // Warning: Reverse rank order of cards (see comment on enum)
    }
}
