// Time: O(n) Mem: O(n) use hashmap
class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(); // <sum, index>
        
        int sum = 0;
        int res = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 1 ? 1 : -1;
            if (sum == 0) {
                res = Math.max(res, i + 1);
            } else if (map.containsKey(sum)) {
                res = Math.max(res, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return res;
    }
}


// Time: O(n) Mem: O(n) use array to represent hashtable is faster than hashtable. no need to calculate hash value
class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] count = new int[2*n + 1]; //map：sum最大是n（全是1）， 最小是-n，所以加一个offset n： 0-2n+1
        Arrays.fill(count, Integer.MIN_VALUE);
        int res = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i] == 1 ? 1 : -1;
            if (sum == 0) {
                res = Math.max(res, i + 1);
            } else if (count[sum + n] != Integer.MIN_VALUE) {
                res = Math.max(res, i - count[sum + n]);
            } else {
                count[sum + n] = i;
            }
        }
        return res;
    }
}