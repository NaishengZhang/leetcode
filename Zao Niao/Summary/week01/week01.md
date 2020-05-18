# 早鸟刷题

## week 01 (5.11- 5.17)

### 1. Two Sum

leetcode届的abandon，大部分同学刷题都是从这道题开始的。

（1）brute force，

对于数组中每一个数，都要通过遍历数组剩下的数，来找另外一个数，这里的查找的时间为O(n)。对每个数都要进行一次查找，那么总的时间复杂度为O(n^2)

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // null check
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        // brute force
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
```

(2) hashmap

要找两数之和等于target，那么肯定是要遍历一遍数组的，时间复杂度至少O(n)。那么优化算法的话，就要通过优化查找另外一个数的时间复杂度。空间换时间，这道题要返回的是index，那么可以想到用hashmap，key是数值，value是对应的index。在遍历数组的同时，查看当前hashmap的key中是否包含complement，如果包含，就说明找到了答案。这样就将查找另外一个数的时间从O(n)减少到O(1).

总的时间复杂度从O(n^2) -> O(n)

总的空间复杂度从O(1) -> O(n)

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // null check
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        // HashMap
        Map<Integer, Integer> map = new HashMap<>(); // <num, index>
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
```



### 15. 3Sum

这道题是要找3个数相加等于target，但注意这道题需要返回的是数值，而不是index

（1）Brute force

固定第一个数x后，再从后边的数组中找two sum == 0 - x, 时间复杂度O(n^3)

（2）hashmap

延续2 sum的优化思路，相当于在2sum加了一层for loop。要注意的是，这道题要求返回不重复的triplets，那么就要考虑去重的方法。 这里用了两个技巧1⃣️对于和相同的三个数，只要记录最大和最小的两个数，就可以确定这组数据。2⃣️用String来表示最大最小值。

Time: O(n^2) 

Space O(n^2)

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        Set<String> found = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> seen = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int complement = -nums[i] - nums[j];
                if (seen.contains(complement)) {
                    int min = Math.min(nums[i], Math.min(nums[j], complement));
                    int max = Math.max(nums[i], Math.max(nums[j], complement));
                    String s = String.valueOf(min) + String.valueOf(max);
                    if (found.add(s)) {
                        res.add(Arrays.asList(complement, nums[i], nums[j]));
                    }
                }
                seen.add(nums[j]);
            }
        }
        
        return res;
    }
}
```

（3）two pointer

反正都O(n^2)了，可以考虑先sort一下，再用two pointer找答案。但是有两个地方是要注意去重的。

第一个数去重

```java
if (i > 0 && nums[i] == nums[i - 1]) { //第一个数去重
	continue;
}
```

第二 三个数去重

```java
while (l < nums.length && nums[l] == nums[l - 1]) { // 去重
  l++;
} 
```



```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) { //第一个数去重
                continue;
            }
            
            int target = 0 - nums[i];
            int l = i + 1, r = nums.length - 1;
            
            while (l < r) {
                int sum = nums[l] + nums[r];
                
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (l < nums.length && nums[l] == nums[l - 1]) { // 去重
                        l++;
                    } 
                    while (r >= 0 && nums[r] == nums[r + 1]) { // 去重
                        r--;
                    }
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
}
```



### 18. 4Sum

这道题思路和3sum是一样的，只不过多加一层for loop。注意在第一层for loop开始的时候也要去重

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        Arrays.sort(nums);
        for (int k = 0; k < nums.length - 3; k++) {
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            for (int i = k + 1; i < nums.length - 2; i++) {
                if (i > i + 1 && nums[i] == nums[i - 1]) { //第2个数去重
                    continue;
                }

                int l = i + 1, r = nums.length - 1;

                while (l < r) {
                    int sum = nums[l] + nums[r] + nums[i] + nums[k];

                    if (sum == target) {
                        res.add(Arrays.asList(nums[k], nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) { // 去重
                            l++;
                        } 
                        while (l < r && nums[r] == nums[r - 1]) { // 去重
                            r--;
                        }
                        l++;
                        r--;
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }            
        }

        return res;
    }
}
```



### 31. Next Permutation

这道题的主要难点在理解字典序排列，举例

12431 => 13124，想要找下一个比12431大的数，那么就要从右向左看，

对于后三位数431已经是最大的了，

对于后四位数2431，要想使2431变大，那么就要在431中挑一个比2大的数 并与2交换。另外一个要求是增幅尽可能小，就要挑一个刚刚比2大的数，也就是3。交换后变成3421，再将后三位数从小到大排列 就找到最后结果 13124。

```java
class Solution {
    public void nextPermutation(int[] nums) {

        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        int n = nums.length;
        
        int small = -1;
        
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                small = i;
                break;
            }
        }
        if (small == -1) {
            reverse(nums, 0, n - 1);
            return;
        }
        
        int big = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > nums[small]) {
                big = i;
                break;
            }
        }
        swap(nums, big, small);
        reverse(nums, small + 1, n - 1);
        
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```



### 46. Permutations

这道题是经典的Backtracking入门题，需要注意的是每个元素只使用一次，也就是在一条路径上只出现一次。

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gewe6jn1d8j31kl0u0tpn.jpg)

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        boolean[] visited = new boolean[nums.length];
        dfs(nums, res, new ArrayList<>(), visited);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> sub, boolean[] visited ) {
        // base case
        if (sub.size() == nums.length) {
            res.add(new ArrayList<>(sub));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            //choose
            sub.add(nums[i]);
            visited[i] = true;

            //explore
            dfs(nums, res, sub, visited);

            //unchoose
            sub.remove(sub.size() - 1);
            visited[i] = false;
        }
        
    }
}
```

### 47. Permutations II

与46不同的是，这道题数组中包含重复数字，要求返回唯一的排列。

**两个剪枝条件**
在permutation的**剪枝条件1**：用过的元素不能再使用之外，又添加了一个新的剪枝条件，也就是我们考虑重复部分思考的结果。

**剪枝条件2**：当当前元素和前一个元素值相同（此处隐含这个元素的index>0），并且前一个元素还没有被使用过的时候，我们要剪枝

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gewea88lcxj30lc0c7wh0.jpg)

前一个元素没被使用过，说明之前其他路径，在相同位置已经搜索过这个相同数字了，所以剪枝。

sub和visited记录的是从上到下一条路径上的状态。

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gewea95rmaj30lz0fb0w3.jpg)

前一个元素被使用过，说明之后还会轮到当前元素放在前一层，就先把这条路径剪枝

```java
class Solution {
    private List<List<Integer>> res;
    private boolean[] visited;
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        dfs(nums, new ArrayList<>());
        
        return res;
    }
    
    private void dfs(int[] nums, List<Integer> subset) {
        if (subset.size() == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            if (i >= 1 && nums[i] == nums[i - 1] && !visited[i - 1]) continue; // !visited[i - 1]意思是两个相同的数是可以出现在当前的res中
            subset.add(nums[i]);
            visited[i] = true;
            dfs(nums, subset);
            subset.remove(subset.size() - 1);
            visited[i] = false;
        }
    }
}
```

Ref: https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/



### 79. Word Search

遍历二维数组，每一步都做dfs，看是否有满足条件的word。

两个小技巧：

（1）用一个二维数组direction来表示上下左右

（2）两种方式去标记已经走过的路 

```java
visited[i][j] = true 
or 
board[i][j] = '*'
```

Time: O(N * 4^L)  N is number of cells in the grid, L is the length of word.
Spcae: O(L) => stack.

```java
class Solution {
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; // up, left, right, down
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
               if (dfs(board, word, 0, i, j, visited)) {
                   return true;
               }
            }
        }
        return false;
        
    }
    
    private boolean dfs(char[][] board, String word, int index, int x, int y, boolean[][] visited ) {
        if (index == word.length()) {
            return true;
        }
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || visited[x][y] || board[x][y] != word.charAt(index)) {
            return false;
        }

        boolean found = false;
        visited[x][y] = true;
        for (int[] dir : direction) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            found = found || dfs(board, word, index + 1, nextX, nextY, visited);
            if (found) {
                break;
            }
        }
        visited[x][y] = false;
        return found;
    }
}
```

### 212. Word Search II

作为79的follow up，也是要用到backtracking的。

先构建trie，从board每一个点开始dfs，在dfs的中间要判断当前在的点是否在trie中，然后在dfs的过程中发现word ！= null，就加入res中

```java


class Solution {
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; // up, left, right, down
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        
        Trie trie = new Trie();
        for (String s : words) {
            trie.insert(s);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, words, res, trie.root, i, j);
            }
        }
        return res;
    }
    
    private void dfs(char[][] board, String[] words, List<String> res, Node node, int x, int y) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
            return;
        }
        char c = board[x][y];
        if (c == '*' || node.child[c - 'a'] == null) {
            return;
        }
        
        node = node.child[c - 'a'];
        
        if (node.word != null) {
            res.add(node.word);
            node.word = null;
        }
        board[x][y] = '*';
        for (int[] d : direction) {
            dfs(board, words, res, node, x + d[0], y + d[1]);
        }
        board[x][y] = c;
    }
    
    
    class Node {
        private String word;
        private Node[] child;
        public Node() {
            child = new Node[26];
        }
    }

    class Trie {
        private Node root;
        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node cur = root;
            for (char c : word.toCharArray()) {
                if (cur.child[c - 'a'] == null) {
                    cur.child[c - 'a'] = new Node();
                }
                cur = cur.child[c - 'a'];
            }
            cur.word = word;
        }
    }
}
```

### 84. Largest Rectangle in Histogram

这道题的总体思路是，在一个固定高度下，怎么找到最大的宽度。从图上很容易可以看出，左右第一个小于该高度的index之差就是最大的宽度。

(1) Brute Force，很直接的想到对于某一高度，左右分别找第一个小于该高度的index。

Time: O(n^2)

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int left = i;
            int cur = heights[i];
            while (left - 1 >= 0 && heights[left - 1] >= cur) { // 左边第一个小于cur的index
                left--;
            }
            int right = i;
            while (right + 1 < heights.length && heights[right + 1] >= cur) { //右边第一个小于cur的index
                right++;
            }
            int area = cur * (right - left + 1);
            res = Math.max(res, area);
        }
        return res;      
        
    }
```

（2）Mono Stack

对于递增单调栈来说

当栈顶元素要被pop时，可以找到右边第一个比自己小的数，以及左边第一个比自己小的数 

=>栈顶可以找到一个区间（l, r），自己在这个区间最大。

注意两种 corner case
1. 弹栈的时候，栈为空；
2. 遍历完成以后，栈中还有元素；

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int n = heights.length;
        int[] h = Arrays.copyOf(heights, n + 1); //2. 遍历完成以后，栈中还有元素；
        int area = 0;
        for (int i = 0; i <= n; i++) {
            while(!stack.isEmpty() && h[stack.peek()] > h[i]) { // 1. 弹栈的时候，栈为空；
                int height = h[stack.pop()];
                area = Math.max(area, height * (stack.isEmpty() ? i : (i - stack.peek() - 1))); // 1. 弹栈的时候，栈为空；
            }
            stack.push(i);
        }
        return area;
    }
}
```

（3）Mono Stack

上边那种方法很容易忘记处理栈为空的条件。

还有另外一种更简单的方法，就是 数组前后两个哨兵（也就是0），不需要再手动判断栈为空，因为保证栈不可能为空，并且除首位（0）外，其他元素全被pop。

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        if (heights == null || heights.length == 0) {
            return res;
        }
        int n = heights.length;
        int[] heights1 = new int[n + 2];
        System.arraycopy(heights, 0, heights1, 1, n);
        
        //首尾都加一个最小值，保证栈不可能为空，并且除首位外，其他元素全被pop
        heights1[0] = 0;
        heights1[n + 1] = 0;
        stack.push(0); //相当于把index = 0先放进stack中
        
        for(int i = 1; i < n + 2; i++) { // 0已经放入stack中了，所以从1开始
            //递增
            while(heights1[stack.peek()] > heights1[i]) {
                int h = heights1[stack.pop()];
                int area = h * (i - stack.peek() - 1);
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        
        return res;
    }
}
```

### 85. Maximal Rectangle

这道题很巧妙利用84的算法，对matrix每行都计算一次84，注意当matrix[i][j] == ‘0’时，就不能累加到之后的高度。

Time：O(mn) 

```java
// 
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int area = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return area;
        }
        
        int[] heights = new int[matrix[0].length];
        
        for (char[] m : matrix) {
            for (int i = 0; i < m.length; i++) {
                if (m[i] == '1') {
                    heights[i] += 1;
                } else {
                    heights[i] = 0;
                }
            }
            area = Math.max(area, largestRectangle(heights));
        }
        return area;
    }
    
    private int largestRectangle(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = heights.length;
        int[] heights1 = Arrays.copyOf(heights, n + 1);
        heights = heights1;
        int res = 0;
        for (int i = 0; i < n + 1; i++) {
            //increase
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int h = heights[stack.pop()];
                int area = h * (stack.isEmpty() ? i : (i -stack.peek() - 1));
                res = Math.max(res, area);
            }
            stack.push(i);
        }

        return res;
    }
}
```

### 153. Find Minimum in Rotated Sorted Array

Binary search

```
  *
*
        *
      *
    *
```

比较mid和end的大小关系，判断在左半段还是右半段，通过二分查找，一直缩小范围，使得最后停止在最低点

```
class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int l = 0, r = nums.length - 1;
        int end = nums[r];
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > end) { //前半段
                l = mid;
            } else if (nums[mid] < end) { //后半段
               r = mid; 
            }
        }
        return Math.min(nums[l], nums[r]);
    }
}
```



### 154. Find Minimum in Rotated Sorted Array II

与153不同的是 可能出现重复，出现重复会导致nums[mid] == nums[left]，使得无法判断mid在那一段上，所以通过left- - 使得mid不等于left（1.可能nums[left]值变化 2. 也可能nums[mid]值变化）

Would allow duplicates affect the run-time complexity? How and why?

log(n) 但是[1, 1, 1, 1, 1]退化成O(n)

```java
class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        
        int l = 0, r = nums.length - 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[r]) {
                l = mid;
            } else if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                r--;
            }
        }
        return Math.min(nums[l], nums[r]);
    }
}
```

