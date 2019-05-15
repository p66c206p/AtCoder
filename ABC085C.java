import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);

        int billCount = sc.nextInt();
        int answerAmount = sc.nextInt();
        
        for (int i = 0; i <= billCount; i++) {
            for (int j = 0; j <= billCount - i; j++) {
                int k = billCount - i - j;
                int amount = 10000 * i + 5000 * j + 1000 * k;
                if (amount == answerAmount) {
                    System.out.println(i + " " + j + " " + k);
                    return;
                }
            }
        }
        
        System.out.println(-1 + " " + -1 + " " + -1);
    }
}
