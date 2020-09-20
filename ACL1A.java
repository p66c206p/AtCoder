import java.util.*;
import java.io.*;
// import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        // Scanner sc = new Scanner(System.in);
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int[][] array = new int[n][3];
        for (int i = 0; i < n; i++) {
            array[i][0] = sc.nextInt()-1;   // x[i]
            array[i][1] = sc.nextInt()-1;   // y[i]
            array[i][2] = i;
        }
        
        // ans[i]:
        // 二次元座標において、「自分より左下の点」「自分より右上の点」に
        // 自由に行き来できる。ans[i] = 自分から行ける点の数(自分含め)
        // ex. (2,2) -> (4,5) -> (1,3)という移動が可能。
        
        // how:
        // 自分より右上、自分より左下の人をUnion-Findで結ぶ。
        // 1. 「←方向」に点を舐め、自分と自分のhigher(y)な点を結ぶ。
        // 2. 「↑方向」に点を舐め、自分と自分のlower(x)な点を結ぶ。
        // (それでなぜかうまく行く。)
        
        // a: xを昇順にソートした点
        int[][] a = new int[n][3];
        for (int i = 0; i < n; i++) {
            a[i][0] = array[i][0];
            a[i][1] = array[i][1];
            a[i][2] = array[i][2];
        }
        Arrays.sort(a, (ax, bx) -> Integer.compare(ax[0], bx[0]));
        
        // b: yを昇順にソートした点
        int[][] b = new int[n][3];
        for (int i = 0; i < n; i++) {
            b[i][0] = array[i][0];
            b[i][1] = array[i][1];
            b[i][2] = array[i][2];
        }
        Arrays.sort(b, (ax, bx) -> Integer.compare(ax[1], bx[1]));
        
        UnionFind uf = new UnionFind(n);
        
        // ←方向に舐める
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i = n-1; i >= 0; i--) {
            int nowy = a[i][1];
            int idx = a[i][2];
            
            if (map.higherKey(nowy) != null) {
                int key = map.higherKey(nowy);
                int val = map.get(key);
                uf.unite(idx, val);
                // System.out.println(i + " " + idx + " " + val);
            }
            map.put(nowy, idx);
        }
        // System.out.println(map.toString());
        
        // ↑方向に舐める
        map = new TreeMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            int nowx = b[i][0];
            int idx = b[i][2];
            
            if (map.lowerKey(nowx) != null) {
                int key = map.lowerKey(nowx);
                int val = map.get(key);
                uf.unite(idx, val);
                // System.out.println(i + " " + idx + " " + val);
            }
            map.put(nowx, idx);
        }
        // System.out.println(map.toString());
        
        // ans: 自分の属するグループの要素数
        PrintWriter out = new PrintWriter(System.out);
        for (int i = 0; i < n; i++) {
            int res = uf.size[uf.root(i)];
            out.println(res);
        }    
        out.flush();
    }
    
    static class FastScanner {
        private BufferedReader reader = null;
        private StringTokenizer tokenizer = null;
        
        public FastScanner(InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }
        
        public String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        
        public String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        
            return tokenizer.nextToken("\n");
        }
        
        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public int nextInt() {
            return Integer.parseInt(next());
        }
        
        public double nextDouble() {
             return Double.parseDouble(next());
        }
        
        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }
        
        public long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        } 
    }
}

class UnionFind {
    int[] par;  // 自身の親
    int[] size; // ※(自身の属するグループの要素数はsize[uf.root(i)]。)
                // (※[i]全てに対して↑を更新するのは時間がかかるので)
    int connectedComponent;

    UnionFind(int n) {
        par = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
        
        size = new int[n];
        Arrays.fill(size, 1);
        connectedComponent = n;
    }

    int root(int x) {
        if (par[x] == x) return x;
        return par[x] = root(par[x]);
        // 1-2-4の場合、4の親=2を4の根=1に繋ぎ変えた上で親を返す
        // ↑根まで辿る深さ(計算量)を減らす為
        // return a = b;とは、aにbを代入し、aを返すという意味
    }
    
    // 点x, yを連結させる=同じグループにする
    void unite(int x, int y) {
        int rx = root(x);
        int ry = root(y);
        if (rx != ry) {
            par[ry] = rx;
            
            int resize = size[rx] + size[ry];
            size[rx] = resize;
            size[ry] = resize;
            connectedComponent--;
        }
    }
    
    // 同グループか否か
    boolean same(int x, int y) {
        int rx = root(x);
        int ry = root(y);
        return rx == ry;
    }
}
