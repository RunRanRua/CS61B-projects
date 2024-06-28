import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private class Node{
        T item;
        Node next;
        Node prev;

        Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }



    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size =0;
    }





    @Override
    public void addFirst(T x) {
        sentinel.next = new Node(x,sentinel.next,sentinel);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    @Override
    public void addLast(T x) {
        sentinel.prev = new Node(x,sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size++;

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node currNode = sentinel.next;
        for(int i = 0; i<size;i++ ){
            returnList.add(currNode.item);
            currNode = currNode.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(sentinel.next == sentinel){
            return null;
        }

        Node firstNode = sentinel.next;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        size--;
        return firstNode.item;
    }

    @Override
    public T removeLast() {
        if(sentinel.prev == sentinel){
            return null;
        }

        Node lastNode = sentinel.prev;
        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;
        size--;
        return lastNode.item;
    }

    @Override
    public T get(int index) {
        if (index <0 || index >=size){
            return null;
        }

        Node node = sentinel.next;
        for(int i =0; i<index;i++){
            node = node.next;
        }
        return node.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index <0 || index >=size){
            return null;
        }
        return getRursive(index,sentinel.next);
    }
    private T getRursive(int index, Node node){
        if(index == 0){
            return node.item;
        }
        return getRursive(index-1, node.next);
    }


    public static void main(String[] args) {
        LinkedListDeque61B<String> a = new LinkedListDeque61B<>();
    }
}
