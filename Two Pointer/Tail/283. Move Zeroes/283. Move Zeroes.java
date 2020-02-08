//比下边版本更简洁
public class Solution {
    public void moveZeroes(int[] nums) {
        int tail = 0;//tail一直指向可以被替换的地方
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[tail];
                nums[tail] = temp;
                tail++;
            }
        }
    }
}

public class Solution {
    public void moveZeroes(int[] nums) {
        int pos = 0;//可以被替换的地方
        for (int n : nums) {
            if (n != 0) {
                nums[pos++] = n;
            } 
        }
        for (int i = pos; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}