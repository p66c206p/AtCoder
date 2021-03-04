import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        // to: 隣接リスト(有向)
        List<Integer>[] to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            to[p].add(q);
        }
        
        // ans: n個の頂点を全部通るパターン数は？
        // 条件: (p, q)がm個与えられる。qに行くのはpを通ってから。
        
        // how:
        // bitDPする。
        // 1. 各頂点へは、「どの頂点を既に通っておけば行けるか」を求める。
        // 2. 各頂点集合について、各頂点へ遷移させる。答えはdp[all]
        
        // 1. 
        // from: (トポロジカルソート用)
        Set<Integer>[] from = new Set[n];
        for (int i = 0; i < n; i++) {
            from[i] = new HashSet<Integer>();
        }
        for (int i = 0; i < n; i++) {
            for (Integer q : to[i]) {
                from[q].add(i);
            }
        }
        
        // parents[i]: 頂点iへ遷移するために通っておく必要のある頂点集合
        int[] parents = new int[n];
        
        // que: 自分以前は全て処理された(確定した)頂点の集合
        Queue<Integer> que = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            if (from[i].size() == 0) que.add(i);
        }
        
        // ダイクストラみたいに確定する頂点から処理していく
        while (!que.isEmpty()) {
            int p = que.poll();
            
            for (Integer q : to[p]) {
                parents[q] |= parents[p];
                parents[q] |= 1 << p;
                
                // 確定できる頂点は処理対象に追加
                // (トポロジカルソート順に処理できる)
                from[q].remove(p);
                if (from[q].size() == 0) que.add(q);
            }
        }
        // System.out.println(Arrays.toString(parents));
        
        // 2.
        // dp[i]: 集合iに行くパターン数
        int all = (1<<n) - 1;
        long[] dp = new long[all+1];
        // 初期化
        dp[0] = 1;
        
        for (int i = 0; i < all; i++) {
            for (int j = 0; j < n; j++) {
                // 集合iはjまでの頂点を全部含むか？
                boolean tmp1 = (i & parents[j]) == parents[j];
                
                // 集合iは頂点jを含まないか？
                boolean tmp2 = (i & (1<<j)) == 0;
                
                if (tmp1 && tmp2) {
                    dp[i | (1<<j)] += dp[i];
                }
            }
        }
        // System.out.println(Arrays.toString(dp));
        System.out.println(dp[all]);
    }
}
