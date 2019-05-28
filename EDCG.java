import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(i);
        }
        
        while (m-- > 0) {
            int parent = sc.nextInt() - 1;
            int child = sc.nextInt() - 1;
            points[parent].addChild(points[child]);
        }
        
        int max = 0;
        for (int i = 0; i < n; i++) {
            int length = getLength(0, points[i]);
            max = Math.max(max, length);
        }
        
        System.out.println(max);
    }
    
    public static class Point {
        int name;
        List<Point> children;
        int generationLength = -1;
        
        Point(int name) {
            this.name = name;
            this.children = new ArrayList<Point>();
        } 
        
        void addChild(Point p) {
            this.children.add(p);
        }
    }
    
    public static int getLength(int depth, Point p) {
        int length = 0;
        
        boolean alreadyKnown = p.generationLength != -1;
        if (alreadyKnown) {
            return depth + p.generationLength;
        }
        
        if (p.children.isEmpty()) {
            length = depth;
        } else {
            for (Point child : p.children) {
                int l = getLength(depth + 1, child);
                length = Math.max(length, l);
            }
        }
        
        p.generationLength = length - depth;
        
        return length;
    }
}
