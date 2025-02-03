import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V extends Comparable<K>> implements Map61B< K, V>{
    private class BSTNode {
        K key;
        V val;
        BSTNode left;
        BSTNode right;

        public BSTNode(){}
        public BSTNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private BSTNode root;
    private HashSet<K> hashSet;

    public BSTMap() {
        hashSet = new HashSet<>();
    }

    @Override
    public int size() {
        return hashSet.size();
    }

    @Override
    public void clear() {
        root = null;
        hashSet.clear();
    }



    private BSTNode putHelper(K key, V val, BSTNode node){
        if (node == null) {
            return new BSTNode(key,val);
        }

        int d = key.compareTo(node.key);
        if (d< 0){
            node.left = putHelper(key, val, node.left);
        }else if (d> 0){
            node.right = putHelper(key, val, node.right);
        }else {
            node.val = val;
        }
        return node;
    }
    @Override
    public void put(K key, V val) {
        root = putHelper(key, val, root);
        hashSet.add(key);
    }

    private V getHelper(K key, BSTNode node){
        if (node == null){
            return null;
        }

        int d = key.compareTo(node.key);
        if (d <0){
            return getHelper(key, node.left);
        }else if (d >0){
            return getHelper(key, node.right);
        }else{
            return node.val;
        }
    }
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }


    @Override
    public boolean containsKey(K key) {
        return hashSet.contains(key);
    }

    @Override
    public Set<K> keySet() {
        return hashSet;
    }

    private void removeHelper(K key, BSTNode node){
        if(!containsKey(key)){
            return ;
        }

        int d = key.compareTo(node.key);
        if (d < 0){

        }
    }
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
