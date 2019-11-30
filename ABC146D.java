import java.util.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Point[] point = new Point[n];
        for (int i = 0; i < n; i++) {
            point[i] = new Point();
        }
        int[] index = new int[n - 1];
        
        // 自分に子を割り当てる
        for (int i = 0; i < n - 1; i++) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            index[i] = q;   // 出力順を記憶する
            
            point[p].child.add(q);
        }
        
        // 自分の子を若い色から塗る
        for (int i = 0; i < n; i++) {
            int nowColor = 1;
            int myColor = 0;
            if (i != 0) {
                myColor = point[i].color;
            }
            
            for (Integer children : point[i].child) {
                if (nowColor == myColor) {
                    nowColor++;
                }
                
                point[children].color = nowColor;
                nowColor++;
            }
        }
        
        // 必要な最小色数を出力
        int max = 0;
        for (Point p : point) {
            max = Math.max(max, p.color);
        }
        System.out.println(max);
        
        // 子の色を出力順に出力
        for (int i = 0; i < n - 1; i++) {
            System.out.println(point[index[i]].color);
        }
        
    }
    
    public static class Point {
        int color;
        List<Integer> child;
        
        Point() {
            this.child = new ArrayList<Integer>();
        }
    }
}
