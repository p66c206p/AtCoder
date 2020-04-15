import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }
        
        int[] gcdLeft = new int[n];
        int[] gcdRight = new int[n];
        gcdLeft[0] = numbers[0];
        gcdRight[n - 1] = numbers[n - 1];
        
        for (int i = 1; i < n; i++) {
            gcdLeft[i] = gcd(gcdLeft[i - 1], numbers[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            gcdRight[i] = gcd(gcdRight[i + 1], numbers[i]);
        }
        
        int max = Math.max(gcdLeft[n - 2], gcdRight[1]);
        for (int i = 1; i < n - 1; i++) {
            max = Math.max(max, gcd(gcdLeft[i - 1], gcdRight[i + 1]));
        }
        
        System.out.println(max);
    }
    
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        
        return gcd(b, a % b);
    }
} 
