import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int cardCount = sc.nextInt();
        int gateCount = sc.nextInt();
        int min = -1;
        int max = 1000000;
        
        while (gateCount-- > 0) {
            int nowMin = sc.nextInt();
            int nowMax = sc.nextInt();
            min = Math.max(min, nowMin);
            max = Math.min(max, nowMax);
        }
        
        if (min > max) {
            System.out.println(0);
        } else {
            System.out.println(max - min + 1);
        }
        
    }
}
