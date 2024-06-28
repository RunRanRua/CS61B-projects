public class DIntNode {
    /*
     * With double linked
     */

    int item;
    DIntNode next;
    DIntNode previous;


    public DIntNode(int item, DIntNode next, DIntNode prev){
        this.item = item;
        this.next = next;
        this.previous = prev;
    }
}
