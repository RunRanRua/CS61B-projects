/**
 * First version of list
 */


public class IntList {
    int first;
    IntList rest;


    public int size(){
        if (rest == null){
            return 1;
        }
        return 1+ rest.size();
    }
    public int iterativeSize(){
        int cnt = 1;
        IntList p = rest;
        while(p != null){
            cnt++;
            p = p.rest;
        }
        return cnt;
    }


    public IntList(int first, IntList rest) {
        this.first = first;
        this.rest = rest;
    }


    public static void main(String[] args) {
        IntList intList = new IntList(1,null);
        intList = new IntList(2,intList);   // add first
        intList = new IntList(3,intList);
        System.out.println(intList.first);
        System.out.println(intList.rest);
        System.out.println("****");
        System.out.println(intList.rest.first);
        System.out.println(intList.rest.rest);
        System.out.println("****");
        System.out.println(intList.rest.rest.first);
        System.out.println(intList.rest.rest.rest);

        System.out.println("** size **");
        System.out.println( "recursive : " + intList.size());
        System.out.println( "iterative : " +intList.iterativeSize());

    }

}
