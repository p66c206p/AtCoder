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
            ab[i][2] = String.valueOf(i + 1);
        }
        
        Arrays.sort(ab, (a, b) -> Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])));
        Arrays.sort(ab, (a, b) -> a[0].compareTo(b[0]));
        
        for (String[] a : ab) {
            System.out.println(a[2]);
        }
    }
}
