package com.mistray.tree;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.tree
 * @create 2020年05月21日 13:22
 * @Desc
 */
public class LowestCommonAncestorOfABinarySearchTree235 {

    // 思路,根节点比两个点都大,去左树查找
    // 根节点比两个都小,去右边查找
    // 否则,直接返回根节点
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left,p,q);
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right,p,q);
        }
        return root;
    }

}
