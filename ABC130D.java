import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        long[] cumsum = new long[n + 1];
        
        for (int i = 1; i <= n; i++) {
            cumsum[i] = cumsum[i - 1] + sc.nextLong();
        }
        
        long count = solve(cumsum, k);
        
        System.out.println(count);
    }
    
    public static long solve(long[] array, long k) {
        long count = 0;
        int n = array.length - 1;
        long target = k;
        
        for (int i = 0; i < n; i++) {
            int left = i;
            int right = n;
            
            while (left < right) {
                int center = (left + right) / 2;
                
                // System.out.println(left + " " + center + " " + right);
                
                if (array[center] - array[i] >= target) {
                    if (center - 1 == -1) break;
                    if (array[center - 1] - array[i] < target) {
                        count += n - (center - 1);
                        break;
                    } else {
                        right = center;
                    }
                } else {
                    if (center + 1 == n + 1) break;
                    if (array[center + 1] - array[i] >= target) {
                        count += n - center;
                        break;
                    } else {
                        left = center + 1;
                    }
                }
            }            
        }
        
        return count;
    }
}
