import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        // to: 隣接リスト(無向)
        List<Integer>[] to = new List[n];
        Map<String, Integer> edge_idxes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n - 1; i++) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            to[p].add(q);
            to[q].add(p);
            edge_idxes.put(p + " " + q, i);
        }
        
        // visited: 当該点を既に通ったか否か
        boolean[] visited = new boolean[n];
        visited[0] = true;
        
        // colors[i]: i番目の辺の値
        // count: colorsの種類数
        int[] colors = new int[n-1];
        int count = 0;
        // 頂点0から順に、親から子へ初期値を配り最終値を求める
        Queue<int[]> que = new ArrayDeque<int[]>();
        que.add(new int[]{0, 0});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int p = cur[0];
            int val = cur[1];
            
            int color = 1;
            for (Integer q : to[p]) {
                if (visited[q]) continue;
                visited[q] = true;
                
                if (color == val) color++;
                String edge = p + " " + q;
                if (q < p) edge = q + " " + p;
                colors[edge_idxes.get(edge)] = color;
                que.add(new int[]{q, color});
                count = Math.max(count, color);
                color++;
            }
        }
        
        System.out.println(count);
        for (int color : colors) {
            System.out.println(color);
        }
    }
}
