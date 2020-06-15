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
两种替代node的方法
predecessor
successor
Time and space complexities are O(H). H is the height of the tree;
首先使用O(H1)的时间找到要删除的节点，然后再用O(H2)的时间替换该节点。O(H1 + H2) = O(H)
*/
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // TreeNode cur = root;
            // root = min(cur.right);
            // root.right = delMin(cur.right);
            // root.left = cur.left;
            TreeNode cur = root;
            root = max(cur.left);
            root.left = delMax(cur.left);
            root.right = cur.right;
        }
        return root;
    }
    
    private TreeNode max(TreeNode root) {
        if (root.right == null) {
            return root;
        }
        return max(root.right);
    }
    
    private TreeNode delMax(TreeNode root) {
        if (root.right == null) {
            return root.left;
        }
        root.right = delMax(root.right);
        return root;
    }
    
    private TreeNode min(TreeNode root) {
        if (root.left == null) {
            return root;
        }
        return min(root.left);
    }
    
    private TreeNode delMin(TreeNode root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = delMin(root.left);
        return root;
    }
}