package preflop;

public class Hand {
    String handType = "";
    Card[] hand = new Card[2];
    public Hand(Card[] cards){
        hand = cards;
        handType = this.handType();
    }

    public String handType() {
        boolean suited = false;
        String sol = "";
        if(hand[0].getSuit() == hand[1].getSuit()) {
            suited = true;
        }

        if (hand[0].getCardVal() > hand[1].getCardVal()){
            sol = String.valueOf(hand[0].getValue()) + 
            String.valueOf(hand[1].getValue());
        }
        else if (hand[0].getCardVal() == hand[1].getCardVal()) {
            return String.valueOf(hand[0].getValue()) + 
            String.valueOf(hand[1].getValue());
        }
        else {
            sol = String.valueOf(hand[1].getValue()) + String.valueOf(hand[0].getValue());
        }
        return suited ? sol + "s" : sol + "o";
    }

    
}
