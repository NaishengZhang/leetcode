class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        if (A == null || A.length == 0) {
            return -1;
        }
        if (B == null || B.length == 0) {
            return -1;
        }
        
        int min1 = Math.min(minRotations(A[0], A, B), minRotations(A[0], B, A));
        int min2 = Math.min(minRotations(B[0], A, B), minRotations(B[0], B, A));
        
        return Math.min(min1, min2) == Integer.MAX_VALUE ? -1 : Math.min(min1, min2);
    }
    
    
    private int minRotations(int target, int[] A, int[] B) { //swap elements from B
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != target) {
                if (B[i] == target) {
                    count++;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }
        return count;
        
    }
}