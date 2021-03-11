import java.util.*;
import java.math.BigDecimal;

public class Main {
    static int geta = 1000000000;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = getaLong(sc.next(), geta);
        }
        
        // ans: 小数(第9位まで)同士の積が整数になるペアの数
        // how:
        // 10^9倍して整数で受け取った後、
        // 積が10^18の倍数になるペアを数える。
        // (積の素因数に、2が18個以上、5が18個以上あるペア)
        
        long[][] prime_2_5 = new long[19][19];
        for (int i = 0; i < n; i++) {
            long num = array[i];
            // System.out.println(num);
            int two = 0;
            while (num % 2 == 0) {
                num /= 2;
                two++;
            }
            two = Math.min(two, 18);
            int five = 0;
            while (num % 5 == 0) {
                num /= 5;
                five++;
            }
            five = Math.min(five, 18);
            
            prime_2_5[two][five]++;
            // System.out.println(two + " " + five);
        }
        for (long[] p : prime_2_5) {
            // System.out.println(Arrays.toString(p));
        }
        
        long res = 0;
        for (int i = 0; i <= 18; i++) {
        for (int j = 0; j <= 18; j++) {
            for (int k = 0; k <= 18; k++) {
            for (int l = 0; l <= 18; l++) {
                long tmp = res;
                if (i+k < 18 || j+l < 18) continue;
                if (i == k && j == l) {
                    res += prime_2_5[i][j] * (prime_2_5[i][j]-1);
                } else {
                    res += prime_2_5[i][j] * prime_2_5[k][l];
                }
                // System.out.println(i + " " + j + " " + k + " " + l + " " + res);
            }
            }
        }
        }
        System.out.println(res / 2);
    }
    
    // 小数strをgeta倍して整数longで返す
    // ex. (314.15, 10000) -> 31415000
    // (10.00, 10000) -> 100000
    public static long getaLong(String str, int geta_) {
        BigDecimal num = new BigDecimal(str);
        BigDecimal geta = new BigDecimal(geta_);
        
        return num.multiply(geta).longValue();
    }
}
