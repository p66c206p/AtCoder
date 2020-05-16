import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long m = sc.nextLong();
        
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
        // 但し、ビル移動毎に1階上がる(二次元の最短経路問題)
        int ans = 0;
        Queue<int[]> que = new ArrayDeque<int[]>();
        que.add(new int[]{start, 0, 0});
        boolean[][] visited = new boolean[n][3];
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int p = cur[0];
            int f = cur[1];
            int l = cur[2];
            
            if (visited[p][f] == true) continue;
            visited[p][f] = true;
            
            if (p == goal && f == 0) {
                ans = l;
                break;
            }
            
            // 子の次の階に歩数+1を配る
            for (Integer q : to[p]) {
                que.add(new int[]{q, (f + 1) % 3, l + 1});
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
