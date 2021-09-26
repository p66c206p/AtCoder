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

int main(void){
    string s; cin >> s;
    int n = s.size();
    
    // ans: n以下の正の整数で、先頭の1の個数の総和
    
    // how: 桁DPをする。
    // 1. 状態は(先頭の1の個数, 0のみ/1継続中/継続終了)を持たせる。
    // 2. nより大きい数に遷移しないように、未満未確定/確定の状態を持たせる。
    
    map<vi, ll> mp;
    vi shoki = {0,0,0};
    mp[shoki] = 1;
    
    rep(i, n) {
        int nd = (int)(s[i] - '0');
        map<vi, ll> nmp;
        
        for (auto mpp : mp) {
            vi j = mpp.first;
            ll val = mpp.second;
            
            // 先頭の1の数, 0のみ/1継続中/継続終了, 未満未確定/確定
            int one = j[0];
            int z = j[1];
            int k = j[2];
            
            rep(d, 10) {
                // 未満未確定ならndより先は見れない
                if (k == 0 && d > nd) break;
                
                int none = one, nz = z, nk = k;
                
                if (z == 0) {
                    if (d == 1) {
                        nz = 1;
                        none = 1;
                    } else if (d == 0) {
                        // do nothing
                    } else {
                        nz = 2;
                    }
                } else if (z == 1) {
                    if (d == 1) {
                        none++;
                    } else {
                        nz = 2;
                    }
                } else {
                    // do nothing
                }
                
                // k: 指定の数未満が確定しているか否か(ex. 263....)
                // 遷移パターンは下記3通り
                // この度確定 = 0 -> 1  ex. 261....
                // 依然未確定 = 0 -> 0  ex. 263....
                // 既に確定   = 1 -> 1  ex. 182....
                if (k == 0 && d < nd) nk = 1;
                
                vi nj = {none, nz, nk};
                
                nmp[nj] += val;
            }
        }
        
        swap(mp, nmp);
    }
    
    ll res = 0;
    for (auto mpp : mp) {
        vi j = mpp.first;
        ll val = mpp.second;
        
        // 先頭の1の数, 0のみ/1継続中/継続終了, 未満未確定/確定
        int one = j[0];
        int z = j[1];
        int k = j[2];
        
        // print(one << " " << z << " " << k << " " << val);
        
        res += one * val;
    }
    
    print(res);
}
