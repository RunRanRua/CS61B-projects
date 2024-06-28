/**
 * DLList with double sentinels
 */
public class DLListv2 {
    /* The 1st item (if it exists) is at sentinel.next */
    DIntNode sentFront;
    int size;
    DIntNode sentBack;


    /* create an empty Slist*/
    public DLListv2(){
        sentFront= new DIntNode(-1,null,null);
        sentBack = new DIntNode(-1,null,null);
        sentFront.next = sentBack;
        sentBack.previous = sentFront;
        size=0;
    }
    public DLListv2(int x) {
        sentFront= new DIntNode(-1,null,null);
        sentBack = new DIntNode(-1,null,null);
        sentFront.next = new DIntNode(x,sentBack,sentFront);
        sentBack.previous = sentFront.next;
        size=1;
    }


    public void addFirst(int x){
        sentFront.next = new DIntNode(x,sentFront.next,sentFront);
        sentFront.next.next.previous = sentFront.next;
        size++;
    }
    public int getFirst(){
        return sentFront.next.item;
    }
    public void addLast(int x){
        sentBack.previous = new DIntNode(x,sentBack,sentBack.previous);
        sentBack.previous.previous.next = sentBack.previous;
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
