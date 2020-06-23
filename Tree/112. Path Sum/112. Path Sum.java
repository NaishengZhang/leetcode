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
recursion => preorder traversal
Time: O(n) Space: O(h) h is the height of the tree from n to logn.
*/
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        int target = sum - root.val;
        if (root.left == null && root.right == null && target == 0) {
            return true;
        }

        return hasPathSum(root.left, target) || hasPathSum(root.right, target);
    }
}

/*
Iteration
Time: O(n) Space: O(h) h from n to logn
*/
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> sumStack = new ArrayDeque<>();
        
        stack.push(root);
        sumStack.push(sum);
        
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            int target = sumStack.pop();
            if (cur.left == null && cur.right == null && target - cur.val == 0) {
                return true;
            }
            if (cur.right != null) {
                stack.push(cur.right);
                sumStack.push(target - cur.val);
            }
            if (cur.left != null) {
                stack.push(cur.left);
                sumStack.push(target - cur.val);
            }
        }
        return false;
    }
}