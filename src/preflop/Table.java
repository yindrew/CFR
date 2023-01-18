package preflop;

public class Table {
    Player[] players = new Player[6];
    Deck deck;
    int bigBlind = (int) (Math.random() * 6);
    int smallBlind = (bigBlind + 5) % 6;
    double potSize = 0;
    int actionOn = (bigBlind + 1) % 6;
    StringBuilder log = new StringBuilder();
    GameLog realLog = new GameLog();

    public Table() throws Exception {
        deck = new Deck();
        for (int x = 0; x < 6; x++) {
            players[x] = new Player(); 
        }

    }

    public void deal() throws Exception {
        deck.newDeck();
        for (int x = 0; x < players.length; x++) {
            players[x].setHand(deck.dealHand());
        }


    }

    public void setHand(int pos, String hand) {
        players[pos].setHand(hand);
    }

    public void setPositions(int bb) {
        this.bigBlind = bb;
        smallBlind = (bigBlind + 5) % 6;
        actionOn = (bigBlind + 1) % 6;
    }


    public void initialSetUp() throws Exception {
        deal();
        players[bigBlind].stack -= 1;
        players[smallBlind].stack -= 1.5;
        potSize = 1.5;
    }



    public String actionTaken(int actionOn) {
        Log act = players[actionOn].getAction(realLog, actionOn);
        if (log.length() == 0){
            log.append("Player" + actionOn + " " + players[actionOn].handToString() + " " + act.actionTaken );
        }
        else {
            log.append(" || Player" + actionOn + " " + players[actionOn].handToString() + " " + act.actionTaken);
        }
        realLog.add(act);

        return act.actionTaken;

        
    }



    public void wholeHand() {
        int lastRaised = -1;
        boolean bigBlindAction = false;
        // first orbit
        for (int i = actionOn; i != bigBlind; i = (i + 1) % 6 ){
            String act = actionTaken(i);
            if(act.equals("r")){
                lastRaised = i;
            }
        }
        for (int x = 0; x < realLog.history.size(); x++){
            if (realLog.history.get(x).actionTaken != "f") {
                bigBlindAction = true;
            }
        }
        if (bigBlindAction) {
            if (actionTaken(bigBlind).equals("r")){
                lastRaised = bigBlind;
            }
            actionOn = (bigBlind + 1) % 6;
        }



        // finish the rest of the action
        if (lastRaised != -1){
            int cur = 0;
            while(actionOn != lastRaised){
                String takenAction = realLog.history.get(cur).actionTaken;
                if (takenAction == "r" || takenAction == "c") {
                    String act = actionTaken(actionOn);
                    if (act.equals("r")) {
                        lastRaised = actionOn;
                    }
                }
                cur = (cur + 1) % 6;
                actionOn = (actionOn + 1) % 6;

            }
        }

        actionOn = -1;



    }

    

    public void curState() {
        System.out.println(log);
    }

}
