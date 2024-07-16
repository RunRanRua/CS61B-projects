package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    Comparator<T> c;
    public MaxArrayDeque61B(Comparator<T> c){
        this.c = c;
    }
    public T max(){
        if(size==0){
            return null;
        }

        T maxElement = get(0);
        for (int i =0; i<size;i++){
            if( c.compare(maxElement,get(i)) < 0 ){
                maxElement = get(i);
            }
        }

        return maxElement;
    }
    public T max(Comparator<T> c){
        if(size==0){
            return null;
        }

        T maxElement = get(0);
        for (int i =0; i<size;i++){
            if( c.compare(maxElement,get(i)) < 0 ){
                maxElement = get(i);
            }
        }

        return maxElement;
    }

}
