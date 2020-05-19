/*
参考快排partition，Java Arrays.sort()源码
Time: on pass O(n) 
Space: in-place
*/

/*
 * Partitioning:
 *
 *   left part           center part                   right part
 * +--------------------------------------------------------------+
 * |  0          |               1          |    ?    |     2     |
 * +--------------------------------------------------------------+
 *               ^                          ^          ^
 *               |                          |          |
 *              zero                        i         two
 *
 * Invariants:
 *
 *              all in [0, zero]       0   => 初始区间应该为空，所以zero = -1 => 先zero++再swap
 *              all in (zero, i)       1   
 *              all in [two, length)   2   => 初始区间应该为空，所以two = length => 先two--再swap
 *
 * Pointer i is the first index of ?-part.
 * while循环条件是 i < two, 当i=two时，三个子区间覆盖整个数组
 *
 *
 *
 */
class Solution {
    public void sortColors(int[] nums) {
        
        int n = nums.length;
        int zero = -1, two = n, i = 0;
        
        while (i < two) {
            if (nums[i] == 0) {
                zero++;
                swap(nums, zero, i);
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums, two, i);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}