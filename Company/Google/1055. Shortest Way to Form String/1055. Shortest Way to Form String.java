// 4 followups: https://leetcode.com/problems/shortest-way-to-form-string/discuss/330938/Accept-is-not-enough-to-get-a-hire.-Interviewee-4-follow-up

//time: O(nm) Memory: O(n)
class Solution {
    public int shortestWay(String source, String target) {
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();
        int n = t.length, m = s.length;
        int i = 0, j = 0;
        int res = 0;
        while (i < n) {
            int temp = i;
            for (char c : s) {
                if (i < n && c == t[i]) {
                    i++;
                }
            }
            if (temp == i) {
                return -1;
            }
            res++;
            
        }
        return res;
    }
}

// previous solution time: O(mn), make it better => O(logm*n), LogM => Binarysearch. 
// use a map to record the all indeies of each element. 
// 遍历target，对于其每个char都去map中找对应的index list，然后看前一个访问的位置是否大于list中最大的index（用二分法查找pre应该在的位置），如果大于，那么就说明需要遍历一遍source(res++)
// 如果小于，那么就说明当前这个source还没遍历完
class Solution {
    public int shortestWay(String source, String target) {
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();
        int n = t.length, m = s.length;
        List<Integer>[] idx = new List[26];
        for (int i = 0; i < 26; i++){
            idx[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            idx[s[i] - 'a'].add(i);
        }
        int res = 1;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            List<Integer> list = idx[t[i] - 'a'];
            if (list.isEmpty()) {
                return -1;
            }
            int index = Collections.binarySearch(list, pre);
            if (index < 0) {
                index = -index - 1; // insert position
            }
            if (index == list.size()) {
                res++;
                pre = list.get(0) + 1;

            } else {
                pre = list.get(index) + 1;
            }
        }
        return res;
    }
}