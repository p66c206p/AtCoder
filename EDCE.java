import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();
        int V = 0;
        int[] weights = new int[n];
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
            V += values[i];
        }
        
        long[][] dp = new long[n + 1][V + 1];
        for (int i = 0; i <= n; i++) {
            for (int v = 0; v <= V; v++) {
                dp[i][v] = 100000000000l; // Vより大きく、weights[i]を足しても溢れない数
            }
        }
        
        dp[0][0] = 0;
        
        for (int i = 0; i < n; i++) {
            for (int v = 0; v <= V; v++) {
                if (values[i] <= v) {
                    dp[i + 1][v] = Math.min(dp[i][v - values[i]] + weights[i], dp[i][v]);
                } else {
                    dp[i + 1][v] = dp[i][v];
                }
            }
        }
        
        int answer = 0;
        for (int v = 0; v <= V; v++) {
            if (dp[n][v] <= W) {
                answer = v;
            }
        }
        
        System.out.println(answer);
    }
}
