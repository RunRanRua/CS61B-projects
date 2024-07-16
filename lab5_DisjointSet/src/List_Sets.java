import java.util.*;

public class List_Sets implements DisjointSets{
    /*
    * A way to realise Disjoint Set
    * But too complicated and Slow
    * */



    List<Set<Integer>> l;


    public List_Sets(int N){
        l = new ArrayList<>();
        for (int i = 0; i<N;i++){
            l.add(new HashSet<>());
            l.get(i).add(i);
        }
    }

    @Override
    public void connect(int p, int q) {
        Set<Integer> p_set = null;
        Set<Integer> q_set = null;

        for (int i = 0; i< l.size(); i++){
            if (l.get(i).contains(p)){
                p_set = l.get(i);
            }
            if (l.get(i).contains(q)){
                q_set = l.get(i);
            }
        }
        if (p_set == null || q_set == null){
            return;
        }

        Set<Integer> biggerSet = p_set.size() > q_set.size() ? p_set : q_set;
        Set<Integer> smallerSet = p_set.size() > q_set.size() ? q_set : p_set;
        biggerSet.addAll(smallerSet);

        l.remove(smallerSet);
    }

    @Override
    public boolean isConnect(int p, int q) {
        for (int i =0; i<l.size();i++){
            if (l.get(i).contains(p)){
                for (Integer element : l.get(i)){
                    if (element == q){
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        for (int i =0;i<l.size();i++){
            sj.add(l.get(i).toString());
        }
        return "[" +
                sj.toString() +
                ']';
    }
}
