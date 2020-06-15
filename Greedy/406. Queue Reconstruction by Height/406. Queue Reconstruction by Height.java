class Solution {
    public int[][] reconstructQueue(int[][] people) {
        
        
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        List<int[]> res = new ArrayList<>();
        
        for (int[] p : people) {
            res.add(p[1], p);
        }
        
        return res.toArray(new int[people.length][2]);
    }
}