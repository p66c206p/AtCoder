import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        // index[i]: i番目に出力する点
        int[] index = new int[n-1];
        
        // to: 隣接リスト
        List<Integer>[] to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n - 1; i++) {
            int s = sc.nextInt() - 1;
            int t = sc.nextInt() - 1;
            to[s].add(t);
            index[i] = t;
        }
        
        // visited: 当該点を既に通ったか否か
        boolean[] visited = new boolean[n];
        visited[0] = true;
        
        
        // max: colorsの最大値
        // colors[i]: iの色
        int max = 0;
        int[] colors = new int[n];
        // 頂点0から順に、親から子へ初期値を配り最終値を求める
        Queue<int[]> que = new ArrayDeque<int[]>();
        que.add(new int[]{0, 0});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int p = cur[0];
            int color = cur[1];
            
            int now = 1;
            for (Integer q : to[p]) {
                if (now == color) now++;
                que.add(new int[]{q, now});
                colors[q] = now;
                max = Math.max(max, now);
                now++;
            }
        }
        
        // colors[x]をindex[i]順に出力
        System.out.println(max);
        for (int i = 0; i < n-1; i++) {
            System.out.println(colors[index[i]]);
        }
    }
}
