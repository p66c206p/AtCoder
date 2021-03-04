import java.util.*;
import java.io.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        int SIZE = 500;
        
        // ans:
        // 区間がm個、区間クエリがq個ある。
        // クエリ: 区間[L, R]に完全に含まれる区間の個数は？
        
        // how:
        // 平面走査する。（←方向になめてクエリのyより小さい点の個数）
        
        List<Integer>[] ys = new List[SIZE];
        for (int i = 0; i < SIZE; i++) {
            ys[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt()-1;
            int y = sc.nextInt()-1;
            ys[x].add(y);
        }
        
        List<int[]>[] queries = new List[SIZE];
        for (int i = 0; i < SIZE; i++) {
            queries[i] = new ArrayList<>();
        }
        for (int i = 0; i < q; i++) {
            int x = sc.nextInt()-1;
            int y = sc.nextInt()-1;
            int idx = i;
            queries[x].add(new int[]{y, idx});
        }
        
        int[] ans = new int[q];
        
        SegmentTree<Integer> seg = new SegmentTree<>(SIZE, Integer::sum, 0);
        
        // 初期の配列を取得
        for (int i = 0; i < SIZE; i++) {
            seg.update(i, 0);
        }
        
        // ←方向に舐める(自分より下の点を調べる)
        for (int i = SIZE-1; i >= 0; i--) {
            for (Integer nowy : ys[i]) {
                int cnt = seg.query(nowy, nowy+1);
                seg.update(nowy, ++cnt);
            }
            
            for (int[] query : queries[i]) {
                int nowy = query[0];
                int idx = query[1];
                int cnt = seg.query(0, nowy+1);
                ans[idx] = cnt;
            }
        }
        
        PrintWriter out = new PrintWriter(System.out);
        for (Integer an : ans) {
            out.println(an);
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

class SegmentTree<T> {
    // dat: 0-indexed
    // 00000000
    // 11112222
    // 33445566
    // 789ABCDE ... a[0]-a[n]
    
    final T[] dat;              // データを格納する配列
    final int n;                // datの一番下のレベルの要素数
    final BinaryOperator<T> f;  // 二項演算子
    final T e;                  // 単位元

    // 単位元で埋めて新規作成
    public SegmentTree(int len, BinaryOperator<T> f, T e) {
        int n = 1;
        while (n < len) n <<= 1;
        this.n = n;
        this.f = f;
        this.e = e;
        this.dat = (T[]) new Object[n << 1];
        Arrays.fill(dat, e);
    }
    
    // 一点更新(a[i] = x) O(logN)
    public void update(int i, T x) {
        i += n - 1;
        dat[i] = x;
        while (i > 0) {
            i = (i - 1) / 2;  // i: parent of vl/vr
            T vl = dat[i * 2 + 1];
            T vr = dat[i * 2 + 2];
            dat[i] = f.apply(vl, vr);
        }
    }
    
    // 区間取得[a, b) O(logN)
    public T query(int a, int b) { return query_sub(a, b, 0, 0, n); }
    public T query_sub(int a, int b, int k, int l, int r) {
        if (r <= a || b <= l) {             // outside completely
            return e;
        } else if (a <= l && r <= b) {      // inside completely
            return dat[k];
        } else {
            T vl = query_sub(a, b, k * 2 + 1, l, (l + r) / 2);
            T vr = query_sub(a, b, k * 2 + 2, (l + r) / 2, r);
            return f.apply(vl, vr);
        }
    }
    
    // 使用例
    // Range Sum Query
    // SegmentTree<Integer> seg = new SegmentTree<>(n, Integer::sum, 0);
    // SegmentTree<Long> seg = new SegmentTree<>(n, Long::sum, 0L);
    
    // Range Minimum Query
    // SegmentTree<Integer> seg = new SegmentTree<>(n, Integer::min, Integer.MAX_VALUE);
    
    // Range Maximum Query
    // SegmentTree<Long> seg = new SegmentTree<>(n, Long::max, Long.MIN_VALUE);
}
