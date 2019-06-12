import java.util.*;

public class Main {
    static int MOD = 1000000007;
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long answer = 0;
        
        if (n == m) {
            answer = fact(n) * fact(m) % MOD * 2 % MOD;
        } else if (Math.abs(n - m) == 1) {
            answer = fact(n) * fact(m) % MOD;
        }
        
        System.out.println(answer);
    }
    
    public static long fact(int n) {
        long answer = 1;
        for (int i = 1; i <= n; i++) {
            answer = answer * i % MOD;
        }
        
        return answer;
    }
}
