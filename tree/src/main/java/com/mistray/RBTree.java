package com.mistray;

/**
 * 红黑二叉树
 * <p>
 * 红黑树五大原则
 * <p>
 * 1. 任何一个节点都有颜色，黑色或者红色
 * 2. 根节点是黑色的
 * 3. 父子节点之间不能出现两个连续的红节点
 * 4. 任何一个节点向下遍历到其子孙的叶子节点，所经过的黑节点个数必须相等
 * 5. 空节点被认为是黑色的
 *
 * @param <T>
 */
public class RBTree<T extends Comparable<T>> {
    private final RBTreeNode<T> root;
    //node number
    private java.util.concurrent.atomic.AtomicLong size =
            new java.util.concurrent.atomic.AtomicLong(0);

    //in overwrite mode,all node's value can not  has same	value
    //in non-overwrite mode,node can have same value, suggest don't use non-overwrite mode.
    private volatile boolean overrideMode = true;

    public RBTree() {
        this.root = new RBTreeNode<T>();
    }

    public RBTree(boolean overrideMode) {
        this();
        this.overrideMode = overrideMode;
    }


    public boolean isOverrideMode() {
        return overrideMode;
    }

    public void setOverrideMode(boolean overrideMode) {
        this.overrideMode = overrideMode;
    }

    /**
     * number of tree number
     *
     * @return
     */
    public long getSize() {
        return size.get();
    }

    /**
     * get the root node
     *
     * @return
     */
    private RBTreeNode<T> getRoot() {
        return root.getLeft();
    }

    /**
     * add value to a new node,if this value exist in this tree,
     * if value exist,it will return the exist value.otherwise return null
     * if override mode is true,if value exist in the tree,
     * it will override the old value in the tree
     *
     * @param value
     * @return
     */
    public T addNode(T value) {
        RBTreeNode<T> t = new RBTreeNode<T>(value);
        return addNode(t);
    }

    /**
     * find the value by give value(include key,key used for search,
     * other field is not used,@see compare method).if this value not exist return null
     *
     * @param value
     * @return
     */
    public T find(T value) {
        RBTreeNode<T> dataRoot = getRoot();
        while (dataRoot != null) {
            int cmp = dataRoot.getValue().compareTo(value);
            if (cmp < 0) {
                dataRoot = dataRoot.getRight();
            } else if (cmp > 0) {
                dataRoot = dataRoot.getLeft();
            } else {
                return dataRoot.getValue();
            }
        }
        return null;
    }

    /**
     * remove the node by give value,if this value not exists in tree return null
     * 删除操作首先需要做的也是BST的删除操作，删除操作会删除对应的节点，如果是叶子节点就直接删除，如果是非叶子节点，
     * 会用对应的中序遍历的后继节点来顶替要删除节点的位置。删除后就需要做删除修复操作，使的树符合红黑树的定义，符合定义的红黑树高度是平衡的。
     * <p>
     * 删除修复操作在遇到被删除的节点是红色节点或者到达root节点时，修复操作完毕。
     * <p>
     * 删除修复操作是针对删除黑色节点才有的，当黑色节点被删除后会让整个树不符合RBTree的定义的第四条。需要做的处理是从兄弟节点上借调黑色的节点过来，
     * 如果兄弟节点没有黑节点可以借调的话，就只能往上追溯，将每一级的黑节点数减去一个，使得整棵树符合红黑树的定义。
     * <p>
     * 删除操作的总体思想是从兄弟节点借调黑色节点使树保持局部的平衡，如果局部的平衡达到了，就看整体的树是否是平衡的，如果不平衡就接着向上追溯调整。
     * <p>
     * 删除修复操作分为四种情况(删除黑节点后)：
     * 1. 待删除的节点的兄弟节点是红色的节点。
     * 2. 待删除的节点的兄弟节点是黑色的节点，且兄弟节点的子节点都是黑色的。
     * 3. 待调整的节点的兄弟节点是黑色的节点，且兄弟节点的左子节点是红色的，右节点是黑色的(兄弟节点在右边)，如果兄弟节点在左边的话，就是兄弟节点的右子节点是红色的，左节点是黑色的。
     * 4. 待调整的节点的兄弟节点是黑色的节点，且右子节点是是红色的(兄弟节点在右边)，如果兄弟节点在左边，则就是对应的就是左节点是红色的。
     *
     * @param value include search key
     * @return the value contain in the removed node
     */
    public T remove(T value) {
        RBTreeNode<T> dataRoot = getRoot();
        RBTreeNode<T> parent = root;

        while (dataRoot != null) {
            int cmp = dataRoot.getValue().compareTo(value);
            if (cmp < 0) {
                parent = dataRoot;
                dataRoot = dataRoot.getRight();
            } else if (cmp > 0) {
                parent = dataRoot;
                dataRoot = dataRoot.getLeft();
            } else {
                if (dataRoot.getRight() != null) {
                    RBTreeNode<T> min = removeMin(dataRoot.getRight());
                    //x used for fix color balance
                    RBTreeNode<T> x = min.getRight() == null ? min.getParent() : min.getRight();
                    boolean isParent = min.getRight() == null;
                    min.setLeft(dataRoot.getLeft());
                    setParent(dataRoot.getLeft(), min);
                    if (parent.getLeft() == dataRoot) {
                        parent.setLeft(min);
                    } else {
                        parent.setRight(min);
                    }
                    setParent(min, parent);

                    boolean curMinIsBlack = min.isBlack();
                    //inherit dataRoot's color
                    min.setRed(dataRoot.isRed());

                    if (min != dataRoot.getRight()) {
                        min.setRight(dataRoot.getRight());
                        setParent(dataRoot.getRight(), min);
                    }
                    //remove a black node,need fix color
                    if (curMinIsBlack) {
                        if (min != dataRoot.getRight()) {
                            fixRemove(x, isParent);
                        } else if (min.getRight() != null) {
                            fixRemove(min.getRight(), false);
                        } else {
                            fixRemove(min, true);
                        }
                    }
                } else {
                    setParent(dataRoot.getLeft(), parent);
                    if (parent.getLeft() == dataRoot) {
                        parent.setLeft(dataRoot.getLeft());
                    } else {
                        parent.setRight(dataRoot.getLeft());
                    }
                    //current node is black and tree is not empty
                    if (dataRoot.isBlack() && !(root.getLeft() == null)) {
                        RBTreeNode<T> x = dataRoot.getLeft() == null
                                ? parent : dataRoot.getLeft();
                        boolean isParent = dataRoot.getLeft() == null;
                        fixRemove(x, isParent);
                    }
                }
                setParent(dataRoot, null);
                dataRoot.setLeft(null);
                dataRoot.setRight(null);
                if (getRoot() != null) {
                    getRoot().setRed(false);
                    getRoot().setParent(null);
                }
                size.decrementAndGet();
                return dataRoot.getValue();
            }
        }
        return null;
    }

    /**
     * fix remove action
     *
     * @param node
     * @param isParent
     */
    private void fixRemove(RBTreeNode<T> node, boolean isParent) {
        RBTreeNode<T> cur = isParent ? null : node;
        boolean isRed = isParent ? false : node.isRed();
        RBTreeNode<T> parent = isParent ? node : node.getParent();

        while (!isRed && !isRoot(cur)) {
            RBTreeNode<T> sibling = getSibling(cur, parent);
            //sibling is not null,due to before remove tree color is balance

            //if cur is a left node
            boolean isLeft = parent.getRight() == sibling;
            if (sibling.isRed() && !isLeft) {//case 1
                //cur in right
                parent.makeRed();
                sibling.makeBlack();
                rotateRight(parent);
            } else if (sibling.isRed() && isLeft) {
                //cur in left
                parent.makeRed();
                sibling.makeBlack();
                rotateLeft(parent);
            } else if (isBlack(sibling.getLeft()) && isBlack(sibling.getRight())) {//case 2
                sibling.makeRed();
                cur = parent;
                isRed = cur.isRed();
                parent = parent.getParent();
            } else if (isLeft && !isBlack(sibling.getLeft())
                    && isBlack(sibling.getRight())) {//case 3
                sibling.makeRed();
                sibling.getLeft().makeBlack();
                rotateRight(sibling);
            } else if (!isLeft && !isBlack(sibling.getRight())
                    && isBlack(sibling.getLeft())) {
                sibling.makeRed();
                sibling.getRight().makeBlack();
                rotateLeft(sibling);
            } else if (isLeft && !isBlack(sibling.getRight())) {//case 4
                sibling.setRed(parent.isRed());
                parent.makeBlack();
                sibling.getRight().makeBlack();
                rotateLeft(parent);
                cur = getRoot();
            } else if (!isLeft && !isBlack(sibling.getLeft())) {
                sibling.setRed(parent.isRed());
                parent.makeBlack();
                sibling.getLeft().makeBlack();
                rotateRight(parent);
                cur = getRoot();
            }
        }
        if (isRed) {
            cur.makeBlack();
        }
        if (getRoot() != null) {
            getRoot().setRed(false);
            getRoot().setParent(null);
        }

    }

    //get sibling node
    private RBTreeNode<T> getSibling(RBTreeNode<T> node, RBTreeNode<T> parent) {
        parent = node == null ? parent : node.getParent();
        if (node == null) {
            return parent.getLeft() == null ? parent.getRight() : parent.getLeft();
        }
        if (node == parent.getLeft()) {
            return parent.getRight();
        } else {
            return parent.getLeft();
        }
    }

    private boolean isBlack(RBTreeNode<T> node) {
        return node == null || node.isBlack();
    }

    private boolean isRoot(RBTreeNode<T> node) {
        return root.getLeft() == node && node.getParent() == null;
    }

    /**
     * find the successor node
     *
     * @param node current node's right node
     * @return
     */
    private RBTreeNode<T> removeMin(RBTreeNode<T> node) {
        //find the min node
        RBTreeNode<T> parent = node;
        while (node != null && node.getLeft() != null) {
            parent = node;
            node = node.getLeft();
        }
        //remove min node
        if (parent == node) {
            return node;
        }

        parent.setLeft(node.getRight());
        setParent(node.getRight(), parent);

        //don't remove right pointer,it is used for fixed color balance
        //node.setRight(null);
        return node;
    }


    /**
     * RBTree的插入与BST的插入方式是一致的，只不过是在插入过后，可能会导致树的不平衡，
     * 这时就需要对树进行旋转操作和颜色修复（在这里简称插入修复），使得它符合RBTree的定义。
     *
     * 新插入的节点是红色的，插入修复操作如果遇到父节点的颜色为黑则修复操作结束。
     * 也就是说，只有在父节点为红色节点的时候是需要插入修复操作的。
     *
     * 插入修复操作分为以下的三种情况，而且新插入的节点的父节点都是红色的：
     * 1. 叔叔节点也为红色。
     * 2. 叔叔节点为空，且祖父节点、父节点和新节点处于一条斜线上。
     * 3. 叔叔节点为空，且祖父节点、父节点和新节点不处于一条斜线上。
     */
    private T addNode(RBTreeNode<T> node) {
        // 将左子节点置为null
        node.setLeft(null);
        // 将右子节点置为null
        node.setRight(null);
        // 将颜色置为红色
        node.setRed(true);
        // 将父节点置为null
        setParent(node, null);
        // 如果root的左子节点为null
        if (root.getLeft() == null) {

            root.setLeft(node);
            //root node is black
            node.setRed(false);
            size.incrementAndGet();
        } else {
            // 找到父节点
            RBTreeNode<T> x = findParentNode(node);
            // 如果相同就覆盖
            int cmp = x.getValue().compareTo(node.getValue());

            // 判断是否是覆盖模式
            if (this.overrideMode && cmp == 0) {
                T v = x.getValue();
                x.setValue(node.getValue());
                return v;
            } else if (cmp == 0) {
                //value exists,ignore this node
                return x.getValue();
            }

            setParent(node, x);
            // 如果大于就赋给左节点
            if (cmp > 0) {
                x.setLeft(node);
            }
            // 小于赋值给右节点
            else {
                x.setRight(node);
            }

            fixInsert(node);
            size.incrementAndGet();
        }
        return null;
    }

    /**
     * find the parent node to hold node x,if parent value equals x.value return parent.
     *
     * @param x
     * @return
     */
    private RBTreeNode<T> findParentNode(RBTreeNode<T> x) {
        RBTreeNode<T> dataRoot = getRoot();
        RBTreeNode<T> child = dataRoot;

        while (child != null) {
            int cmp = child.getValue().compareTo(x.getValue());
            if (cmp == 0) {
                return child;
            }
            if (cmp > 0) {
                dataRoot = child;
                child = child.getLeft();
            } else if (cmp < 0) {
                dataRoot = child;
                child = child.getRight();
            }
        }
        return dataRoot;
    }

    /**
     * red black tree insert fix.
     *
     * @param x
     */
    private void fixInsert(RBTreeNode<T> x) {
        RBTreeNode<T> parent = x.getParent();

        while (parent != null && parent.isRed()) {
            RBTreeNode<T> uncle = getUncle(x);
            if (uncle == null) {//need to rotate
                RBTreeNode<T> ancestor = parent.getParent();
                //ancestor is not null due to before before add,tree color is balance
                if (parent == ancestor.getLeft()) {
                    boolean isRight = x == parent.getRight();
                    if (isRight) {
                        rotateLeft(parent);
                    }
                    rotateRight(ancestor);

                    if (isRight) {
                        x.setRed(false);
                        parent = null;//end loop
                    } else {
                        parent.setRed(false);
                    }
                    ancestor.setRed(true);
                } else {
                    boolean isLeft = x == parent.getLeft();
                    if (isLeft) {
                        rotateRight(parent);
                    }
                    rotateLeft(ancestor);

                    if (isLeft) {
                        x.setRed(false);
                        parent = null;//end loop
                    } else {
                        parent.setRed(false);
                    }
                    ancestor.setRed(true);
                }
            } else {//uncle is red
                parent.setRed(false);
                uncle.setRed(false);
                parent.getParent().setRed(true);
                x = parent.getParent();
                parent = x.getParent();
            }
        }
        getRoot().makeBlack();
        getRoot().setParent(null);
    }

    /**
     * get uncle node
     *
     * @param node
     * @return
     */
    private RBTreeNode<T> getUncle(RBTreeNode<T> node) {
        RBTreeNode<T> parent = node.getParent();
        RBTreeNode<T> ancestor = parent.getParent();
        if (ancestor == null) {
            return null;
        }
        if (parent == ancestor.getLeft()) {
            return ancestor.getRight();
        } else {
            return ancestor.getLeft();
        }
    }

    private void rotateLeft(RBTreeNode<T> node) {
        RBTreeNode<T> right = node.getRight();
        if (right == null) {
            throw new java.lang.IllegalStateException("right node is null");
        }
        RBTreeNode<T> parent = node.getParent();
        node.setRight(right.getLeft());
        setParent(right.getLeft(), node);

        right.setLeft(node);
        setParent(node, right);

        if (parent == null) {//node pointer to root
            //right  raise to root node
            root.setLeft(right);
            setParent(right, null);
        } else {
            if (parent.getLeft() == node) {
                parent.setLeft(right);
            } else {
                parent.setRight(right);
            }
            //right.setParent(parent);
            setParent(right, parent);
        }
    }

    private void rotateRight(RBTreeNode<T> node) {
        RBTreeNode<T> left = node.getLeft();
        if (left == null) {
            throw new java.lang.IllegalStateException("left node is null");
        }
        RBTreeNode<T> parent = node.getParent();
        node.setLeft(left.getRight());
        setParent(left.getRight(), node);

        left.setRight(node);
        setParent(node, left);

        if (parent == null) {
            root.setLeft(left);
            setParent(left, null);
        } else {
            if (parent.getLeft() == node) {
                parent.setLeft(left);
            } else {
                parent.setRight(left);
            }
            setParent(left, parent);
        }
    }


    private void setParent(RBTreeNode<T> node, RBTreeNode<T> parent) {
        if (node != null) {
            node.setParent(parent);
            if (parent == root) {
                node.setParent(null);
            }
        }
    }

    /**
     * debug method,it used print the given node and its children nodes,
     * every layer output in one line
     *
     * @param root
     */
    public void printTree(RBTreeNode<T> root) {
        java.util.LinkedList<RBTreeNode<T>> queue = new java.util.LinkedList<RBTreeNode<T>>();
        java.util.LinkedList<RBTreeNode<T>> queue2 = new java.util.LinkedList<RBTreeNode<T>>();
        if (root == null) {
            return;
        }
        queue.add(root);
        boolean firstQueue = true;

        while (!queue.isEmpty() || !queue2.isEmpty()) {
            java.util.LinkedList<RBTreeNode<T>> q = firstQueue ? queue : queue2;
            RBTreeNode<T> n = q.poll();

            if (n != null) {
                String pos = n.getParent() == null ? "" : (n == n.getParent().getLeft()
                        ? " LE" : " RI");
                String pstr = n.getParent() == null ? "" : n.getParent().toString();
                String cstr = n.isRed() ? "R" : "B";
                cstr = n.getParent() == null ? cstr : cstr + " ";
                System.out.print(n + "(" + (cstr) + pstr + (pos) + ")" + "\t");
                if (n.getLeft() != null) {
                    (firstQueue ? queue2 : queue).add(n.getLeft());
                }
                if (n.getRight() != null) {
                    (firstQueue ? queue2 : queue).add(n.getRight());
                }
            } else {
                System.out.println();
                firstQueue = !firstQueue;
            }
        }
    }


    public static void main(String[] args) {
        RBTree<String> bst = new RBTree<String>();
        bst.addNode("d");
        bst.addNode("d");
        bst.addNode("c");
        bst.addNode("c");
        bst.addNode("b");
        bst.addNode("f");

        bst.addNode("a");
        bst.addNode("e");

        bst.addNode("g");
        bst.addNode("h");


        bst.remove("c");

        bst.printTree(bst.getRoot());
    }
}