package com.mistray.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.tree
 * @create 2020年03月06日 13:54
 * @Desc
 */
public class BinaryTreeInorderTraversal94 {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        BinaryTreeInorderTraversal94 bin = new BinaryTreeInorderTraversal94();
        List<Integer> integers = bin.inorderTraversal(root);

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (stack.size() > 0 || root != null) {
            if (root != null) {
                stack.add(root);
                root = root.left;
            } else {
                TreeNode pop = stack.pop();
                list.add(pop.val);
                root = pop.right;
            }
        }
        return list;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }


    public void helper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }
}
