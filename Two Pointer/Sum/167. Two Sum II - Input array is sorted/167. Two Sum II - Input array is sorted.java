class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return new int[]{-1, -1};
        }
        int lo = 0, hi = numbers.length - 1, sum = -1;
        while (lo < hi) {
            sum = numbers[hi] + numbers[lo];
            if (sum < target) {
                lo++;
            } else if (sum > target) {
                hi--;
            } else {
                return new int[]{lo + 1, hi + 1};
            }
        }
        return new int[]{-1, -1};

    }
}