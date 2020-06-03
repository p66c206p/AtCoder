import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        
        int max = 1000000;
        boolean[] isPrime = new boolean[max+1];
        Arrays.fill(isPrime, true);
        for (int i = 2*2; i <= max; i += 2) {
            isPrime[i] = false;
        }
        for (int i = 3; i <= max; i += 2) {
            if (isPrime[i]) {
                for (int j = i*2; j <= max; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        System.out.println(isPrime[2019]);
    }
}
