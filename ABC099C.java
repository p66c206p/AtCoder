import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();
        
        // dp[i] = i円引き出すときの最小回数
        int[] dp = new int[100001];
        
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = dp[i - 1] + 1;
            
            // 6^x円は1回で引き出せるので
            // dp[i - 6^x]から+1回で遷移できる
            for (int pow = 1; pow <= 100000; pow *= 6) {
                if (i - pow < 0) break;
                dp[i] = Math.min(dp[i], dp[i - pow] + 1);
            }
            
            // 9^x円も同様
            for (int pow = 1; pow <= 100000; pow *= 9) {
                if (i - pow < 0) break;
                dp[i] = Math.min(dp[i], dp[i - pow] + 1);
            }
        }
        
        System.out.println(dp[amount]);
    }
}
