import java.util.*;


public class Main {
    private static String queue = "";
    private static boolean finished = false;
    
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);
        int row = 2;
        int col = sc.nextInt();
        int[][] candies = new int[row][col];
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                candies[x][y] = sc.nextInt();
            }
        }
        
        int maxCount = 0;
        
        for (int i = 0; i < col; i++) {
            int count = 0;
            int x = 0;
            for (int y = 0; y < col; y++) {
                count += candies[x][y];
                if (i == y) {
                    count += candies[++x][y];
                }
            }
            
            maxCount = Math.max(maxCount, count);
        }
        
        System.out.println(maxCount);
    }
}
