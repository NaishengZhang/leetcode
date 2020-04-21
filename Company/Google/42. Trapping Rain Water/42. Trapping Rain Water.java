// Monotonic stack time:O(n) space:O(n)
class Solution {
    public int trap(int[] height) { //对于任意一点，找左右第一个比这个点大的值；
        if (height == null || height.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>(); //递减
        int n = height.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int t = stack.pop();
                System.out.println(t);
                if (stack.isEmpty()) { // 前边没有比t刚刚大的数
                    continue;
                }
                //每个元素出栈的时候，栈顶是左边刚刚大的数，i是右边刚刚大的数。
                res += (i - stack.peek() - 1) * (Math.min(height[stack.peek()], height[i]) - height[t]);
            }
            stack.push(i);
        }
        return res;
    }
}

//https://www.youtube.com/watch?v=8BHqSdwyODs
// two pointer time: O(n) space: O(1)
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int n = height.length;
        int res = 0;
        int lMax = height[0];
        int rMax = height[n - 1];
        
        int i = 0;
        int j = n - 1;
        
        while(i < j) {
            lMax = Math.max(height[i], lMax);
            rMax = Math.max(height[j], rMax);
            
            if (lMax < rMax) { //左边小，那么l的最大水量是确定的
                res += lMax - height[i];
                i++;
            } else {
                res += rMax - height[j];
                j--;
            }
        }
        
        return res;
    }
}