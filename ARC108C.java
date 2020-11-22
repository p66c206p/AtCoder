import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!  
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        // to: 隣接リスト
        List<int[]>[] to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt() - 1;
            int t = sc.nextInt() - 1;
            int color = sc.nextInt() - 1;
            to[s].add(new int[]{t, color});
            to[t].add(new int[]{s, color});
        }
        
        // ans: 条件を満たす頂点の色の塗り方
        // 条件: 辺の色が一方の点の色とだけ一致する
        // ex. 辺の色 = 1 -> (p, q) = (1, 8)
        
        // color[i]: 頂点[i]の色
        int[] color = new int[n];
        Arrays.fill(color, -1);
        color[0] = 0;
        
        // 根から順に、頂点に値を配る
        Queue<Integer> que = new ArrayDeque<Integer>();
        que.add(0);
        while (!que.isEmpty()) {
            int p = que.poll();
            
            for (int[] qData : to[p]) {
                int q = qData[0];
                int e_color = qData[1];
                
                if (color[q] == -1) {
                    if (color[p] == e_color) {
                        color[q] = (e_color + 1) % n;
                    } else {
                        color[q] = e_color;
                    }
                    
                    que.add(q);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            System.out.println(color[i]+1);
        }
    }
}
