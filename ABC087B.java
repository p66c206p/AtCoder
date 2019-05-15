import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);
        int count = 0;
        int count500yen = sc.nextInt();
        int count100yen = sc.nextInt();
        int count50yen = sc.nextInt();
        int answerAmount = sc.nextInt();
        
        for (int i = 0; i <= count500yen; i++) {
            for (int j = 0; j <= count100yen; j++) {
                for (int k = 0; k <= count50yen; k++) {
                    int amount = 500 * i + 100 * j + 50 * k;
                    if (amount == answerAmount) count++;
                }
            }
        }

        System.out.println(count);
    }
}
