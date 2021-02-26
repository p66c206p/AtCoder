import java.util.*;
import java.io.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        array = coordinateCompress(array);
        
        // ans: 単調減少部分列(広義)の長さのmax
        
        // how:
        // 単調減少(広義)とは、idxが右かつ、値が〇〇以下。
        // 座標で表すなら、x座標が右かつ、y座標が自分以下。
        // -> ans = 自分の点から「右下の点に行く」を何回繰り返せるか？のmax
        // -> 平面走査する。(y座標をn種類に抑えたいので座標圧縮が必要)
        
        // dp[i]: y座標の集合。y座標=iから「右下の点に行く」を何回繰り返せるか？のmax
        SegmentTree<Integer> seg = new SegmentTree<>(n, Integer::max, 0);
        
        // 初期の配列を取得
        for (int i = 0; i < n; i++) {
            seg.update(i, 0);
        }
        
        // ←方向に舐める(自分より下の点を調べる)
        for (int i = n-1; i >= 0; i--) {
            int nowy = array[i];
            
            // dp[i] = 自分より右下(広義)の点のbest + 1
            int res = seg.query(0, nowy+1) + 1;
            seg.update(nowy, res);
        }
        
        int ans = seg.query(0, n);
        System.out.println(ans);
    }
    
    // 座標圧縮
    // ex. 8, 100, 33, 12, 6, 1211
    // ->  1, 4, 3, 2, 0, 5
    public static int[] coordinateCompress(int[] array) {
        int n = array.length;
        int[][] a = new int[n][3];
        for (int i = 0; i < n; i++) {
            a[i][0] = array[i];
            a[i][1] = i;
        }
        Arrays.sort(a, (ax, bx) -> Integer.compare(ax[0], bx[0]));
        
        int now_val = a[0][0];
        int now_idx = 0;
        for (int i = 0; i < n; i++) {
            int val = a[i][0];
            if (val != now_val) {
                now_idx++;
                now_val = a[i][0];
            }
            a[i][2] = now_idx;
        }
        Arrays.sort(a, (ax, bx) -> Integer.compare(ax[1], bx[1]));
        
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = a[i][2];
        }
        
        return res;
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
