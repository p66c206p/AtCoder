import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        // ans: 木上で根からimos法をする。頂点iの値は？
        // how: 都度加算しながら、親から子へ値を配る。
        
        // to: 隣接リスト
        List<Integer>[] to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            to[p].add(q);
            to[q].add(p);
        }
        
        // sum: 各点の値
        int[] sum = new int[n];
        for (int i = 0; i < k; i++) {
            int p = sc.nextInt() - 1;
            int val = sc.nextInt();
            sum[p] += val;
        }
        
        // visited: 当該点を既に通ったか否か 
        boolean[] visited = new boolean[n];
        visited[0] = true;
        
        // BFS: 根から順に、子へ値を配る
        Queue<Integer> que = new ArrayDeque<>();
        que.add(0);
        while (!que.isEmpty()) {
            int p = que.poll();
            
            for (Integer q : to[p]) {
                if (visited[q]) continue;
                visited[q] = true;
                
                sum[q] += sum[p];
                que.add(q);
            }
        }
        
        for (int i = 0; i < n; i++) {
            System.out.print(sum[i]);
            if (i != n - 1) {
                System.out.print(" ");
            } else {
                System.out.println();
            }
        }
    }
}
