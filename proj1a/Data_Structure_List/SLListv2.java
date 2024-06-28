    /*
     *  To solve empty SLList case : better solution : Sentinel
     */

public class SLListv2 {



    /* The 1st item (if it exists) is at sentinel.next */
    IntNode sentinel;
    int size;

    /* create an empty Slist*/
    public SLListv2(){
        sentinel= new IntNode(-1,null);
        size=0;
    }
    public SLListv2(int x) {
        sentinel = new IntNode(-1,null);
        sentinel.next = new IntNode(x,null);
        size=1;
    }


    public void addFirst(int x){
        sentinel.next = new IntNode(x,sentinel.next);
        size++;
    }
    public int getFirst(){
        return sentinel.next.item;
    }
    public void addLast(int x){
        IntNode p = sentinel;

        while(p.next != null){
            p=p.next;
        }
        p.next = new IntNode(x,null);
        size++;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        SLListv2 SLListv2 = new SLListv2(15);
        System.out.println(SLListv2.sentinel.next.item);
        System.out.println("** add first **");
        SLListv2.addFirst(10);
        SLListv2.addFirst(5);
        System.out.println("** SIZE **");
        System.out.println(SLListv2.size());
    }
}
