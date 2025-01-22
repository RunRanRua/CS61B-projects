public class IntList{
    public int first; // 1st element
    public IntList rest; // rest elements

    // size methods
    public int size(){
        return (rest== null) ? 1 : 1 + this.rest.size();
    }
    public int iterativeSize(){
        IntList p = this;
        int cnt = 0;
        while (p!=null){
            cnt++;
            p = p.rest;
        }
        return cnt;
    }

    // get methods
    public int get(int i){
        return (i==0) ? first : rest.get(i-1);
    }
    public int iterativeGet(int i){
        IntList p = this;
        while (p != null){
            if (i == 0) {
                return p.first;
            }
            i--;
            p = p.rest;
        }
        return -1;
    }

    public IntList(int f, IntList r){
        first = f;
        rest = r;
    }


    public static IntList incrList(IntList L, int x){
        IntList incL = new IntList(L.first+x, null);
        for(int i =L.size()-1; i>=0;i--) {
            incL = new IntList(L.get(i) + x, incL);
        }
        return incL;
    }

    public static IntList dincrList(IntList L, int x){
        IntList p = L;
        for(int i=0; i< L.size(); i++){
            p.first = L.get(i) + x;
            p = p.rest;
        }
        return L;
    }


    public static void main(String[] args){
        // Test for intList
        IntList L = new IntList(15,null);
        L = new IntList(10, L);
        L = new IntList(5, L);


        // Test for incList
        IntList incL = incrList(L,2);
        System.out.println("incL = " + incL);

        // Test for dincList
        IntList dincL = dincrList(L,2);
        System.out.println("dincL = " + dincL);
    }
}