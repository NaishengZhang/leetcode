// 12345 => 12354
// 12431 => 13124
class Solution {
    public void nextPermutation(int[] nums) {

        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        int n = nums.length;
        
        int small = -1;
        
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                small = i;
                break;
            }
        }
        if (small == -1) {
            reverse(nums, 0, n - 1);
            return;
        }
        
        int big = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > nums[small]) {
                big = i;
                break;
            }
        }
        swap(nums, big, small);
        reverse(nums, small + 1, n - 1);
        
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}