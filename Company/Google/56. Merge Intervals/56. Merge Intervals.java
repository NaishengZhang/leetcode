class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (int[] i, int[] j) -> (i[0] - j[0]));
        LinkedList<int[]> merge = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merge.isEmpty() || merge.getLast()[1] < interval[0]) {
                //no overlap
                merge.add(interval);
            } else {
                // overlap
                merge.getLast()[1] = Math.max(merge.getLast()[1], interval[1]);
            }
        }
        return merge.toArray(new int[merge.size()][2]);
    }   
}