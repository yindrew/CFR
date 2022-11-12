package preflop;
import java.util.ArrayList;
public class HandRanking {
    Hand[][] hand = new Hand[13][13];


    


    public HandRanking() throws Exception {

        // pairs
        for (int i = Card.values.length-1; i > -1; i--){
            Card[] cards = new Card[2];
            cards[0] = new Card(0, i);
            cards[1] = new Card(1, i);
            hand[12-i][12-i] = new Hand(cards);
        }

        // off suit combos
        for (int x = 0; x < Card.values.length; x++) {
            for (int i = Card.values.length-1; i > -1; i--){
                Card[] cards = new Card[2];
                cards[0] = new Card(0, 12 - x);
                cards[1] = new Card(1, i);
                hand[x][12-i] = new Hand(cards);
            }
        }

        // suited combos
        for(int y = 0; y < Card.values.length; y++){
            for(int x = 0; x < Card.values.length - y; x++){
                Card[] cards = new Card[2];
                cards[0] = new Card(0, 12-y);
                cards[1] = new Card(0, x);
                hand[12-x][y] = new Hand(cards);
            }
        }


    }


    public double[][] range(double val) {
        double[][] sol = new double[13][13];
        for(int x = 0; x < 13; x++){
            for(int y = 0; y < 13; y++){
                sol[x][y] = 1;
            }
        }

        // pairs
        for (int i = 0; i < 13; i++) {
            double temp = (i / 7) * val;
            sol[i][i] = sol[i][i] - temp;
        }


        return sol;
    }

}
