package com.mistray.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.tree
 * @create 2020年03月06日 14:47
 * @Desc
 * 给定一个二叉树，返回它的 后序 遍历。
 * 前序: 根左右
 * 中序: 左根右
 * 后序: 左右根
 */
public class BinaryTreePostorderTraversal145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null){
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(root);
        while (stack1.size() > 0) {
            TreeNode pop = stack1.pop();
            stack2.add(pop);
            if (pop.left != null) {
                stack1.add(pop.left);
            }
            if (pop.right != null) {
                stack1.add(pop.right);
            }
        }

        while (stack2.size() > 0) {
            list.add(stack2.pop().val);
        }
        return list;
    }
}
