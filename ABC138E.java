import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();
        
        char s[] = str1.toCharArray();
        char t[] = str2.toCharArray();
        int n = s.length;
        int m = t.length;
        
        // set: 英小文字の出現位置
        TreeSet[] set = new TreeSet[26];
        for (int i = 0; i < 26; i++) {
            set[i] = new TreeSet<Integer>();
        }
        
        // 2周分覚える
        // contestcontest -> set[c] = {0, 7}
        for (int i = 0; i < n; i++) {
            char c = s[i];
            set[c - 'a'].add(i);
        }
        for (int i = 0; i < n; i++) {
            char c = s[i];
            set[c - 'a'].add(i + n);
        }
        
        // ans: 文字列tの各文字の文字列sにおける出現位置を足したもの
        // ex. (s, t) = (contest, son) => ans = 6 + 3 + 1 = 10
        long ans = 0;
        int now = -1;   // startは-1文字目から
        for (int i = 0; i < m; i++) {
            char c = t[i];
            if (set[c - 'a'].size() == 0) {
                System.out.println(-1);
                return;
            }
            
            // 文字cのnow文字目以降で最も早い出現位置を代入
            // 進んだ距離を足す
            int next = (int)set[c - 'a'].higher(now);
            ans += (next - now);
            
            // 次の周に行く場合無駄にn多くなるので減算
            if (next >= n) {
                next -= n;
            }
            now = next;
        }
        System.out.println(ans);
    }
}
