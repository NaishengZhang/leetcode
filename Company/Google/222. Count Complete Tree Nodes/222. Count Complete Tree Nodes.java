/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// O(n)
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        
        return left + right + 1;
    }
}



// O(d^2) d is the depth of the tree, (depth start from 0) => O(1)
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode node = root;
        int d = 0;
        while (node.left != null) {
            d++;
            node = node.left;
        }
        
        int left = 1, right = (int)Math.pow(2, d) - 1;
        int mid = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            
            if (exist(mid, d, root)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int)Math.pow(2, d) - 1 + left;
    }
    
    private boolean exist(int idx, int d, TreeNode node) {
        int left = 0, right = (int)Math.pow(2, d) - 1;
        int mid = -1;
        for(int i = 0; i < d; ++i) {
            mid = left + (right - left) / 2; 
            //因为mid总是指向前一半树的最后一个节点，所以左右指针移动的时候left = mi d + 1 => left到后一半树的第一个节点， right = mid =>右指针到前一半树最后一个节点
            if (idx <= mid) {
                node = node.left;
                right = mid;
            } else {
                node = node.right;
                left = mid + 1;
            }
            

        }
        return node != null;
    }
}