class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        char[] c = s.toCharArray();
        int start = 0, i = 0;
        while (i < c.length) {
            if (c[i] == ' ') {
                reverse(start, i, c);
                start = i + 1;
            }
            i++;
        }
        reverse(start, c.length, c);
        
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