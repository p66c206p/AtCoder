import java.util.*;
import java.io.*;
// import java.io.PrintWriter; 
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        // Scanner sc = new Scanner(System.in);
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        
        // Range Sum Query
        SegmentTree<Long> seg = new SegmentTree<>(n, Long::sum, 0L);
        
        // 初期の配列を取得
        for (int i = 0; i < n; i++) {
            seg.update(i, sc.nextLong());
        }
        
        List<Long> ans = new ArrayList<Long>();
        while (q-- > 0) {
            int type = sc.nextInt()-1;
            
            if (type == 0) {
                // 一点更新 (0-indexed)
                int p = sc.nextInt()-1;
                long x = sc.nextInt();
                
                long now = seg.query(p, p+1);
                seg.update(p, now + x);
            } else {
                // 区間取得 [l, r)
                int l = sc.nextInt()-1;
                int r = sc.nextInt();
                long res = seg.query(l, r);
                ans.add(res);
            }
        }
        
        // 出力
        PrintWriter out = new PrintWriter(System.out);
        for (int i = 0; i < ans.size(); i++) {
            out.println(ans.get(i));
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
    
    // Range Bitwise-OR Query
    // BinaryOperator<Long> op = (num1, num2) -> (num1 | num2);
    // SegmentTree<Long> seg = new SegmentTree<>(n, op, 0L);
    // ※seg.update(p, now + x) -> seg.update(p, now | x)にするのに注意
}
