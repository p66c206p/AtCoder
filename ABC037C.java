import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[] cumsum = new long[n + 1];
        
        for (int i = 1; i <= n; i++) {
            cumsum[i] = cumsum[i - 1] + sc.nextLong();
        }
        
        long sum = 0;
        for (int i = k; i <= n; i++) {
            sum += cumsum[i] - cumsum[i - k];
        }
        
        System.out.println(sum);
    }
}
