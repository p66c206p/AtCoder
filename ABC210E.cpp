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

ll INF = 7e18;

int main(void){
    int h, w; cin >> h >> w;
    ll c; cin >> c;
    vector<vll> v(h, vll(w));
    rep(i, h)rep(j, w) cin >> v[i][j];
    
    // v[x1][y1]+v[x2][y2]+c*((x2-x1) + (y2-y1))
    // v[x2][y2]+c*(x2+y2) + v[x1][y1]-c*(x1+y1);
    
    ll res = INF;
    rep(_, 2) {
        // minv: 二次元累積min
        vector<vll> minv(h, vll(w));
        rep(i, h)rep(j, w) {
            ll tmp = v[i][j] - c *(i+j);
            if (i-1 >= 0) tmp = min(tmp, minv[i-1][j]);
            if (j-1 >= 0) tmp = min(tmp, minv[i][j-1]);
            minv[i][j] = tmp;
        }
        // rep(i, h) Arrays_toString(minv[i]);
        
        // 「(i, j)」「i, jより左上のマス」を探索する
        // 後者は累積minでO(1)で求める
        rep(i, h)rep(j, w) {
            if (i == 0 && j == 0) continue;
            
            ll one = v[i][j] + c * (i+j);
            ll another = INF;
            if (i-1 >= 0) another = min(another, minv[i-1][j]);
            if (j-1 >= 0) another = min(another, minv[i][j-1]);
            
            res = min(res, one + another);
        }
        
        // 上下ひっくり返す
        // <- 左上/右下ペアと、左下/右上ペアの2つを調べたいから
        reverse(all(v));
    }
    
    print(res);
}
