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
        newTable.setPositions(2);
        newTable.setHand(3, "Ac5s");
        newTable.setHand(4, "5dQh");    
        newTable.setHand(5, "AdAh");
        newTable.setHand(0, "KsKd");
        newTable.setHand(1, "JhTh");
        newTable.setHand(2, "8s8d");

        newTable.wholeHand();
        newTable.curState();

    }

    
}


