import java.util.*;

public class Main {
    static int MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        
        // ans: (1,1)から(H,W)に行くパターン数
        int ans = 0;
        
        // 移動方法:
        // 右or下or右下へ、障害物にぶつかるまでコスト1で行ける。
        // ex. (3,5)
        // -> (1,5),(2,5)をもらう (右)
        //  + (3,1),(3,2),(3,3),(3,4)をもらう (下)
        //  + (1,3),(2,4)をもらう (右下)
        // <= これらは累積和でO(1)で取得可能。
        // -> 累積和によるDPをする。
        
        // board[i][j]: 迷路
        char[][] board = new char[h][w];
        for (int i = 0; i < h; i++) {
            String str = sc.next();
            for (int j = 0; j < w; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        
        // dp[i][j]: (i,j)に行くパターン数
        long[][] dp = new long[h][w];
        dp[0][0] = 1;
        
        // 上から1列、1行、1斜めラインの累積和
        long[] sdp_h = new long[w];
        sdp_h[0] = 1;
        long[] sdp_w = new long[h];
        sdp_w[0] = 1;
        long[] sdp_n = new long[h+w-1]; // 斜めラインはh+w-1個ある
        sdp_n[h-1] = 1;
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 && j == 0) continue;
                
                // 障害物があると累積和はリセット
                if (board[i][j] == '#') {
                    sdp_h[j] = 0;
                    sdp_w[i] = 0;
                    sdp_n[h-1-i+j] = 0;
                    continue;
                }
                
                dp[i][j] += sdp_h[j];
                dp[i][j] %= MOD;
                dp[i][j] += sdp_w[i];
                dp[i][j] %= MOD;
                dp[i][j] += sdp_n[h-1-i+j];
                dp[i][j] %= MOD;
                
                sdp_h[j] += dp[i][j];
                sdp_h[j] %= MOD;
                sdp_w[i] += dp[i][j];
                sdp_w[i] %= MOD;
                sdp_n[h-1-i+j] += dp[i][j];
                sdp_n[h-1-i+j] %= MOD;
            }
        }
        
        // for (long[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        
        System.out.println(dp[h-1][w-1]);
    }
}
