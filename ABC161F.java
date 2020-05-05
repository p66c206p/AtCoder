import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        
        // ans: nをkで割れるなら割り、そうでないならkを引く...をした数が1になるkの数
        // (2 <= k <= n)
        long ans = 0;
        
        // ex. n=12
        // 12: 12/12 = 1 OK
        // 11: 12-11 = 1 OK
        // 10: 12-10 = 2
        // 9: 12-9 = 3
        // 8: 12-8 = 4
        // 7: 12-7 = 5
        // 6: 12/6 = 2
        // 5: 12-5-5 = 2
        // 4: 12/4 = 3
        // 3: 12/3 -3 = 1 OK
        // 2: 12/2/2 -2 = 1 OK
        
        // -> 割り切って、その後は引くだけ
        // (「割れる」->「mod kが0」であり、割るとmod kの値は変わる)
        // (mod kが0でなくなったら割れない、引くしかない)
        // (引いてもmod kの値は変わらないので「引く後に割る」は起きない)
        
        // 全てなめるとO(10^12) -> 分けて考える。
        
        // 1.「/」もして「=1」になる
        // -> 「/」し切った値 (mod k) = 1ならOK
        
        // 2.「-」のみで「=1」になる
        // -> n (mod k) = 1
        // -> n-1 ≡ 0 (mod k) 
        // -> n-1の約数はOK(1はNG) (ex. n = 11 -> 11 (mod 2,5,10)は1)
        
        // list1: nの約数リスト
        // list2: n-1の約数リスト
        List<Long> list1 = divisorList(n);
        List<Long> list2 = divisorList(n - 1);
        
        // 上のように条件を満たすものをansに加算
        for (Long num : list1) {
            if (num == 1) continue;
            long tmp = n;
            while (tmp % num == 0) {
                tmp /= num;
            }
            if (tmp % num == 1) ans++;
        }
        ans += (list2.size()-1);
        System.out.println(ans);
        
    }
    
    public static List<Long> divisorList(long n) {
        List<Long> res = new ArrayList<Long>();
        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
                
                long j = n / i;
                if (j != i) {
                    res.add(j);
                }
            }
        }
        res.sort(Comparator.naturalOrder());
        
        return res;
    }
}
