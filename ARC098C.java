import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        String[] directions = new String[n];
        for (int i = 0; i < n; i++) {
            directions[i] = String.valueOf(str.charAt(i));
        }
        
        int[] WCount = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (directions[i - 1].equals("W")) {
                WCount[i] += WCount[i - 1] + 1;
            } else {
                WCount[i] += WCount[i - 1];
            }
        }
        
        int minCount = n + 1;
        for (int i = 1; i <= n; i++) {
            int leftWCount = WCount[i - 1];
            int rightECount = n - i - (WCount[n] - WCount[i]);
            int count = leftWCount + rightECount;
            minCount = Math.min(minCount, count);
        }
        
        System.out.println(minCount); 
    }
}
