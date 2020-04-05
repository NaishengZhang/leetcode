class Solution {
    public String getHint(String secret, String guess) {
        int[] cnt = new int[10];
        char[] s = secret.toCharArray();
        char[] g = guess.toCharArray();
        int bull = 0, cow = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == g[i]) {
                bull++;
            } else {
                if (cnt[s[i] - '0']++ < 0) {
                    cow++;
                }
                if (cnt[g[i] - '0']-- > 0) {
                    cow++;
                }
            }
            
        }
        
        return bull + "A" + cow + "B";
    }
}

class Solution {
    public String getHint(String secret, String guess) {
        int[] cnt = new int[10];
        char[] s = secret.toCharArray();
        char[] g = guess.toCharArray();
        int bull = 0, cow = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == g[i]) {
                bull++;
            } else {
                cnt[s[i] - '0']++;
            }
        }
        
        for (int i = 0; i < s.length; i++) {
            if (s[i] != g[i] && cnt[g[i] - '0'] != 0) {
                cow++;
                cnt[g[i] - '0']--;
            }
        }
        
        return bull + "A" + cow + "B";
    }
}