import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        double sumKakuritsu = 0;
        for (int i = 1; i <= n; i++) {
            if (i >= k) {
                sumKakuritsu += (double)1 / n;
                continue;
            }
            int num = i;
            int jou = 0;
            while (num < k) {
                num *= 2;
                jou++;
            }
            
            sumKakuritsu += ((double)1 / n) * Math.pow(0.5,jou);
        }
        
        System.out.println(sumKakuritsu);
    }
}
