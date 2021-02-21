import java.util.*;

public class Main {
    static long MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        // to: 隣接リスト(無向)
        // トポロジカルソート用にSetにする
        Set<Integer>[] to = new Set[n];
        for (int i = 0; i < n; i++) {
            to[i] = new HashSet<Integer>();
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
        long[][] dp = new long[n][2];
        for (long[] dpi : dp) Arrays.fill(dpi, 1);
        
        // que: 自分以前は全て処理された(確定した)頂点の集合
        Queue<Integer> que = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            if (to[i].size() == 1) que.add(i);
        }
        
        // ダイクストラみたいに確定する頂点から処理していく
        long ans = 2;
        while (!que.isEmpty()) {
            int p = que.poll();
            ans = (dp[p][0] + dp[p][1]) % MOD;
            
            for (Integer q : to[p]) {
                dp[q][0] *= (dp[p][0] + dp[p][1]) % MOD;
                dp[q][0] %= MOD;
                dp[q][1] *= dp[p][0];
                dp[q][1] %= MOD;
                
                // 確定できる頂点は処理対象に追加
                // (トポロジカルソート順に処理できる)
                to[q].remove(p);
                if (to[q].size() == 1) que.add(q);
            }
        }
        System.out.println(ans);
    }
}
