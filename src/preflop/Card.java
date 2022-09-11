package preflop;

public class Card {
    private static char[] suits = { 'c', 'd', 'h', 's' };
    private static char[] values = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' };
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

    public char getValue() {
        return value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        sb.append(suit);
        return sb.toString();
        
    }


}
