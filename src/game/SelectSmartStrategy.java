package game;

import ch.aplu.jcardgame.*;

public class SelectSmartStrategy implements ISelectStrategy{
    /**
     * Smart selection: The advanced NPC will record the relevant information
     * (e.g., collecting the cards that been already played)
     * and makes a reasonable selection.
     * For example, the NPC may consider the chance whether it will win or not.
     * If not, it will violate the rules by selecting card with the lowest rank.
     * For this approach, NERD Games Inc. is open to your ideas for the smart selection approach.
     * However, NERD Games Inc. does not expect a sophisticated algorithm.
     * At least, this approach should make a decision based on the collected information
     * and it should look smarter than the combination of pre-defined approaches above.
     * @param hand
     * @return
     */
    private Card winCard;
    private Whist.Suit trump;
    private int winCardPos = 0;

    @Override
    public Card makeSelectStrategy(Hand hand) {

        if(canWin(hand)){
            //follow the rule and play the card
            return hand.get(winCardPos);
        }else{
            //break the rule
            int min = 0;
            for(int i=0;i<hand.getNumberOfCards();i++){
                if(rankGreater(hand.get(i),hand.get(min))
                        && !hand.get(i).getSuit().equals(winCard.getSuit())){
                    min = i;
                }
            }
            return hand.get(min);
        }
    }

    @Override
    public void getWinCardAndTrump(Card currentWinCard, Whist.Suit trump){
        this.winCard = currentWinCard;
        this.trump = trump;
    }

    private boolean rankGreater(Card card1, Card card2) {
        return card1.getRankId() < card2.getRankId(); // Warning: Reverse rank order of cards (see comment on enum)
    }

    private boolean canWin(Hand hand) {
        boolean win = false;
        for (int i = 0; i < hand.getNumberOfCards(); i++) {
            Card card = hand.get(i);
            if (card.getSuit().equals(winCard.getSuit()) && card.getRankId() < winCard.getRankId()) {
                win = true;
                this.winCardPos = i;
                break;
            } else if (!winCard.getSuit().equals(trump) && card.getSuit().equals(trump)) {
                win = true;
                this.winCardPos = i;
                break;
            }
        }
        return win;
    }
}
