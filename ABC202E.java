import java.util.*;
import java.io.*;

public class Main {
    static List<Integer>[] to;
    static long[] dist;
    static int n;
    
    static int time;
    static int[] in;                // 頂点iに入った時刻
    static int[] out;               // 頂点iから出た時刻
    static List<Integer>[] depth;   // 深さiである頂点に入った時刻のリスト
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        // to: 隣接リスト(無向)
        to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            int num = sc.nextInt()-1;
            to[i].add(num);
            to[num].add(i);
        }
        
        // ans: 
        // 根が0の木がある。
        // 頂点0との最短パス上に頂点uがあり、
        // 頂点0との距離がdである個数を出力せよ。
        
        // how:
        // 最短パス上にuがある頂点
        // -> uの子孫である頂点
        // -> DFSした時「uに入った時刻 < 自分に入った時刻」かつ、
        //    「自分から出た時刻 < uから出た時刻」である頂点
        
        // how2:
        // オイラーツアーで、
        // 「各頂点の入出時刻」「深さiである頂点にいつ入ったか」をメモる。
        // -> 答えは、深さdである頂点の[uの入時刻, uの出時刻)の個数。
        
        // オイラーツアー
        time = 0;
        in = new int[n];
        out = new int[n];
        depth = new List[n];
        for (int i = 0; i < n; i++) {
            depth[i] = new ArrayList<>();
        }
        
        dfs(0, 0, -1);
        
        int q = sc.nextInt();
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int u = sc.nextInt()-1;
            int d = sc.nextInt();
            
            // ans: 深さdで、時刻[L, R)の頂点数
            int l = in[u];
            int r = out[u];
            int tmp1 = lowerBound(depth[d], l);
            int tmp2 = lowerBound(depth[d], r);
            
            ans[i] = tmp2 - tmp1;
        }
        
        // 出力
        PrintWriter out = new PrintWriter(System.out);
        for (int i = 0; i < q; i++) {
            out.println(ans[i]);
        }    
        out.flush();
    }
    
    // DFS: 頂点kからの距離を求める
    public static void dfs(int p, int d, int par) {
        // オイラーツアーの記録
        in[p] = time;
        depth[d].add(in[p]);
        
        time++;
        
        for (int q : to[p]) {
            if (q == par) continue;
            
            dfs(q, d+1, p);
        }
        
        // オイラーツアーの記録
        out[p] = time;
    }
    
    public static int lowerBound(List<Integer> array, int target) {
        // target"以上の"初めての場所を返す
        // {1, 3, 3, 7} target: 3 -> 1
        
        int left = 0;
        int right = array.size();
        
        while (left < right) {
            int center = (left + right) / 2;
            if (array.get(center) < target) {
                left = center + 1;
            } else {
                right = center;
            }
        }
        
        return left;
    }
}
