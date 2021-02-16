import java.util.*;
import java.io.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        
        // Range Sum Query
        LazySegTree<F, Integer> seg = new LazySegTree<>(n, (v1, v2) -> new F(v1.val+v2.val, v1.size+v2.size), (m1, m2) -> m2, (dat, lazy) -> new F(lazy*dat.size, dat.size), new F(0,0), 0);
    
        // 初期の配列を取得
        for (int i = 0; i < n; i++) {
            seg.set(i, new F(0, 1));
        }
        seg.build();
        
        List<Integer> ans = new ArrayList<>();
        while (q-- > 0) {
            int type = sc.nextInt()-1;
            
            if (type == 0) {
                // 区間更新 (0-indexed)
                int l = sc.nextInt()-1;
                int r = sc.nextInt();
                int x = sc.nextInt();
                seg.update(l, r, x);
            } else {
                // 区間取得 [l, r)
                int l = sc.nextInt()-1;
                int r = sc.nextInt();
                int res = seg.query(l, r).val;
                ans.add(res);
            }
        }
        
        // 出力
        PrintWriter out = new PrintWriter(System.out);
        for (Integer an : ans) out.println(an);
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

class F {
    int val;
    int size;
    
    public F(int val, int size) {
        this.val = val;
        this.size = size;
    }
}

class LazySegTree<T, M> {
    // dat: 0-indexed
    // 00000000
    // 11112222
    // 33445566
    // 789ABCDE ... a[0]-a[n]
    
    final T[] dat;              // データを格納する配列
    final M[] lazy;             // 更新待ちの値を格納する配列
    final int n;                // datの一番下のレベルの要素数
    final BinaryOperator<T> fx; // 二項演算子(dat * dat)
    final BinaryOperator<M> fm; // 二項演算子(lazy * lazy)
    final BiFunction<T,M,T> fv; // 二項演算子(dat * lazy)
    final T ex;                 // 単位元(dat)
    final M em;                 // 単位元(lazy)

    // 単位元で埋めて新規作成
    public LazySegTree(int len, BinaryOperator<T> fx, BinaryOperator<M> fm, BiFunction<T,M,T> fv, T ex, M em) {
        int n = 1;
        while (n < len) n <<= 1;
        this.n = n;
        this.fx = fx;
        this.fm = fm;
        this.fv = fv;
        this.ex = ex;
        this.em = em;
        this.dat = (T[]) new Object[n << 1];
        this.lazy = (M[]) new Object[n << 1];
        Arrays.fill(dat, ex);
        Arrays.fill(lazy, em);
    }
    
    public void set(int i, T x) { dat[i + n - 1] = x; }
    public void build() {
        for (int k = n - 2; k >= 0; k--) {
            dat[k] = fx.apply(dat[k * 2 + 1], dat[k * 2 + 2]);
        }
    }
    
    /* lazy eval */
    public void eval(int k) {
        if (lazy[k] == em) return;  // 更新するものが無ければ終了
        if (k < n - 1) {            // 葉でなければ子に伝搬
            lazy[k * 2 + 1] = fm.apply(lazy[k * 2 + 1], lazy[k]);
            lazy[k * 2 + 2] = fm.apply(lazy[k * 2 + 2], lazy[k]);
        }
        // 自身を更新
        dat[k] = fv.apply(dat[k], lazy[k]);
        lazy[k] = em;
    }
    
    // 区間更新(a[i] = x) O(logN)
    public void update(int a, int b, M x, int k, int l, int r) {
        eval(k);
        if (a <= l && r <= b) {  // 完全に内側の時
            lazy[k] = fm.apply(lazy[k], x);
            eval(k);
        } else if (a < r && l < b) {                     // 一部区間が被る時
            update(a, b, x, k * 2 + 1, l, (l + r) / 2);  // 左の子
            update(a, b, x, k * 2 + 2, (l + r) / 2, r);  // 右の子
            dat[k] = fx.apply(dat[k * 2 + 1], dat[k * 2 + 2]);
        }
    }
    public void update(int a, int b, M x) { update(a, b, x, 0, 0, n); }
    
    // 区間取得[a, b) O(logN)
    public T query(int a, int b) { return query_sub(a, b, 0, 0, n); }
    public T query_sub(int a, int b, int k, int l, int r) {
        eval(k);
        if (r <= a || b <= l) {             // outside completely
            return ex;
        } else if (a <= l && r <= b) {      // inside completely
            return dat[k];
        } else {
            T vl = query_sub(a, b, k * 2 + 1, l, (l + r) / 2);
            T vr = query_sub(a, b, k * 2 + 2, (l + r) / 2, r);
            return fx.apply(vl, vr);
        }
    }
    
    // 使用例
    // Range Sum Query (上は区間更新、下は区間加算)
    // LazySegTree<F, Integer> seg = new LazySegTree<>(n, (v1, v2) -> new F(v1.val+v2.val, v1.size+v2.size), (m1, m2) -> m2, (dat, lazy) -> new F(lazy*dat.size, dat.size), new F(0,0), 0);
    // LazySegTree<F, Integer> seg = new LazySegTree<>(n, (v1, v2) -> new F(v1.val+v2.val, v1.size+v2.size), Integer::sum, (dat, lazy) -> new F(dat.val+lazy*dat.size, dat.size), new F(0,0), 0);
    
    // Range Minimum Query (下は上と同意)
    // LazySegTree<Integer, Integer> seg = new LazySegTree<>(n, Integer::min, (m1, m2) -> Math.min(m1, m2), (dat, lazy) -> Math.min(dat, lazy), Integer.MAX_VALUE, Integer.MAX_VALUE);
    // LazySegTree<Integer, Integer> seg = new LazySegTree<>(n, Integer::min, Integer::min, Integer::min, Integer.MAX_VALUE, Integer.MAX_VALUE);
    
    // Range Maximum Query
    // LazySegTree<Long, Long> seg = new LazySegTree<>(n, Long::max, Long::max, Long::max, Long.MIN_VALUE, Long.MIN_VALUE);
    
    // Range XOR Query
    // BinaryOperator<Long> op = (num1, num2) -> (num1 ^ num2);
}
