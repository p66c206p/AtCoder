import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int startA = sc.nextInt();
        int startB = sc.nextInt();
        int goalA = sc.nextInt();
        int goalB = sc.nextInt();
        String str = sc.next();
        char[] array = new char[n + 1];
        for (int i = 0; i < n; i++) {
            array[i + 1] = str.charAt(i);
        }
        
        String str2 = str.substring(startB - 2, Math.min(n, goalB + 1));
        
        if (str2.contains("...")) {
            if (goalB < goalA) {
                int tmp = startA;
                startA = startB;
                startB = tmp;
            }
        } 
        
        int aIndex = startA;
        int bIndex = startB;
        boolean clear = true;
        boolean stopA = false;
        boolean stopB = false;
        boolean aGoaled = false;
        boolean bGoaled = false;
        
        while (true) {
            // bが動く
            if (!bGoaled) {
                if (bIndex + 2 == goalB || bIndex + 1 == goalB) {
                    bIndex = goalB;
                    bGoaled = true;
                    stopB = true;
                } else if (array[bIndex + 2] == '.' && bIndex + 2 != aIndex) {
                    bIndex += 2;
                    if (!aGoaled) stopA = false;
                } else if (array[bIndex + 1] == '.' && bIndex + 1 != aIndex) {
                    bIndex += 1;
                    if (!aGoaled) stopA = false;
                } else {
                    stopB = true;
                }            
            }

            
            // System.out.println(aIndex + " " + bIndex);
            
            // aが動く
            if (!aGoaled) {
                if (aIndex + 2 == goalA || aIndex + 1 == goalA) {
                    aIndex = goalA;
                    aGoaled = true;
                    stopA = true;
                } else if (array[aIndex + 2] == '.' && aIndex + 2 != bIndex) {
                    aIndex += 2;
                    if (!bGoaled) stopB = false;
                } else if (array[aIndex + 1] == '.' && aIndex + 1 != bIndex) {
                    aIndex += 1;
                    if (!bGoaled) stopB = false;
                } else {
                    stopA = true;
                }            
            }
            
            // System.out.println(aIndex + " " + bIndex);
            
            if (aGoaled && bGoaled) {
                System.out.println("Yes");
                break;
            }
            
            if (stopA && stopB) {
                System.out.println("No");
                break;
            }
        }
    }
}
