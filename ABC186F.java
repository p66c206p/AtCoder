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
        int h = sc.nextInt();
        int w = sc.nextInt();
        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt()-1;
            y[i] = sc.nextInt()-1;
        }
        
        // ans:
        // board上に'#'(障害物)がある。
        // 「各行左から」+「各列上から」ライトを当てる。
        // 照らされるマスの数を出力せよ。
        // (左から照らせる行は0列目の最上の障害物まで)
        // (上から照らせる列は0行目の最左の障害物まで)
        
        // how:
        // ans = 「左から + 上から - どちらからでも」照らせるセル数
        
        // 「どちらからでも」:
        // 上から1行ずつ、「各列のマスが上からも照らせるか？」をセグ木で持つ
        // -> 1. 1で初期化、障害物が来たら0にする
        // -> 2. どちらからでも = query[0, 最左の障害物)となる
        
        // block_r: 各行の最左の障害物
        // block_c: 各列の最上の障害物
        int[] block_r = new int[h];
        int[] block_c = new int[w];
        Arrays.fill(block_r, w);
        Arrays.fill(block_c, h);
        for (int i = 0; i < n; i++) {
            block_r[x[i]] = Math.min(block_r[x[i]], y[i]);
            block_c[y[i]] = Math.min(block_c[y[i]], x[i]);
        }
        // System.out.println(Arrays.toString(block_r));
        // System.out.println(Arrays.toString(block_c));
        
        // 照らせる行はmax_h、列はmax_wからだけ
        int max_h = block_c[0];
        int max_w = block_r[0];
        
        // ends[i]: 「i行目に障害物のある列」の集合
        List<Integer>[] ends = new List[h + 1];
        for (int i = 0; i < h + 1; i++) {
            ends[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < max_w; i++) {
            int r = block_c[i];
            ends[r].add(i);
        }
        
        long ans = 0;
        
        // 左から
        for (int i = 0; i < max_h; i++) {
            ans += block_r[i];
        }
        // 上から
        for (int i = 0; i < max_w; i++) {
            ans += block_c[i];
        }
        
        // どちらからでも
        // seg.get(i): i列目のセルがまだ上から照らせるなら1、そうでないなら0
        SegmentTree<Integer> seg = new SegmentTree<>(max_w, Integer::sum, 0);
        
        // 初期の配列を取得
        for (int i = 0; i < max_w; i++) {
            seg.update(i, 1);
        }
        
        for (int i = 0; i < max_h; i++) {
            // i行目に障害物のある列は0にする
            for (int c : ends[i]) {
                seg.update(c, 0);
            }
            
            // ダブリ = 左から照らせる列 かつ まだ生きてる列
            ans -= seg.query(0, block_r[i]);
        }
        
        System.out.println(ans);
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
