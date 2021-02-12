#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef pair<int, int> P;

#define rep(i, n) for (int i = 0; i < (int)(n); i++)
#define print(x) cout << x << endl
#define vi vector<int>
#define vvi vector<vi>
#define all(v) (v).begin(),(v).end()
#define YN(ok) print((ok ? "YES" : "NO"))
#define yn(ok) print((ok ? "Yes" : "No"))
#define decimal(n) setprecision(12) << n

#define Arrays_toString(v) rep(o,(v).size()){cout<<v[o]<<", ";if(o==(v).size()-1){cout<<endl;}}

int n, k;
string s;

void check(int i, queue<int> choice[], vector<P>& match) {
    if (choice[i].size() == 0) return;
    int j = choice[i].front();
    if (choice[j].size() == 0) return;
    if (choice[j].front() != i) return;
    
    P p(i, j);
    if (i > j) swap(p.first, p.second);
    match.push_back(p);
    return;
}

int main(void){
    int n; cin >> n;
    
    // ans:
    // n人がリーグ戦をする。全日程が完了する日数のminは？
    // 条件:
    // 1. 各人は自分の希望順に相手と戦う
    // 2. 各人は1日1回だけ試合する。
    
    // choice[i]: iが次戦いたい相手のキュー
    queue<int> choice[n];
    rep(i, n) {
        rep(j, n-1) {
            int opponent;
            cin >> opponent;
            opponent--;
            choice[i].push(opponent);
        }
    }
    
    // match: 現在実施可能な試合の集合
    vector<P> match;
    rep(i, n) check(i, choice, match);
    
    int day = 0;
    while (match.size() > 0) {
        day++;
        
        // vector内の重複を削除
        sort(all(match));
        match.erase(unique(all(match)), match.end());
        
        // swapを用いることで、
        // 今回の要素のみ処理しつつ、次回の要素をmatchに入れる
        vector<P> tmp;
        swap(tmp, match);
        for (P p : tmp){
            int i = p.first;
            int j = p.second;
            choice[i].pop();
            choice[j].pop();
        }
        for (P p : tmp) {
            int i = p.first;
            int j = p.second;
            check(i, choice, match);
            check(j, choice, match);
        }
    }
    
    rep(i, n) {
        if (choice[i].size() > 0) day = -1;
    }
    print(day);
}
