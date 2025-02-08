public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {
        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /**
         * Creates a RBTreeNode with item ITEM and color depending on ISBLACK
         * value.
         * @param isBlack
         * @param item
         */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /**
         * Creates a RBTreeNode with item ITEM, color depending on ISBLACK
         * value, left child LEFT, and right child RIGHT.
         * @param isBlack
         * @param item
         * @param left
         * @param right
         */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Creates an empty RedBlackTree.
     */
    public RedBlackTree() {
        root = null;
    }

    /**
     * Flips the color of node and its children. Assume that NODE has both left
     * and right children
     * @param node
     */
    void flipColors(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
    }

    /**
     * Rotates the given node to the right. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        if (node == null || node.left == null) return node;

        RBTreeNode<T> nodeL = node.left;
        node.left = nodeL.right;
        nodeL.right = node;

        nodeL.isBlack = node.isBlack;
        node.isBlack = false;
        return nodeL;
    }

    /**
     * Rotates the given node to the left. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        if (node == null || node.right == null) return node;

        RBTreeNode<T> nodeR = node.right;
        node.right = nodeR.left;
        nodeR.left = node;

        nodeR.isBlack = node.isBlack;
        node.isBlack = false;
        return nodeR;
    }

    /**
     * Helper method that returns whether the given node is red. Null nodes (children or leaf
     * nodes) are automatically considered black.
     * @param node
     * @return
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    /**
     * Inserts the item into the Red Black Tree. Colors the root of the tree black.
     * @param item
     */
    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /**
     * Inserts the given node into this Red Black Tree. Comments have been provided to help break
     * down the problem. For each case, consider the scenario needed to perform those operations.
     * Make sure to also review the other methods in this class!
     * @param node
     * @param item
     * @return
     */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        // TODO: Insert (return) new red leaf node.
        if (node == null){
            return new RBTreeNode<T>(false, item);
        }

        // TODO: Handle normal binary search tree insertion.
        int cmp = item.compareTo(node.item);
        if (cmp<0){
            node.left = insert(node.left, item);
        } else if (cmp>0) {
            node.right = insert(node.right, item);
        }else{
            return node;
        }

        // TODO: Rotate left operation
        if (!isRed(node.left) && isRed(node.right)){
            node = rotateLeft(node);
        }
        // TODO: Rotate right operation
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        // TODO: Color flip
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node; //fix this return statement
    }

}
