package com.mistray.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.tree
 * @create 2020年03月06日 14:42
 * @Desc
 * 给定一个二叉树的根节点 root ，返回它的 前序 遍历。
 * 前序: 根左右
 * 中序: 左根右
 * 后序: 左右根
 */
public class BinaryTreePreorderTraversal144 {


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root,list);
        return list;
    }

    public void helper(TreeNode node, List<Integer> list) {
        if (node == null){
            return;
        }
        list.add(node.val);
        helper(node.left,list);
        helper(node.right,list);
    }
}
