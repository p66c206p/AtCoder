import java.util.*;

public class Main {
    static List<Integer>[] to;
    static long[][] dp;
    static int n;
    static long MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        // to: 隣接リスト(無向)
        to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            to[p].add(q);
            to[q].add(p);
        }
        
        // ans:
        // 「隣り合う頂点がともに黒色」ではない頂点の塗り方のパターン数
        
        // dp[p][0] = for (q : to[p]) {dp[q][0] + dp[q][1]} の積
        // dp[p][1] = for (q : to[p]) {dp[q][0]} の積
        
        // dp[i]: 頂点iが根の部分木のans
        dp = new long[n][2];
        
        dfs(0, -1);
        long ans = (dp[0][0] + dp[0][1]) % MOD;
        System.out.println(ans);
    }
    
    // DFS: 木DPする
    public static void dfs(int p, int par) {
        dp[p][0] = dp[p][1] = 1;
        
        for (Integer q : to[p]) {
            if (q == par) continue;
            
            dfs(q, p);
            
            dp[p][0] *= (dp[q][0] + dp[q][1]) % MOD;
            dp[p][0] %= MOD;
            dp[p][1] *= dp[q][0];
            dp[p][1] %= MOD;
        }
    }
}
