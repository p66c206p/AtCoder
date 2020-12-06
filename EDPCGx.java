import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        // Setである理由: 要素の削除をしたい
        // from[i]: 点iの親
        Set<Integer>[] from = new Set[n];
        for (int i = 0; i < n; i++) {
            from[i] = new HashSet<Integer>();
        }
        // to[i]: 点iの子
        Set<Integer>[] to = new Set[n];
        for (int i = 0; i < n; i++) {
            to[i] = new HashSet<Integer>();
        }
        
        // 有向リストの作成
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt() - 1;
            int t = sc.nextInt() - 1;
            from[t].add(s);
            to[s].add(t);
        }
        
        // que: 親がいない点のキュー
        Queue<Integer> que = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            if (from[i].size() == 0) {
                que.add(i);
            }
        }
        
        // dist[i]: 点iまでの最長距離
        int[] dist = new int[n];
        
        // max: 最長パスの距離
        int max = 0;
        while (!que.isEmpty()) {
            // 親のいない点から、子へ距離+1を配る
            // (親のいない点までの最長距離は確定している)
            int p = que.poll();
            
            for (Integer q : to[p]) {
                dist[q] = Math.max(dist[q], dist[p] + 1);
                max = Math.max(max, dist[q]);
                
                // qの親からpを外し、親が0なら距離を配れる
                from[q].remove(p);
                if (from[q].size() == 0) {
                    que.add(q);
                }
            }
        }
        System.out.println(max);
    }
}
