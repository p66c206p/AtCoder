import java.util.*;

public class Main {
    static boolean[][] canPass;
    static int n;
    static int count = 0;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();
        int[] p = new int[m];
        int[] q = new int[m];
        canPass = new boolean[n][n];
        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited, false);
        
        for (int i = 0; i < m; i++) {
            p[i] = sc.nextInt() - 1;
            q[i] = sc.nextInt() - 1;
            canPass[p[i]][q[i]] = true;
            canPass[q[i]][p[i]] = true;
        }
        
        dfs(0, visited);
        
        System.out.println(count);
    }
    
    public static void dfs(int p, Boolean[] previousVisited) {
        if (previousVisited[p]) return;
        
        // Deepコピー、値渡しのコピー
        Boolean[] visited = previousVisited.clone();
        visited[p] = true;
        
        boolean allVisited = !Arrays.asList(visited).contains(false);
        if (allVisited) count++;
        
        for (int q = 0; q < n; q++) {
            if (canPass[p][q]) {
                dfs(q, visited);
            }
        }
    }
}
