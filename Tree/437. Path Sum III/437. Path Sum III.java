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
dfs的作用是，找从root开始并且和为sum的路径个数。
pathSum是找不一定从root开始但和为sum的路径个数。
对于root来说，想找pathsum，就有两种情况，一种是从root出发的路径，另外一种就是不由root出发。
不由root就是子问题，左右节点下的路径个数。 so dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
Time O(n^2) space O(h) h is from logn to n
*/
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum -= root.val;
        // number of path = root + left + right;
        return (sum == 0 ? 1 : 0) + dfs(root.left, sum) + dfs(root.right, sum);
    }
}

/*
time: O(n) space O(n)
*/
class Solution {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); //root curSum
        return dfs(root, sum, 0, map); // map => key=curSum; value=frequence
    }
    
    private int dfs(TreeNode root, int sum, int curSum, Map<Integer, Integer> map) {
        // base case
        if (root == null) {
            return 0;
        }
        
        curSum += root.val;
        int numOfPaths = map.getOrDefault(curSum - sum, 0);
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        
        numOfPaths += dfs(root.left, sum, curSum, map);
        numOfPaths += dfs(root.right, sum, curSum, map);
        
        map.put(curSum, map.getOrDefault(curSum, 0) - 1);
        
        return numOfPaths;
    }
}


