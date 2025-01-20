import java.util.Arrays;

public class JumpGame {
    public static boolean canWin(int[] array) {
        if (array == null) {
            return false;
        }
        if (array.length == 1)  {
            return true;
        }
        int lastIndex = array.length - 1;
        int current = lastIndex-1;
        boolean[] dp = new boolean[array.length];
        dp[lastIndex] = true;

        while (current >= 0) {
            if(array[current] == 0) {
                current--;
                continue;
            }
            for(int i = 1; i<=array[current]; i++) {
                if(current + i > lastIndex) continue;
                dp[current]  = dp[current] || dp[current+i];
            }
            current--;
        }

        return dp[0];
    };
}
