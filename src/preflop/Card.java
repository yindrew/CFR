package preflop;

public class Card {
    static char[] suits = { 'c', 'd', 'h', 's' };
    static char[] values = { '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
    private char suit = '0';
    private char value = '0';


    public Card(int suitIndex, int valueIndex) throws Exception {
        if(suitIndex < 0 || valueIndex < 0 || suitIndex > 3 || valueIndex > 12){
            throw new Exception("Not a Real Card");
        }
        else{
            this.suit = suits[suitIndex];
            this.value = values[valueIndex];
        }


    }

    public Card(String card){
        char value = card.charAt(0);
        char suit = card.charAt(1);
        this.value = value;
        this.suit = suit;
    }


    public int getCardVal() {
        if (value == 'A'){
            return 14;
        }
        else if (value == 'K') {
            return 13;
        }
        else if (value == 'Q') {
            return 12;
        }
        else if (value == 'J') {
            return 11;
        }
        else if (value == 'T') {
            return 10;
        }
        else{
            return (int) (value - '0');
        }


    }

    public char getValue() {
        return value;
    }

    public char getSuit() {
        return suit;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        sb.append(suit);
        return sb.toString();
        
    }


}
