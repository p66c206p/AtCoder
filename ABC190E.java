import java.util.*;

public class Main {
    static int INF = 1001001009;
    
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        // to: 隣接リスト(無向)
        List<Integer>[] to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int p = sc.nextInt()-1;
            int q = sc.nextInt()-1;
            
            to[p].add(q);
            to[q].add(p);
        }
        
        // ans:
        // N頂点M辺のグラフで、K(<=17)個の頂点すべてをたどる時、
        // 頂点列で最小の長さは？
        
        // how:
        // 1. N頂点M辺のグラフ -> K頂点のグラフに変換
        // 2. 変換したグラフで巡回セールスマン問題を解く
        
        // array: 頂点k個の配列
        int k = sc.nextInt();
        int[] array = new int[k];
        for (int i = 0; i < k; i++) {
            array[i] = sc.nextInt()-1;
        }
        
        // 1. K頂点からBFSすることでcost[i][j]を求める
        
        // cost[i][j]: 頂点i -> jへのコスト
        int[][] cost = new int[k][k];
        for (int i = 0; i < k; i++) {
            int[] dist = calcDist(to, array[i]);
            
            // K頂点のみにリサイズ
            for (int j = 0; j < k; j++) {
                cost[i][j] = dist[array[j]];
            }
        }
        
        // 2. K頂点の巡回セールスマン問題を解く
        // 条件: (始点はどの点でもOK, 戻ってこなくてOK)
        
        int all = (1 << k) - 1;
        
        // dp[i][j]: 集合iを既に通り、最後にjを訪れた時の最小コスト
        long[][] dp = new long[all+1][k];
        // 初期化
        for (long[] dps : dp) {
            Arrays.fill(dps, INF);
        }
        
        // 始点: 頂点0以外からでもOK
        for (int i = 0; i < k; i++) {
            dp[1 << i][i] = 0;
        }
        for (int i = 1; i <= all; i++) {
            for (int j = 0; j < k; j++) {
                for (int l = 0; l < k; l++) {
                    // 状態i,jで(i | k)へ遷移する
                    int ni = i | (1 << l);
                    int nj = l;
                    int plus = cost[j][l];
                    
                    dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + plus);
                }
            }
        }
        // for (long[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        
        long ans = INF;
        for (int i = 0; i < k; i++) {
            ans = Math.min(ans, dp[all][i]);
        }
        if (ans == INF) ans = -2;
        System.out.println(ans + 1);    // 自身の点も含めた距離
    }
    
    public static int[] calcDist(List<Integer>[] to, int k) {
        // dist[i]: 点iへの最短距離
        int[] dist = new int[to.length];
        Arrays.fill(dist, INF);
        dist[k] = 0;
        
        // BFS: 頂点kからの距離を求める
        Queue<Integer> que = new ArrayDeque<>();
        que.add(k);
        while (!que.isEmpty()) {
            int p = que.poll();
            
            for (int q : to[p]) {
                if (dist[q] == INF) {
                    dist[q] = dist[p] + 1;
                    que.add(q);
                }
            }
        }
        
        return dist;
    }
}
