グラフ/木: 
 - 木は必ず親が1人、グラフはその限りでない(合流が有る)
 - 二部グラフ: 2色に塗り分け、同じ色同士が手を繋がないグラフ
 - 最小全域木: 全ての頂点を繋ぐ木のうち、重みの総和が最小
DP[i]: [i]時点で最善の○○、遷移
 - bitDP: bitXを要素に見立てて、ある集合から別の集合への遷移を考える
 - 桁DP: その桁の値だけで遷移が決まる、未満確定か否かを考慮する
         317: 0～3 -> 00～31 -> 000~317をdp[i][j][k]で分類しながら遷移させる
         ↑dp[1]=4通り、dp[2]=32通り、dp[3]=318通り
DFS/BFS: 分岐する時、行けるとこまで行く(DFS)/次の深さは前の深さが全部終わってから(BFS)
DFS: 再帰
BFS: 最短経路問題、迷路、距離1のグラフ
最短路:
 - BFS(辺の長さが1) O(M)
 - ダイクストラ法(辺の長さバラバラ) O(MlogM)
 - ワーシャルフロイド法(全点の組み合わせ) O(N^3)
 - ベルマンフォード法(負辺がある) O(NM)
しゃくとり法: 条件を満たす区間(の長さ/の個数)、うねうね進む
累積和: 連続部分列の和をO(1)で求める手法
ダイクストラ法: S→各頂点までの最短経路(距離)を求める
 - 所要コスト(S→p)が最小の頂点pから、隣接する点qへ距離now+distを配っていく
 - PriorityQueueの先頭の頂点へは、そう行く他最短経路がないので所要コストが確定
 - youtu.be/X1AsMlJdiok
ワーシャルフロイド法: 全ての点i→jの最短距離を計算する
 - i→k→jという道がi→jという道より短ければi→jの距離を更新する
ベルマンフォード法: 2点間の距離だけ計算できる（各頂点は無理）
 - 2点間上にある辺についてN-1回ループを回して最短距離を確定させる
 - N回目も更新される頂点がある場合、S→Gのminは-INF
クラスカル法: 最小全域木の作り方
 - 最小の重みの辺を選択→その点につながる最小の辺を選択→その点につながる最小の辺を選択…
    - 但し、閉路を作る辺は選択しない
Union-Find: 同グループに属する要素数、グラフ
合同式: ex. 11 ≡ 1 (mod 5) ⇔ 11 % 5 = 1 % 5
 - mの倍数 = mで割った余りが0 = (mod m)の世界で値が0
 - 剰余計算は桁ごとに処理できる
重複組合せ: ex. (n,r)=(6,3) ボール6個/箱3個
 - r種類のボール(各無限個)からn個選ぶパターン数 n+r-1 C n
 - = n個の無色のボールをr箱に入れるパターン (赤箱3,青箱2,黒箱1)
 - = n個の無色のボールとr-1個の仕切りを並べるパターン (000 1 00 1 0)
 - = (ボールと仕切りの合計)C(ボールの数) (6+2)C6
 - = (ボールと仕切りの合計)/(ボールの数)!(仕切りの数)! 8!/(6!2!)
 - = ボールと仕切りを昇順に並べた数列をnext_permutationしてできるパターン数(00000011~11000000)
形式的冪級数: 数え上げを(1+x^i)で考える
 - 状態 = xの肩、パターン数 = xの係数で管理する
 - りんご3個ぶどう2個みかん4個からn個選ぶパターン数
    - [x^n] (1+x^1+x^2+x^3)(1+x^1+x^2)(1+x^1+x^2+x^3+x^4)
    - ↑で全パターン((0,0,0)～(3,2,4))網羅できている
包除原理: 数え上げを集合で分けて考える
 - A∪B = A + B - A∩B
    - 2でも3でも割れる数 = 2で割れる数 + 3で割れる数 - 2*3で割れる数
    - O(N)をO(1)に短縮できる
 - gcdが5の数列 = gcdが5の倍数の数列 - gcdが5*2の数列 - gcdが5*3の数列 - ...
誤差: 誤差が許されない→整数で扱う
 -　double, floatで入力を受け取った時点で誤差が既に出る
 -　小数を含む四則演算は全てNG
 - 123 * 4.56 = ?
    - 4.56をStringに入れる -> "."を除く -> 456を作る -> 123*456/100(ans)
 - √a + √b < √c ?
    - 「a+b+2√a√b < c」 -> 「2√a√b < c-a-b」 -> 「4ab < (c-a-b)^2 かつ c-a-b > 0」(ans)
