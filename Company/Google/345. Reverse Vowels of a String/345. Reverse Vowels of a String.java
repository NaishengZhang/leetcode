class Solution {
    public String reverseVowels(String s) {
        String vowels = "aeiouAEIOU";
        char[] c = s.toCharArray();
        
        int i = 0, j = c.length - 1;
        while(i < j) {
            while (i < j && !vowels.contains(String.valueOf(c[i]))) {
                i++;
            }
            
            while (i < j && !vowels.contains(String.valueOf(c[j]))) {
                j--;
            }
            
            char temp = c[i];
            c[i] = c[j];
            c[j] = temp;
            i++;
            j--;
        }
        return new String(c);
    }
}