package com.mistray.tree;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.tree
 * @create 2020年03月05日 16:57
 * @Desc
 */
public class InvertBinaryTree226 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.right.left = new TreeNode(6);
        invertTree(root);
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.right = left;
        root.left = right;
        return root;
    }
}
