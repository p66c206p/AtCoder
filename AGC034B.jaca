import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int n = str.length();
        char[] s = new char[n];
        for (int i = 0; i < n; i++) {
            s[i] = str.charAt(i);
        }
        
        long curACount = 0;
        long AJumpedCount = 0;
        
        for (int i = 0; i < n; i++) {
            boolean meetsA = s[i] == 'A';
            boolean canJump = i + 1 < n && s[i] == 'B' && s[i + 1] == 'C';
            
            if (meetsA) {
                curACount++;
            } else if (canJump) {
                AJumpedCount += curACount;
                i++;
            } else {
                curACount = 0;
            }
        }
        
        System.out.println(AJumpedCount);
    }
}
