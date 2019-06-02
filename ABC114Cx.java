import java.util.*;

public class Main {
    static int max;
    static int count = 0;
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        max = sc.nextInt();
        
        dfs(0);
        
        System.out.println(count);
    }
    
    public static void dfs(long x) {
        if (x > max) return;
        
        if (pass(x)) count++;
        
        dfs(10 * x + 7);
        dfs(10 * x + 5);
        dfs(10 * x + 3);
    }
    public static boolean pass(long x) {
        String str = String.valueOf(x);
        if (str.contains("7") && str.contains("5") && str.contains("3")) return true;
        return false;
    }
}
