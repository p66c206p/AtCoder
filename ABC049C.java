import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        if (answer.matches("((dream)|(dreamer)|(erase)|(eraser))*")) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
