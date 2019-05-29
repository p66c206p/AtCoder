import java.util.*;
import java.util.Map.*;

public class Main {
    private static int[] colors;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = n - 1;
        colors = new int[n];
        Arrays.fill(colors, -1);
        
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(i);
        }
        
        while (m-- > 0) {
            int me = sc.nextInt() - 1;
            int you = sc.nextInt() - 1;
            int length = sc.nextInt();
            points[me].becomeFriend(points[you], length);
            points[you].becomeFriend(points[me], length);
        }
        
        dfs(points[0], 0);
        
        for (int c : colors) {
            System.out.println(c);
        }
    }
    
    public static void dfs(Point p, int length) {
        boolean alreadyKnown = colors[p.name] != -1;
        if (alreadyKnown) return;
        
        if (length % 2 == 0) {
            colors[p.name] = 0;
        } else {
            colors[p.name] = 1;
        }
        
        for (Entry<Point, Integer> entry : p.friends.entrySet()) {
            Point friend = entry.getKey();
            int length2 = entry.getValue();
            dfs(friend, length + length2);
        }
    }
    
    public static class Point {
        int name;
        Map<Point, Integer> friends;
         
        Point(int name) {
            this.name = name;
            this.friends = new HashMap<Point, Integer>();
        }
        
        void becomeFriend(Point p, int length) {
            this.friends.put(p, length);
        }
    }
} 
