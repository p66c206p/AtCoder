import java.util.*;
import java.io.*;
// import java.io.PrintWriter; 
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Main {
    static int SIZE = 500001;
    
    public static void main(String[] args) throws Exception {
        // Your code here! 
        // Scanner sc = new Scanner(System.in);
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        // ans[i]: 各区間クエリ[L, R]の、値の種類数
        // -> ans = R-L+1 - 重複数
        // (重複の個数は矢印を張れば数えられる)
        
        // 重複の数え方:
        // -> 同じ値同士に矢印"←"を張る(下のlast_app参照)
        // -> 区間内に矢印の先も元も入っているものを数えればOK
        
        // この図がすべて:
        // -> https://youtu.be/h0MGG8rxrYc?t=8175
        
        
        // queries[l]: 要素が{lに向かう矢印の元, 出力する順番}のリスト
        List<int[]>[] queries = new List[n];
        for (int i = 0; i < n; i++) {
            queries[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < q; i++) {
            int L = sc.nextInt()-1;
            int R = sc.nextInt()-1;
            queries[L].add(new int[]{R, i});
        }
        
        // rights[l]: 「lに向かう矢印の元」の集合
        // ex. {1, 4, 1, 4, 2, 1, 3, 5, 6}
        // ->  rights[0] = {2, 5}
        List<Integer>[] rights = new List[n];
        for (int i = 0; i < n; i++) {
            rights[i] = new ArrayList<Integer>();
        }
        
        // last_app[i]: a[i]の値が、lastに出たidx
        // ex. { 1, 4, 1, 4, 2, 1, 3, 5, 6}
        // ->  {-1,-1, 0, 1,-1, 2,-1,-1,-1}
        int[] last_app = new int[SIZE];
        Arrays.fill(last_app, -1);
        for (int i = 0; i < n; i++) {
            int num = array[i];
            if (last_app[num] != -1) {
                rights[last_app[num]].add(i);
            }
            last_app[num] = i;
        }
        
        // bit: 右からx座標まで見た時のy座標の集合
        BIT bit = new BIT(SIZE);
        
        // ans: R-L+1 - 右下の点の数
        int[] ans = new int[q];
        // ←方向になめていく
        for (int x = n - 1; x >= 0; x--) {
            // xに向けられた矢印を追加する
            for (Integer y : rights[x]) {
                bit.add(y, 1);
            }
            
            // 現在BITに入ってる矢印の中で、
            // 矢印の元がright以下のものが区間内の重複分。
            for (int[] query : queries[x]) {
                int L = x;
                int R = query[0];
                int idx = query[1];
                
                int daburi = bit.sum(R);
                ans[idx] = R-L+1 - daburi;
            }
        }
      
        PrintWriter out = new PrintWriter(System.out);
        for (Integer a : ans) {
            out.println(a);
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

class BIT {
    int n;
    int[] d;

    // 単位元で埋めて新規作成
    public BIT(int n) {
        this.n = n;
        this.d = new int[n + 1];
    }
    
    public void add(int i, int x) {
        for (i++; i <= n; i += i&-i) {
            d[i] += x;
        }
    }
    
    public int sum(int i) {
        int x = 0;
        for (i++; i > 0; i -= i&-i) {
            x += d[i];
        }
        return x;
    }
    
    public int sum(int l, int r) {
        return sum(r-1) - sum(l-1);
    }
}
