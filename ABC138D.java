import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        // to: 隣接リスト
        List<Integer>[] to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n - 1; i++) {
            int s = sc.nextInt() - 1;
            int t = sc.nextInt() - 1;
            to[s].add(t);
            to[t].add(s); 
        }
        
        // first: 各点の初期値
        int[] first = new int[n];
        for (int i = 0; i < k; i++) {
            int p = sc.nextInt() - 1;
            int count = sc.nextInt();
            first[p] += count;
        }
        
        // visited: 当該点を既に通ったか否か
        boolean[] visited = new boolean[n];
        visited[0] = true;
        
        // last: 各点の最終値
        int[] last = new int[n];
        // 頂点0から順に、親から子へ初期値を配り最終値を求める
        Queue<int[]> que = new ArrayDeque<int[]>();
        que.add(new int[]{0, 0});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int p = cur[0];
            int count = cur[1] + first[p];
            last[p] = count;
            
            for (Integer q : to[p]) {
                if (!visited[q]) {
                    que.add(new int[]{q, count});
                    visited[q] = true;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            System.out.print(last[i]);
            if (i != n - 1) {
                System.out.print(" ");
            } else {
                System.out.println();
            }
        }
    }
}
