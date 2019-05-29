import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        UnionFind uf = new UnionFind(n);
        
        while (m-- > 0) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            sc.nextInt();
            uf.unite(x, y);
        }
        
        System.out.println(uf.connectedComponent);
    }
    
    public static class UnionFind {
        int[] par;
        int connectedComponent;
    
        UnionFind(int n) {
            par = new int[n];
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
