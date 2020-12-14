import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }
        
        // ans:
        // 1. 2つの文字列から好きなだけ文字を取り除く (この時|a|=|b|)
        // 2. ans = 「消した文字数 + 文字が一致しない(i,j)の個数」のmin
        // ex. (1324, 152643) -> (1324, 1524) -> ans = 2+1 = 3
        // -> LCSの罰金ver.を考える (消す = 次の文字に行くとみなす)
        
        // dp[n][m] = (0,0) -> (n,m)に行く時の最小コスト
        // (i,j)にいる時、
        // -> 1. i文字目を見送る(i++) (+1)
        // -> 2. j文字目を見送る(j++) (+1)
        // -> 3. i文字目とj文字目を同時に見送る
        //       (s[i]=t[j]なら+0、そうでないなら+1)
        // = 右 or 下 or 斜め右下に行く(ただし'='なら斜め右下は0歩で行ける)
        
        // dp[i][j]: 0～i、0～j文字目まで見たときの上の最小コスト
        int[][] dp = new int[n+1][m+1];
        for (int[] dps : dp) {
            Arrays.fill(dps, 1001001009);
        }
        dp[0][0] = 0;
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int cost = 1;
                
                // 1.
                if (i+1 <= n) dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + cost);
                
                // 2.
                if (j+1 <= m) dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + cost);
                
                // 3.
                if (i+1 <= n && j+1 <= m) {
                    if (a[i] == b[j]) cost = 0;
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j] + cost);
                }
            }
        }
        // for (int[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        System.out.println(dp[n][m]);
    } 
}
