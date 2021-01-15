import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
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
        
        // dp[i]: 頂点iに至るまでの最安値(頂点iの値は含まない)
        long[] dp = new long[n];
        long INF = 10010010010010010L;
        Arrays.fill(dp, INF);
        
        // que: 自分以前は全て処理された(確定した)頂点の集合
        Queue<Integer> que = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            if (from[i].size() == 0) que.add(i);
        }
        
        // ダイクストラみたいに確定する頂点から処理していく
        while (!que.isEmpty()) {
            int p = que.poll();
            
            for (Integer q : to[p]) {
                dp[q] = Math.min(dp[q], dp[p]);
                dp[q] = Math.min(dp[q], array[p]);
                
                // 確定できる頂点は処理対象に追加
                // (トポロジカルソート順に処理できる)
                from[q].remove(p);
                if (from[q].size() == 0) que.add(q);
            }
        }
        
        long ans = -INF;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, array[i] - dp[i]);
        }
        System.out.println(ans);
    }
}
