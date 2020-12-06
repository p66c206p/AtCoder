import java.util.*;

public class Main {
    static long MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        // ans: 赤玉1,白玉2,青玉3から4個選ぶパターン数
        // = dp[n][k]
        
        // 01234567
        // 1
        // 11           i=1 (Aに配る,配らない)
        
        // 11
        //  11
        //   11
        // 1221         i=2 (ex. j=2 -> 赤1白1,白2)
        
        // 1221
        //  1221
        //   1221
        //    1221
        // 1356531      i=3
        
        // -> i=3,j=3 -> dp[i-1][0,1,2,3]からまとめて遷移させる
        // <- 累積和によるDP高速化
        
        // dp[i][j]: 前からi人にj個配るパターン数
        long[][] dp = new long[n+1][k+1];
        dp[0][0] = 1;
        
        // sdp: dp[i-1]の累積和
        long[] sdp = new long[k+1];
        Arrays.fill(sdp, 1);
        
        for (int i = 0; i < n; i++) {
            int len = array[i];
            for (int j = 0; j <= k; j++) {
                int R = j;
                int L = Math.max(j-len, 0) - 1;
                
                if (L >= 0) dp[i+1][j] = sdp[R] - sdp[L];
                else        dp[i+1][j] = sdp[R];
                
                dp[i+1][j] %= MOD;
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
