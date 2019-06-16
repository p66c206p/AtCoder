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
        
        long count = 0;
        for (int i = 0; i < n; i++) {
            int index = binarySearch(cumsum, k, i);
            if (index != -1) {
                count += n - index;
            }
        }
        
        System.out.println(count);
    }
    
    public static int binarySearch(long[] array, long target, int i) {
        int index = -1;
        int left = i;
        int right = array.length - 1;
        
        while (left < right) {
            int center = (left + right) / 2;
            // System.out.println(left + " " + center + " " + right);
            if (array[center] - array[i] >= target) {
                if (center - 1 == -1) break;
                if (array[center - 1] - array[i] < target) {
                    index = center - 1;
                    break;
                } else {
                    right = center;
                }
            } else {
                if (center + 1 == array.length) break;
                if (array[center + 1] - array[i] >= target) {
                    index = center;
                    break;
                } else {
                    left = center + 1;
                }
            }
        }
        
        return index;
    }
}
