import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = 3;
        int[][] as = new int[n + 1][k];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < k; j++) {
                as[i][j] = sc.nextInt();
            }
        }
        
        int[][] dp = new int[n + 1][k];
        for (int i = 0; i < k; i++) {
            dp[1][i] = as[1][i];
        }
        
        for (int i = 2; i <= n; i++) {
            int[] plans = new int[k];
            dp[i][0] = as[i][0] + Math.max(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = as[i][1] + Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = as[i][2] + Math.max(dp[i - 1][0], dp[i - 1][1]);
        }
        
        int answer = Math.max(Math.max(dp[n][0],dp[n][1]), dp[n][2]);
        
        System.out.println(answer);
    }
}
