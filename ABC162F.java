import java.util.*;

public class Main {
    static long INF = (long)1e+18;
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        // ans: 配列の中からn/2個選ぶ時の総和のmax
        // (※ただし、隣り合う2要素は選べない)
        
        // dp[i][j]: 最後にarray[i]を選択した時の、総和のmax
        long[][] dp = new long[n+1][3];
        // 初期化
        for (long[] dps : dp) {
            Arrays.fill(dps, -INF);
        }
        if (n % 2 == 0) {
            dp[0][0] = 0;
            dp[1][0] = array[0];
            dp[2][1] = array[1];
        } else {
            dp[0][0] = 0;
            dp[1][0] = array[0];
            dp[2][1] = array[1];
            dp[3][2] = array[2];
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    if (i+2 <= n) {
                        dp[i+2][j] = Math.max(dp[i+2][j], dp[i][j] + array[i+1]);
                    }
                    if (i+3 <= n) {
                        dp[i+3][j+1] = Math.max(dp[i+3][j+1], dp[i][j] + array[i+2]);
                    }
                    if (i+4 <= n) {
                        dp[i+4][j+2] = Math.max(dp[i+4][j+2], dp[i][j] + array[i+3]);
                    }
                } else if (j == 1) {
                    if (i+2 <= n) {
                        dp[i+2][j] = Math.max(dp[i+2][j], dp[i][j] + array[i+1]);
                    }
                    if (i+3 <= n) {
                        dp[i+3][j+1] = Math.max(dp[i+3][j+1], dp[i][j] + array[i+2]);
                    }
                } else {
                    if (i+2 <= n) {
                        dp[i+2][j] = Math.max(dp[i+2][j], dp[i][j] + array[i+1]);
                    }
                }
            }
        }
        // for (long[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        
        long ans = 0;
        if (n % 2 == 0) {
            ans = Math.max(dp[n-1][0], dp[n][1]);
        } else {
            ans = Math.max(Math.max(dp[n-2][0], dp[n-1][1]), dp[n][2]);
        }
        System.out.println(ans);
    }
}
