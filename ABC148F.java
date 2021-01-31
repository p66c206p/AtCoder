
import java.util.*;

public class Main {
    static List<Integer>[] to;
    static int[] dist;
    static int n;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int u = sc.nextInt() - 1;
        int v = sc.nextInt() - 1;
        
        // to: 隣接リスト(無向)
        to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int s = sc.nextInt() - 1;
            int t = sc.nextInt() - 1;
            to[s].add(t);
            to[t].add(s); 
        }
        
        // ans:
        // U地点とV地点から鬼ごっこをする。（先手はUにいる人、後手が鬼）
        // 2人が交わったら終了。終了した時、後手の歩数の最小値は？
        
        // how:
        // 先手は自分のほうが早く行ける点のうち、
        // 最も遠い点に行くのが最適。
        // 後手の歩数の最小値は、distV[その点] - 1。
        // (交わるのは必ず、その点の一歩手前なので)
        
        // distU: 頂点Uからの距離
        // distV: 頂点Vからの距離
        int[] distU = calcDist(u);
        int[] distV = calcDist(v);
        
        // Uの方が近い点のうち、最も遠い点までの距離を取得
        int max = -1;
        for (int i = 0; i < n; i++) {
            if (distU[i] < distV[i]) {
                System.out.println(i + " " + distU[i] + " " + distV[i]);
                max = Math.max(max, distV[i]);
            }
        }
        
        System.out.println(max - 1);
    }
    
    // calcDist: 頂点pからDFSする
    public static int[] calcDist(int p) {
        dist = new int[n];
        dfs(p, 0, -1);
        return dist;
    }
    
    // DFS: ある頂点からの最短歩数を求める
    public static void dfs(int p, int d, int par) {
        dist[p] = d;
        
        for (Integer q : to[p]) {
            if (q == par) continue;
            
            dfs(q, d+1, p);
        }
    }
}
