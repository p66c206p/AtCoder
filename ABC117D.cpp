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

int main(void){
    int m; cin >> m;
    ll x; cin >> x;
    vector<ll> v(m);
    rep(i, m) cin >> v[i];
    
    // s: to_binarystring(x)
    string s = "";
    while (x > 0) {
        string tmp = "";
        if (x % 2 == 1) tmp = "1";
        else tmp = "0";
        
        s = tmp + s;
        x /= 2;
    }
    int n = s.size();
    
    // ans:
    // 数がn個ある。任意のビットを選んで、全ての数に対して反転させられる。
    // うまく反転した時のn個の数の和の最大値は？
    // ただし、反転したビットの和がs以下でないといけない。
    // ex. s=7, {1, 3, 6} -> X=4, {1^4 + 3^4 + 6^4}, ans=14
    
    // how:
    // 桁DPする(2進数)。
    // (s以下の条件がない場合は'0'の方が多いビットは反転すれば良い)
    // dp: 左からi桁目まで見た時、どれだけ得するかのmax。
    // 遷移: 反転する、しないの2種類。
    // ans: 元々のsum + max(dp[n][0], dp[n][1])
    
    // one, zero: その桁の'1', '0'の個数
    vi one(n);
    vi zero(n);
    rep(i, m) {
        ll tmp = v[i];
        rep(j, n) {
            if (tmp % 2 == 1) one[j]++;
            else zero[j]++;
            
            tmp /= 2;
        }
    }
    // Arrays_toString(zero);
    // Arrays_toString(one);
    
    // dp[n][0] + dp[n][1] = n桁目まで見てうまく操作した時のmax
    vector<vector<ll>> dp(60, vector<ll>(2, -3e18));
    dp[0][0] = 0;
    rep(i, n) {
        rep(k, 2) {
            int nd = s[i] - '0';
            
            rep(d, 2) {
                // 未満未確定ならndより先は見れない
                if (k == 0 && d > nd) break;
                
                // i: 必ずi+1へ遷移
                int ni = i + 1, nk = k;
                
                // k: 指定の数未満が確定しているか否か(ex. 263....)
                // 遷移パターンは下記3通り
                // この度確定 = 0 -> 1  ex. 261....
                // 依然未確定 = 0 -> 0  ex. 263....
                // 既に確定   = 1 -> 1  ex. 182....
                if (k == 0 && d < nd) nk = 1;
                
                // plus:
                // 反転(d == 1)する場合は元々の和から、
                // 1立ってる分だけ減り、0の分だけ増える
                // 立てない場合は変化なし
                ll plus = 0;
                if (d == 1) {
                    plus -= one[n-1-i] * pow(2, n-1-i);
                    plus += zero[n-1-i] * pow(2, n-1-i);
                }
                
                dp[ni][nk] = max(dp[ni][nk], dp[i][k] + plus);
            }
        }
    }
    
    ll res = 0;
    rep(i, m) res += v[i];
    res += max(dp[n][0], dp[n][1]);
    print(res);
}
