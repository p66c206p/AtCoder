import java.util.*;

public class Main {
    static int MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int n = str.length();
        char c[] = str.toCharArray();
        
        // dp[i][j] = i文字目までの値xを、13で割った余りがjになるパターン数
        // 初期段階(1通り)を入れる
        long[][] dp = new long[n + 1][13];
        dp[0][0] = 1;
        
        // 1?2?3: 0 -> 1 -> 1? -> 1?2 -> ...
        for (int i = 0; i < n; i++) {
            if (c[i] == '?') {
                for (int k = 0; k <= 9; k++) {
                    // 余りjの集団を余り「(jを10倍してkを足した数) % 13」に遷移させる
                    // ex.余り2の集団は、10倍されると余り7へ移動する
                    // [2,15,132] -> [20, 150, 1320]
                    for (int j = 0; j < 13; j++) {
                        dp[i + 1][(j * 10 + k) % 13] += dp[i][j];
                        dp[i + 1][(j * 10 + k) % 13] %= MOD;
                    }
                }
            } else {
                int k = c[i] - '0';
                    for (int j = 0; j < 13; j++) {
                        dp[i + 1][(j * 10 + k) % 13] += dp[i][j];
                        dp[i + 1][(j * 10 + k) % 13] %= MOD;
                    }
            }
        }
        
        // 13で割って5余るパターン数を出力
        System.out.println(dp[n][5]);
    }
}
