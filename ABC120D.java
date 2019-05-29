import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        UnionFind uf = new UnionFind(n);
        
        int[][] xy = new int[m][2];
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            xy[i][0] = x;
            xy[i][1] = y;
        }
        
        long[] answer = new long[m];
        answer[m - 1] = (long)n * (n - 1) / 2;
        
        for (int i = m - 1; i > 0 ; i--) {
            int x = xy[i][0];
            int y = xy[i][1];
            if (uf.same(x, y)) {
                answer[i - 1] = answer[i];
                continue;
            }
            
            int rx = uf.root(x);
            int ry = uf.root(y);
            answer[i - 1] = answer[i] - uf.size[rx] * uf.size[ry];
            uf.unite(x, y);
        }
        
        for (long a : answer) {
            System.out.println(a);
        }
    }
    
    public static class UnionFind {
        int[] par;  // 自身の親
        int[] size; // 自身の属するグループの要素数
        int connectedComponent;
    
        UnionFind(int n) {
            par = new int[n];
            size = new int[n];
            Arrays.fill(size, 1);
            connectedComponent = n;
            for (int i = 0; i < n; i++) {
                par[i] = i;
            }
        }
    
        int root(int x) {
            if (par[x] == x) return x;
            return par[x] = root(par[x]);
            // 1-2-4の場合、4の親=2を4の根=1に繋ぎ変えた上で親を返す
            // ↑根まで辿る深さを減らす為
            // return a = b;とは、aにbを代入し、aを返すという意味
        }
    
        void unite(int x, int y) {
            int rx = root(x);
            int ry = root(y);
            if (rx != ry) {
                par[ry] = rx;
                connectedComponent--;
                int resize = size[rx] + size[ry];
                size[rx] = resize;
                size[ry] = resize;
            }
        }
    
        boolean same(int x, int y) {
            int rx = root(x);
            int ry = root(y);
            return rx == ry;
        }
        
        void printPar() {
            System.out.println(Arrays.toString(par));
        }
    }
} 
