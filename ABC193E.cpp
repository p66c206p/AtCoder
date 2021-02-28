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

#include <atcoder/all>
using namespace atcoder;

ll INF = 4e18;

int main(void){
    int t; cin >> t;
    
    // ans:
    // 1. 電車は街A, B間を片道X分、停車時間Y分でピストン運行する。
    // 2. 人はP分睡眠、Q分起床を繰り返す。
    // 3. 人が起き、かつ電車が街Bにある時刻の最小値は？
    
    // ex. a,b,p,q = 5,2,7,6
    // 電車: [5,7), [19,21), [33,35)
    // 人: [7,13), [20,26), [33,39)
    // -> ans = 20
    // mod 14 [5, 7)、mod 13 [7,13)
    
    // how:
    // 電車は周期2(x+y)、人は周期p+q。
    // -> ある時刻が電車の周期のOKゾーン、人の周期のOKゾーンに属していればOK。
    // = ある数tが、mod A で[La, Ra)、mod B で[Lb, Rb)の両方に属する。
    
    // (実は、{i (mod A), j (mod B)}となる数はA*B内に必ず存在する。(AとBは互いに素))
    // -> i, jを全探索し、その中の最小値をansとする。
    
    while (t-- > 0) {
        ll x, y, p, q;
        cin >> x >> y >> p >> q;
        
        ll cycle_a = 2*(x+y);
        ll cycle_b = p+q;
        
        ll res = INF;
        for (int i = x; i < x+y; i++) {
            for (int j = p; j < p+q; j++) {
                // crt({i,j}, {mod1,mod2}): 中国剰余定理
                // 入力(mod1,mod2)は互いに素でなくても問題なし
                // (互いに素でない場合は、解ありとは限らない)
                pair<ll, ll> tmp = crt({i, j}, {cycle_a, cycle_b});
                
                // first: mod1で割った余りがi、mod2で割った余りがjになる数
                // (t ≡ i (mod m1), t ≡ j (mod m2)なるt)
                // second: (mod1,mod2)のlcm
                ll t = tmp.first;
                ll lcm = tmp.second;
                
                // lcm = 0の時解なし
                if (lcm == 0) continue;
                res = min(res, t);
            }
        }
        
        if (res == INF) print("infinity");
        else print(res);
    }
}
