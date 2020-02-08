public class Solution {
    public void sortLetters(char[] chars) {
        int tail = 0;
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLowerCase(chars[i])) {
            // if (chars[i] >= 'a') { 也可    小写字母ASCII要大于大写
                swap (chars, tail, i);
                tail++;
            }
        } 
    }
    
    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}