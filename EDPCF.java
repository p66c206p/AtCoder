import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] s = str.toCharArray();
        str = sc.next();
        char[] t = str.toCharArray();
        int n = s.length;
        int m = t.length;
        
        // ans: 2つの文字列の共通部分の最大の長さ(LCS)
        // ex. (as, abs) -> 2
        //  as
        // a000
        // b011
        // s011
        //  012
        
        // dp[i][j]: 0～i、0～j文字目まで見たときのlcsの長さ
        int[][] dp = new int[n+1][m+1];
        
        // dp[n][m] = (0,0) -> (n,m)に行く時の最高得点
        // (i,j)にいる時、
        // -> 1. i文字目を見送る(i++) (得点+0)
        // -> 2. j文字目を見送る(j++) (得点+0)
        // -> 3. i文字目とj文字目を同時に見送る
        //       (s[i]=t[j]なら得点+1、そうでないなら+0)
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                // 1.
                if (i+1 <= n) dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);
                
                // 2.
                if (j+1 <= m) dp[i][j+1] = Math.max(dp[i][j+1], dp[i][j]);
                
                // 3.
                if (i+1 <= n && j+1 <= m) {
                    int plus = 0;
                    if (s[i] == t[j]) plus = 1;
                    dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + plus);
                }
            }
        }
        // for (int[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        // System.out.println(dp[n][m]);
        
        // lcs: Longest Common Subsequence (最長共通部分列)
        String lcs = "";
        int i = n;
        int j = m;
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i-1][j]) {
                i--;
            } else if (dp[i][j] == dp[i][j-1]) {
                j--;
            } else {
                lcs = String.valueOf(s[i-1]) + lcs;
                i--;
                j--;
            }
        }
        System.out.println(lcs);
    } 
}
