class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return 0;
        }
        
        Arrays.sort(intervals, (int[] i, int[] j) -> (i[0] - j[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //把pq当成预定的屋子，首先第一个会议占了第一个屋子
        pq.add(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= pq.peek()) {
                  //会议室最早结束的会议结束后，会议i才开始，这是可以在会议室去除最早结束的
                 pq.poll();
            }
            //每次都要把新的会议结束时间加到队列中，只是需要判断 之前最早结束的是否能够结束。
            pq.add(intervals[i][1]);
        }
        return pq.size();
    }
}