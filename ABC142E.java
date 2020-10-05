import java.util.*;

public class Main {
    static long INF = (long)1e+18;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] array = new int[m][2];
        int all = (1<<n) - 1;
        
        for (int i = 0; i < m; i++) {
            int cost = sc.nextInt();
            int kind = sc.nextInt();
            
            // 集合s (bitで持つ)
            // ex. {0,2,3} -> 0b1101 (0-indexed)
            int s = 0;
            for (int j = 0; j < kind; j++) {
                int digit = sc.nextInt() - 1;
                
                // digit桁目のbitを立てる
                s |= 1<<digit;
            }
            
            array[i][0] = cost;
            array[i][1] = s;
        }
        
        // dp[i][j]: アイテムiまで見てきたときの、集合jの最小コスト
        long[][] dp = new long[m+1][all+1];
        // 初期化
        for (long[] dps : dp) {
            Arrays.fill(dps, INF);
        }
        dp[0][0] = 0;
        
        for (int i = 0; i < m; i++) {
            int c = array[i][0];
            int s = array[i][1];
            for (int j = 0; j <= all; j++) {
                for (int k = 0; k <= 1; k++) {
                    // 状態jでアイテムiをk個使う
                    int ni = i+1;
                    int nj = j | s * k;
                    long plus = c * k;
                    
                    // カットとかcontinue
                    
                    // 遷移
                    dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + plus);
                }
            }
        }
        // for (long[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        
        long ans = dp[m][all];
        if (ans == INF) ans = -1;
        System.out.println(ans);
    }
}
