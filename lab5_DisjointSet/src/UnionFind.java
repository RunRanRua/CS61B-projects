public class UnionFind {
    int[] parent;


    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        parent = new int[N];
        for(int i =0; i<N; i++){
            parent[i] = -1;
        }
    }


    /* check if input argument is a valid number */
    private void validate(int v){
        if (v<0 || v>= parent.length){
            throw new IllegalArgumentException("Invalid index : " + v);
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        validate(v);
        int root = find(v);
        return -parent[root];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        validate(v);
        return parent[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are isConnected. */
    public boolean isConnected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int v) {
        validate(v);
        // if v is root
        if (parent[v] <0){
            return v;
        }
        // path compression
        parent[v] = find(parent[v]);
        return parent[v];
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already isConnected should not change the structure. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int r1 = find(v1);
        int r2 = find(v2);


        // if in the same set
        if(r1 == r2){
            return;
        }

        int size1 = -parent[r1];
        int size2 = -parent[r2];

        if(size1 <= size2){
            parent[r1] = r2;
            parent[r2] = -(size1 + size2);
        }else{
            parent[r2] = r1;
            parent[r1] = -(size1 + size2);
        }
    }

}