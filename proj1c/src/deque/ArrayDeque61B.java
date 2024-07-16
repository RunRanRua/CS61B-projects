package deque;

import java.util.*;
import java.util.function.Consumer;

public class ArrayDeque61B<T> implements Deque61B<T>{

    int positionFirst;
    int positionLast;
    T[] items;
    int capacity;
    int size;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        capacity = items.length;
        positionFirst = capacity-1;
        positionLast = 1;
    }

    public ArrayDeque61B(int givenCapacity) {
        if(givenCapacity <=0){
            throw new IllegalArgumentException("Invalid capacity");
        }

        items = (T[]) new Object[givenCapacity];
        size = 0;
        capacity = givenCapacity;
        positionFirst = capacity-1;
        positionLast = 1;
    }


    private void resize(int newCapacity){
        // create new array
        T[] a = (T[]) new Object[newCapacity];

        // fill items
        for(int i =0; i<size;i++){
            a[i] = items[(positionFirst+1+i) % capacity];
        }
        // point positions
        capacity = newCapacity;
        positionFirst = capacity-1;
        positionLast = size;

        // replace array
        items = a;
    }
    @Override
    public void addFirst(T x) {
        if (size == items.length){
            resize(capacity*2);
        }

        if(size ==0){
            items[0] = x;
        }else {

            items[positionFirst] = x;
            positionFirst = positionFirst == 0 ? capacity - 1 : positionFirst - 1;
        }
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length){
            resize(capacity*2);
        }


        if(size ==0){
            items[0] = x;
        }else {

            items[positionLast] = x;
            positionLast = (positionLast+1) % capacity;
        }
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for(int i =0; i<size;i++){
            returnList.add(items[ (positionFirst+1+i) % capacity]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        positionFirst = (positionFirst+1) % capacity;
        size--;

        return items[positionFirst];
    }

    @Override
    public T removeLast() {
        positionLast = positionLast == 0 ? capacity-1 : positionLast-1;
        size--;

        return items[positionLast];
    }

    @Override
    public T get(int index) {
        if(index > size){
            return null;
        }
        return items[ (positionFirst+1+index) % capacity ];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new ADIterator();
    }

    public class ADIterator implements Iterator<T>{
        int pos;
        public ADIterator(){
            pos=0;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T x = get(pos);
            pos++;
            return x;
        }
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){ return true;}

        if(obj instanceof ArrayDeque61B<?> ad){
            // check size
            if (this.size != ad.size()){
                return false;
            }

            // check items
            for (T x : this){
                if(!ad.toList().contains(x)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");

        for (T x : this){
            sj.add(x.toString());
        }
        return "["+ sj.toString() + "]";
    }
}
