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
这道题有点难点在于什么时候【状态重置】
第一种解法中，在return之后的上一层状态重置
第二种解法中，在本层移除本层的节点，也就是在本层状态重置，所以也就带来一个问题，base case中需要【状态重置】
第三种解法中，不需要path.remove()， 因为每次向下传递都复制整个path，所以不需要手动【状态重置】
*/
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), root, sum);
        return res;        
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> path, TreeNode node, int target) {
        path.add(node.val);
        
        if (node.left == null && node.right == null && target - node.val == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
  
        if (node.left != null) {
            dfs(res, path, node.left, target - node.val);
            path.remove(path.size() - 1); // 删除的是下一层中（dfs后）添加的node
        }
        
        if (node.right != null) {
            dfs(res, path, node.right, target - node.val);
            path.remove(path.size() - 1);
        }
    }
}

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), root, sum);
        return res;        
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> path, TreeNode node, int target) {
        // base case1
        if (node == null) {
            return;
        }

        // base case2
        if (node.left == null && node.right == null) {
            if (target - node.val == 0) {
                path.add(node.val);
                res.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
            }
            return;
        }
        

        path.add(node.val);
        dfs(res, path, node.left, target - node.val);
        dfs(res, path, node.right, target - node.val);
        path.remove(path.size() - 1); //删除的是本层的节点
        
    }
}


public class Solution {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 从根结点到叶子结点的路径
        List<Integer> path = new ArrayList<>();
        dfs(root, sum, path, res);
        return res;
    }

    private void dfs(TreeNode root, int sum, List<Integer> path, List<List<Integer>> res) {
        if (root == null) {
            return;
        }

        path.add(root.val);
        sum -= root.val;

        if (root.left == null && root.right == null) {
            if (sum == 0) {
                // 正是因为每一次向下传递的过程中复制整个列表，在叶子结点出直接添加即可
                res.add(path);
                return;
            }
        }

        // 基本数据类型在方法传递过程中的行为是是复制
        // new ArrayList<>() 每一次向下传递的过程中复制整个列表，低效
        dfs(root.left, sum, new ArrayList<>(path), res);
        dfs(root.right, sum, new ArrayList<>(path), res);
        
        // 在递归结束以后无需「状态重置」
    }
}