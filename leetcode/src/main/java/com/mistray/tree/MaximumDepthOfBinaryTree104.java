package com.mistray.tree;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.tree
 * @create 2020年03月06日 11:39
 * @Desc
 */
public class MaximumDepthOfBinaryTree104 {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return max(root, 0, 0);
    }

    // 获取当前树的最大深度
    public int max(TreeNode node, int level, int maxLevel) {
        // 当前深度+1
        int curLevel = level + 1;
        // 当前深度如果比最大深度要大的话,更新最大深度
        if (curLevel > maxLevel) {
            maxLevel = curLevel;
        }
        // 如果当前节点的左节点不为空的话递归,直到左节点的尽头
        if (node.left != null) {
            maxLevel = max(node.left, curLevel, maxLevel);
        }
        // 第一次执行时到这里时,为左节点的尽头,已经没有更左的节点
        if (node.right != null) {
            maxLevel = max(node.right, curLevel, maxLevel);
        }
        return maxLevel;
    }


}
