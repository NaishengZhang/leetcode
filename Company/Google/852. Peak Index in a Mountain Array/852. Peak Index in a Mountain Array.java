// O(n)
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < A.length; i++) {
            if (max < A[i]) {
                index = i;
                max = A[i];
            }
        }
        return index;
    }
}

// O(logn) 能用binary search是因为 comparison是sorted，true true false false；所以只要找到第一个false就行
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        if (A == null) {
            return 0;
        }
        
        int l = 0, r = A.length - 1;
        int mid = -1;
        while (l + 1 < r) {
            mid = l + (r - l) / 2;
            if (A[mid] > A[mid + 1]) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return A[l] > A[r] ? l : r;
    }
}