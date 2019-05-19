import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int a = Integer.valueOf(str.substring(0,2));
        int b = Integer.valueOf(str.substring(2,4));
        
        if (1 <= a && a <= 12 && 1 <= b && b <= 12) {
            System.out.println("AMBIGUOUS");
        } else if (0 <= a && a <= 99 && 1 <= b && b <= 12) {
            System.out.println("YYMM");
        } else if (0 <= b && b <= 99 && 1 <= a && a <= 12) {
            System.out.println("MMYY");
        } else {
            System.out.println("NA");
        }
    }
}
