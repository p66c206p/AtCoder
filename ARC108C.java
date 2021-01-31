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
            to[i] = new ArrayList<>();
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
        
        // colors[i]: 頂点[i]の色
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        colors[0] = 0;
        
        // BFS: グラフからBFSで全域木をたどる
        // 頂点pが辺の色なら、頂点qは辺と異なる色に、
        // 頂点qが辺と異なる色なら、頂点qは辺の色にする。
        Queue<Integer> que = new ArrayDeque<Integer>();
        que.add(0);
        while (!que.isEmpty()) {
            int p = que.poll();
            
            for (int[] qData : to[p]) {
                int q = qData[0];
                int e_color = qData[1];
                
                if (colors[q] == -1) {
                    if (colors[p] == e_color) {
                        colors[q] = (e_color + 1) % n;
                    } else {
                        colors[q] = e_color;
                    }
                    
                    que.add(q);
                }
            }
        }
        
        for (int color : colors) {
            System.out.println(color+1);
        }
    }
}
