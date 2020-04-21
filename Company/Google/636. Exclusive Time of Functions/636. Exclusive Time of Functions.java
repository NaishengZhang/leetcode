// time: O(n), space: O(n/2)
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[n];
        
        int pre = 0, cur = 0, id = -1;
        for (String log : logs) {
            String[] c = log.split(":");
            id = Integer.valueOf(c[0]);
            cur = Integer.valueOf(c[2]);
            String type = c[1];
            if (type.equals("start")) {
                if (!stack.isEmpty()) {
                    res[stack.peek()] += cur - pre; // pend 
                }
                stack.push(id);
                pre = cur;
            } else {
                
                res[stack.pop()] += cur - pre + 1;
                pre = cur + 1;
            }
        }
        return res;
    }
}   
