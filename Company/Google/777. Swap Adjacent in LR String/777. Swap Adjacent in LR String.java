// Time: O(n) Space O(n)-replace
class Solution {
    public boolean canTransform(String start, String end) {
        if (!start.replace("X", "").equals(end.replace("X", ""))){
            return false;
        }
        
        int n = start.length();
        int t = 0;
        for (int i = 0; i < n; i++) {
            if (start.charAt(i) == 'L') {
                while (t < n && end.charAt(t) != 'L') {
                    t++;
                }
                if (i < t) {
                    return false;
                }
                t++;
            }
        }
        
        t = 0;
        for (int i = 0; i < n; i++) {
            if (start.charAt(i) == 'R') {
                while (t < n && end.charAt(t) != 'R') {
                    t++;
                }
                if (i > t) {
                    return false;
                }
                t++;
            }
        }
        return true;
    }
}

// Two pointer Time: O(n)
class Solution {
    public boolean canTransform(String start, String end) {
        int n = start.length();
        char[] s = start.toCharArray();
        char[] e = end.toCharArray();
        
        int i = 0, j = 0;
        while (i < n && j < n) {
            while (i < n && s[i] == 'X') {
                i++;
            }
            while (j < n && e[j] == 'X') {
                j++;
            }
            if ((i < n) ^ (j < n)) {
                return false;
            }
            //需要再检测一次i j 小于n，因为上一步中i j可能等于n
            if (i < n && j < n) {
                if (s[i] != e[j] || (s[i] == 'L' && i < j) || (s[i] == 'R' && i > j)) {
                    return false;
                }
            }
            i++;
            j++;
        }
        while (i < n) {
            if (s[i] == 'X') {
                i++;
            } else {
                return false;
            }
        }
        while (j < n) {
            if (e[j] == 'X') {
                j++;
            } else {
                return false;
            }
        }
        
        return true;
    }
}



// testcase 未通过
// "XXRXL"
// "LXRXX"
class Solution {
    public boolean canTransform(String start, String end) {
        int n = start.length();
        char[] s = start.toCharArray();
        char[] e = end.toCharArray();
        
        int c1 = 0, c2 = 0;
        
        for (int i = 0; i < n; i++) {
            if (s[i] == 'R') { // start中R要在前边
                c1++;
            }
            if (e[i] == 'L') { // end中L要先出现
                c2++;
            }
            if (s[i] == 'L') {
                c2--;
            }
            if (e[i] == 'R') { // 后出现的要-1
                c1--;
            }
            
            //破坏规则的三种情况， 其中c1 * c2 != 0 意思是start中r在抵消后第一个出现，end中l在第一个，这样两个string中l。r的相对顺序就不一样了
            if (c1 < 0 || c2 < 0 || c1 * c2 != 0) {
                return false;
            }
        }
        return c1 == 0 && c2 == 0;
    }
}