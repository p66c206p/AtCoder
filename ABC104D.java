import java.util.*;

public class Main {
    static int MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] c = str.toCharArray();
        int n = c.length;
        
        // ans:
        // ???C?AB?C?CBAB??のような文字列について、
        // ?をA,B,Cに置き換えた全パターンについて、
        // c[i]=A,c[j]=B,c[k]=Cである(i<j<k)のパターン数
        
        // dp[i][j]: i文字目まで見てアルファベットjまで行けてるパターン数
        long[][] dp = new long[n+1][4];
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            char chr = c[i];
            
            // 'A'
            if (chr == 'A' || chr == '?') {
                // 選択する
                dp[i+1][1] += dp[i][0];
                dp[i+1][1] %= MOD;
                // 選択しない
                for (int j = 0; j < 4; j++) {
                    dp[i+1][j] += dp[i][j];
                    dp[i+1][j] %= MOD;
                }
            }
            
            // 'B'
            if (chr == 'B' || chr == '?') {
                // 選択する
                dp[i+1][2] += dp[i][1];
                dp[i+1][2] %= MOD;
                // 選択しない
                for (int j = 0; j < 4; j++) {
                    dp[i+1][j] += dp[i][j];
                    dp[i+1][j] %= MOD;
                }
            }
            
            // 'C'
            if (chr == 'C' || chr == '?') {
                // 選択する
                dp[i+1][3] += dp[i][2];
                dp[i+1][3] %= MOD;
                // 選択しない
                for (int j = 0; j < 4; j++) {
                    dp[i+1][j] += dp[i][j];
                    dp[i+1][j] %= MOD;
                }
            }
        }
        
        System.out.println(dp[n][3]);
    }
}
