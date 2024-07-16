public interface DisjointSets {

    // to link 2 number
    void connect(int p, int q);

    // to check if p and q are linked
    boolean isConnect(int p, int q);
}
