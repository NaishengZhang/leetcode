/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/*
自顶向下
对每个节点的左右孩子都求一次maxDepth，有大量的重复
time O(nlogn) => O(n)  + 2*O(n/2) + 4*O(n/4) + ....
space O(h)
*/
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return 1 + Math.max(r, l);
    }
}

/*
自底向上
在计算每个节点的最大高度时，就已经知道这个节点的左右子树的maxdepth，这样就可以边计算maxDepth边判断是否是平衡的。return -1 来表示已经出现不平衡的子树，向上传递信息。
Time O(n)
Space O(h)
*/
class Solution {
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = maxDepth(root.left);
        if (l == -1) {
            return -1;
        }
        int r = maxDepth(root.right);
        if (r == -1 || Math.abs(l - r) > 1) {
            return -1;
        }
        return 1 + Math.max(l, r);
    }
}
