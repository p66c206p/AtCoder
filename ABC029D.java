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
            // j個 -> j + 1個の遷移を調べる
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 2; k++) {
                    int nd = c[i] - '0';
                    
                    for (int d = 0; d <= 9; d++) {
                        // 未満未確定ならndより先は見れない
                        if (k == 0 && d > nd) break;
                        
                        // i: 必ずi+1へ遷移
                        int ni = i + 1, nj = j, nk = k;
                        
                        // j: 今までの1の登場個数
                        // 条件を満たす => j個からj+1個へ
                        if (d == 1) nj++;
                        
                        // k: 指定の数未満が確定しているか否か(ex. 263....)
                        // 遷移パターンは下記3通り
                        // この度確定 = 0 -> 1  ex. 261....
                        // 依然未確定 = 0 -> 0  ex. 263....
                        // 既に確定   = 1 -> 1  ex. 182....
                        if (k == 0 && d < nd) nk = 1;
                        
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
