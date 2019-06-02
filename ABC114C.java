import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int max = sc.nextInt();
        
        int keta = String.valueOf(max).length();
        
        Set<Integer> set = new TreeSet<Integer>();
        set.add(7);
        set.add(5);
        set.add(3);
        
        for (int i = 0; i < keta - 1; i++) {
            Set<Integer> set2 = new TreeSet<Integer>();
            for (Integer number : set) {
                String str = String.valueOf(number);
                set2.add(Integer.parseInt(str + "7"));
                set2.add(Integer.parseInt(str + "5"));
                set2.add(Integer.parseInt(str + "3"));
            }
            for (Integer number : set2) {
                set.add(number);
            }
        }
        
        int count = 0;
        for (Integer number : set) {
            if (number > max) break;
            
            String str = String.valueOf(number);
            if (str.contains("7") && str.contains("5") && str.contains("3")) count++;
        }
        
        System.out.println(count);
    }
}
