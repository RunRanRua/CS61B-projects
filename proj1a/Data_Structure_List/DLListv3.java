/**
 *  DLList v3 : with circular sentinel
 */

public class DLListv3 {
    /* The 1st item (if it exists) is at sentinel.next */
    DIntNode sentinel;
    int size;


    /* create an empty Slist*/
    public DLListv3(){
        sentinel = new DIntNode(-1,null,null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size=0;
    }
    public DLListv3(int x) {
        sentinel = new DIntNode(-1,null,null);
        sentinel.next = new DIntNode(x,sentinel,sentinel);
        sentinel.previous = sentinel.next;
        size=1;
    }


    public void addFirst(int x){
        sentinel.next = new DIntNode(x,sentinel.next,sentinel);
        sentinel.next.next.previous = sentinel.next;
        size++;
    }
    public int getFirst(){
        return sentinel.next.item;
    }
    public void addLast(int x){
        sentinel.previous = new DIntNode(x,sentinel, sentinel.previous);
        sentinel.previous.previous.next= sentinel.previous;
        size++;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        DLListv2 L = new DLListv2(5);
        System.out.println(L.getFirst());
        System.out.println("SentFont:" + L.sentFront);
        System.out.println("1: " + L.sentFront.next +" §=§ " + L.sentBack.previous);
        System.out.println("SentBack:" + L.sentBack);
        System.out.println("*** SentFront.next **");
        System.out.println(L.sentFront.next.previous);
        System.out.println(L.sentFront.next.item);
        System.out.println(L.sentFront.next.next);
        System.out.println("** SentBack.previous **");
        System.out.println(L.sentBack.previous.previous);
        System.out.println(L.sentBack.previous.item);
        System.out.println(L.sentBack.previous.next);

    }
}
