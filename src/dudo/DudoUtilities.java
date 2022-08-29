package dudo;
public class DudoUtilities {
    public static final int NUM_SIDES = 6, NUM_ACTIONS = (2 * NUM_SIDES) + 1,
            DUDO = NUM_ACTIONS - 1;
    public static final int[] claimNum = { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2 };
    public static final int[] claimRank = { 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1 };

    public static String claimHistoryToString(boolean[] isClaimed) {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < NUM_ACTIONS - 1; x++) {
            if (isClaimed[x]) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(claimNum[x]);
                sb.append('*');
                sb.append(claimRank[x]);
            }
        }
        return sb.toString();
    }

    public static int infoSetToInteger(int playerRoll, boolean[] isClaimed) {
        int infoSetNum = playerRoll;
        for (int a = NUM_ACTIONS - 2; a >= 0; a--)
            infoSetNum = 2 * infoSetNum + (isClaimed[a] ? 1 : 0);
        return infoSetNum;
    }

}
