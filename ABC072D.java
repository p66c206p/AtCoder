import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            numbers[i] = sc.nextInt();
        }
        
        int count = 0;
        
        for (int i = 1; i <= n; i++) {
            if (numbers[i] == i) {
                count++;
                
                if (i == n) break;
                if (numbers[i + 1] == i + 1) {
                    i++;
                }
            }
        }
        System.out.println(count);
    }
}
