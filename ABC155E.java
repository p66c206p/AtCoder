import java.util.*;

public class Main {
    static int INF = 1001001009;
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int n = str.length();
        
        // dp[i][j] = 下からn-i桁目までで使うお札の最少枚数
        // ex. 73円 -> 100円1枚 + 1円3枚 + お釣り(10円3枚) -> 7
        // j: 繰り上がり中か否か (= 70円を70円でなく100円で払う)
        int[][] dp = new int[n + 1][2];
        // 最初から繰り上がってる状態はないので.minに引っかからないようにする
        dp[n][1] = INF;
        for (int i = n; i > 0; i--) {
            char c = str.charAt(i - 1);
            int num = c - '0';
            
            // ex. 73円
            // 0 -> 0の遷移 3枚+7枚 = 10枚
            // 1 -> 0の遷移 (11-3)枚+7枚 = 15枚
            // 0 -> 1の遷移 3枚+(11-7)枚 = 7枚
            // 1 -> 1の遷移 (11-3)枚+(9-7)枚 = 10枚
            dp[i - 1][0] = Math.min(dp[i][0], dp[i][1]) + num;
            dp[i - 1][1] = Math.min(dp[i][0] + 11 - num, dp[i][1] + 9 - num);
        }
        
        System.out.println(Math.min(dp[0][0], dp[0][1]));
    }
}
