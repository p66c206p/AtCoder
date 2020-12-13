import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        // ans: 赤玉∞青玉∞白玉∞...からk個選ぶパターン数
        // = dp[n][k]
        
        // dp[n][k]: n人にk個のアメを配るパターン数(nHk)
        long[][] dp = new long[n+1][k+1];
        dp[0][0] = 1;
        
        // sdp: dp[i-1]の累積和
        long[] sdp = new long[k+1];
        Arrays.fill(sdp, 1);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i+1][j] = sdp[j];
            }
            
            sdp = new long[k+1];
            sdp[0] = dp[i+1][0];
            for (int j = 1; j <= k; j++) {
                sdp[j] = sdp[j-1] + dp[i+1][j];
            }
        }
        // for (long[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        System.out.println(dp[n][k]);
    }
}
