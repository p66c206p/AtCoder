import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int jumpLength = sc.nextInt();
        int[] highs = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            highs[i] = sc.nextInt();
        }
        int[] leastCostAt = new int[n + 1];
        leastCostAt[1] = 0;
        leastCostAt[2] = Math.abs(highs[2] - highs[1]);
        for (int i = 3; i <= n; i++) {
            int[] costOfPlan = new int[jumpLength];
            Arrays.fill(costOfPlan,99999999);
            for (int j = 0; j < jumpLength; j++) {
                if (i - 1 - j == 0) break;
                costOfPlan[j] = leastCostAt[i - 1 - j] + Math.abs(highs[i] - highs[i - 1 - j]);
            }
            
            Arrays.sort(costOfPlan);
            leastCostAt[i] = costOfPlan[0];
        }
        System.out.println(leastCostAt[n]);
    }
}
