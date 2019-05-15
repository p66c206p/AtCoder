import java.util.*;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int count_0 = 0;
        int count_1 = 0;
        
        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);
            
            if (chr == '0') {
                count_0++;
            } else {
                count_1++;
            }
        }
        
        int answer = Math.min(count_0, count_1) * 2;
        System.out.println(answer);
    }
}
