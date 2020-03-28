import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int n  = str.length();
        char c[] = str.toCharArray();
        
        // dp[n][m][0] + dp[n][m][1] = n桁までで1がm個ある数のパターン数
        int[][][] dp = new int[n+1][11][2];
        dp[0][0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
            // m -> mの遷移もあるので「< m + 1」
            // 配列からはみ出る場合は中のcontinueで抜ける
                for (int k = 0; k < 2; k++) {
                    int nd = c[i] - '0';
                    
                    for (int d = 0; d <= 9; d++) {
                        // i: 必ずi+1へ遷移
                        int ni = i + 1, nj = j, nk = k;
                        
                        // j: 今までの1の登場個数
                        if (d == 1) nj++;
                        
                        // k: 指定の数未満が確定しているか否か
                        // d > nd => 指定の数より大きいので遷移できない
                        // d < nd => 指定の数未満が確定なのでk=1へ遷移
                        if (k == 0) {
                            if (d > nd) continue;
                            if (d < nd) nk = 1;
                        }
                        
                        dp[ni][nj][nk] += dp[i][j][k];
                    }
                }
            }
        }
        
        // count: 何回1を書いたか
        // dp[n][2][0]が3パターン => 6回
        int count = 0;
        for (int i = 1; i < 10; i++) {
            count += dp[n][i][0] * i;
            count += dp[n][i][1] * i;
        }
        System.out.println(count);
    }
}
