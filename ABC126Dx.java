import java.util.*;
import java.util.Map.*;

public class Main {
    private static int[] colors;
    private static Map<Integer, Integer>[] friendship;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = n - 1;
        
        colors = new int[n];
        Arrays.fill(colors, -1);
        
        friendship = new HashMap[n];
        Arrays.setAll(friendship, i -> new HashMap<Integer, Integer>());
        
        while (m-- > 0) {
            int me = sc.nextInt() - 1;
            int you = sc.nextInt() - 1;
            int length = sc.nextInt();
            friendship[me].put(you, length);
            friendship[you].put(me, length);
        }
        
        dfs(0, 0);
        
        for (int c : colors) {
            System.out.println(c);
        }
    }
    
    public static void dfs(int p, int length) {
        boolean alreadyKnown = colors[p] != -1;
        if (alreadyKnown) return;
        
        if (length % 2 == 0) { 
            colors[p] = 0;
        } else {
            colors[p] = 1;
        }
        
        for (Entry<Integer, Integer> entry : friendship[p].entrySet()) {
            int friend = entry.getKey();
            int length2 = entry.getValue();
            dfs(friend, length + length2);
        }
    } 
}
