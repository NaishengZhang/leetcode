class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1, r = nums.length - 1, sum = 0;
            while (l < r) {
                sum = nums[i] + nums[l] + nums[r];
                if (sum < target) {
                    res += r - l;
                    l++;
                } else {
                    r--;
                }
            } 
        }
        return res;
    }
}