package com.mistray.tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.tree
 * @create 2020年03月05日 14:02
 * @Desc
 */
public class FlattenBinaryTreeToLinkedList114 {

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        dsf(root, list);
        TreeNode head = list.removeFirst();
        head.left = null;
        while (list.size() > 0) {
            TreeNode cur = list.removeFirst();
            cur.left = null;
            head.right = cur;
            head = cur;
        }

    }

    public void flatten2(TreeNode root) {
        contrary(root);
    }


    TreeNode pre = null;

    public void contrary(TreeNode root) {
        if (root == null) {
            return;
        }
        contrary(root.right);
        contrary(root.left);
        root.left = null;
        root.right = pre;
        pre = root;
    }


    public void dsf(TreeNode node, LinkedList<TreeNode> list) {
        if (node == null) {
            return;
        }
        list.add(node);
        dsf(node.left, list);
        dsf(node.right, list);
    }

}
