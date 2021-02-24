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
#define decimal(n) setprecision(30) << n

#define Arrays_toString(v) rep(o,(v).size()){cout<<v[o]<<", ";if(o==(v).size()-1){cout<<endl;}}
template<class T> void que_toString(queue<T> q){while(q.size()){cout<<q.front()<<", ";q.pop();if(q.empty()){cout<<endl;}}}

int main(void){
    int n, m, k;
    cin >> n >> m >> k;
    vi v(k);
    rep(i, k) cin >> v[i];
    
    // ans:
    // マス0 → Nまですごろくする時、サイコロを振る回数の期待値は？
    // (サイコロの目は1～Mで等確率)
    // (「ふりだしに戻る」マスもある)
    
    // how:
    // 期待値DPする。
    // (点0から点Nに行くのに貼る辺の本数の期待値は？と同値)
    // 例えば6まで出るならiの期待値は1/6 * (i+1, i+2, ... i+6) + 1
    
    // DAGでない(ループがある)DPなので、dp[地雷] = dp[0] = xとして進める。
    // dp[i] = ax + bの形でデータを持つ。(a = dp[i][0], b = dp[i][1])
    // -> dp[0] = a * x(=dp[0]) + b
    // -> dp[0] = b / (1 - a)
    
    // dp[i]: マスiからゴールに行くまでに振る回数の期待値
    vector<vector<double>> dp(n+1+m, vector<double>(2));
    for (int vv : v) {
        dp[vv][0] = 1;
        dp[vv][1] = 0;
    }
    
    // sum: 区間長mのdpのsum
    vector<double> sum(2);
    for (int i = n+m-1; i >= n; i--) {
        sum[0] += dp[i][0];
        sum[1] += dp[i][1];
    }
    
    for (int i = n-1; i >= 0; i--) {
        if (dp[i][0] != 1) {
            dp[i][0] = sum[0] / m;
            dp[i][1] = sum[1] / m + 1;
        }
        
        // update sum
        sum[0] -= dp[i+m][0];
        sum[1] -= dp[i+m][1];
        sum[0] += dp[i][0];
        sum[1] += dp[i][1];
    }
    // for (auto dpi : dp) Arrays_toString(dpi);
    
    // x = dp[0][0] * x + dp[0][1]
    // -> x = dp[0][1] / (1 - dp[0][0])
    double res = -1; 
    if (dp[0][0] != 1) res = dp[0][1] / (1 - dp[0][0]);
    if (res > 1e12) res = -1;
    print(decimal(res));
}
