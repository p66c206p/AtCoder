import java.util.*;

public class Main {
    static int k;
    static int n;
    static int[] c;
    static int[][] s;
    static int[] t;
    static TreeSet<Integer>[] held;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        
        // k: コンテストの種類数
        k = 26;
        
        // n: 1日目～n日目にコンテストが開催される
        n = sc.nextInt();
        
        // c[i]: タイプiのコンテストのマイナス係数
        c = new int[k];
        for (int i = 0; i < k; i++) {
            c[i] = sc.nextInt();
        }
        
        // s[d][i]: タイプiをd日目に開催したときに増加する満足度
        s = new int[n+1][k];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < k; j++) {
                s[i][j] = sc.nextInt();
            }
        }
        
        // t[i]: i日目に開かれるコンテストのタイプ
        t = new int[n+1];
        for (int i = 1; i <= n; i++) {
            Random random = new Random();
            int randomValue = random.nextInt(k);
            t[i] = randomValue;
        }
        
        // held[i]: タイプiのコンテストの開催日の集合
        held = new TreeSet[k];
        for (int i = 0; i < k; i++) {
            held[i] = new TreeSet<Integer>();
            
            held[i].add(0);
            held[i].add(n+1);
        }
        for (int i = 1; i <= n; i++) {
            int contest = t[i];
            held[contest].add(i);
        }
        
        ContestInfo ci = new ContestInfo();
        long score = ci.calcScore();
        
        // 局所探索法:
        // 1.「既に見つけた解を少しずつ変化」
        // 2.「改善なら採用、悪化なら不採用」       
        // 3. 1～2を繰り返す。
        
        // m: 変更クエリ数
        int m = 2000000;
        while(m-- > 0) {
            // d: 日にち
            // q: タイプ何に変更するか
            // 2つを変数で決定
            Random random = new Random();
            int randomValue = random.nextInt(n);
            int d = randomValue + 1;
            randomValue = random.nextInt(k);
            int q = randomValue;
            while (q == t[d]) {
                randomValue = random.nextInt(k);
                q = randomValue;
            }
            
            // 復元できるように覚えとく
            int old = t[d];
            
            // 実際に操作してしまう
            ci.remove(d);
            ci.add(d, q);
            long newScore = ci.score;
            
            // スコアUPなら続行、DOWNなら復元
            if (newScore > score) {
                score = newScore;
                // System.out.println(score);
            } else {
                ci.remove(d);
                ci.add(d, old);
            }
        }
        
        for (int i = 1; i <= n; i++) {
            System.out.println(t[i] + 1);
        }
    }
    
    static public class ContestInfo {
        long score;
        
        ContestInfo() {
            score = calcScore();
        }
        
        long calcScore() {
            long ret = 0;
            for (int i = 1; i <= n; i++) {
                int contest = t[i];
                
                // UP
                ret += s[i][contest];
                
                // DOWN
                for (int j = 0; j < k; j++) {
                    int last = held[j].floor(i);
                    ret -= c[j] * (i - last);
                }
            }
            
            score = ret;
            
            return score;
        }
        
        // d日目のコンテストのタイプをcontestとする
        void add(int d, int contest) {
            score += s[d][contest];
            
            int prev = held[contest].lower(d);
            int next = held[contest].higher(d);
            score += c[contest] * (next - d) * (d - prev);
            
            held[contest].add(d);
            t[d] = contest;
        }
        
        // d日目のコンテストを削除する
        void remove(int d) {
            int contest = t[d];
            score -= s[d][contest];
            
            int prev = held[contest].lower(d);
            int next = held[contest].higher(d);
            score -= c[contest] * (next - d) * (d - prev);
            
            held[contest].remove(d);
            t[d] = -1;
        }
    }
}
