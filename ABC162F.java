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
        
        // ans:
        // 長さnの数列からどれも隣り合わないようにn/2個選ぶ総和のmax
        
        // how:
        // n=6 -> 1個飛ばす、n=7 -> 2個飛ばす
        // dpで(i番目, j個飛ばした)状態を持って遷移させていく。
        
        // dp[i][j]: (i番目, j個飛ばした)総和の最大値
        long[][] dp = new long[n+1][3];
        // 初期化
        for (long[] dps : dp) {
            Arrays.fill(dps, -INF);
        }
        
        dp[0][0] = 0;
        dp[1][0] = array[0];
        dp[2][1] = array[1];
        if (n % 2 == 1) dp[3][2] = array[2];
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; j+k < 3; k++) {
                    // ni: デフォルトで1個飛ばし(+2)
                    // 追加でk個飛ばす
                    int ni = i+2 + k;
                    
                    // nj: 飛ばした分だけ遷移
                    int nj = j + k;
                    
                    // カットとかcontinue
                    if (ni > n) continue;
                    
                    // 遷移
                    dp[ni][nj] = Math.max(dp[ni][nj], dp[i][j] + array[ni-1]);
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
