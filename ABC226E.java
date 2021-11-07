import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        // ans: 2^なもりグラフの個数を出力せよ。
        
        // なもりグラフ:
        // どの頂点も他の頂点へ向かう辺が1本だけある(有向-)
        // 木に1本辺を足したグラフ(無向-)
        // n頂点n辺のグラフ(無向-)
        
        // to: 隣接リスト(無向)
        List<Integer>[] to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<>();
        }
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < m; i++) {
            int p = sc.nextInt()-1;
            int q = sc.nextInt()-1;
            to[p].add(q);
            to[q].add(p);
            uf.unite(p, q);
        }
        
        List<Integer> roots = new ArrayList<>();
        int[] ecount = new int[n];
        for (int i = 0; i < n; i++) {
            int root = uf.root(i);
            if (root == i) roots.add(i);
            int ec = to[i].size();
            ecount[root] += ec;
        }
        
        int count = 0;
        for (Integer root : roots) {
            int vcount = uf.size[root];
            
            if (vcount*2 == ecount[root]) {
                count++;
            } else {
                System.out.println(0);
                return;
            }
        }
        
        long res = 1;
        for (int i = 0; i < count; i++) {
            res *= 2;
            res %= 998244353;
        }
        System.out.println(res);
    }
}

class UnionFind {
    int[] par;  // 自身の親
    int[] size; // ※(自身の属するグループの要素数はsize[uf.root(i)]。)
                // (※[i]全てに対して↑を更新するのは時間がかかるので)
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
    
    // 同グループか否か
    boolean same(int x, int y) {
        int rx = root(x);
        int ry = root(y);
        return rx == ry;
    }
    
    void printPar() {
        System.out.println(Arrays.toString(par));
    }
}
