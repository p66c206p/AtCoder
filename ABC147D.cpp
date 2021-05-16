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

#include <atcoder/all>
using namespace atcoder;
// using mint = modint998244353;
using mint = modint1000000007;

int main(void){
    int n; cin >> n;
    vll v(n);
    rep(i, n) cin >> v[i];
    
    // ans: 長さnの数列の全ペアのxorの総和
    
    // how:
    // xorは桁ごとに処理できる。(2^0, 2^1, 2^2...)
    // -> 各桁の値は0 or 1。
    // -> 答えに寄与するのは0xor1だけ。
    // -> sum(2^何桁目)*(0の個数)*(1の個数)が答え。
    
    mint res = 0;
    mint pow2 = 1;
    rep(i, 60) {
        mint zero = 0;
        mint one = 0;
        rep(j, n) {
            if (v[j] % 2 == 0) zero++;
            else one++;
            v[j] /= 2;
        }
        
        res += zero * one * pow2;
        pow2 *= 2;
    }
    
    print(res.val());
}
