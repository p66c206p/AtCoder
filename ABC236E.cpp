#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;
typedef vector<ll> vll;
typedef vector<vi> vvi;
typedef pair<int, int> P;

#define rep(i, n) for (int i = 0; i < (int)(n); i++)
#define print(x) cout << x << endl
#define all(v) (v).begin(),(v).end()
#define YN(ok) print((ok ? "YES" : "NO"))
#define yn(ok) print((ok ? "Yes" : "No"))
#define decimal(n) setprecision(25) << n

#define Arrays_toString(v) rep(o,(v).size()){cout<<v[o]<<", ";if(o==(v).size()-1){cout<<endl;}}

int geta = 1e3;

ll binarysearch(vi v) {
    int n = v.size();
    
    // めぐる式二分探索
    // xxxxxooooooのxとoの境界(ok)を返す
    // <- ooooooxxxxならok < ngにする
    ll ok = 0;
    ll ng = 2e9*geta;
    
    while (abs(ok - ng) > 1) {
        ll mid = (ok + ng) / 2;
        
        // okがどうかの判定
        bool isOK = true;
        vll dp1(n+1), dp2(n+1);
        rep(i, n) {
            dp1[i+1] = max(dp1[i], dp2[i]) + (ll)v[i]*geta - mid;
            dp2[i+1] = dp1[i];
        }
        ll sum = max(dp1[n], dp2[n]);
        isOK = (sum >= 0);
        
        if (isOK) {
            ok = mid;
        } else {
            ng = mid;
        }
    }
    
    // ngに最も近いokを出力
    return ok;
}


ll binarysearch2(vi v) {
    int n = v.size();
    
    // めぐる式二分探索
    // xxxxxooooooのxとoの境界(ok)を返す
    // <- ooooooxxxxならok < ngにする
    ll ok = 0;
    ll ng = 2e9;
    
    while (abs(ok - ng) > 1) {
        ll mid = (ok + ng) / 2;
        
        // okがどうかの判定
        bool isOK = true;
        vi tmp(n);
        rep(i, n) {
            if (v[i] >= mid) tmp[i] = 1;
            else tmp[i] = -1;
        }
        vll dp1(n+1), dp2(n+1);
        rep(i, n) {
            dp1[i+1] = max(dp1[i], dp2[i]) + tmp[i];
            dp2[i+1] = dp1[i];
        }
        ll sum = max(dp1[n], dp2[n]);
        isOK = (sum > 0);
        // print(mid << " " << sum);
        
        if (isOK) {
            ok = mid;
        } else {
            ng = mid;
        }
    }
    
    // ngに最も近いokを出力
    return ok;
}

int main(void){
    int n; cin >> n;
    vi v(n);
    rep(i, n) cin >> v[i];
    
    double max_ave = (double)binarysearch(v) / geta;
    print(decimal(max_ave));
    
    ll max_med = binarysearch2(v);
    print(max_med);
}
