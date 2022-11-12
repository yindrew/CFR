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
            players[x] = new Player(100); 
        }

    }

    public void deal() throws Exception {
        deck.newDeck();
        for (int x = 0; x < players.length; x++) {
            players[x].setHand(deck.dealHand());
            System.out.println("Player " + x + " is holding " + players[x].handToString());
        }


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
            log.append("player" + actionOn + " " + act.actionTaken + " " + act.sizing);
        }
        else {
            log.append(" player" + actionOn + " " + act.actionTaken + " " + act.sizing);
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
            while(actionOn != lastRaised){
                String takenAction = players[actionOn].takenAction;
                if (takenAction == "r" || takenAction == "c") {
                    String act = actionTaken(actionOn);
                    if (act.equals("r")) {
                        lastRaised = actionOn;
                    }
                }
                actionOn = (actionOn + 1) % 6;

            }
        }

        actionOn = -1;



    }

    

    public void curState() {
        System.out.println("The big blind is player " + bigBlind);
        System.out.println("The small blind is player " + smallBlind);
        System.out.println("action is currently on player " + actionOn);
        System.out.println("The pot size is " + potSize);
        System.out.println("The action log: " + log);







    }

}
