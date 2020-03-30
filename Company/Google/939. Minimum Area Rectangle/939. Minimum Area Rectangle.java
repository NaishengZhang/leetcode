class Solution {
    public int minAreaRect(int[][] points) {
        int res = Integer.MAX_VALUE;
        if (points == null || points.length == 0 || points[0].length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            set.add(points[i][0] + "," + points[i][1]);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0];
                int y1 = points[j][1];
                int x2 = points[j][0]; 
                int y2 = points[i][1];
                String point1 = x1 + "," + y1;
                String point2 = x2 + "," + y2;
                if (x1 != x2 && y1 != y2) {
                    if (set.contains(point1) && set.contains(point2)) {
                        int length = Math.abs(x1 - x2) ;
                        int width = Math.abs(y1 - y2);
                        int area = length * width;
                        res = Math.min(res, area);
                    }                    
                }
            }
        }
        if (res == Integer.MAX_VALUE) {
            return 0;
        }
        return res;
        
    }
    
}