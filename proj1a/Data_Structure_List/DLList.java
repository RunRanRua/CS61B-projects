/**
 * Double Linked List
 */

public class DLList {


    /* The 1st item (if it exists) is at sentinel.next */
    DIntNode sentinel;
    int size;
    DIntNode last;


    /* create an empty Slist*/
    public DLList(){
        sentinel= new DIntNode(-1,null,null);
        size=0;
        last = sentinel;
    }
    public DLList(int x) {
        sentinel = new DIntNode(-1,null,null);
        sentinel.next = new DIntNode(x,null,sentinel);
        size=1;


        last = sentinel.next;
    }


    public void addFirst(int x){
        sentinel.next = new DIntNode(x,sentinel.next, sentinel);
        size++;
    }
    public int getFirst(){
        return sentinel.next.item;
    }
    public void addLast(int x){
        last.next = new DIntNode(x,null,last);
        last = last.next;
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
