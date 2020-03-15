import java.util.*;

public class Main {
    static int MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        String binary = sc.next();
        char c[] = binary.toCharArray();
        int n = c.length;
        
        // dp[n][0] + dp[n][1] = n桁までの、条件を満たすパターン数
        // 条件: 2進数A, Bの両方が1でない、かつA + B <= binary
        long[][] dp = new long[n+1][2];
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < 2; k++) {
                int d = c[i] - '0';
                
                if (k == 0) {
                    // 未満が確定していないなら
                    if (d == 0) {
                        // d == 0なら(0, 0)の1パターン
                        dp[i+1][0] += dp[i][k];
                        dp[i+1][0] %= MOD;
                    } else {
                        // d == 1なら
                        // (0, 1), (1, 0)はまだ未確定へ
                        // (0, 0)は確定へ遷移
                        dp[i+1][0] += (dp[i][k] * 2) % MOD;
                        dp[i+1][0] %= MOD;
                        dp[i+1][1] += dp[i][k];
                        dp[i+1][1] %= MOD;
                    }
                } else {
                    // 未満が確定なら(0, 0), (0, 1), (1, 0)の3パターンを取れる
                    dp[i+1][1] += (dp[i][k] * 3) % MOD;
                    dp[i+1][1] %= MOD;
                }
            }
        }
        
        System.out.println((dp[n][0] + dp[n][1]) % MOD);
    }
}
