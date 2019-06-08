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
            
            for (int j = 1; j <= 6; j++) {
                int pow = (int)Math.pow(6, j);
                if (pow > i) break;
                
                dp[i] = Math.min(dp[i], dp[i - pow] + 1);
            }
            
            for (int j = 1; j <= 5; j++) {
                int pow = (int)Math.pow(9, j);
                if (pow > i) break;
                
                dp[i] = Math.min(dp[i], dp[i - pow] + 1);
            }
        }
        
        System.out.println(dp[amount]);
    }
}
