/*
* Returns an IntList identical to L, but with all values incremented by x.
* Values in L cannot change!
* */
public class IncrList {
    public int first;
    public IncrList rest;

    public IncrList(IntList L, int x){
        first = L.first + x;

        IncrList p = this;
        int size = L.size();
        for (int i =1; i<size; i++){
            p.rest = new IncrList(L.rest, x);
        }
    }


    public static void main(String[] args) {
        IntList L = new IntList(15, null);
        L = new IntList(10, L);
        L = new IntList(5, L);

        IncrList il = new IncrList(L, 2);
        System.out.println(";");
    }
}
