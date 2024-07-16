import java.util.HashSet;
import java.util.Random;
import java.util.StringJoiner;

public class QuickFindDS implements DisjointSets{
    /*
    * Quick Find => O(1) [ isConnect() ]
    * */




    private int[] id;


    public QuickFindDS(int N){
        id = new int[N];
        for(int i =0;i<N;i++){
            id[i] = -1;
        }
    }

    @Override
    public void connect(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        if (pid == -1 && qid == -1){
            int newID = randomID();
            id[p] = newID;
            id[q] = newID;
            return;
        }

        for(int i=0; i<id.length; i++){
            if (id[i] == pid){
                id[i] = qid;
            }
        }
    }

    private int randomID(){
        HashSet<Integer> idNums = new HashSet<>();
        for (int element :id){
            idNums.add(element);
        }

        Random random = new Random();
        int num = -1;
        do {
         num = random.nextInt(100);
        }while(idNums.contains(num));
        return num;
    }

    @Override
    public boolean isConnect(int p, int q) {
        if (p >= id.length  || q>= id.length || (id[p] == -1 && id[q] == -1) ){
            return false;
        }
        return id[p] == id[q];
    }


    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        for (int i =0;i<id.length;i++){
            sj.add( String.valueOf(id[i]));
        }
        return
                "[" +
                sj.toString() +
                ']';
    }
}
