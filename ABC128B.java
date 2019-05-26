import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[][] ab = new String[n][3];
        for (int i = 0; i < n; i++) {
            ab[i][0] = sc.next();
            ab[i][1] = sc.next();
            ab[i][2] = String.valueOf(i);
        }
        
        int count = 0;
        while (count < n) {
            String name = "zzzzzzzzzzzz";
            int score = 0;
            int idx = 0;
            for (int i = 0; i < n; i++) {
                if (ab[i][0].compareTo(name) < 0) {
                    name = ab[i][0];
                    score = Integer.valueOf(ab[i][1]);
                    idx = i + 1;
                } else if (ab[i][0].compareTo(name) == 0) {
                    if (Integer.valueOf(ab[i][1]) > score) {
                        score = Integer.valueOf(ab[i][1]);
                        idx = i + 1;
                    }
                }
            }
            
            System.out.println(idx);
            count++;
            ab[idx - 1][0] = "zzzzzzzzzzzz";
        }
    }
}
