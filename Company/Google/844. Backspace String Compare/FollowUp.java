//link: https://www.1point3acres.com/bbs/interview/google-software-engineer-470783.html
//LC844，followup：加一个大写键CAPS

// stack
public class FollowUp844 {
    public static void main(String[] args) {
        String S = "a~b#c", T = "a~d#c";
        System.out.println(build(S));
        System.out.println(build(S).equals(build(T)));

    }

    private static String build(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        boolean flag = false;
        for (char c : s.toCharArray()) {
            if (c != '#' && c != '~') {
                if (flag == true) {
                    stack.push(Character.toUpperCase(c));
                    flag = false;
                } else {
                    stack.push(c);
                }
            } else if (c == '~') {
                flag = true;
            } else if (!stack.isEmpty()) {
                stack.pop();
            }


        }
        return String.valueOf(stack);
    }
}

// Two pointer
    