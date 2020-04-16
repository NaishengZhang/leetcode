// Time: O(n)
class Solution {
    // StringBuilder: sb.length(); sb.deleteCharAt(int index); sb.charAt(int index)
    // sb.reverse(); sb.indexOf(String str)
    public String licenseKeyFormatting(String S, int K) {
        if (S == null || S.length() == 0) {
            return S;
        }
        S = S.toUpperCase();
        StringBuilder sb = new StringBuilder();
        int n = S.length();
        
        int count = 0;
        char c = ' ';
        for (int i = n - 1; i >= 0; i--) {
            c = S.charAt(i);
            if (c != '-') {
                sb.append(c);
                count++;
                if (count % K == 0) {
                    sb.append('-');
                }
            }
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.reverse().toString();
    }
}

class Solution {
    public String licenseKeyFormatting(String S, int K) {
        S = S.toUpperCase();
        S = S.replace("-", "");
        StringBuilder sb = new StringBuilder(S);
        for (int i = S.length() - K; i > 0; i -= K) {
            sb.insert(i, "-");
            System.out.println(sb.toString());
        }
        return sb.toString();
    }
}