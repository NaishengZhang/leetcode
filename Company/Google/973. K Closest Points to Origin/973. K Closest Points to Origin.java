 // time: O(nlogn) space: O(n)
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        
        int n = points.length;
        int m = points[0].length;
        int[] dis = new int[n];
        
        for (int i = 0; i < n; i++) {
            dis[i] = compute(points[i]);
        }
        Arrays.sort(dis); // O(nlogn)
        
        int disK = dis[k - 1];
        int[][] res = new int[k][2];
        int t = 0;
        for (int i = 0; i < n; i++) {
            if (compute(points[i]) <= dis[k-1]) {
                res[t++] = points[i];
            }
        }
        return res;
    }
    
    private int compute(int[] point) {
        return point[1] * point[1] + point[0]*point[0];
    }
