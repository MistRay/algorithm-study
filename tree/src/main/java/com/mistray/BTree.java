package com.mistray;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray
 * @create 2019年10月17日 14:59
 * @Desc B Tree 实现
 */
public class BTree<Key extends Comparable<Key>, Value> {

    // 每个节点最多有几个子节点
    private static final int M = 4;
    // 根节点
    private Node root;
    // 树高
    private int height;
    // 当前节点的子节点的number
    private int n;

    private static final class Node {
        private int m;
        // 子节点数组
        private Entry[] children = new Entry[M];

        private Node(int k) {
            this.m = k;
        }
    }

    private static class Entry {
        private Comparable key;
        private Object val;
        // 下一个节点
        private Node next;

        public Entry(Comparable key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public BTree() {
        root = new Node(0);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // 获取子节点数
    public int size() {
        return n;
    }

    // 获取树高
    public int height() {
        return height;
    }

    public Value get(Key key) {
        // 查询key
        if (null == key) {
            throw new RuntimeException("key为nul");
        }
        int h = height;
        return search(root, key, h);
    }

    @SuppressWarnings("unchecked")
    private Value search(Node node, Key key, int height) {
        Entry[] children = node.children;
        // 说明树为最底层,遍历root节点的所有子节点即可
        if (height == 0) {
            for (int i = 0; i < node.m; i++) {
                if (eq(children[i].key, key)) {
                    return (Value) children[i].val;
                }
            }
        } else {
            for (int i = 0; i < node.m; i++) {
                // 如果当前node中key比输入的key小的话,则递归,查找下一个
                if (i + 1 == node.m || less(key, children[i + 1].key)) {
                    // 递归调用查找下一个node
                    search(children[i].next, key, height - 1);
                }
            }
        }
        return null;
    }

    public void put(Key key, Value value) {

        if (key == null) {
            throw new NullPointerException("key must not be null");
        }
        Node u = insert(root, key, value, height); //分裂后生成的右结点
        n++;
        if (u == null) {
            return;
        }
        // need to split root重组root
        Node t = new Node(2);
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(u.children[0].key, null, u);
        root = t;
        height++;
    }

    private Node insert(Node node, Key key, Value value, int height) {
        int j;
        Entry entry = new Entry(key, value, null);
        // external node外部结点，也是叶子结点，在树的最底层，存的是内容value
        if (height == 0) {
            for (j = 0; j < node.m; j++) {
                if (less(key, node.children[j].key)) {
                    break;
                }
            }
        }
        // internal node内部结点，存的是next地址
        else {
            for (j = 0; j < node.m; j++) {
                // 如果当前node.Entry[i + 1]的key比输入的key小的话
                if ((j + 1 == node.m) || less(key, node.children[j + 1].key)) {
                    Node u = insert(node.children[j++].next, key, value, height - 1);
                    if (u == null) {
                        return null;
                    }
                    // 获取到数据内容.
                    entry.key = u.children[0].key;
                    entry.next = u;
                    break;
                }
            }
        }
        // 把j后面的元素右移
        for (int i = node.m; i > j; i--) {
            node.children[i] = node.children[i - 1];
        }
        // 把新元素添加到children[j]上
        node.children[j] = entry;
        // 元素数量+1
        node.m++;
        // 判断node内节点数是否超出约束节点个数
        if (node.m < M) {
            return null;
        } else {
            // 分裂节点
            return split(node);
        }
    }


    // split node in half
    private Node split(Node h) {
        Node t = new Node(M / 2);
        h.m = M / 2;
        for (int j = 0; j < M / 2; j++) {
            t.children[j] = h.children[M / 2 + j];
        }
        return t;
    }


    /**
     * Returns a string representation of this B-tree (for debugging).
     *
     * @return a string representation of this B-tree.
     */
    @Override
    public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(Node h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.children;

        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s.append(indent + children[j].key + " " + children[j].val + "\n");
            }
        } else {
            for (int j = 0; j < h.m; j++) {
                if (j > 0) {
                    s.append(indent + "(" + children[j].key + ")\n");
                }
                s.append(toString(children[j].next, ht - 1, indent + "   "));
            }
        }
        return s.toString();
    }

    /**
     * Unit tests the {@code BTree} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        BTree<String, String> st = new BTree<String, String>();

        st.put("www.cs.princeton.edu", "128.112.136.12");
        st.put("www.cs.princeton.edu", "128.112.136.11");
        st.put("www.princeton.edu", "128.112.128.15");
        st.put("www.yale.edu", "130.132.143.21");
        st.put("www.simpsons.com", "209.052.165.60");
        st.put("www.apple.com", "17.112.152.32");
        st.put("www.amazon.com", "207.171.182.16");
        st.put("www.ebay.com", "66.135.192.87");
        st.put("www.cnn.com", "64.236.16.20");
        st.put("www.google.com", "216.239.41.99");
        st.put("www.nytimes.com", "199.239.136.200");
        st.put("www.microsoft.com", "207.126.99.140");
        st.put("www.dell.com", "143.166.224.230");
        st.put("www.slashdot.org", "66.35.250.151");
        st.put("www.espn.com", "199.181.135.201");
        st.put("www.weather.com", "63.111.66.11");
        st.put("www.yahoo.com", "216.109.118.65");


        System.out.println("cs.princeton.edu: " + st.get("www.cs.princeton.edu"));
        System.out.println("hardvardsucks.com: " + st.get("www.harvardsucks.com"));
        System.out.println("simpsons.com:   " + st.get("www.simpsons.com"));
        System.out.println("apple.com:     " + st.get("www.apple.com"));
        System.out.println("ebay.com:     " + st.get("www.ebay.com"));
        System.out.println("dell.com:     " + st.get("www.dell.com"));
        System.out.println();

        System.out.println("size:  " + st.size());
        System.out.println("height: " + st.height());
        System.out.println(st);
        System.out.println();
    }


    @SuppressWarnings("unchecked")
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    @SuppressWarnings("unchecked")
    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }
}
