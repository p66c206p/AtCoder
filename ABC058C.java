import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] c = new int[n][26];
        
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < str.length(); j++) {
                char chr = str.charAt(j);
                int toInt = (int)chr - 97;
                c[i][toInt]++;
            }
        }
        
        for (int i = 0; i < 26; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                min = Math.min(min, c[j][i]);
            }
            
            for (int k = 0; k < min; k++) {
                char toAlphabet = (char)(i + 97);
                System.out.print(toAlphabet);
            }
        }
        
        System.out.println();
    }
}
