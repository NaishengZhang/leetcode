class Solution {
    public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] c = s.toCharArray();
        
        // "abcdefga" k = 3
        for (int i = 0; i < c.length; i += 2*k) {
            if (i + k < c.length) {
                //
                reverse(i, i + k, c);
            } else {
                reverse(i, c.length, c);
            }
        }
        
        return new String(c);
    }
    private void reverse (int start, int end, char[] c) {
        int i = start, j = end - 1;
        char temp = '0';
        while (i < j) {
            temp = c[i];
            c[i] = c[j];
            c[j] = temp;
            i++;
            j--;
        }
    }
}