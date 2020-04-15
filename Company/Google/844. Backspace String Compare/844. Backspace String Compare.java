// stack. Time: O(n) Memory: O(n)
class Solution {
    public boolean backspaceCompare(String S, String T) {
        return build(S).equals(build(T));
    }
    
    private String build(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != '#') {
                stack.push(c);
            } else if (!stack.isEmpty()){
                stack.pop();
            }
        }
        return String.valueOf(stack);
    }
}

// Two pointer. Time: O(n) Memory: O(1)
class Solution {
    public boolean backspaceCompare(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return false;
        }
        
        int i = s.length() - 1;
        int j = t.length() - 1;
        char c1 = '0';
        char c2 = '0';
        int count = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    i--;
                    count++;
                } else if (count > 0){
                    count--;
                    i--;
                } else {
                    break;
                }
            }
            System.out.print(j);
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    j--;
                    count++;
                } else if (count > 0) {
                    count--;
                    j--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) {
                return false;
            }
            if ((i >= 0) != (j >= 0)) {
                return false;
            }
            i--;
            j--;
        }
        return true;
    }
}


