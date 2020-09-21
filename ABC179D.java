import java.util.*;

public class Main {
    static int MOD = 998244353;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        int[] left = new int[k];
        int[] right = new int[k];
        for (int i = 0; i < k; i++) {
            left[i] = sc.nextInt();
            right[i] = sc.nextInt();
        }
        
        // ans: マス1からマスNへ行くパターン数
        // (ただし、各マスからは各区間iの値進める)
        
        // ex. {[1, 1], [3, 4], [6, 10]}
        // もらうDPで考えると、
        // dp[i]へ dp[i-1]...dp[i-1]とdp[i-4]...dp[i-3]とdp[i-10]...dp[i-6]から遷移できる
        
        // how:
        // dp[L] + dp[L+1] ... + dp[R]をまとめて遷移させたいなら
        // dpの累積和(sdp)を取って、区間和(sdp[R] - sdp[L-1])を求めて遷移させれば良い
        // -> dpの累積和を都度取りながらdpする。
        
        // dp[i]: iマスに到達するパターン数
        int[] dp = new int[n+1];
        dp[1] = 1;
        // sdp[i+1]: dp[0]～dp[i]までの累積和
        // ex.[L, R] = sdp[R+1] - sdp[L]
        int[] sdp = new int[n+2];
        sdp[2] = sdp[1] + dp[1];
        
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < k; j++) {
                int l = left[j];
                int r = right[j];
                int L = i-r;
                int R = i-l;
                if (L-1 < 0) L = 1;
                if (R < 0) R = 0;
                
                int sum_LR = sdp[R+1] - sdp[L];
                if (sum_LR < 0) sum_LR += MOD;
                dp[i] += sum_LR;
                dp[i] %= MOD;
            }
            
            // sdp[i]確定、dp[i]確定した時、sdp[i+1]も確定する
            sdp[i+1] = sdp[i] + dp[i];
            sdp[i+1] %= MOD;
            // System.out.println(Arrays.toString(dp));
        }
        // System.out.println(" " + Arrays.toString(dp));
        // System.out.println(Arrays.toString(sdp));
        
        System.out.println(dp[n]);
    }
}
