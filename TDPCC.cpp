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
template<class T> void que_toString(queue<T> q){while(q.size()){cout<<q.front()<<", ";q.pop();if(q.empty()){cout<<endl;}}}

double pwin_prob(double rp, double rq) {
    double res = (double)1 / (1 + pow(10, (rq - rp) / 400));
    return res;
}

int main(void){
    int n;
    cin >> n;
    vi rate(1 << n);
    rep(i, (1<<n)) cin >> rate[i];
    
    // ans: 2^n人でトーナメントする。選手iが優勝する確率は？
    
    // how:
    // dp[i][j] = 選手iがj連勝する確率と置く。
    // j-1連勝からj連勝へ行く確率は、
    // => dp[i][j-1] * Σ(dp[第j回戦で戦いうる相手][j-1] * 選手iがその相手に勝つ確率)
    
    // dp[i][j]: 選手iがj連勝する確率
    vector<vector<double>> dp(1<<n, vector<double>(n+1));
    rep(i, (1<<n)) dp[i][0] = 1.0;
    
    rep(period, n) {
        int len = 1 << period;
        rep(i, (1<<n)) {
            int myL, myR;
            myL = i/len*len; myR = i/len*len+len;
            int yourL, yourR;
            if (myL % (len*2) != 0) {
                yourL = myL - len;
                yourR = myR - len;
            } else {
                yourL = myL + len;
                yourR = myR + len;
            }
            
            double tmp = 0;
            for(int j = yourL; j < yourR; j++) {
                tmp += dp[j][period] * pwin_prob(rate[i], rate[j]);
            }
            dp[i][period + 1] = dp[i][period] * tmp;
        }
    }
    
    rep(i, (1<<n)) {
        print(decimal(dp[i][n]));
    }
}
