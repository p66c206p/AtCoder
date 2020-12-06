import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();
        
        int[][] array = new int[n][2];
        for (int i = 0; i < n; i++) {
            int w = sc.nextInt();
            int v = sc.nextInt();
            array[i][0] = w;
            array[i][1] = v;
        }
        
        // dp[i][j]: アイテムiまで見てきたときの、重さjの最大価値
        long[][] dp = new long[n+1][W+1];
        // 初期化
        for (int j = 0; j <= W; j++) {
            dp[0][j] = 0;
        }
        
        for (int i = 0; i < n; i++) {
            int w = array[i][0];
            int v = array[i][1];
            for (int j = 0; j <= W; j++) {
                for (int k = 0; k <= 1; k++) {
                    // 状態jでアイテムiをk個使う
                    int ni = i+1;
                    int nj = j + w * k;
                    long plus = v * k;
                    
                    // カットとかcontinue
                    if (nj > W) continue;
                    
                    // 遷移
                    dp[ni][nj] = Math.max(dp[ni][nj], dp[i][j] + plus);
                }
            }
        }
        // for (long[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        
        System.out.println(dp[n][W]);
    }
}
