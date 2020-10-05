import java.util.*;

public class Main {
    static long INF = (long)1e+18;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int n = sc.nextInt();
        int V = H;
        
        int[][] array = new int[n][2];
        for (int i = 0; i < n; i++) {
            int v = sc.nextInt();
            int w = sc.nextInt();
            array[i][0] = v;
            array[i][1] = w;
        }
        
        // dp[i][j]: アイテムiまで見てきたときの、ダメージjの最小MP
        long[][] dp = new long[n+1][V+1];
        // 初期化
        for (long[] dps : dp) {
            Arrays.fill(dps, INF);
        }
        dp[0][0] = 0;
        
        // 「アイテムiを1回使うと状態jの区間[0, v]はwになる」を先にしておく
        // (下のdpで配れなくなるから)
        for (int i = 0; i < n; i++) {
            int v = array[i][0];
            int w = array[i][1];
            for (int j = 0; j <= Math.min(v, V); j++) {
                dp[i+1][j] = w;
            }
        }
        
        for (int i = 0; i < n; i++) {
            int v = array[i][0];
            int w = array[i][1];
            for (int j = 0; j <= V; j++) {
                for (int k = 0; k <= 1; k++) {
                    // 状態jでアイテムiをk個使う
                    int ni = i+1;
                    int nj = j + v * k;
                    long plus = w * k;
                    
                    // カットとかcontinue
                    if (nj > V) continue;
                    
                    // 遷移:
                    // (ni,j)から(ni,nj)へ遷移できること(個数制限なし)、
                    // また、(ni,j)へ来る遷移は現時点で全て終わっているので
                    // (ni,j)は、(ni,nj)のbestな遷移元である。
                    if (k == 0) {
                        dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + plus);
                    } else {
                        dp[ni][nj] = Math.min(dp[ni][nj], dp[ni][j] + plus);
                    }
                }
            }
        }
        // for (long[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        
        System.out.println(dp[n][V]);
    }
}
