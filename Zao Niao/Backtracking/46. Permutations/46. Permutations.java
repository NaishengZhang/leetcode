/*
https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
Backtracking
Time: 
非叶子节点个数： N! <= 1 + An1 + An2 + .... + Ann-1(An2,从n中选2个) <= N*N!
叶子节点个数: N! 每个节点都做一次深拷贝O(n)

Space: n factorial
N! since one has to keep N! solutions.
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        boolean[] visited = new boolean[nums.length];
        dfs(nums, res, new ArrayList<>(), visited);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> sub, boolean[] visited ) {
        // base case
        if (sub.size() == nums.length) {
            res.add(new ArrayList<>(sub));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            //choose
            sub.add(nums[i]);
            visited[i] = true;

            //explore
            dfs(nums, res, sub, visited);

            //unchoose
            sub.remove(sub.size() - 1);
            visited[i] = false;
        }
        
    }
}