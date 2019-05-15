import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);

        int cardCount = sc.nextInt();
        int[] cards = new int[cardCount];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = sc.nextInt();
        }
        
        Arrays.sort(cards);
        
        int alicesPoint = 0;
        int bobsPoint = 0;
        
        for (int i = cards.length - 1; i >= 0; i -= 2) {
            alicesPoint += cards[i];
            if (i == 0) break;
            bobsPoint += cards[i - 1];
        }

        System.out.println(alicesPoint - bobsPoint);
    }
}
