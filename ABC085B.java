import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);

        int numberCount = sc.nextInt();
        Set<Integer> numbers = new HashSet<Integer>();
        for (int i = 0; i < numberCount; i++) {
            numbers.add(sc.nextInt());
        }

        System.out.println(numbers.size());
    }
}
