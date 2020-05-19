import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        
        // ルンルン数: (次の桁に-1～+1で行ける)
        // ex. 334, 2123, 3234566667 (0はNG)
        
        // k番目のルンルン数を出力
        int count = 0;
        for (long i = 1; i <= 3234566667L; i++) {
            // System.out.println(i);
            String str = String.valueOf(i);
            char c[] = str.toCharArray();
            
            boolean ok = true;
            for (int j = 0; j < c.length-1; j++) {
                int prev = c[j];
                int next = c[j+1];
                
                // 条件を満たさないものは除外
                if (Math.abs(prev - next) > 1) ok = false;
                
                // 次の桁が前の桁より2以上小さいならそこまで飛ぶ
                // ex. 299 -> 300 -> 320
                if (prev - next > 1) {
                    ok = false;
                    
                    long tmp = prev - next - 1;
                    for (int l = 0; l < c.length-1-(j+1); l++) {
                        tmp *= 10;
                    }
                    
                    i += tmp;
                    i--;
                    
                    break;
                }
            }
            
            if (ok) count++;
            
            if (count == k) {
                System.out.println(i);
                return;
            }
        }
    }
}
