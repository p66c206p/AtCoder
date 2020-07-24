import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int n  = str.length();
        int m = sc.nextInt();
        char c[] = str.toCharArray();
        
        // dp[n][m][0] + dp[n][m][1] = n桁までで非0がm個ある数の個数
        int[][][] dp = new int[n+1][m+1][2];
        dp[0][0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
            // m -> mの遷移もあるので「<= m」
            // 配列からはみ出る場合は中のcontinueで抜ける
                for (int k = 0; k < 2; k++) {
                    int nd = c[i] - '0';
                    
                    for (int d = 0; d <= 9; d++) {
                        // i: 必ずi+1へ遷移
                        int ni = i + 1, nj = j, nk = k;
                        
                        // j: jは現在までの非0の個数
                        // !0なら1個多い状態へ遷移
                        // m超過は遷移を見る必要はないのでcontinue
                        if (d != 0) nj++;
                        if (nj > m) continue;
                        
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
        
        System.out.println(dp[n][m][0] + dp[n][m][1]);
    }
}
