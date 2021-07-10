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
    ll m; cin >> m;
    
    // ans: n以下の正の整数で、桁の積がm以下になるパターン数
    
    // how: 桁DPをする。
    // 1. 素因数が何回現れたかで状態を持つ。
    // 2. 017と170を区別したい(leading-zeros)ので、
    //    0のみ/0以外出た/両方出たの3状態を持たせる。
    // 3. mより大きい数に遷移しないように、未満未確定/確定の状態を持たせる。
    
    map<vi, ll> mp;
    vi shoki = {0,0,0,0,0,0};
    mp[shoki] = 1;
    
    rep(i, n) {
        int nd = (int)(s[i] - '0');
        map<vi, ll> nmp;
        
        for (auto mpp : mp) {
            vi j = mpp.first;
            ll val = mpp.second;
            
            // 2, 3, 5, 7, 0のみ/0以外出た/両方出た, 未満未確定/確定
            int v = j[0];
            int w = j[1];
            int x = j[2];
            int y = j[3];
            int z = j[4];
            int k = j[5];
            
            rep(d, 10) {
                // 未満未確定ならndより先は見れない
                if (k == 0 && d > nd) break;
                
                int nv = v, nw = w, nx = x, ny = y, nz = z, nk = k;
                
                if (d == 2) nv++;
                if (d == 3) nw++;
                if (d == 4) nv += 2;
                if (d == 5) nx++;
                if (d == 6) {nv++; nw++;}
                if (d == 7) ny++;
                if (d == 8) nv += 3;
                if (d == 9) nw += 2;
                
                if (z == 0) {
                    if (d != 0) nz = 1;
                } else if (z == 1) {
                    if (d == 0) nz = 2;
                }
                
                // k: 指定の数未満が確定しているか否か(ex. 263....)
                // 遷移パターンは下記3通り
                // この度確定 = 0 -> 1  ex. 261....
                // 依然未確定 = 0 -> 0  ex. 263....
                // 既に確定   = 1 -> 1  ex. 182....
                if (k == 0 && d < nd) nk = 1;
                
                vi nj = {nv, nw, nx, ny, nz, nk};
                
                nmp[nj] += val;
            }
        }
        
        swap(mp, nmp);
    }
    
    ll res = 0;
    for (auto mpp : mp) {
        vi j = mpp.first;
        ll val = mpp.second;
        
        // 2, 3, 5, 7, 0のみ/0以外出た/両方出た, 未満未確定/確定
        int v = j[0];
        int w = j[1];
        int x = j[2];
        int y = j[3];
        int z = j[4];
        
        ll tmp = 1;
        if (z == 0) continue;
        if (z == 2) tmp *= 0;
        
        tmp *= (ll)pow(2, v);
        tmp *= (ll)pow(3, w);
        tmp *= (ll)pow(5, x);
        tmp *= (ll)pow(7, y);
        
        // print(tmp);
        
        if (tmp <= m) res += val;
    }
    
    print(res);
}
