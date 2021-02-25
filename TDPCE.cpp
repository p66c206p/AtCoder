#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef pair<int, int> P;

#define rep(i, n) for (int i = 0; i < (int)(n); i++)
#define print(x) cout << x << endl
#define all(v) (v).begin(),(v).end()
#define decimal(n) setprecision(25) << n

#define Arrays_toString(v) rep(o,(v).size()){cout<<v[o]<<", ";if(o==(v).size()-1){cout<<endl;}}

#include <atcoder/all>
using namespace atcoder;
// using mint = modint998244353;
using mint = modint1000000007;

mint dp[11000][110][2];

int main(void){
    int x; cin >> x;
    string s; cin >> s;
    int n = s.size();
    
    dp[0][0][0] = 1;
    rep(i, n) {
        rep(j, x) {
            rep(k, 2) {
                int nd = s[i] - '0';
                
                rep(d, 10) {
                    // 未満未確定ならndより先は見れない
                    if (k == 0 && d > nd) break;
                    
                    // i: 必ずi+1へ遷移
                    int ni = i + 1, nj = j, nk = k;
                    
                    // j: 今までの各桁の和(mod x)
                    // -> dを加算してmod x
                    nj = (j+d) % x;
                    
                    // k: 指定の数未満が確定しているか否か(ex. 263....)
                    // 遷移パターンは下記3通り
                    // この度確定 = 0 -> 1  ex. 261....
                    // 依然未確定 = 0 -> 0  ex. 263....
                    // 既に確定   = 1 -> 1  ex. 182....
                    if (k == 0 && d < nd) nk = 1;
                    
                    dp[ni][nj][nk] += dp[i][j][k];
                    
                }
            }
        }
    }
    
    // 1以上の数が対象なので0は除外
    mint res = dp[n][0][0] + dp[n][0][1] - 1;
    print(res.val());
}
