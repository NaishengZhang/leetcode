class Solution { 
    // \n and \t 字符串 长度是1；
    public int lengthLongestPath(String input) {
        int res = 0;
        if (input == null || input.length() == 0) {
            return res;
        }
        
        List<String> files = new ArrayList<>();
        
        int i0 = 0; //保存起点
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\n') {
                files.add(input.substring(i0, i));
                i0 = i + 1;
            }
        }
        files.add(input.substring(i0, input.length()));
        
        List<String> dir = new ArrayList<>();
        for (String s : files){
            // int cnt = 0;
            // for (int k = 0; k < s.length(); k++) {
            //     if (s.charAt(k) == '\t') {
            //         cnt++;
            //     }
            // }
            int cnt = 0;
            while (cnt < s.length() && s.charAt(cnt) == '\t') {
                cnt++;
            }
            
            dir.add(cnt, s.substring(cnt, s.length()));
            
            int temp = 0;
            if (s.contains(".")) {
                for (int x = 0; x <= cnt; x++) {
                    temp += dir.get(x).length();
                }
                temp += cnt;
                res = Math.max(temp, res);
            }
        }
        return res;
    }
}