import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[] p = new double[n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextDouble();
        }
        
        // dp[i][j] = i枚目までやったときに、j枚表が出る確率
        double[][] dp = new double[n + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i + 1][j + 1] += dp[i][j] * p[i];
                dp[i + 1][j] += dp[i][j] * (1 - p[i]);
            }
        }
        
        double answer = 0;
        for (int x = n / 2 + 1; x <= n; x++) {
            answer += dp[n][x];
        }
        
        System.out.println(answer);
    }
}
