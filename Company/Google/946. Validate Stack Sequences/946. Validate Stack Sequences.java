class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = popped.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int j = 0;
        for (int num : pushed) {
            stack.push(num);
            while(!stack.isEmpty() && j < n && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
            
        }

        return j == n;
    }
}