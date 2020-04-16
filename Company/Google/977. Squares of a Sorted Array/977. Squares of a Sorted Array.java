class Solution {
    public int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) {
            return A;
        }
        int n = A.length;
        int p = 0;
        int[] res = new int[n];
        while (p < n && A[p] < 0) {
            p++;
        }
        int left = p - 1;
        int right = p;
        int t = 0;
        while (left >=0 && right < n) {
            if (A[left] * A[left] < A[right] * A[right]) {
                res[t++] = A[left] * A[left];
                left--;
            } else {
                res[t++] = A[right] * A[right];
                right++;
            }
        }
        
        while (right < n) {
            res[t++] = A[right] * A[right];
            right++;
        }
        while (left >= 0) {
            res[t++] = A[left] * A[left];
            left--;
        }
        return res;
    }
}