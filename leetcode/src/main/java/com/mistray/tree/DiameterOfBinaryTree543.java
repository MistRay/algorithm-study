package com.mistray.tree;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.tree
 * @create 2020年03月06日 17:58
 * @Desc
 */
public class DiameterOfBinaryTree543 {

    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        max(root);
        return max;
    }

    public int max(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int L = max(root.left);
        int R = max(root.right);
        max = Math.max(max, L + R);
        // 返回的是当前节点子树的最大深度,左节点和右节点对比后得到较大值后再加一.
        // 加一的原因是,需要算当前树高.
        return Math.max(L, R) + 1;
    }
}
