import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();
        int[] weights = new int[n];
        long[] values = new long[n];
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextLong();
        }
        
        long[][] dp = new long[n + 1][W + 1];
        for (int w = 0; w <= W; w++) {
            dp[0][w] = 0;
        }
        
        for (int i = 0; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                if (weights[i] <= w) {
                    dp[i + 1][w] = Math.max(dp[i][w - weights[i]] + values[i], dp[i][w]);
                } else {
                    dp[i + 1][w] = dp[i][w];
                }
            }
        }
        
        System.out.println(dp[n][W]);
    }
} 
