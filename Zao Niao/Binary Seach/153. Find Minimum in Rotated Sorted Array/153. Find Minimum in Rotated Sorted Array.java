class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int l = 0, r = nums.length - 1;
        int end = nums[r];
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > end) { //前半段
                l = mid;
            } else if (nums[mid] < end) { //后半段
               r = mid; 
            }
        }
        return Math.min(nums[l], nums[r]);
    }
}