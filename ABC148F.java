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
        
        to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<Integer>();
        }
        
        // to: 隣接リスト(無向)
        for (int i = 0; i < n - 1; i++) {
            int s = sc.nextInt() - 1;
            int t = sc.nextInt() - 1;
            to[s].add(t);
            to[t].add(s); 
        }
        
        // u/vから各点までの距離を計算
        int[] distU = calcDist(u);
        int[] distV = calcDist(v);
        
        // uの方が近い点のうち、最も遠い点までの距離を取得
        int max = -1;
        for (int i = 0; i < n; i++) {
            if (distU[i] < distV[i]) {
                max = Math.max(max, distV[i]);
            }
        }
        
        System.out.println(max - 1);
    }
    
    public static int[] calcDist(int p) {
        dist = new int[n];
        dfs(p, 0, -1);
        return dist;
    }
    
    // 子qに、距離+1を配る
    public static void dfs(int p, int d, int par) {
        dist[p] = d;
        
        for (Integer q : to[p]) {
            if (q == par) continue;
            
            dfs(q, d+1, p);
        }
    }
}
