public class QuickUnionDS implements DisjointSets{
    /*
    *
    * Prob : Trees can get tall.
    * */
    private int[] parent;



    public QuickUnionDS(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i++)
        {  parent[i] = -1; }
    }


    public boolean isConnect(int p, int q) {
        return find(p) == find(q);
    }

    public void connect(int p, int q) {
        int i = find(p);
        int j = find(q);
        parent[i] = j;
    }

    /* find the root index*/
    private int find(int p) {
        int r = p;
        while (parent[r] >= 0)
        { r = parent[r]; }
        return r;
    }

}
