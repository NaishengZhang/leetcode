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
第一个想法是用递归判断当前节点是否大于左右子孩子，这种方法是错误的，因为bst的定义是 当前节点比左子树上所有节点都要大，比右子树所有节点都要小。

所以可以设置一个有限范围，判断当前节点是否在这个范围内，如果在，则是一个bst的节点。
Time: O(n) Space: O(h)
*/
class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean dfs(TreeNode root, long min, long max) { 
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }
}

/*
利用二叉树的性质：二叉树的中序遍历结果是一个从小到大排序的数组。
设置一个全局变量来记录中序遍历的前一个节点，并与当前节点的值进行比较，如果当前节点大于前一个节点，那么符合要求。
*/
class Solution {
    private long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        //left
        if (!isValidBST(root.left)) {
            return false;
        }
        
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        //right 
        return isValidBST(root.right);
    }
}
/*
复习中序遍历的迭代写法
*/
class Solution {
    private long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) {
            return true;
        }
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null){
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (cur.val <= pre) {
                return false;
            }
            pre = cur.val;
            cur = cur.right;
        }
        return true;
    }
}