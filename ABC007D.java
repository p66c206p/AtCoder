import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        long tmp1 = sc.nextLong();
        long tmp2 = sc.nextLong();
        
        String L_1 = String.valueOf(tmp1-1);
        String R = String.valueOf(tmp2);
        
        // f(x): x以下の正の数で、4or9がある桁に現れるものの個数
        long res = f(R) - f(L_1);
        System.out.println(res);
    }
    
    public static long f(String str) {
        int n  = str.length();
        char c[] = str.toCharArray();
        
        // dp[n][0] + dp[n][1] = 4or9がある桁に現れるパターン数
        long[][][] dp = new long[n+1][2][2];
        dp[0][0][0] = 1;
        for (int i = 0; i < n; i++) {
            // j個 -> j + 1個の遷移を調べる
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    int nd = c[i] - '0';
                    
                    for (int d = 0; d <= 9; d++) {
                        // 未満未確定ならndより先は見れない
                        if (k == 0 && d > nd) break;
                        
                        // i: 必ずi+1へ遷移
                        int ni = i + 1, nj = j, nk = k;
                        
                        // j: 今までに4or9が現れたか否か
                        // 条件を満たす => 1へ遷移
                        if (d == 4 || d == 9) nj = 1;
                        
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
        
        long res = dp[n][1][0] + dp[n][1][1];
        return res;
    }
}
