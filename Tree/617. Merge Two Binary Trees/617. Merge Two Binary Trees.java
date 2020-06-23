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
recursive
*/
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}

class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);
        return root;
    }
}

/*
iterative
*/
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        Deque<TreeNode[]> stack = new ArrayDeque<>();
        TreeNode[] root = new TreeNode[]{t1, t2};
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode[] cur = stack.pop();
            if (cur[0] == null || cur[1] == null) {
                continue;
            }
            cur[0].val += cur[1].val;
            if (cur[0].left == null) {
                cur[0].left = cur[1].left;
            } else {
                stack.push( new TreeNode[]{cur[0].left, cur[1].left});
            }
            
            if (cur[0].right == null) {
                cur[0].right = cur[1].right;
            } else {
                stack.push(new TreeNode[]{cur[0].right, cur[1].right});
            }
        }  
        return t1;
    }
    
}