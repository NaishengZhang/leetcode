class Solution {
    public String customSortString(String s, String t) {
        if (s == null || s.length() == 0) {
            return t;
        }
        int[] count = new int[26];
        
        for (char c : t.toCharArray()) {
            count[c - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            // 从count中提取char, 第一种写法
            for (int i = 0; i < count[c - 'a']; i++) {
                sb.append(c);
            }
            count[c - 'a'] = 0;
        }
        for (int i = 0; i < count.length; i++) {
            //第二种
            while (count[i] > 0) {
                sb.append((char)(i + 'a'));
                count[i]--;
            }
        }
        
        return sb.toString();
    }
}