import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        String str = sc.next();
        String[] strAry = new String[n];
        for (int i = 0; i < n; i++) {
            strAry[i] = String.valueOf(str.charAt(i));
        }
        
        int[] cumsum = new int[n + 1];
        for (int i = 1; i < n; i++) {
            if (strAry[i - 1].equals("A") && strAry[i].equals("C")) {
                cumsum[i + 1] = cumsum[i] + 1;
            } else {
                cumsum[i + 1] = cumsum[i];
            }
        }
        
        while (q-- > 0) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int count = cumsum[end] - cumsum[start];
            
            System.out.println(count);
        }
    }
}
