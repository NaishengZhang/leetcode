public class Solution {
    /**
     * @param A: An array of integers
     * @return: A long integer
     */
    public long permutationIndex(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        
        //key: use long(hint: return type is long)
        long result = 1;
        long factor = 1;
        
        for (int i = A.length - 1; i >= 0; i--) {
            int rank = 0;
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    rank += 1;
                }
            }
            result += factor*rank;
            factor *= (A.length - i);
        }
        return result;
    }
}