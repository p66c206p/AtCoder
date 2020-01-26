import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int W = sc.nextInt();
        int n = sc.nextInt();
        int[] weights = new int[n];
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
        }
        
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                dp[i][w] = 1000000000;
            }
        }
        
        // 0ダメージ与えられる最小のマナは0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        
        // dp[i + 1][w] (品物iまでを使ったときの)wダメージ与えられる最小のマナ
        for (int i = 0; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                if (weights[i] <= w) {
                    dp[i + 1][w] = Math.min(dp[i + 1][w - weights[i]] + values[i], dp[i][w]);
                } else {
                    dp[i + 1][w] = Math.min(values[i], dp[i][w]);
                }
            }
        }
        
        System.out.println(dp[n][W]);
    }
}
