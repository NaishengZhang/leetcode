//缺点：加入1s内进来了1000个hits， 那么我们queue里面就有1000个timestamp，在调用getHits(301), 也就需要删除1000个timestamp， 这样严重影响性能。
class HitCounter {
    private Queue<Integer> queue;

    /** Initialize your data structure here. */
    public HitCounter() {
        queue  = new LinkedList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        queue.add(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!queue.isEmpty()) {
            if (timestamp - queue.peek() >= 300) {
                queue.poll();
            } else {
                break;
            }
        }
        return queue.size();
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */


//Followup: What if the number of hits per second could be very large? Does your design scale?
class HitCounter {
    private int[] times;
    private int[] hits;
    /** Initialize your data structure here. */
    public HitCounter() {
        times = new int[300];
        hits = new int[300];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int index = timestamp % 300;
        if (times[index] != timestamp) {
            hits[index] = 1;
            times[index] = timestamp;            
        } else {
            hits[index]++;

        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        // int index = timestamp % 300;
        int total = 0;
        for (int i = 0; i < 300; i++) {
            if (timestamp - times[i] < 300) {
                total += hits[i];
            }
        }
        return total;
    }
}