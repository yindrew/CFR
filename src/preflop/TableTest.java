package preflop;

import org.junit.Test;

public class TableTest {

    @Test
    public void testing() throws Exception {
        Table newTable = new Table();
        newTable.deal();
    }


    @Test
    public void testSetUp() throws Exception {
        Table newTable = new Table();
        newTable.initialSetUp();
        newTable.curState();
    }

    @Test
    public void testActionTaken() throws Exception {
        Table newTable = new Table();
        newTable.initialSetUp();
        newTable.actionTaken(newTable.actionOn);
        newTable.curState();

    }

    @Test
    public void testWholeHand() throws Exception{
        Table newTable = new Table();
        newTable.initialSetUp();
        newTable.wholeHand();
        newTable.curState();
    }


    @Test
    public void testSpecific() throws Exception {

        Table newTable = new Table();
        newTable.initialSetUp();

        newTable.bigBlind = 1;
        newTable.smallBlind = 0;
        newTable.actionOn = 2;


        newTable.players[0].setHand("AhKh");
        newTable.players[1].setHand("QdQh");

        newTable.wholeHand();
        newTable.curState();

    }

    
}


