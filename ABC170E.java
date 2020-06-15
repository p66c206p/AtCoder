import java.util.*;

public class Main {
    static int max = 200000;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        
        // process:
        // 生徒aは学校bに所属し成績はcである。
        // -> 番号iの生徒を学校jに転校させる
        // -> 「各学校の1位」の最小値を出力する　をQ回繰り返す。
        
        // idea:
        // 時間がかかること
        // => 学校の成績の集合で、生徒iの成績を検索/削除すること。
        // -> Setなら検索が速い。
        
        // how:
        // 1.各学校の成績の集合score[]を作る。
        // 2.各学校の1位の成績の集合firstを作る。
        // 3.転校させる
        // 3-1.学校iの1位の成績をfirstから削除する。
        // 3-2.生徒sの成績をscore[i]から削除する。
        // 3-3.生徒s抜きの1位の成績をfirstに追加する。
        // 3-4.学校jの1位の成績をfirstから削除する。
        // 3-5.生徒sの成績をscore[j]に追加する。
        // 3-6.生徒s有りの1位の成績をfirstに追加する。
        // 3-7.生徒sの学校をjにする。
        // 4.firstの最小値を出力する。
        // 5.「3～4」を計Q回繰り返す。
        
        // array: {生徒iの成績, 生徒iの学校}
        int[][] array = new int[n][2];
        for (int i = 0; i < n; i++) {
            array[i][0] = sc.nextInt();
            array[i][1] = sc.nextInt() - 1;
        }
        
        // score: 学校iの成績の集合
        // (重複なしならTreeSet<Integer>でOK)
        TreeMap<Integer, Integer>[] score = new TreeMap[max];
        for (int i = 0; i < max; i++) {
            score[i] = new TreeMap<Integer, Integer>();
        }
        for (int i = 0; i < n; i++) {
            int key = array[i][0];
            int school = array[i][1];
            
            int val = score[school].getOrDefault(key, 0);
            score[school].put(key, ++val);
        }
        
        // first: 各学校1位の成績の集合
        TreeMap<Integer, Integer> first = new TreeMap<Integer, Integer>();
        for (int i = 0; i < max; i++) {
            if (score[i].size() == 0) continue;
            
            int key = score[i].lastKey();
            int val = first.getOrDefault(key, 0);
            first.put(key, ++val);
        }
        
        while (q-- > 0) {
            int index = sc.nextInt() - 1;
            int to = sc.nextInt() - 1;
            
            // 3-1.学校iの1位の成績をfirstから削除する。
            int from = array[index][1];
            int thefirst = score[from].lastKey();
            int val = first.get(thefirst);
            if (val == 1) {
                first.remove(thefirst);
            } else {
                first.put(thefirst, --val);
            }
            
            // 3-2.生徒sの成績をscore[i]から削除する。
            int thescore = array[index][0];
            val = score[from].get(thescore);
            if (val == 1) {
                score[from].remove(thescore);
            } else {
                score[from].put(thescore, --val);
            }
            
            // 3-3.生徒s抜きの1位の成績をfirstに追加する。
            if (score[from].size() != 0) {
                thefirst = score[from].lastKey();
                val = first.getOrDefault(thefirst, 0);
                first.put(thefirst, ++val);
            }
            
            // 3-4.学校jの1位の成績をfirstから削除する。
            if (score[to].size() != 0) {
                thefirst = score[to].lastKey();
                val = first.get(thefirst);
                if (val == 1) {
                    first.remove(thefirst);
                } else {
                    first.put(thefirst, --val);
                }
            }
            
            // 3-5.生徒sの成績をscore[j]に追加する。
            val = score[to].getOrDefault(thescore, 0);
            score[to].put(thescore, ++val);
            
            // 3-6.生徒s有りの1位の成績をfirstに追加する。
            thefirst = score[to].lastKey();
            val = first.getOrDefault(thefirst, 0);
            first.put(thefirst, ++val);
            
            // 3-7.生徒sの学校をjにする。
            array[index][1] = to;
            
            // 4.firstの最小値を出力する。
            System.out.println(first.firstKey());
        }
    }
}
