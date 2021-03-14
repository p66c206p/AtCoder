#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef pair<int, int> P;

#define rep(i, n) for (int i = 0; i < (int)(n); i++)
#define print(x) cout << x << endl
#define all(v) (v).begin(),(v).end()
#define YN(ok) print((ok ? "YES" : "NO"))
#define yn(ok) print((ok ? "Yes" : "No"))
#define decimal(n) setprecision(25) << n

#define Arrays_toString(v) rep(o,(v).size()){cout<<v[o]<<", ";if(o==(v).size()-1){cout<<endl;}}

vi primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71};

int main(void){
    ll l, r; cin >> l >> r;
    int n = r-l+1;
    int m = primes.size();
    
    // ans: 区間[L, R]の部分集合の中で、要素同士が互いに素であるパターン数
    // (ただし、区間の長さは最大72)
    
    // how:
    // 1. 要素同士が互いに素な集合 = ある素因数を持つ要素は1個以下。
    // 2. 対象の素数は72以下だけでOK。 (72以下の素数は20個)
    // (ex. 79を素因数に持ってる要素は必ず1個以下)
    // (5の倍数は高々72/5の切り上げ個あるけど、79の倍数は高々72/79の切り上げ個(=1個))
    
    // bitDP:
    // 何も持たない, 2だけ, 3だけ, 2と3だけ, 5だけ, ...のように
    // 部分集合を分類しbit集合で管理する。
    // 遷移:
    // ex. i番目が14の場合、
    // 「2」や「7」を含まないなら、「2」「7」を済にした集合に行ける
    // 含むなら、遷移できない (互いに素でなくなるから)
    
    // dp[i][j]: i個目まで見た時の条件を満たす集合jのパターン数
    vector<vector<ll>> dp(n+1, vector<ll>(1<<m));
    dp[0][0] = 1;
    rep(i, n) {
        ll num = l+i;
        
        // s: numが持つ素因数の番号の集合
        ll s = 0;
        rep(j, m) {
            if (num % primes[j] == 0) {
                s |= (1<<j);
            }
        }
        rep(j, (1<<m)) {
            if ((j&s) != 0) continue;
            dp[i+1][j | s] += dp[i][j];
        }
        
        rep(j, (1<<m)) {
            dp[i+1][j] += dp[i][j];
        }
    }
    
    ll res = 0;
    rep(i, (1<<m)) res += dp[n][i];
    print(res);
}
