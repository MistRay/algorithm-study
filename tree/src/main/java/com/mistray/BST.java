package com.mistray;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray
 * @create 2019年10月17日 10:45
 * @Desc 二叉查找树
 */
public class BST<Key extends Comparable<Key>, Value> {
    //二叉树查找根节点
    private Node root;

    private class Node {
        // 键
        private Key key;
        // 值
        private Value value;
        // 执行子树的连接
        private Node left, right;
        // 以该节点为根的子树中的总结点数
        private int N;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    /**
     * 获取根节点的子树中总结点数
     *
     * @return 根节点的子树找那个总结点数
     */
    public int size() {
        return size(root);
    }

    /**
     * 获取x节点的子树中总结点数
     *
     * @param x 节点
     * @return 子树中总结点数
     */
    public int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node x, Key key) {
        // 在以x为根节点的子树中查找并返回key所对应的的值
        // 找不到就返回null
        if (x == null) {
            return null;
        }
        int compare = key.compareTo(x.key);
        // 如果查询元素比key小,则找左子树
        if (compare < 0) {
            // 递归找左子树
            return get(x.left, key);
        }
        // 如果查询元素比key大,则找右子树
        else if (compare > 0) {
            return get(x.right, key);
        }
        // 如果查找元素和key相同,则直接返回
        else {
            return x.value;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    public Node put(Node x, Key key, Value value) {
        // 如果key存在于以x为根节点的子树中则更新它的值
        // 否则将以key和val为键值对的新节点插入到该子树中
        if (x == null) {
            return new Node(key, value, 1);
        }
        // 将key与x节点对比.
        int compare = key.compareTo(x.key);
        if (compare < 0) {
            x.left = put(x.left, key, value);
        } else if (compare > 0) {
            x.right = put(x.right, key, value);

            x.value = value;
        }
        // 修改当前节点的size为左边子树节点数+右侧子树节点数+1;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    public Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x);
        }
    }

    // 向下取整
    private Node floor(Node x, Key key) {
        // 如果根节点为null,则直接返回null.
        if (x == null) {
            return null;
        }
        // key与根节点比较大小
        int compare = key.compareTo(x.key);
        // 如果key == 根节点的key,直接返回
        if (compare == 0) {
            return x;
        }
        // 如果key小于根节点的key,则往左边找.
        else if (compare < 0) {
            return floor(x.left, key);
        }
        // 左边已经没有符合要求的同时,key也不等于root.
        // 把当前节点右侧的节点按上面的逻辑的递归一下
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }


}
