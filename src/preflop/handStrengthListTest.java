package preflop;
import org.junit.Test;

public class handStrengthListTest {
    
    @Test
    public void testing() {
        HandStrength handStrength = new HandStrength();
        System.out.println((double) handStrength.cur / 1326);
        for(int i = 0; i < (handStrength.cur); i++){
            System.out.println(handStrength.hands[i]);
        }


    }
}





//     A   K   Q   J   T   9  8  7  6  5  4  3  2
// A   986 305 178 121 104 50 30 23 22 26 20 17 15
// K   239 750 105 85  75  33 16 14 14 12 9  7  5
// Q   99  40  544 64  56  20 13 8  8  6  3  1  x
// J   44  21  18  368 55  21 12 7  4  3  x  x  x
// T   31  18  16  14  231 31 16 10 7  3  x  x  x
// 9   19  7   5   5   8  128 16 12 8  2  x  x  x 
// 8   10  x   x   x   2    2 77 12 10 4  x  x  x
// 7   5   x   x   x   x    x  x 49 12 7  x  x  x
// 6   5                            43 11 5  
// 5   6                               29 x  x  x
// 4   2                                  21
// 3                                          16
// 2                                            3



// AA KK QQ AKs AKo JJ AQs TT AJs AQo KQs ATs KJs 99 
// KTs QJs 88 QTs JTs A9s AJo KQo 77  K9s ATo A8s T9s
// 66 A7s A5s KJo 55 J9s KJo A6s A4s QJs A9o KTo 55 QJo 
// Q9s  44 A3s K8s T8s 98s QTo JTo A2s K7s 33 K6s Q8s 
// J8s 87s 97s 76s K5s 65s T7s 86s A8o K4s 96s Q7s Q6s 
// 9To J7s K3s 75s K9o T6s Q5s  Q9o J9o A7o K2s 64s A5o
// j6s 85s 22 T5s J5s Q4s T8o 98o A4o 95s Q3s
                                   