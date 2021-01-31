import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
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
        
        int start = sc.nextInt() - 1;
        int goal = sc.nextInt() - 1;
        
        // ans: ビルstartの0階からビルgoalの0階に行くまでの歩数
        // 但し、ビル移動毎に0->1, 1->2, 2->0階へ移動(二次元の最短経路問題)

        // visited: 当該(点,階)を既に通ったか否か
        boolean[][] visited = new boolean[n][3];
        
        // BFS: 頂点を移動する毎に階数を+1する。
        int ans = 0;
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{start, 0, 0});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int p = cur[0];
            int floor = cur[1];
            int dis = cur[2];
            
            if (visited[p][floor]) continue;
            visited[p][floor] = true;
            
            if (p == goal && floor == 0) {
                ans = dis;
                break;
            }
            
            // 子の次の階に歩数+1を配る
            for (Integer q : to[p]) {
                que.add(new int[]{q, (floor + 1) % 3, dis + 1});
            }
        }
        
        if (ans == 0) {
            System.out.println(-1);
        } else {
            // 3歩 = けんけんぱ1回分 
            System.out.println(ans / 3);
        }
    }
}
