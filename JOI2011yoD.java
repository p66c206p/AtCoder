import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        // dp[i][x] = i番目まで考えて計算結果がxになるパターン数
        long[][] dp = new long[n][21];
        int first = sc.nextInt();
        dp[1][first] = 1;
        for (int i = 1; i < n - 1; i++) {
            int num = sc.nextInt();
            
            // (j -> j + num), (j -> j - num)の
            // 遷移パターンが計算結果0～20で考えられる
            for (int j = 0; j <= 20; j++) {
                if (j + num <= 20) {
                    dp[i + 1][j + num] += dp[i][j];
                }
                if (j - num >= 0) {
                    dp[i + 1][j - num] += dp[i][j];
                }
            }
        }
        
        // ans = 最後の数字までの計算結果が最後の数字と一致するパターン数
        int last = sc.nextInt();
        long ans = dp[n - 1][last];
        System.out.println(ans);
    }
}
