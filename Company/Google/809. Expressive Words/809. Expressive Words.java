class Solution {
    public int expressiveWords(String S, String[] words) {
        int res = 0;
        for (String word : words) {
            res += isStretchy(S, word);
        }
        return res;
    }
    
    private int isStretchy(String S, String word) {
        int j = 0, i = 0;
        while (i < S.length() && j < word.length()) {
            char c1 = S.charAt(i);
            char c2 = word.charAt(j);
            if (c1 == c2) {
                int len1 = getReaptedNum(S, i);
                int len2 = getReaptedNum(word, j);
                if (len1 < 3 && len1 != len2 || len1 >= 3 && len1 < len2) {
                    return 0;
                }
                i += len1;
                j += len2;
            } else {
                return 0;
            }
        }
        if (i != S.length()  || j != word.length()) {
            return 0;
        }
        return 1;
    }
    public int getReaptedNum(String str, int i) {
        int j = i;
        while (j < str.length() && str.charAt(j) == str.charAt(i)) {
            j++;
        }
        return j - i;
    }

}