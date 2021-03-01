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

P f(string s) {
    int n = s.size();
    int now = 0;
    int tmp1 = 0;
    for (int i = n-1; i >= 0; i--) {
        if (s[i] == ')') {
            now++;
        } else {
            now = max(0, --now);
        }
    }
    tmp1 = now;
    
    now = 0;
    int tmp2 = 0;
    rep(i, n) {
        if (s[i] == '(') {
            now++;
        } else {
            now = max(0, --now);
        }
    }
    tmp2 = now;
    
    P res(tmp1, tmp2);
    return res;
}

int main(void){
    int n; cin >> n;
    
    vector<P> v;
    rep(i, n) {
        string s; cin >> s;
        P p = f(s);
        // print(p.first << " " << p.second);
        if (p.first == 0 && p.second == 0) continue;
        else v.push_back(p);
    }
    
    // question:
    // 括弧列がn個与えられる。
    // 好きな順序で並べて、いい括弧列にできる？
    // ok: (()),  ()(), (()())
    // ng: ), )(
    
    // ans:
    // 1. (x, y)がn個ある。操作: x下がり、y上がる
    // 2. n個を並べ替えて、0からスタートして丁度0で終われる？
    // 3. ただし、途中で0を下回るのはNG。
    
    // how:
    // 貪欲に並べる。
    // 1. x=0 を並べる。
    // 2. x+y>0 を、xの昇順に並べる(同じならyの降順)。
    // 3. x+y<0 を xの降順に並べる(同じならyの降順)。
    // 4. y=0 を並べる。
    
    n = v.size();
    vector<P> sec1;
    vector<P> sec2;
    vector<P> sec3;
    vector<P> sec4;
    rep(i, n) {
        int down = v[i].first;
        int up = v[i].second;
        
        if (down == 0) {
            sec1.push_back(v[i]);
        } else if (up == 0) {
            sec4.push_back(v[i]);
        } else if (down < up) {
            sec2.push_back(v[i]);
        } else {
            sec3.push_back(v[i]);
        }
    }
    
    // firstを昇順、同じならsecondを降順にソート
    rep(i, sec2.size()) sec2[i].second *= -1;
    sort(all(sec2));
    rep(i, sec2.size()) sec2[i].second *= -1;
    
    // firstを降順、同じならsecondを降順にソート
    rep(i, sec3.size()) {sec3[i].first *= -1; sec3[i].second *= -1;}
    sort(all(sec3));
    rep(i, sec3.size()) {sec3[i].first *= -1; sec3[i].second *= -1;}
    
    bool ok = true;
    int now = 0;
    for (auto onlyabove : sec1) {
        // int down = onlyabove.first;
        int up = onlyabove.second;
        
        now += up;
    }
    for (auto above : sec2) {
        int down = above.first;
        int up = above.second;
        
        now -= down;
        if (now < 0) ok = false;
        now += up;
    }
    for (auto below : sec3) {
        int down = below.first;
        int up = below.second;
        
        now -= down;
        if (now < 0) ok = false;
        now += up;
    }
    for (auto onlybelow : sec4) {
        int down = onlybelow.first;
        // int up = onlybelow.second;
        
        now -= down;
        if (now < 0) ok = false;
    }
    if (now != 0) ok = false;
    
    yn(ok);
}
