import java.util.*;

public class Main {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    
    int a = sc.nextInt();
    int b = sc.nextInt();
    
    if (isOdd(a, b)) {
    	System.out.println("Odd");
    } else {
      System.out.println("Even");
    }
  }
  
  public static boolean isOdd(int a, int b) {
  	if ((a * b) % 2 == 1) {
    	return true;
    } else {
      return false;
  	}
  }
}
