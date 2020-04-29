import java.util.*;

public class Main {
    static long INF = 1001001001001001018L;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = Math.min(sc.nextInt(), 50 * 50 - 1);
        
        // to: 隣接リスト(無向)
        List<int[]>[] to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < m; i++) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            int fee = sc.nextInt();
            int time = sc.nextInt();
            to[p].add(new int[]{q, fee, time});
            to[q].add(new int[]{p, fee, time});
        }
        
        // 頂点iでは、d[i]分待機で、c[i]円得られる
        int[] c = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = sc.nextInt();
            d[i] = sc.nextInt();
        }
        
        // dp[i][j]: 所持金j円で頂点iに行くまでの最短時間
        long[][] dp = new long[n][50 * 50];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][s] = 0;
        
        // ダイクストラ法: 最小コストの頂点から距離を配る。
        // (キューの先頭の点はそう来る他最短経路がないので最短距離が確定する)
        PriorityQueue<Point> que = new PriorityQueue<>();
        que.add(new Point(0, s, 0));
        while (!que.isEmpty()) {
            Point cur = que.poll();
            int p = cur.name;
            int cash = cur.cash;
            long now = cur.time;
            
            //System.out.println(p+1 + " " + cash + " " + now);
            
            // distより大きいdisを持つ点からは配らせない
            if (now > dp[p][cash]) continue;
            
            // 遷移パターン1: 同頂点でd分待機してc円得る
            // 50*50円以上は稼ぐ必要がない(遷移させない)
            int nc = Math.min(cash + c[p], 50 * 50 - 1);
            if (now + d[p] < dp[p][nc]) {
                dp[p][nc] = now + d[p];
                que.add(new Point(p, nc, now + d[p]));
            }
            
            // 遷移パターン2: fee円払い, time分掛けて隣接の頂点へ移動
            for (int[] qData : to[p]) {
                int q = qData[0];
                int fee = qData[1];
                int time = qData[2];
                
                if (cash - fee < 0) continue;
                
                // 最短距離が更新される場合だけキューに入れる
                if (now + time < dp[q][cash - fee]) {
                    dp[q][cash - fee] = now + time;
                    que.add(new Point(q, cash - fee, now + time));
                }
            }
        }
        
        // 頂点iにいる時の最短時間を出力
        for (int i = 1; i < n; i++) {
            long min = INF;
            for (int j = 0; j < 50 * 50; j++) {
                min = Math.min(min, dp[i][j]);
            }
            System.out.println(min);
        }
    }
}
    
class Point implements Comparable<Point> {
    int name;
    int cash;
    long time;
     
    Point(int name, int cash, long time) {
        this.name = name;
        this.cash = cash;
        this.time = time;
    }
    
    @Override
    public int compareTo(Point o) {
        //重みの小さい順
        if (this.time < o.time) {
            return -1;
        } 
        return 1;
    }
}
