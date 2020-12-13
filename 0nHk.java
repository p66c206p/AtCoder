import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        // dp[n][k]: n人にk個のアメを配るパターン数(nHk)
        long[][] dp = new long[n+1][k+1];
        dp[0][0] = 1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                int ni = i+1;
                
                for (int nj = j; nj <= k; nj++) {
                    dp[ni][nj] += dp[i][j];
                }
            }
        }
        // for (long[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        System.out.println(dp[n][k]);
    }
}
