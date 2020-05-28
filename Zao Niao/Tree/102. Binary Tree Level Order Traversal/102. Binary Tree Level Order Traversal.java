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
BFS Time and Space O(N)
*/
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return res;
        }
        queue.offer(root);
        int length = -1;
        while (!queue.isEmpty()) {
            length = queue.size();
            List<Integer> sub = new ArrayList<>();
            while (length > 0) {
                TreeNode cur = queue.poll();
                sub.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                length--;
            }
            res.add(sub);
        }
        return res;
    }
    
}

/*
dfs Time and Space O(n), memory stack h = logn
*/
class Solution {
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        helper(res, root, 0);
        return res;
    }
    
    private void helper(List<List<Integer>> res, TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (level == res.size()) {
            res.add(new ArrayList<>());
        }
        
        res.get(level).add(node.val);
        
        if (node.left != null) {
            helper(res, node.left, level + 1);
        }
        
        if (node.right != null) {
            helper(res, node.right, level + 1);
        }
    }
}