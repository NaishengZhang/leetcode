class Solution {
    public boolean checkRecord(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        
        char[] arr = s.toCharArray();
        int absent = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 2 && arr[i] == 'L' && arr[i + 1] == 'L' 
                && arr[i + 2] == 'L') {
                return false;
            }
            if (arr[i] == 'A') {
                absent++;
            }
        }
        return absent <= 1;
    }
}