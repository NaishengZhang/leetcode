class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;
        int small = Integer.MIN_VALUE;
        int large = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                small = i;
                break;
            }
        }
        if (small == Integer.MIN_VALUE) {
            reverse(nums, 0, n -1);
            return;
        }
        for (int i = n - 1; i > small; i--) {
            if (nums[i] > nums[small]) {
                large = i;
                break;
            }
        }
        
        swap(nums, small, large);
        reverse(nums, small + 1, n - 1);
        return;
    }
    
    private void swap(int[] nums, int small, int large) {
        int temp = nums[small];
        nums[small] = nums[large];
        nums[large] = temp;
    }
    
    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}