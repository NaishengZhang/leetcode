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
Recursion
ref https://charlesliuyx.github.io/2018/10/22/【直观算法】树的基本操作/#广度遍历
*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root);
        return res;
    }
    
    private void helper(List<Integer> res, TreeNode node) {
        if (node == null) {
            return;
        }
        helper(res, node.left);
        res.add(node.val);
        helper(res, node.right);
        
    }
    
}

/*
Iteration
Ref: https://blog.csdn.net/zhangxiangDavaid/article/details/37115355
*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}

