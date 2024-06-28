/*
 *  hide "null" part: bureaucracy : SLList
 *  Acess control : public -> private
 *  Nested Class : bring intNode in SLList
 *  Caching : size as an int
 */

public class SLList {
    IntNode first;
    int size;

    /* create an empty Slist*/
    public SLList(){
        first=null;
        size=0;
    }
    public SLList(int x) {
        first = new IntNode(x,null);
        size=1;
    }


    public void addFirst(int x){
        first = new IntNode(x,first);
        size++;
    }
    public int getFirst(){
        return first.item;
    }
    public void addLast(int x){
        IntNode p = first;

        /* case with empty SLList : not good version */
        if(first == null){
            first = new IntNode(x,null);
            return;
        }

        while(p.next != null){
            p=p.next;
        }
        p.next = new IntNode(x,null);
        size++;
    }

    /*Return the size of the list that starts at p */
    private static int size(IntNode p){
        if (p.next == null){
            return 1;
        }
        return 1+ size(p.next);
    }
    public int naiveSize(){
        return size(first);
    }
    public int size(){
        return size;
    }

    public static void main(String[] args) {
        SLList slList = new SLList(15);
        System.out.println(slList.first);
        System.out.println(slList.first.item);
        System.out.println(slList.first.next);
        System.out.println("** add first **");
        slList.addFirst(10);
        slList.addFirst(5);
        System.out.println("** SIZE **");
        System.out.println(slList.naiveSize());
        System.out.println(slList.size());
    }
}
