package preflop;

import java.util.ArrayList;

public class GameLog {
    ArrayList<Log> history = new ArrayList<Log>();


    public GameLog() {
    }

    public void add(Log log) {
        history.add(log);
    }
    
}
