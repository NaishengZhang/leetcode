// Time: O(logn)，因为num每次都是除以2或者减1
class Solution {
    public int numberOfSteps (int num) {
        int count = 0;
        while (num != 0) {
            count++;
            num = num % 2 == 0 ? num / 2: num - 1;
        }
        
        return count;
    }
}


class Solution {
    public int numberOfSteps (int num) {
        String s = Integer.toBinaryString(num);
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                count += 2;
            } else {
                count++;
            }
        }
        return count - 1; //因为最后一个bit 1 只需要substract 1， no need to remove the last bit 0 after substaction
    }
}