import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        x -= 12;
        
        // x個のボールと11個の仕切り
        // = comb(x+11, 11) = comb(x+11, x)
        
        // comb(x, y)の計算を、
        // x*x-1*x-2... / y*y-1*y-2...じゃなく、
        // x/1*x-2/2*x-3/3...と分子分母交互に掛ける割るする。
        
        int now = x + 11;
        long ans = 1;
        for (int i = 1; i <= 11; i++) {
            ans *= now;
            now--;
            
            ans /= i;
        }
        System.out.println(ans);
    }
}
