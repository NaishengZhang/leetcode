//Two Pointer
class Solution {
    public void sortColors(int[] nums) {
        // 0 1 2 2 1 0 0 1
        int zero = 0, cur = 0, two = nums.length - 1;
        
        while (cur <= two) {
            if (nums[cur] == 0) {
                swap(nums, zero, cur);
                zero++;
                cur++;
            } else if (nums[cur] == 2) {
                swap(nums, two, cur);
                two--;
            } else {
                cur++;
            }
            
        }
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
//counting sort
class Solution {
    public void sortColors(int[] nums) {
        //couting sort
        if (nums == null || nums.length < 2) {
            return;
        }
        int[] count = new int[3];
        for(int i = 0; i < nums.length; i++) {
            count[nums[i]] += 1;
        }
        // for (int n : nums) {
        //     count[n] += 1;
        // }
        int k = 0;
        for (int j = 0; j < 3; j++) {
            while(count[j] > 0) {
                nums[k++] = j;
                count[j] -= 1;
            }
        } 
    }
}