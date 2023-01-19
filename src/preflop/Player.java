package preflop;

import java.util.ArrayList;


public class Player {
    private Hand hand;
    HandStrength hs = new HandStrength();
    int stack = 100;
    int curPosition = 0;
    boolean ip = false;

    public Player(){
    }

    public Hand getHand() {
        return hand;
    }

    public String handToString() {
        return hand.handType;
    }

    public void setHand(Card[] hand) {
        this.hand = new Hand(hand);
    }


    public void setHand(String hand) {
        Card[] cards = new Card[2];
        cards[0] = new Card(hand.substring(0, 2));
        cards[1] = new Card(hand.substring(2));
        setHand(cards);
    }



    public int getRaiseRange(GameLog log) {
        double range;
        int numberOfRaises = 0;
        ArrayList<Integer> positionsRaised = new ArrayList<Integer>();


        for (int x = 0; x < log.history.size(); x++) {
            if(log.history.get(x).actionTaken.equals("r")){
                numberOfRaises += 1;
                positionsRaised.add(x);
            }
        }


        if (numberOfRaises == 0) {
            range = this.RFI(log.history.size());
        }
        else if (numberOfRaises == 1){
            range = threeBet(positionsRaised);
        } 
        else {
            range = threeBet(positionsRaised) * .4;
        }
        return (int) range;
    }



    public int threeBet(ArrayList<Integer> positions) {
        int rfi = this.RFI(positions.get(0));
        return (int) (rfi * .45);
    }



    public int RFI(int pos) {
        if (pos == 0) {
            return (int) (hs.cur * .45);
        }
        else if (pos == 1) {
            return (int) (hs.cur * .55);
        }
        else if (pos == 2) {
            return (int) (hs.cur * .66);
        }
        else if (pos == 3) {
            return (int) (hs.cur);
        }
        else {
            return (int) (hs.cur * .95);
        }
    }

    public int bigBlindReaction(ArrayList<Integer> action) {
        if (action.size() == 1) {
            return (int) (RFI(action.get(0)));
        }
        else {
            int fourBet = (int) (threeBet(action) * .4);
            return fourBet;
        }
        
    }

    

    public Log facingThreeBet(GameLog log) {
        int rfi = 0;
        ArrayList<Integer> positionsRaised = new ArrayList<Integer>();

        for (int x = 0; x < log.history.size();x++) {
            if(log.history.get(x).pos == curPosition){
                rfi = RFI(x);
            }
            if (log.history.get(x).actionTaken.equals("r")){
                positionsRaised.add(x);
            }

        }

        if (positionsRaised.get(1) == 4 || positionsRaised.get(1) == 5) {
            ip = true;
        }
        else {
            ip = false;
        }

        // 0 1 2 3 4 5 

        
        int raisingV = (int) (rfi * .10);
        int calling = raisingV + (int) (rfi * .20);
        int raisingB = calling + (int) (rfi * .10);

        

        for (int x = 0; x < raisingV; x++) {
            if (hs.hands[x].equals(hand.handType)) {
                return new Log(curPosition, "r", 24);
            }
        }

        for (int x = raisingV; x < calling; x++) {
            if (hs.hands[x].equals(hand.handType)) {
                return new Log(curPosition, "c", 8);
            }
        }

        for (int x = calling; x < raisingB; x++) {
            if (hs.hands[x].equals(hand.handType)) {
                return new Log(curPosition, "r", 24);
            }
        }

        return new Log(curPosition, "f", 0);



    }


    public Log getAction(GameLog actionHappened, int whoOn) {
        curPosition = whoOn;
        String handClass = hand.handType();
        int numRaises = 0;
        ArrayList<Integer> positionsRaised = new ArrayList<Integer>();



        for (int x = 0; x < actionHappened.history.size(); x++) {
            if(actionHappened.history.get(x).actionTaken.equals("r")){
                numRaises += 1;
                positionsRaised.add(x);
            }
        }

        // BB action 
        if (actionHappened.history.size() == 5) {
            int bbAction = bigBlindReaction(positionsRaised);

            if (positionsRaised.size() == 1) {
                int raisingVal = (int) (bbAction * .15);
                int calling = raisingVal + (int) (bbAction * .83);
    
                for (int x = 0; x < raisingVal; x++) {
                    if (hs.hands[x].equals(handClass)) {
                        return new Log(whoOn, "r", 10);
                    }
                }
    
                for (int x = raisingVal; x < calling; x++) {
                    if (hs.hands[x].equals(handClass)) {
                        return new Log(whoOn, "c", 2.5);
                    }
                }
    
                for (int x = calling; x < bbAction; x++) {
                    if (hs.hands[x].equals(handClass)) {
                        return new Log(whoOn, "r", 10);
                    }
                }
                return new Log(whoOn, "f", 0);

            }
            else {
                for (int x = 0; x < bbAction; x++) {
                    if (hs.hands[x].equals(handClass)) {
                        return new Log(whoOn, "r", 24);
                    }
                }
                return new Log(whoOn, "f", 0);
            }
        


        }

        else {
            if (numRaises == 0) {
                int range = this.getRaiseRange(actionHappened);
                for (int x = 0; x < range; x++) {
                    if(hs.hands[x].equals(handClass)){
                        return new Log(whoOn, "r", 2.5);
                    }

                }
                return new Log(whoOn, "f", 0);


            }
            else if (numRaises == 1) {
                int range = threeBet(positionsRaised);
                for (int x = 0; x < range; x++) {
                    if(hs.hands[x].equals(handClass)){
                        return new Log(whoOn, "r", 8);
                    }

                }
                return new Log(whoOn, "f", 0);


            }

            else {
                if (actionHappened.history.size() > 5 && numRaises == 2){
                    return facingThreeBet(actionHappened);
                }
                else {
                    for (int x = 0; x < 26; x++) {
                        if(hs.hands[x].equals(handClass) && actionHappened.history.size() < 7){
                            return new Log(whoOn, "r", 22);
                        }
                        if (hs.hands[x].equals(handClass) && actionHappened.history.size() == 7) {
                            return new Log(whoOn, "all in", 100);
                        }
    
                    }
                    
                    return new Log(whoOn, "f", 0);
                }
            }
        }

    }





        
}
