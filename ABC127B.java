import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        long r = sc.nextLong();
        long d = sc.nextLong();
        long x = sc.nextLong();
        
        for (int i = 0; i < 10; i++) {
            x = x * r - d;
            System.out.println(x);
        }
    }
}
