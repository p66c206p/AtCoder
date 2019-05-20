import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] highs = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            highs[i] = sc.nextInt();
        }
        int[] leastCostAt = new int[n + 1];
        leastCostAt[1] = 0;
        leastCostAt[2] = Math.abs(highs[2] - highs[1]);
        for (int i = 3; i <= n; i++) {
            int planACost = leastCostAt[i - 1] + Math.abs(highs[i] - highs[i - 1]);
            int planBCost = leastCostAt[i - 2] + Math.abs(highs[i] - highs[i - 2]);
            if (planACost < planBCost) {
                leastCostAt[i] = planACost;
            } else {
                leastCostAt[i] = planBCost;
            }
        }
        System.out.println(leastCostAt[n]);
    }
}
