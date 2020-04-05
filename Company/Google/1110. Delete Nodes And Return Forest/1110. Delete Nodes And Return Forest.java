/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    HashSet<Integer> set = new HashSet<>();
    List<TreeNode> res = new ArrayList<>();
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        
        for (int x : to_delete) {
            set.add(x);
        }
        helper(root, true);
        return res;
    }
    
    private TreeNode helper (TreeNode node, boolean isRoot) {
        if (node == null) {
            return null;
        }
        
        boolean deleted = set.contains(node.val);
        
        if (isRoot && !deleted) {
            res.add(node);
        }
        node.left = helper(node.left, deleted);
        node.right = helper(node.right, deleted);
        return deleted ? null : node;
    }
    
}