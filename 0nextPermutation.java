import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = 4;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
        
        // 順列全探索
        do {
            for (int i = 0; i < n; i++) {
                System.out.print(array[i]);
                System.out.print(" ");
            }
            
            System.out.println();
        } while (nextPermutation(array));
        
    }
    
    public static boolean nextPermutation(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i - 1] < array[i]) {
                int j = find(array, array[i - 1], i, array.length - 1);
                int temp = array[j];
                array[j] = array[i - 1];
                array[i - 1] = temp;
                Arrays.sort(array, i, array.length);
                return true;
            }
        }
        return false;
    }
	 
    private static int find(int[] array, int dest, int f, int l) {
        if (f == l) return f;
        
        int m = (f + l + 1) / 2;
        return (array[m] <= dest) ? find(array, dest, f, m - 1) : find(array, dest, m, l);
    }
}
