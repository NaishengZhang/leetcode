class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> temp = new ArrayList<>();//浅拷贝 深拷贝
    public List<List<Integer>> permute(int[] nums) {
        
        if (nums == null || nums.length == 0) {
            return res;
        }
        // List<Integer> temp = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, temp, visited);
        return res;
    }
    
    private void dfs(int[] nums, List<Integer> temp, boolean[] visited) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            temp.add(nums[i]);
            visited[i] = true;
            dfs(nums, temp, visited);
            temp.remove(temp.size() -  1);
            visited[i] = false;
        }
    }
}


class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> temp = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, temp, visited);
        return res;
    }
    
    private void dfs(int[] nums, List<Integer> temp, boolean[] visited) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp)); //深拷贝
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            temp.add(nums[i]);
            visited[i] = true;
            dfs(nums, temp, visited);
            temp.remove(temp.size() -  1);
            visited[i] = false;
        }
    }
}