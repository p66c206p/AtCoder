import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);
        int priceA = sc.nextInt();
        int priceB = sc.nextInt();
        int priceAB = sc.nextInt();
        int needACount = sc.nextInt();
        int needBCount = sc.nextInt();
        
        int countOfTheMore = Math.max(needACount, needBCount);
        int countOfTheLess = Math.min(needACount, needBCount);
        int priceOfCountOfTheMore = 0;
        if (countOfTheLess == needACount) {
            priceOfCountOfTheMore = priceB;
        } else {
            priceOfCountOfTheMore = priceA;
        }
        
        int pricePlanA = priceA * needACount + priceB * needBCount;
        int pricePlanB = priceAB * 2 * countOfTheMore;
        int pricePlanC = priceAB * 2 * countOfTheLess + priceOfCountOfTheMore * (countOfTheMore - countOfTheLess);
        int answer = 0;
        if (pricePlanA < pricePlanB) {
            answer = pricePlanA;
        } else {
            answer = pricePlanB;
        }
        
        if (pricePlanC < answer) {
            answer = pricePlanC;
        }
        
        System.out.println(answer);
    }
}
