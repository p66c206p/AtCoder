import java.util.*;

public class Main {
    static int n;
    static int[][] inout;
    static int[] getid;
    static int[] type;
    
    static int in = 0;
    static int out = 1;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        // ans: どの区間も条件を満たすような配列の区切り方が存在するか？
        // 条件:
        // 区間[L, R]で乗り降りした人を並べると[14821482]のようになる
        // (前半の階は乗、後半の階は降、乗った順に人が降りていく)
        
        // inout[i]: 人iはin階で乗り、out階で降りたい
        inout = new int[n][2];
        for (int i = 0; i < n; i++) {
            inout[i][in] = sc.nextInt();
            inout[i][out] = sc.nextInt();
        }
        
        // getid[f]: f階で乗り降りする人のid
        getid = new int[2*n+1];
        Arrays.fill(getid, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                int floor = inout[i][j];
                if (floor == -1) continue;
                
                if (getid[floor] == -1) {
                    getid[floor] = i;
                } else {
                    System.out.println("No");
                    return;
                }
            }
        }
        
        // type[f]: -1:決まってない、0:in、1:out
        type = new int[2*n+1];
        Arrays.fill(type, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                int floor = inout[i][j];
                if (floor == -1) continue;
                
                if (j == in) {
                    type[floor] = in;
                } else {
                    type[floor] = out;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (inout[i][in] != -1 && inout[i][out] != -1) {
                if (inout[i][in] > inout[i][out]) {
                    System.out.println("No");
                    return;
                }
            }
        }
        
        // dp[i]: 配列(0～i)は条件を満たす区切り方がある/ない
        boolean[] dp = new boolean[2*n+1];
        // 初期化
        dp[0] = true;
        for (int i = 0; i < 2*n; i++) {
            for (int j = i+1; j <= 2*n; j++) {
                // 0～iがいい感じに区切れて、かつ、i～jもそうならば
                // 0～jをいい感じに区切れる
                if (dp[i] && isOK(i, j)) {
                    dp[j] = true;
                }
            }
        }
        // System.out.println(Arrays.toString(dp));
        
        String ans = "";
        if (dp[2*n]) {
            ans = "Yes";
        } else {
            ans = "No";
        }
        System.out.println(ans);
    }
    
    public static boolean isOK(int l, int r) {
        boolean ok = true;
        l++;    // l=0 r=4 -> 1階から4階
        
        // 長さが奇数なら無理
        if ((r-l) % 2 == 0) ok = false;
        
        // 「前半の階は乗」
        for (int i = l; i < (l+r)/2 + 1; i++) {
            if (type[i] == out) ok = false;
        }
        
        // 「後半の階は降」
        for (int i = (l+r)/2 + 1; i <= r; i++) {
            if (type[i] == in) ok = false;
        }
        
        // 「乗った順に人が降りていく」
        int k = ((r+1)-l)/2;
        for (int i = l; i < (l+r)/2 + 1; i++) {
            if (getid[i] == getid[i+k]) {
                
            } else if (getid[i] != -1 && getid[i+k] == -1) {
                int id = getid[i];
                int floor = inout[id][out];
                if (floor != -1) ok = false;
            } else if (getid[i] == -1 && getid[i+k] != -1) {
                int id = getid[i+k];
                int floor = inout[id][in];
                if (floor != -1) ok = false;
            } else {
                ok = false;
            }
        }
        
        return ok;
    }
}
