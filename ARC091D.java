import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long count = 0;
        
        if (k == 0) {
            count = (long)n * n;
        } else {
            for (int i = k + 1; i <= n; i++) {
                count += (long)(n / i) * (i - k);
                if (n % i >= k) {
                    count += (long)n % i - (k - 1);
                }
            }
        }
        
        System.out.println(count);
    } 
}
