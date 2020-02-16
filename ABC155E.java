import java.util.*;

public class Main {
    static int INF = 1001001009;
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int n = str.length();
        
        // dp[i][j] = 下からn-i桁目までで使うお札の枚数
        // ex. 73 -> 100x1+1x3 + お釣り 10x3 -> 7
        // j: 繰り上がり中か否か
        int[][] dp = new int[n + 1][2];
        // 最初から繰り上がってる状態はないので.minに引っかからないようにする
        dp[n][1] = INF;
        for (int i = n; i > 0; i--) {
            char c = str.charAt(i - 1);
            int num = c - '0';
            
            // (3←3) -> 3 ([0]→[0]の遷移)
            // (3←7) -> 3 ([1]→[0]の遷移)
            // (7←3) -> 11 - 7 ([0]→[1]の遷移)
            // (7←7) -> 9 - 7 ([1]→[1]の遷移)
            dp[i - 1][0] = Math.min(dp[i][0], dp[i][1]) + num;
            dp[i - 1][1] = Math.min(dp[i][0] + 11 - num, dp[i][1] + 9 - num);
        }
        
        System.out.println(Math.min(dp[0][0], dp[0][1]));
    }
}
