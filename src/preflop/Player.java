package preflop;

import java.util.ArrayList;

public class Player {
    private Card[] hand = new Card[2];
    private Hand realHand;
    int stack = 0;
    String takenAction = "";
    HandStrength handStrength = new HandStrength();

    public Player(int stack){
        this.stack = stack;
    }


    public Hand getHand() {
        return realHand;
    }

    public String handToString() {
        return (hand[0].toString() + " " + hand[1].toString());
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
        this.realHand = new Hand(this.hand);
    }


    public int getRange(GameLog log) {
        double totalHands = handStrength.cur;
        boolean rfi = true;
        int numberOfRaises = 0;
        ArrayList<Integer> positionsRaised = new ArrayList<Integer>();
        for (int x = 0; x < log.history.size(); x++) {
            if(log.history.get(x).actionTaken.equals("r")){
                numberOfRaises += 1;
                positionsRaised.add(log.history.get(x).pos);
                rfi = false;
            }
        }
        if (rfi) {
            totalHands = this.RFI(log.history.size());
        }
        else {
            totalHands = this.raiseFold(numberOfRaises, positionsRaised);
        }


        return (int) totalHands;
    }

    public int raiseFold(int numberRaises, ArrayList<Integer> positions) {
        double value = handStrength.cur;
        for(int x = 0; x < numberRaises; x++) {
                value = value * .22;
        }
        return (int) value;
    }


    public int RFI(int pos) {
        if (pos == 0) {
            return (int) (handStrength.cur * .44);
        }
        else if (pos == 1) {
            return (int) (handStrength.cur * .55);
        }
        else if (pos == 2) {
            return (int) (handStrength.cur * .66);
        }
        else if (pos == 3) {
            return (int) (handStrength.cur);
        }
        else {
            return (int) (handStrength.cur * .5);
        }
    }




    public Log getAction(GameLog actionHappened, int whoOn) {
        String handClass = realHand.handType();
        int range = this.getRange(actionHappened);
        boolean goodEnough = false;


        for (int x = 0; x < range; x++) {
            if (handStrength.hands[x].equals(handClass)) {
                goodEnough = true;
            }
        }

        if(!goodEnough){
            takenAction = "f";
            return new Log(whoOn, "f", 0);
        }
        else{
            double size = 2.5;
            for (int x = 0; x < actionHappened.history.size(); x++) {
                size = size + 3 * actionHappened.history.get(x).sizing; 
            }

            takenAction = "r";
            return new Log(whoOn, "r", size);
        }

    }



    public int getPosition() {
        return 0;

    }


    

    
}
