# List

## 01 Arrays

```java
int[] a = new int[]{1,2,3} // creates a 64 bit box for storing an int array @
```

## 02 IntList

### **1- Initial version**

> not practical

```java
public class IntList{
	public int first; // 1st element
	public IntList rest; // rest elements
    
    public static void main(String[] args){
        IntList L = new IntList();
        L.first = 5;
        L.rest = null;
        
        L.rest = new IntList();
        L2.rest.first = 6;
    }
}
```

![](.\images\IntList.png)

### 2- with construct

```java
public class IntList{
    public int first; // 1st element
    public IntList rest; // rest elements

    // size methods
    public int size(){
        return (rest== null) ? 1 : 1 + this.rest.size();
    }
    public int iterativeSize(){
        IntList p = this;
        int cnt = 0;
        while (p!=null){
            cnt++;
            p = p.rest;
        }
        return cnt;
    }

    // get methods
    public int get(int i){
        return (i==0) ? first : rest.get(i-1);
    }
    public int iterativeGet(int i){
        IntList p = this;
        while (p != null){
            if (i == 0) {
                return p.first;
            }
            i--;
            p = p.rest;
        }
        return -1;
    }

    public IntList(int f, IntList r){
        first = f;
        rest = r;
    }

    public static void main(String[] args){
        IntList L = new IntList(15,null);
        L = new IntList(10, L);
        L = new IntList(5, L);
    }
}
```

![](.\images\IntList2.png)

### 3- recursive version : IntNode

> IntList is a naked linked list, which is usually hard to use. We use more IntNode as helper for other List

```java
public class IntNode {
    public int item;
    public IntNode next;
    
    public IntNode(int i, IntNode n){
        item = i;
        next = n;
    }
}
```

## 03 SLList

> An SLList is a list of integers, which hides the terrible truth of the nakedness within

### 1- initial version

```java
public class SLList {
    public static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n){
            item = i;
            next = n;
        }
    }
    
    private IntNode first;
    
    
    public void addFirst(int x){
        first = new IntNode(x, first);
    }
    public int getFirst(){
        return first.item;
    }
    public void addLast(int x){
        IntNode p = first;
        while (p.next != null){
            p = p.next;
        }
        p.next = new IntNode(x,null);
    }
    // SIZE : helper + real func
    private static int size(IntNode p){
        return p.next == null ? 1 : 1 + size(p.next); 
    }
    public int size(){
        return size(first);
    }
    
    
    public SLList(int x){
        first = new IntNode(x, null);
    }
    
    public static void main(String[] args){
        SLList L = new SLList(10); // better than intNode in term of code: L = new IntNode(10, null)
        L.addFirst(10);
        L.addFirst(5);
        System.out.println(L.getFirst());

    }
}
```

![](.\images\SLList.png)

### 2- improved version : size improvement

```java
public class SLList {
    public static class IntNode {...}
    
    private IntNode first;
    private int size;	// size variale
    
    public int getFirst(){...}
    public void addFirst(int x){
        size ++;	// size variable
        first = new IntNode(x, first);
    }
    public void addLast(int x){
        size ++;	// size variable
        ...
    }
    
    // SIZE : improved .ver
    public int size(){
        return size;
    }
    
    // warning : empty list may occur an error because of  p=null
    public SLList(){
        first = null;
        size = 0
    }
    public SLList(int x){
        first = new IntNode(x, null);
        size = 1
    }
}
```

### 3- improved version : empty list

> Create a sentinel Node to fix empty list bug

```java
public class SLList {
    public static class IntNode {...}
    
    private IntNode sentinel; // sentinel node instead of first
    private int size;
    
    public int size(){...}
    public int getFirst(){
        return sentinel.next.item;
    }
    public void addFirst(int x){
        size ++;
        sentinel.next = new IntNode(x, sentinel.next);
    }
    public void addLast(int x){
        size ++;
        IntNode p = sentinel;
        ...
    }
   
    
    public SLList(){
        sentinel = new IntNode(114514, null); // random value node representing sentinel node
        size = 0
    }
    public SLList(int x){
        sentinel = new IntNode(114514, null); // random value node representing sentinel node
        sentinel.next = new IntNode(x, null); // 2 nodes present in the list
        size = 1
    }
}
```

## 04 DLList

> SLList takes too many time for inserting last item => DLList (Doubl Linked List)

### 1- initial version

```java
public class DLList {
    public static class IntNode {
        public IntNode prev;	// previous node
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode nextN, IntNode, prevN){
            prev = prevN;
            item = i;
            next = nextN;
        }
    }
    
    private IntNode sentinel;
    private size;
    private IntNode last;	// pointer for last node
    
    public int size(){...}
    public int getFirst(){...}
    public void addFirst(int x){
        size ++;
        sentinel.next.prev = new IntNode(x, sentinel.next, null); // N1<-->N2
        sentinel.next = sentinel.next.prev;	// Sent-->N1
    }
    public void addLast(int x){
        size ++;
        last.next = new IntNode(x,null, last.next) // N8<-->N9
        last = last.next; // Last-->N9
    }
    
    
    public SLList(){
        sentinel = new IntNode(114514, null, null);
        last = null;
        size = 0
    }
    public DLList(int x){
        sentinel = new IntNode(114514, null, null);
        sentinel.next = new IntNode(x, null, null); // sentinel point at N1
        last = sentinel.next;	// point at the last node
        size = 1
    }
}
```

![](.\images\DLList.png)

### 2- improved version : with 2 sentinels

> the issue is that `last` may point at sentinel or nodes

```java
public class DLList {
    public static class IntNode {...}
    
    private IntNode sentFront; // Sentinel front
    private size;
    private IntNode sentBack;	// Senitnel last
    
    public int size(){...}
    public int getFirst(){...}
    public void addFirst(int x){
        size ++;
        sentinel.next.prev = new IntNode(x, sentinel.next, null); // N1<-->N2
        sentinel.next = sentinel.next.prev;	// Sent-->N1
    }
    public void addLast(int x){
        size ++;
        sentBack.next.next = new IntNode(x,null, sentBack.next); // N8<-->N9
        sentBack.next = sentBack.next.next; // point at the last node N9
    }
    
    
    public DLList(){
        sentFront = new IntNode(114514, null, null);
        sentBack = new IntNode(114514, null, null);
        size = 0
    }
    public DLList(int x){
        sentFront = new IntNode(114514, null, null);
        sentFront.next = new IntNode(x, null, null); // SentF-->N1
        sentBack = new IntNode(114514, sentFront.next, null); // SentB-->N1
        size = 1
    }
}
```

![](.\images\DLList2.png)

### 3- improved version : with circular sentinel

> only need 1 sentinel

```java
public class DLList {
    public static class IntNode {...}
    
    private IntNode sentinel;
    private size;
    
    public int size(){...}
    public void addLast(int x){
        size ++;
        sentinel.next.next = new IntNode(x, sentinel.next.next, sentinel.next); // N8<-->N9-->N1
        sentinel.next = sentinel.next.next; // Sent-->N9
        sentinel.next.next.prev = sentinel.next; // N1-->N9
    }
    
    
    public DLList(){
        sentinel = new IntNode(114514, null, null);
        size = 0
    }
    public DLList(int x){
        sentinel = new IntNode(114514, null, null);
        sentinel.next = new IntNode(x, this, this); // Sent-->N1
        size = 1
    }
}
```

![](.\images\DLList3.png)

## 05 AList

> previous list is good at insertion/remove, but what if we want to be good at search ?

### 1- initial version

```java
public class AList {
    private int[] items;
    private int size;
    
    public void addLast(int x){
        items[size] = x;
        size++;
    }
    public int getLast(){
        return items[size-1];
    }
    public int get(int i){
        return items[i];
    }
    public int size(){
        return size;
    }
    public int removeLast(){
        int x = getLast();
        size--;
        return x;
    }
    
    public AList() {
        items = new int[100]; // initial size = 100
        size = 0;
    }
}
```

### 2- AList with resizing (v1)

```java
public class AList {
    private int[] items;
    private int size;
    
    public int getLast(){...}
    public int get(int i){...}
    public int size(){...}
    
    public void addLast(int x){
        if (size == items.length){
            int[] a = new int[size + 1];
            System.arraycopy(items, 0, a, size);
            items = a;
        }
        items[size] = x;
        size++;
    }
    public int removeLast(){...}
    
    public AList() {...}
}
```

### 3- AList with resizing (v2)

> easier to read + test

```java
public class AList {
    private int[] items;
    private int size;
    
    public int getLast(){...}
    public int get(int i){...}
    public int size(){...}
    
    // helper func for resize
    private void resize(int capacity){
        int[] a = new int[capacity];
        System.arraycopy(items, 0, a, size);
        items = a;
    }
    public void addLast(int x){
        if (size == items.length){
            resize(size + 1) // better use resize(size * Rfactor)
        }
        items[size] = x;
        size++;
    }
    public int removeLast(){...}
    
    public AList() {...}
}
```

## 06 Comparator

> We want to compare elements for all types of variables

### 1- first approach

```java
public interface OurComparable {
   /** Return negative num if this < o.
    *  Return 0 if this equals o.
    *  Return positive num if this > o.
    */
   public int compareTo(Object o);
}

public class Dog implements OurComparable {
    private String name;
    private int size;
   
    public int compareTo(Object o){
        Dog uddaDog = (Dog) o; // convert o into Dog class
        return this.size - uddaDog.size;
}

public class Maximizer {
    public static OurComparable max(OurComparable[] items){
        int maxDex = 0;	// max element
        for (int i=0; i<items.length; i++){
            int cmp = items[i].compareTo(items[maxDex]); // compare i-element to max-element in using compareTo()
            if (cmp >0){
                maxDex = i;
            }
        }
        return items[maxDex]
    }
    
    
    
    public static void main(String[] args){
        Dog[] dogs = {...};
        Dog maxDog = (Dog) Maximizer.max(dogs);
    }
}
```

### 2- built-in approach : Comparable

> The previous approach has some limits such as : primary variables/classess have no implement OurComparable

```java
public class Dog implements Comparable<Dog> {
    private String name;
    private int size;
   
    public int compareTo(Dog uddaDog){
        return this.size - uddaDog.size;
}
    
public class Maximizer {
    public static Comparable max(Comparable[] items){
        int maxDex = 0;	// max element
        for (int i=0; i<items.length; i++){
            int cmp = items[i].compareTo(items[maxDex]); // compare i-element to max-element in using compareTo()
            if (cmp >0){
                maxDex = i;
            }
        }
        return items[maxDex]
    }
}
```

### 3- comparator : customized comparison

```java
import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    private String name;
    private int size;

    public int compareTo(Dog uddaDog) {
        return this.size - uddaDog.size;
    }

    // customized comparator : by name
    public static class NameComparator implement Comparable<Dog>{
        // func to compare
        public int compare(Dog a, Dog b){
            return a.name.compareTo(b.name);
        }
    }
    
    
    public static void main(String[] args){
        Dog[] dogs = new Dogs[]{...}
        
        Dog.NameComparator nc = new Dog.NameComparator(); // instantialize comparator
        if ( nc.compare(d1, d3) > 0){
            print("d1");
        }
    }
}
```

### 4- comparator : user-friendly version

```java
import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    private String name;
    private int size;
    public int compareTo(Dog uddaDog) {...}

    // customized comparator   [helper class]
    private static class NameComparator implement Comparable<Dog>{
        // func to compare
        public int compare(Dog a, Dog b){
            return a.name.compareTo(b.name);
        }
    }
    // customized comparator  [public]
    public static class Comparator<Dog> getNameComparator(){
        return new NameComparator();
    }
    
    
    public static void main(String[] args){
        Dog[] dogs = new Dogs[]{...}
        
        Comparator<Dog> nc = Dog.getNameComparator(); // Dog.NameComparator nc = new Dog.NameComparator()
        if ( nc.compare(d1, d3) > 0){
            print("d1");
        }
    }
}
```

## 07 ArraySet + Iterator + toString

> Construct an arrayset with iterator (iterator class + hasNext() + next() + implements)

### 1- ugly version of iterator

> we make our own iterator

```java
public class ArraySet<T> implements Iterable<T> {
    private T[] items;
    private int size;
    
    // helper
    private class ArraySetIterator implements Iterator<T>{
        private int pos;
        // constructor
        public ArraySetIterator() {
            pos = 0;
        }
        public boolean hasNext(){
            return pos < size;
        }
        public T next(){
            T returnItem = items[pos];
            pos++;
            return returnItem;
        }
    }
    // iterator : public
    public Iterator<T> iterator() {
    	return new ArraySetIterator;
    }

    
    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);

        Iterator<Integer> aseer = aset.iterator();

        while (aseer.hasNext()) {
            int i = asser.next();
            System.out.println(i);
        }
   }
}

```

### 2- ToString()

```java
public class ArraySet<T> implements Iterable<T> {
   @Override
   public String toString() {
       /*
       String returnString = "{";
       for (T item : this) {
           returnString += item.toString();
           returnString += ", ";
       }
       returnString += "}";
       return returnString;
       */
       
       // better approach : using StringBuilder
       StringBuilder returnSB = new StringBuilder("{");
       for (int i=0; i<size; i++){
           returnSB.append(items[i]).append(', ');
       }
       returnSB.append("}");
       return returnSB.toString();
   }
}
```

### 3- Equal()

```java
public class ArraySet<T> implements Iterable<T> {
   @Override
   public boolean equals(Object o) {
       if (o instanceof ArraySet oas) {
           // check sets are of the same size
           if (oas.size != this.size) {
               return false;
           }

           // check that all of MY items are in the other array set
           for (T x : this) {
               if (!oas.contains(x)) {
                   return false;
               }
           }

           return true;
       }
       // o is not an arrayset, so return false
       return false;


   }
}

```

## 08 Disjoint Sets

> intuitive version : ArrayList< Set<Integer> >    = bad for search + hard to implement

### 1- QuickFindDS

> QuickFind optimize the runtime of `isConnected()`

```java
public interface DisjointSets {
	void connect(int p, int q); 
	boolean isConnected(int p, int q);
}

public class QuickFindDS implements DisjointSets {
	private int[] id;	// id to differentiate each connected set
     
    public QuickFindDS(int N) {
     	id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = -1;	// init id
     }         

    
	public boolean isConnected(int p, int q) {
        return id[p] == id[q];
	}
	public void connect(int p, int q) {
        int pid = id[p];
        int qid = id[q];
       	for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
}
```

![](.\images\QuickFind.png)

### 2- QuickUnionDS

> QuickUnion optimize the runtime of `connected()`

```java
public class QuickFindDS implements DisjointSets {
    private int[] parent;	// id -> (tree) indice =element|value = parent node
    public boolean isConnected(int p, int q) {...}
    
    
    public QuickFindDS(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i++) {  parent[i] = -1; }
    }         
    // helper
	private int find(int p){
        int r = p;
        while (parent[r] >=0){ r= parent[r]; }  // climb the tree
        return r;
    }
    public void connect(int p, int q) {
        int i =find(p);		// find the parent of p set
        int j =find(q);		// find the parent of q set
        parent[i] = j;		// connect the 2 parents
    }
}
```

![](.\images\QuickUnion.png)

### 3- Weighted QuickUnion

> Modify quick-union to avoid tall trees. 
>
> Track tree size (**number** of elements).
>
> New rule: Always link root of **smaller** tree **to** **larger** tree.

```python
# 2 approaches possibles
1. replace -1 by -weight for root (Top approach)
2. create a separate size array   (bottom approach)

# why w instead of h ?
- same resultat but more complicated to implement
```

### 4- Weighted QuickUnion With Path Compression

> Reduce hight

```python
/*
When we connect A and B, we replace each traveled node's parent by the root 
*/
```

![](.\images\ComplexityDS.png)

## 09 Binary Trees

> an unordered linked list set : perform insert/remove
>
> **problem** : hard to search

```mermaid
graph TD
A(1) --> B(2)
A --> C(3)
B--> D(4)
B-->E(5)

```

### 1- BST

> ordered linked list set : improve search capacity
>
> For every node X in the tree:
>
> - Every key in the **left** subtree is **less** than X’s key.
> - Every key in the **right** subtree is **greater** than X’s key.
>
> **problem** : insertion may cause spindly tree (↘) = brut search  [`logN -> N`]

```mermaid
graph TD
A(5) --> B(3)
A --> C(6)
B--> D(2)
B-->E(4)
```

### 2- AVL

> ordered linked list set : but rotate nodes to ensure height
>
> - The difference between L-subtree and R-subtree's height <=1
>
> **problem** : when we insert too much, the tree will need too many inter-insertion to adjust tree's structure

### 3- Red-Black Trees

> - Nodes are red or black
> - root is black
> - leaves are black
> - NO two consecutive red nodes
> - All paths from any node to each of its leaves contain the same number of black nodes
>
> **problem** : with insertion, these kind of trees will still augment heights => slow 

## 10- N-Trees

> Instead of binary tree, try N-nary tree
>
> - each nodes has at most n child
> - all nodes (except root) have at least N/2 child
> - root itself not being leaf, has at least 2 child
> - all leaves are at the same depth
>
> ![](.\images\B-trees.png)
>
> - n : nb of items in this node
> - p : pointer point at subtree
> - k : item

### 1- B-trees

> good for a large items
>
> problem: when N is small, it suffer from performance problem :
>
> - maintaining different node types
> - interconversion between 2-nodes and 3-nodes
> - walking up the tree to split

![](.\images\B-trees2.png)

```

```

