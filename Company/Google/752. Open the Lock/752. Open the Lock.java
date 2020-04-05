class Solution {
    public int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        HashSet<String> dead = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        int level = 0;
        String start = "0000";
        for (String s : deadends) {
            dead.add(s);
        }
        if (target == null || target.length() == 0 || dead.contains(start)) {
            return -1;
        }
        
        queue.add(start);
        visited.add(start);
        
        while (!queue.isEmpty()) {
            int len = queue.size();
            level++;
            for (int i = 0; i < len; i++) {
                String node = queue.poll();
                for (int k = 0; k < 4; k++) {
                    for (int j = -1; j <= 1; j += 2) {
                        char[] arr = node.toCharArray();
                        arr[k] = (char)(((arr[k] - '0') + j + 10) % 10 + '0');
                        String next = new String(arr);
                        if (next.equals(target)) {
                            return level;
                        }
                        if (dead.contains(next) || visited.contains(next)){
                            continue;
                        }
                        queue.add(next);
                        visited.add(next);
                    }
                }
            }
        }
        
        return -1;
    }
}