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
        
        // 1111
        //  2222
        //   2222
        //    1111
        // 1356531      i=3
        
        // -> i=3,j=1 -> dp[i+1][0,1,2,3]へまとめて遷移する
        // <- imos法によるDP高速化(始点+=val,終点+1-=valして後で累積和)
        
        // dp[i][j]: 前からi人にj個配るパターン数
        long[][] dp = new long[n+1][k+1];
        dp[0][0] = 1;
        
        for (int i = 0; i < n; i++) {
            int len = array[i];
            for (int j = 0; j <= k; j++) {
                long val = dp[i][j];
                int L = j;
                int R = j+len + 1;
                
                dp[i+1][L] += +val;
                dp[i+1][L] %= MOD;
                if (R <= k) dp[i+1][R] += -val;
                if (R <= k) dp[i+1][R] %= MOD;
                if (R <= k) if (dp[i+1][R] < 0) dp[i+1][R] += MOD;
            }
            
            for (int j = 1; j <= k; j++) {
                dp[i+1][j] += dp[i+1][j-1];
                dp[i+1][j] %= MOD;
                if (dp[i+1][j] < 0) dp[i+1][j] += MOD;
            }
        }
        // for (long[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        
        System.out.println(dp[n][k]);
    }
}
