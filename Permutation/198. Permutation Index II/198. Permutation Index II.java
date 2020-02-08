public class Solution {
    public long permutationIndexII(int[] A) {
         // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        
        //key: use long(hint: return type is long)
        long result = 1;
        long factor = 1;
        long multi = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = A.length - 1; i >= 0; i--) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
            multi *= map.get(A[i]);
            int rank = 0;
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    rank += 1;
                }
            }
            result += factor * rank / multi;
            factor *= (A.length - i);
        }
        return result;
    }
}