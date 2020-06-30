import java.util.*;

public class Main {
    static int MOD = 365;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        
        // k: コンテストの種類数
        int k = 26;
        
        // n: 1日目～n日目にコンテストが開催される
        int n = sc.nextInt();
        
        // c[i]: タイプiのコンテストのマイナス係数
        int[] c = new int[k];
        for (int i = 0; i < k; i++) {
            c[i] = sc.nextInt();
        }
        
        // s[d][i]: タイプiをd日目に開催したときに増加する満足度
        int[][] s = new int[n+1][k];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < k; j++) {
                s[i][j] = sc.nextInt();
            }
        }
        
        // t[i]: i日目に開かれるコンテストのタイプ
        int[] t = new int[n+1];
        // for (int i = 1; i <= n; i++) {
        //     t[i] = sc.nextInt() - 1;
        // }
        
        // last[i]: タイプiのコンテストが最近何日目に開かれたか
        int[] last = new int[k];
        
        // 満足度計算
        long score = 0;
        for (int i = 1; i <= n; i++) {
            long max = -1;
            int choice = -1;
            
            // 貪欲法:
            // タイプ何を開催するのがi日目時点で最適か調べる
            for (int contest = 0; contest < k; contest++) {
                // int contest = t[i];
                
                long tmp = 0;
                
                // UP
                tmp += s[i][contest];
                // last[contest] = i;
                
                // DOWN
                for (int j = 0; j < k; j++) {
                    tmp -= c[j] * (i-last[j]);
                }
                tmp += c[contest] * (i-last[contest]);
                
                // System.out.println(score);
                
                if (contest == 0 || tmp > max) {
                    choice = contest;
                    max = tmp;
                }
                
                // System.out.println(contest + " " + choice + " " + tmp + " " + max);
            }
            
            score += max;
            last[choice] = i;
            t[i] = choice;
        }
        
        for (int i = 1; i <= n; i++) {
            System.out.println(t[i] + 1);
        }
    }
    
    static public class Test {
        Test() {
            System.out.println(MOD);
        }
    }
}
