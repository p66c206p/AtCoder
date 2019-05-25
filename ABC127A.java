import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int age = sc.nextInt();
        int price = sc.nextInt();
        
        if (age >= 13) {
            System.out.println(price);
        } else if (age <= 5) {
            System.out.println(0);
        } else {
            System.out.println(price / 2);
        }
    }
}
