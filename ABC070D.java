import java.util.*;

public class Main {
    static List<int[]>[] to;
    static long[] dist;
    static int n;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        // ans: 木で頂点x -> k -> yの最短距離を出力せよ。
        // how: 頂点kから順に、辺の重みの累積和を取る。
        
        // to: 隣接リスト(無向)
        to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < n - 1; i++) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            int dis = sc.nextInt();
            to[p].add(new int[]{q, dis});
            to[q].add(new int[]{p, dis});
        }
        
        // dist[i]: kからiへの最短距離
        int query = sc.nextInt();
        int k = sc.nextInt() - 1;
        dist = new long[n];
        dfs(k, 0, -1);
        
        // x→k→yの最短距離を出力
        while (query-- > 0) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            
            System.out.println(dist[x] + dist[y]);
        }
    }
    
    // 子qに、「pまでの距離+qへの距離」を配る
    public static void dfs(int p, long d, int par) {
        dist[p] = d;
        
        for (int[] qData : to[p]) {
            int q = qData[0];
            int PtoQ = qData[1];
            if (q == par) continue;
            
            dfs(q, d + PtoQ, p);
        }
    }
}
