//int[26]
// Time complexity: O(n)
// Space complexity: O(1) 因为hash数组的长度是固定的，不随n的增大而增大。
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] hash = new int[26]; // 'a' -> 'z': 97 -> 122
        for (char c : t.toCharArray()) {
            hash[c - 'a']++;
        }
        for (char a : s.toCharArray()) {
            if (hash[a - 'a'] > 0) {
                hash[a - 'a']--;
            } else {
                return false;
            }
            
        }
        return true;
    }
}
//
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }
    int[] counter = new int[26];
    for (int i = 0; i < s.length(); i++) {
        counter[s.charAt(i) - 'a']++;
        counter[t.charAt(i) - 'a']--;
    }
    for (int count : counter) {
        if (count != 0) {
            return false;
        }
    }
    return true;
}

//sort

