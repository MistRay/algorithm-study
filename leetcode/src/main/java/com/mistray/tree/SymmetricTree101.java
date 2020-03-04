package com.mistray.tree;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.tree
 * @create 2020年03月04日 13:31
 * @Desc
 */
public class SymmetricTree101 {

    public static void main(String[] args) {

    }

    public boolean isSymmetric(TreeNode root) {
       return helper(root,root);
    }

    public boolean helper(TreeNode t1, TreeNode t2) {
        if ((t2 != null && t1 != null) && t1.val == t2.val) {
            return helper(t1.right, t2.left) && helper(t1.left, t2.right);
        } else if (t1 == null && t2 == null) {
            return true;
        } else {
            return false;
        }
    }
}
