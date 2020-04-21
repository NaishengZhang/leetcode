/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// dfs time: O(n^2) space:O(n^2) 字符串拼接时间复杂度是n不是1
class Solution {
    List<TreeNode> res = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) {
            return res;
        }
        collect(root);
        return res;
    }
    
    private String collect(TreeNode node) {
        if (node == null) {
            return "";
        }
        
        String s = node.val + "," + collect(node.left) + "," + collect(node.right);
        map.put(s, map.getOrDefault(s, 0) + 1);
        if (map.get(s) == 2) {
            res.add(node);
        }
        return s;
    }
}

// Time: O(n) Space: O(n)
class Solution {
    int t = 1;
    Map<String, Integer> trees = new HashMap<>();
    Map<Integer, Integer> count = new HashMap<>();
    List<TreeNode> res = new ArrayList<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) {
            return res;
        }
        lookup(root);
        return res;
    }
    
    private int lookup(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        
        int uid = trees.computeIfAbsent(serial, x->t++);
        count.put(uid, count.getOrDefault(uid, 0) + 1);
        if (count.get(uid) == 2) {
            res.add(node);
        }
        return uid;
    }
}