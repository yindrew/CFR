package preflop;

import java.util.Hashtable;

public class HandStrength {
    Hashtable<String, Integer> hand = new Hashtable<String, Integer>();
    String[] hands = new String[1326];
    int cur = 0;


    public void getRange(int val) {
        for(int x = 0; x < val; x++){
            System.out.println(hands[x]);
        }
    }

    public HandStrength() {
        a("AA"); a("KK");a("QQ");a("AKs");a("AKo");
        a("JJ");a("AQs");a("TT");a("AJs");a("AQo");
        a("KQs");a("ATs");a("KJs");a("99");a("KTs");
        a("QJs");a("88");a("QTs");a("JTs");a("A9s");
        a("AJo");a("KQo");a("77");a("K9s");a("ATo");
        a("A8s");a("T9s");a("66");a("A7s");a("A5s");
        a("A6s");a("A4s");a("KJo");a("55");a("A9o");a("QJo");a("Q9s");a("A3s");
        a("K8s");a("44");a("J9s");a("KTo");a("T8s");
        a("98s");a("QTo");;a("A2s");a("K7s");
        a("33");a("K6s");a("Q8s");a("JTo");a("J8s");a("87s");
        a("97s");a("76s");a("K5s");a("65s");a("T7s");a("86s");
        a("A8o");a("K4s");a("96s");a("Q7s");a("Q6s");
        a("T9o");a("J7s");a("K3s");a("75s");a("K9o");
        a("T6s");a("Q5s");a("Q9o");a("J9o");a("A7o");
        a("K2s");a("64s");a("A5o");a("J6s");a("85s");
        a("22");a("T5s");a("J5s");a("Q4s");a("T8o");
        a("98o");a("A4o");a("95s");a("Q3s");








    }

    public void a(String hand){
        if(hand.charAt(hand.length()-1) == 's'){
            for (int i = cur; i < cur + 4; i++){
                hands[i] = hand;
            }
            cur = cur + 4;
        }
        else if (hand.charAt(hand.length()-1) == 'o'){
            for (int i = cur; i < cur + 12; i++){
                hands[i] = hand;
            }
            cur = cur + 12;
        }
        else{
            for (int i = cur; i < cur + 6; i++){
                hands[i] = hand;
            }
            cur = cur + 6;
        }
    }

    public String[] getHands() {
        return hands;
    }


}
