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
BFS: 最短経路問題、迷路、距離1のグラフ
最短路:
 - BFS(辺の長さが1) O(M)
 - ダイクストラ法(辺の長さバラバラ) O(MlogM)
 - ワーシャルフロイド法(全点の組み合わせ) O(N^3)
 - ベルマンフォード法(負辺がある) O(NM)
しゃくとり法: 条件を満たす区間の長さ、うねうね進む
累積和[i]: [i]までの和
ダイクストラ法: S→Gまでの最短経路(距離)を求める
 - [点p]の[子供q]らに[自分の距離 + p→qの距離]を配り(BFS)、(名前,距離)をPriorityQueueに突っ込む
 - 今あるキューの先頭(未確定で最も距離が小さい点)を[点p]とし、↑をやる
    - キューの先頭の点はそう行く他最短経路がないので距離が確定するから
 - youtu.be/X1AsMlJdiok
ワーシャルフロイド法: 全ての点i→jの最短距離を計算する
 - i→k→jという道がi→jという道より短ければi→jの距離を更新する
クラスカル法: 最小全域木の作り方
 - 最小の重みの辺を選択→その点につながる最小の辺を選択→その点につながる最小の辺を選択…
    - 但し、閉路を作る辺は選択しない
Union-Find: 同グループに属する要素数、グラフ
