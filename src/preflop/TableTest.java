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
        newTable.setPositions(5);
        newTable.setHand(0, "5c2c"); 
        newTable.setHand(1, "2h2h");
        newTable.setHand(2, "3d3h");
        newTable.setHand(3, "5s4s");
        newTable.setHand(4, "Jh7h");
        newTable.setHand(5, "As5s");

        newTable.wholeHand();
        newTable.curState();

    }

    
}


