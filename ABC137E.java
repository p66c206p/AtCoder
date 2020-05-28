import java.util.*;

public class Main {
    static int INF = 1001001009;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int start = 0;
        int goal = n - 1;
        
        // ans: 点0から点Nに行くまでの最小コスト
        
        // to: 隣接リスト(有向)(自己ループ有)
        // rto: 逆向きの矢印
        List<int[]>[] to = new List[n];
        List<Integer>[] rto = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<int[]>();
            rto[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            int dis = sc.nextInt();
            dis = -(dis - k);
            
            to[p].add(new int[]{q, dis});
            rto[q].add(p);
        }
        
        // ベルマンフォード法: 負の辺も行ける最短経路問題の解法
        // (特定の2点間の最短距離だけを求められる)
        // (startから各点までだとTLE)
        
        // froms: startから行ける点か否か
        boolean[] froms = new boolean[n];
        Queue<Integer> que = new ArrayDeque<Integer>();
        que.add(start);
        while (!que.isEmpty()) {
            int p = que.poll();
            
            // 出現1回目のpだけに配らせる
            if (froms[p]) continue;
            froms[p] = true;
            
            // fromsであると知らなかった点だけキューに入れる
            for (int[] qData : to[p]) {
                int q = qData[0];
                if (!froms[q]) {
                    que.add(q);
                }
            }
        }
        
        // tog: goalへ行ける点か否か
        boolean[] tog = new boolean[n];
        que = new ArrayDeque<Integer>();
        que.add(goal);
        while (!que.isEmpty()) {
            int p = que.poll();
            
            // 出現1回目のpだけに配らせる
            if (tog[p]) continue;
            tog[p] = true;
            
            // togであると知らなかった点だけキューに入れる
            for (Integer q : rto[p]) {
                if (!tog[q]) {
                    que.add(q);
                }
            }
        }
        
        // dist: startからの最短距離
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        // 頂点数-1回のループを回せば2点間の最短距離が確定する
        // (負の閉路から行ける頂点以外は除く)
        boolean negativeLoop = false;
        for (int i = 0; i < n; i++) {
            for (int p = 0; p < n; p++) {
                // start-goal上にない点は無視
                if (!froms[p]) continue;
                if (!tog[p]) continue;
                
                for (int[] qData : to[p]) {
                    int q = qData[0];
                    int PtoQ = qData[1];
                    
                    // 最短距離が更新されるか？
                    if (dist[p] + PtoQ < dist[q]) {
                        // System.out.println(p + " " + q);
                        dist[q] = dist[p] + PtoQ;
                        
                        // n回目でも更新される点があれば
                        // 道中に必ず負の閉路がある
                        if (i == n - 1) {
                            negativeLoop = true;
                        }
                    }
                }
            }
        }
        
        // System.out.println(Arrays.toString(dist));
        
        if (negativeLoop) {
            System.out.println(-1);
        } else {
            if (-dist[goal] < 0) {
                System.out.println(0);
            } else {
                System.out.println(-dist[goal]);
            }
        }
    }
}
