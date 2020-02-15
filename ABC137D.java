import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 仕事の数
        int m = sc.nextInt();   // 締め切り
        
        // list[i]: i日後に報酬がもらえる各仕事の報酬
        List<Integer>[] list = new List[100101];
        for (int i = 0; i < 100101; i++) {
            list[i] = new ArrayList<Integer>();
        }
        
        // a日後にb円もらえる仕事をリストに入れる
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            list[a].add(b);
        }
        
        // que: 今できる仕事のキュー(報酬の大きい順に並んでいる)
        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
        
        long ans = 0;
        for (int i = 1; i <= m; i++) {
            // 残りi日でできる仕事の報酬bをキューに入れる
            for (Integer b : list[i]) {
                que.add(b);
            }
            
            // 今できる仕事のうち、最大の報酬の仕事を選ぶ
            if (!que.isEmpty()) {
                ans += que.poll();
            }
        }
        
        System.out.println(ans);
    }
}
