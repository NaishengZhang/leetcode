class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < nums.length -2; j++) {
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        int l = j + 1, r = nums.length -1;
                        while (l < r) {
                            int sum = nums[i] + nums[j] + nums[l] + nums[r];
                            if (sum == target) {
                                ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                                while (l < r && nums[l] == nums[l + 1]) {
                                    l++;
                                }
                                while (l < r && nums[r] == nums[r - 1]) {
                                    r--;
                                }
                                l++;
                                r--;
                            } else if (sum < target) {
                                l++;
                            } else {
                                r--;
                            }
                        }
                    }
                }
            }
        }
        return ans;
        
    }
}