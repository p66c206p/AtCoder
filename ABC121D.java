import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        long start = sc.nextLong();
        long end = sc.nextLong();
        long num;
        if (end - start == 1) {
            num = start ^ end;
        } else if (end - start == 0) {
            num = start;
        } else {
            num = 0;
            for (long i = start - (start % 4) + 3; i >= start; i--) {
                num ^= i;
            }
            for (long i = end - (end % 4); i <= end; i++) {
                num ^= i;
            }
        }
        
        System.out.println(num);
    }
}
