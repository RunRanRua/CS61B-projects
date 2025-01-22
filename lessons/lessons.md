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

![](C:\Users\parki\Documents\GitHub\CS61B-projects\lessons\images\IntList.png)

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

![](C:\Users\parki\Documents\GitHub\CS61B-projects\lessons\images\IntList2.png)

