import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] numbers = new long[n];
        int count2xButNot4x = 0;
        int count4x = 0;
        for (int i = 0; i < n; i++) {
            long num = sc.nextLong();
            if (num % 4 == 0) {
                count4x++;
            } else {
                if (num % 2 == 0) {
                    count2xButNot4x++;
                }
            }
            numbers[i] = num;
        }
        
        boolean pass = false;
        if (count2xButNot4x < 2) {
            if (count4x >= n / 2) {
                pass = true;
            }
        } else {
            if (count4x >= (n - count2xButNot4x + 1) / 2) {
                pass = true;
            }
        }
        
        if (pass) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
