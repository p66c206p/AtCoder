import java.util.*;

public class Main {
    static List<Integer>[] to;
    static int[] colors;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        
        // n点m辺のグラフ
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        // to: 隣接リスト(無向)
        to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt() - 1;
            int t = sc.nextInt() - 1;
            to[s].add(t);
            to[t].add(s); 
        }
        
        colors = new int[n];
        boolean isBipartite = dfs(0, 1);
        System.out.println(isBipartite);
    }
    
    // 二部グラフ判定: 
    // 頂点をDFSで「-1 -> 1 -> -1 -> ...」と塗り分け、
    // (-1, -1) or (1, 1)の辺がある場合はfalse
    private static boolean dfs(int p, int color) {
        colors[p] = color;
        
        for (Integer q : to[p]) {
            if (colors[q] == 0 - color) continue;
            if (colors[q] == color) {
                return false;
            }
            
            dfs(q, 0 - color);
        }
        
        return true;
    }
}
