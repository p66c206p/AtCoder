import java.util.*;
import java.io.*;
// import java.io.PrintWriter; 

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        // Scanner sc = new Scanner(System.in);
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        
        // c[i]: 生徒iの所属クラス
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = sc.nextInt()-1;
        }
        
        UnionFind uf = new UnionFind(n);
        
        // map[i]: 生徒iのグループの情報
        // key: クラス番号, val: そのクラスの人数
        Map<Integer, Integer>[] map = new HashMap[n];
        for (int i = 0; i < n; i++) {
            map[i] = new HashMap<Integer, Integer>();
        }
        for (int i = 0; i < n; i++) {
            map[i].put(c[i], 1);
        }
        
        List<Integer> ans = new ArrayList<Integer>();
        
        while (q-- > 0) {
            int type = sc.nextInt();
            // System.out.println(type);
            
            if (type == 1) {
                // 生徒aグループと生徒bグループをマージする
                int a = sc.nextInt()-1;
                int b = sc.nextInt()-1;
                a = uf.root(a);
                b = uf.root(b);
                
                if (a > b) {
                    int tmp = a;
                    a = b;
                    b = tmp;
                }
                
                // bをaに全部移す
                if (a != b) {
                    for (Integer key : map[b].keySet()) {
                        int val = map[a].getOrDefault(key, 0);
                        int pls = map[b].getOrDefault(key, 0);
                        map[a].put(key, val + pls);
                    }
                    map[b] = new HashMap<Integer, Integer>();
                    
                    uf.unite(a, b);
                }
            } else {
                // aを含む連結成分のデータを出力
                // データ: クラスがclsの人数
                int a = sc.nextInt()-1;
                int cls = sc.nextInt()-1;
                
                int r = uf.root(a);
                Integer val = map[r].get(cls);
                if (val == null) val = 0;
                
                ans.add(val);
            }
        }
        
        PrintWriter out = new PrintWriter(System.out);
        for (Integer an : ans) {
            System.out.println(an);
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
