

# LeetCode

## 0. Templet

### 0.1 Recursion

递归代码最重要的两个特征：结束条件和自我调用。自我调用是在解决子问题，而结束条件定义了最简子问题的答案。

```java
int func(传入数值) {
  if (终止条件) return 最小子问题解;
  return func(缩小规模);
}
```

0

00

## 1. Binary Search

总结：

二分条件：sorted and no duplicate

当有重复的时候，极端情况 1 11 1 1 2，无法通过mid = 1二分，





### 34. Search for a Range

https://www.lintcode.com/problem/search-for-a-range/description

```
Description

Given a sorted array of n integers, find the starting and ending position of a given target value.

If the target is not found in the array, return [-1, -1].

Example
Example 1:

Input:
[]
9
Output:
[-1,-1]

Example 2:

Input:
[5, 7, 7, 8, 8, 10]
8
Output:
[3, 4]
Challenge
O(log n) time.
```



```java
public class Solution {
    /**
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        
        if (A.length == 0) {
            return new int[]{-1,-1};
        }
        
        int start = 0;
        int end = A.length - 1;
        int mid = -1;
        int[] result = new int[]{-1,-1};
        
        // Find the left bound = Find the first time
        while(start + 1 < end){
            mid = start + (end - start) / 2;
            // mid = (start + end) / 2 可能回溢出 
            
            if(A[mid] == target) {
                end = mid;
            } else if(A[mid] < target){
                start = mid;
            } else if(A[mid] > target){
                end = mid;
            }
        }
       
        if (A[end] == target) {
            result[0] = end;
        }
        if (A[start] == target) {
            result[0] = start;
        }
        
        
        // Find the right bound = Find the last time
        start = 0;
        end = A.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
               start = mid; 
            } else if (A[mid] < target){
                start = mid;
            } else if (A[mid] > target) {
                end = mid;
            }
        }
        
        if (A[start] == target) {
            result[1] = start;
        }
        if (A[end] == target) {
            result[1] = end;
        }
        
        return result;
    }
}
```

````
四点要素:
1. start + 1 < end
2. start + (end - start) / 2 
3. A[mid] ==, <, >
4. A[start] A[end] ? target
````



### 35. Search Insert Position

https://leetcode.com/problems/search-insert-position/

```
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2
Example 2:

Input: [1,3,5,6], 2
Output: 1
Example 3:

Input: [1,3,5,6], 7
Output: 4
Example 4:

Input: [1,3,5,6], 0
Output: 0
```



```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0;
        int end = nums.length -1;
        int mid = -1;
        
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            
            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] < target){
                start = mid;
            } else {
                end = mid;
            }
            
        }
        
        //Find the first position >= target
        if (nums[start] >= target) {
            return start;
        } else if (nums[end] >=target) {
            return end;
        } else {
            return end + 1;
        }
    }
}
```

```
二分法的逻辑主要在于处理最后两个数start和end 与 target的关系。
```



### 74. Search a 2D Matrix

https://leetcode.com/problems/search-a-2d-matrix/

```
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
```

```java
class Solution {
  //将matrix看成一个sorted list
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
         if(matrix[0] == null || matrix[0].length == 0){
            return false;
        }
        int start = 0;
        int row = matrix.length;
        int column = matrix[0].length;
        int end = row * column - 1;
        
        while (start + 1 < end) {
            
            int middle = start + (end - start) / 2;
            int mid = matrix[middle / column][middle % column];
            
            if (mid == target) {
                return true;
            } else if(mid < target) {
                start = middle;
            } else {
                end = middle;
            }
        }    
        
        int startNum = matrix[start / column][start % column];
        int endNum = matrix[end / column][end % column];
        if (startNum == target) {
            return true;
        }
        if (endNum == target) {
            return true;
        }
        return false;
        
    }
}
```

### 240. Search a 2D Matrix II

https://leetcode.com/problems/search-a-2d-matrix-ii/

```
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
```

```java
class Solution {
  //right corner是这一行最大的数，这一列最小的数。
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        
        int row = 0;
        int col = matrix[0].length - 1;
        
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                row++;
            } else{
                col--;
            }
        }
        
        return false;
    }
}
```

### 38. Search a 2D Matrix II

https://www.lintcode.com/problem/search-a-2d-matrix-ii/description

```
Description

Write an efficient algorithm that searches for a value in an m x n matrix, return the occurrence of it.

This matrix has the following properties:

Integers in each row are sorted from left to right.
Integers in each column are sorted from up to bottom.
No duplicate integers in each row or column.
Have you met this question in a real interview?  Yes
Problem Correction
Example
Example 1:

Input:
	[[3,4]]
	target=3
Output:1
Example 2:

Input:
    [
      [1, 3, 5, 7],
      [2, 4, 7, 8],
      [3, 5, 9, 10]
    ]
    target = 3
Output:2
Challenge
O(m+n) time and O(1) extra space
```

```java
public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @param target: An integer you want to search in matrix
     * @return: An integer indicate the total occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int row = 0;
        int col = matrix[0].length - 1;
        int result = 0;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                result++;
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else{
                col--;
            }
        }
        
        return result;
    
    }
}
```



### 74. First Bad Version

```
74. First Bad Version

The code base version is an integer start from 1 to n. One day, someone committed a bad version in the code case, so it caused this version and the following versions are all failed in the unit tests. Find the first bad version.

You can call isBadVersion to help you determine which version is the first bad one. The details interface can be found in the code's annotation part.

Example
Given n = 5:

    isBadVersion(3) -> false
    isBadVersion(5) -> true
    isBadVersion(4) -> true

Here we are 100% sure that the 4th version is the first bad version.
Challenge
You should call isBadVersion as few as possible.

Notice
Please read the annotation in code area to get the correct way to call isBadVersion in different language. For example, Java is SVNRepo.isBadVersion(v)
```

```java
/**
 * public class SVNRepo {
 *     public static boolean isBadVersion(int k);
 * }
 * you can use SVNRepo.isBadVersion(k) to judge whether 
 * the kth code version is bad or not.
*/
public class Solution {
    /**
     * @param n: An integer
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        // write your code here
        int start = 1;
        int end = n;
        
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (SVNRepo.isBadVersion(mid) == false) {
                start = mid;
            } else {
                end = mid;
            } 
        }
        if(SVNRepo.isBadVersion(start) == true) {
            return start;
        }
        return end;
    }
}
```

### 162. Find Peak Element

https://leetcode.com/problems/find-peak-element/

```
A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.

```

```
class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int start = 0;
        int end = nums.length - 1;
        
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] > nums[end]) {
            return start;
        } else {
            return end;
        }
    }
}
```



### 33. Search in Rotated Sorted Array

https://leetcode.com/problems/search-in-rotated-sorted-array/

```
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

```

```java
class Solution {
  //画图
  //如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的
  // == 如果中间的数小于最左边的数，则右半段是有序的，若中间数大于最左边数，则左半段是有序的
    public int search(int[] nums, int target) {
        
        if (nums.length == 0 || nums == null) {
            return -1;
        }
        
        int start = 0;
        int end = nums.length - 1;
        int mid = -1;
        
    
        while(start + 1 < end) {
            mid = start + (end - start) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            // left
            if (nums[mid] > nums[start]) {
                if (target >= nums[start] && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else{
                // right
                if (target >= nums[mid] && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        
        return -1;
        
    }
}
```

### 81. Search in Rotated Sorted Array II

https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

Ref: https://www.cnblogs.com/grandyang/p/4325840.html

```
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?
```

```java
class Solution {
  // 有重复数字就会导致，mid可能等于start，只要通过start++就可以让loop继续。
    public boolean search(int[] nums, int target) {
            if (nums.length == 0 || nums == null) {
            return false;
        }
        
        int start = 0;
        int end = nums.length - 1;
        int mid = -1;
        
    
        while(start + 1 < end) {
            mid = start + (end - start) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            // left
            if (nums[mid] > nums[start]) {
                if (target >= nums[start] && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[mid] < nums[start]){
                // right
                if (target >= nums[mid] && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else if (nums[start] != target) {
                start++;
            }
        }
        
        if (nums[start] == target) {
            return true;
        }
        if (nums[end] == target) {
            return true;
        }
        
        return false;
        
    
    }
}
```

### 9. Recover Rotated Sorted Array

https://www.lintcode.com/problem/recover-rotated-sorted-array/description

```
Given a rotated sorted array, recover it to sorted array in-place.

Clarification
What is rotated array?

For example, the orginal array is [1,2,3,4], The rotated array of it can be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]

Example
Example1:
[4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
Example2:
[6,8,9,1,2] -> [1,2,6,8,9]
Challenge
In-place, O(1) extra space and O(n) time.

```

````
public class Solution {
    /**
     * @param nums: An integer array
     * @return: nothing
     */
     
     private void reverse(List<Integer> nums, int start, int end) {
         
         int mid = start + (end - start) /2;
         
         for (int i = start; i<= mid; i++) {
             int temp = nums.get(start);
             nums.set(start, nums.get(end));
             nums.set(end, temp);
             start++;
             end--;
         } 
     }
     
    public void recoverRotatedSortedArray(List<Integer> nums) {
        // 先找到第一个比后面大的数，然后这个数之前reverse，之后reverse，总体reverse
        for(int i = 0 ;i < nums.size() - 1; i++ ) {
            if (nums.get(i) > nums.get(i+1)) {
                reverse(nums, 0, i);
                reverse(nums, i + 1, nums.size() - 1);
                reverse(nums, 0, nums.size() - 1);
                return;
            } 
        }
        
    }
}
````

```java
public class Solution {
    /**
     * @param nums: An integer array
     * @return: nothing
     */
     
     private void reverse(List<Integer> nums, int start, int end) {\
     //不同的for loop写法
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, temp);
        }
     }
     
    public void recoverRotatedSortedArray(List<Integer> nums) {
        // write your code here
        
        for(int i = 0 ;i < nums.size() - 1; i++ ) {
            if (nums.get(i) > nums.get(i+1)) {
                reverse(nums, 0, i);
                reverse(nums, i + 1, nums.size() - 1);
                reverse(nums, 0, nums.size() - 1);
                return;
            } 
        }
        
    }
}
```

### 88. Merge Sorted Array

https://leetcode.com/problems/merge-sorted-array/

```
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
```

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //from end
        int end = m + n -1;
        m = m - 1;
        n = n - 1;
        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[end] = nums1[m];
                end--;
                m--;
            } else {
                nums1[end] = nums2[n];
                end--;
                n--;
            }
        }
        while (n >= 0) {
            nums1[end--] = nums2[n--];
        }
    }
}
```

### 6. Merge Two Sorted Arrays

https://www.lintcode.com/problem/merge-two-sorted-arrays/description

```
Description

Example
Example 1:

Input:  A=[1], B=[1]
Output: [1,1]	
Explanation:  return array merged.
Example 2:

Input:  A=[1,2,3,4], B=[2,4,5,6]
Output: [1,2,2,3,4,4,5,6]	
Explanation: return array merged.
Challenge
How can you optimize your algorithm if one array is very large and the other is very small?
```

```java
public class Solution {
    /**
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        
        if (A == null || B == null) {
            return null;
        } 
        int a = A.length;
        int b = B.length;
        int[] result = new int[a+b];
        int i = 0;
        int j = 0;
        int p = 0;
        while(i < a && j < b) {
            result[p++] = A[i] < B[j] ? A[i++] : B[j++];
     
        }
        
        while(i < A.length) {
            result[p++] = A[i++];
        }

        while(j < B.length) {
            result[p++] = B[j++];
        } 
        return result;
    }

```



## 2. Binary Tree

### 144. Binary Tree Preorder Traversal

https://leetcode.com/problems/binary-tree-preorder-traversal/

```
Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  //traverse, recursive
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }
    
    public void traverse(TreeNode root, List<Integer> result) {
        if(root == null) {
            return;
        }

        result.add(root.val);
        traverse(root.left, result);
        traverse(root.right, result); 
    }
    
}

//iteration
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
        
    }
}

//divide and conquer
public class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        // null or leaf
        if (root == null) {
            return result;
        }

        // Divide
        ArrayList<Integer> left = preorderTraversal(root.left);
        ArrayList<Integer> right = preorderTraversal(root.right);

        // Conquer
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);
        return result;
    }
}

```

### 145. Binary Tree Postorder Traversal

https://leetcode.com/problems/binary-tree-postorder-traversal/

Given a binary tree, return the *postorder* traversal of its nodes' values.

**Example:**

```
Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
```

**Follow up:** Recursive solution is trivial, could you do it iteratively?

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return ans;
        }
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(0, node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return ans;
        
    }
}
```





### 94. Binary Tree Inorder Traversal

```
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//recursive
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }
    
    public void traverse(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        traverse(root.left, result);
        result.add(root.val);
        traverse(root.right, result);       
    }
}

//iterative
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode node = root;
        
        while(node != null || !stack.isEmpty()) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            result.add(node.val);
            node = node.right;
        }
        return result;
    }
}

//divide and conquer
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        //divide
        List<Integer> left = inorderTraversal(root.left);
        List<Integer> right = inorderTraversal(root.right);
        //conquer
        result.addAll(left);
        result.add(root.val);
        result.addAll(right);
        
        return result;

    }
    
}
```



### 104. Maximum Depth of Binary Tree

```
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//iterative
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int max = 0;
        int depth = 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        TreeNode node = root;
        stack.push(node);
        depths.push(1);
        
        while(!stack.isEmpty()) {
            node = stack.pop();
            depth = depths.pop();
            max = Math.max(depth, max);
            if (node.left != null) {
                stack.push(node.left);
                depths.push(depth + 1);
            }
            
            if (node.right != null) {
                stack.push(node.right);
                depths.push(depth+1);
            }
            
        }
        return max;      
    }
}

//recursive
class Solution {
    int result; //全局变量
    public int maxDepth(TreeNode root) {
        result = 0;
        helper(root, 1);
        return result;
    }   
    
    public void helper(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        
        result = Math.max(depth, result);
        helper(root.left, depth + 1);
        helper(root.right, depth + 1);
    }
        
}

//divide and conquer 
class Solution {
    public int maxDepth(TreeNode root) {
        int result;
        if (root == null) {
            return 0;
        }
        //divide
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        //conquer
        result = left > right ? left + 1 : right + 1;
        
        return result;
    }
}
```



### 110. Balanced Binary Tree

```
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Return false.
```

```java
讲解：https://www.bilibili.com/video/av54683147?from=search&seid=12303381534698015731
https://leetcode.com/problems/balanced-binary-tree/discuss/157645/Python-Tree-tm

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

（1）
空间复杂度分析：https://raw.githubusercontent.com/yuzhoujr/spazzatura/master/img_box/balanced.jpg

时间复杂度：最好n(节点都在一根线上)  最坏nlog(n)
空间复杂度：最坏n   最好log(n)
 
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        //判断root是否平衡
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
            return false;
        }
        //判断root的左右节点是否平衡
        return isBalanced(root.left) && isBalanced(root.right); 
    }
    
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right) + 1;
    }
}


(2)
//在计算root高度的同时，检查每个节点的左右子树的高度差是否大于1，如果大于1，则返回-1，从root开始的二叉树不平衡，所以只需要计算一次root的高度，不再需要迭代计算每一个node是否平衡。
//时间复杂度: n
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        int result = getHeight(root);
        return result == -1 ? false : true;
    }
    
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (left == -1 || right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        
        return Math.max(left, right) + 1;
    }
}
```



### 236. Lowest Common Ancestor of a Binary Tree

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

```
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//recursion 不要想这个tree是怎么计算的，只看一个单独的node
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      // base case, when do we have an answer to a search call
        if (root == null || root == p || root == q) {
            return root;
        }
  //general(recursive) case: The case for which the solution is expressed in terms of a smaller version of itself
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }

    }
    
}
```

### 235. Lowest Common Ancestor of a Binary Search Tree

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the [definition of LCA on Wikipedia](https://en.wikipedia.org/wiki/Lowest_common_ancestor): “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow **a node to be a descendant of itself**).”

Given binary search tree: root = [6,2,8,0,4,7,9,null,null,3,5]

![img](https://assets.leetcode.com/uploads/2018/12/14/binarysearchtree_improved.png)

**Example 1:**

```
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
```

**Example 2:**

```
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//根据bst的性质，来比较
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }    
        return root;
      
    }
}
```

### 112. Path Sum

https://leetcode.com/problems/path-sum/

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

**Note:** A leaf is a node with no children.

**Example:**

Given the below binary tree and `sum = 22`,

```
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
```

return true, as there exist a root-to-leaf path `5->4->11->2` which sum is 22.

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//注意题目要求是root-leaf，A leaf is a node with no children, 所以递归出口是root.left == null && root.right == null
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        //corner case
        if (root == null) {
            return false;
        }
        
        //递归出口
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
     
    }
}
```





## 3. Sort Algorithm

### Merge Sort

```java


import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
    }

    private static void sort(int[] arr, int low, int high, int[] temp) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;

        sort(arr, low, mid, temp);
        sort(arr, mid + 1, high, temp);
        merge(arr, low, mid, high, temp);
    }

    private static void merge(int[] arr, int low, int mid, int high, int[] temp) {
        int i = low;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= high) {
            temp[t++] = arr[j++];
        }
        t = 0;
        while (low <= high) {
            arr[low++] = temp[t++];
        }
    }

}

```

### 437. Path Sum III

https://leetcode.com/problems/path-sum-iii/

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

**Example:**

```
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// pathSum解决了分别计算以root以及其左右子节点为根的树，dfs解决了某个节点下有几条路径
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        //以root为根开始的path，以root.left开始的path，以root.right开始的path
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        
        return (root.val == sum ? 1 : 0) + dfs(root.left, sum - root.val) + dfs(root.right, sum - root.val);
    }
}
```

##4. DFS and BFS

BFS: 只有非递归写法，使用queue

DFS: 两种写法：递归/非递归（stack）

###124. (DFS)Binary Tree Maximum Path Sum

https://leetcode.com/problems/binary-tree-maximum-path-sum/

Given a **non-empty** binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain **at least one node** and does not need to go through the root.

**Example 1:**

```
Input: [1,2,3]

       1
      / \
     2   3

Output: 6
```

**Example 2:**

```
Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int max = Integer.MIN_VALUE; //或者用int[] max
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }
    
    private int dfs(TreeNode root) {
        int left = root.left == null ? 0 : Math.max(dfs(root.left), 0);
        int right = root.right == null ? 0 : Math.max(dfs(root.right), 0);
        int cur = root.val + left + right;
        
        max = Math.max(cur, max);
        return Math.max(left, right) + root.val;
        
    }
}
```

### 687. (DFS)Longest Univalue Path

https://leetcode.com/problems/longest-univalue-path/

Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

The length of path between two nodes is represented by the number of edges between them.

**Example 1:**

**Input:**

```
              5
             / \
            4   5
           / \   \
          1   1   5
```

**Output:** 2

**Example 2:**

**Input:**

```
              1
             / \
            4   5
           / \   \
          4   4   5
```

**Output:** 2

**Note:** The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//1.
class Solution {
    private int max;
    public int longestUnivaluePath(TreeNode root) {
        max = 0;
        dfs(root);
        return max;
    }
    
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        
        if (root.left !=null && root.left.val == root.val) {
            left = left + 1;
        } else {
            left = 0;
        }
        if (root.right !=null && root.right.val == root.val) {
            right = right + 1;
        } else {
            right = 0;
        }
        max = Math.max(max, right + left);
        return Math.max(left, right);
    }
}
// 2. 将父节点的val传到下一层
class Solution {
    private int max;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root, -1);
        return max;
    }
    
    private int dfs(TreeNode node, int val) {
        if (node == null) {
            return 0;
        }
        
        int left = dfs(node.left, node.val);
        int right = dfs(node.right, node.val);
        max = Math.max(left + right, max);
        if (node.val == val) {
            return Math.max(left, right) + 1;
        } else {
            return 0;
        }
        
    }
}
```

### 102. (BFS)Binary Tree Level Order Traversal

https://leetcode.com/problems/binary-tree-level-order-traversal/

Given a binary tree, return the *level order* traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```



return its level order traversal as:

```
[
  [3],
  [9,20],
  [15,7]
]
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// BFS由queue（linkedlist）实现，先进先出，queue来存放每一层的node，queue.offer(node);queue.poll()。
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
         List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }
}
```

### 107. Binary Tree Level Order Traversal II

https://leetcode.com/problems/binary-tree-level-order-traversal-ii/

Given a binary tree, return the *bottom-up level order* traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```



return its bottom-up level order traversal as:

```
[
  [15,7],
  [9,20],
  [3]
]
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//BFS
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                } 
            }
            result.add(0, level);
        }
        return result;
    }
}


//DFS
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        dfs(result, root, 0);
        return result;
    }
    
    private void dfs(List<List<Integer>> result, TreeNode node, int level) {
        if (node == null) {
            return;
        }
        while(result.size() <= level) {
            result.add(0, new ArrayList());
        }
        dfs(result, node.left, level + 1);
        dfs(result, node.right, level + 1);
        result.get(result.size() - level - 1).add(node.val);
    }
}
```

### 637. Average of Levels in Binary Tree

https://leetcode.com/problems/average-of-levels-in-binary-tree/

Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

**Example 1:**

```
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
```



**Note:**

1. The range of node's value is in the range of 32-bit signed integer.

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                sum += node.val;
            }
            result.add(sum/size);
        }
        return result;
    }
}
```

### 103. Binary Tree Zigzag Level Order Traversal

https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

Given a binary tree, return the *zigzag level order* traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

return its zigzag level order traversal as:

```
[
  [3],
  [20,9],
  [15,7]
]
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        boolean flag = true;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (flag) {
                    level.add(node.val);
                } else {
                    level.add(0, node.val);
                }        
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            flag = !flag;
            ans.add(level);
        }
        return ans;    
    }
}
```





### 98. Validate Binary Search Tree

https://leetcode.com/problems/validate-binary-search-tree/

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

- The left subtree of a node contains only nodes with keys **less than** the node's key.
- The right subtree of a node contains only nodes with keys **greater than**the node's key.
- Both the left and right subtrees must also be binary search trees.

 

**Example 1:**

```
    2
   / \
  1   3

Input: [2,1,3]
Output: true
```

**Example 2:**

```
    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//DFS
class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, null, null);
    }
    
    private boolean dfs(TreeNode node,Integer low, Integer high) {
        if (node == null) {
            return true;
        }
        if (low != null &&  node.val <= low) {
            return false;
        }
        if (high != null && node.val >= high) {
            return false;
        }
        
        return dfs(node.left, low, node.val) && dfs(node.right, node.val, high);   
    }
}

//in-order iteration
class Solution {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root == null){
            return true;
        }
        TreeNode node = root;
        double inorder = - Double.MAX_VALUE;

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            TreeNode cur = stack.pop();
            if (cur.val <= inorder) {
                return false;
            }
            node = cur.right;
            inorder = cur.val;
        }
        return true;
    }
}
```

### 701. Insert into a Binary Search Tree

Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

For example, 

```
Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the value to insert: 5
```

You can return this binary search tree:

```
         4
       /   \
      2     7
     / \   /
    1   3 5
```

This tree is also valid:

```
         5
       /   \
      2     7
     / \   
    1   3
         \
          4
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return root;
        }
        TreeNode node = root;
        TreeNode cur = root;
        while(node != null) {
            cur = node;
            if (val < node.val) {
                node = node.left;
            } else if (val > node.val) {
                node = node.right;
            }
        }
        if (val < cur.val) {
            cur.left = new TreeNode(val);
        } else {
            cur.right = new TreeNode(val);
        }
        return root;
        
    }
}
```



### 700. Search in a Binary Search Tree

https://leetcode.com/problems/search-in-a-binary-search-tree/

Given the root node of a binary search tree (BST) and a value. You need to find the node in the BST that the node's value equals the given value. Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.

For example, 

```
Given the tree:
        4
       / \
      2   7
     / \
    1   3

And the value to search: 2
```

You should return this subtree:

```
      2     
     / \   
    1   3
```

In the example above, if we want to search the value `5`, since there is no node with value `5`, we should return `NULL`.

Note that an empty tree is represented by `NULL`, therefore you would see the expected output (serialized tree format) as `[]`, not `null`.

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
         if (root == null) {
            return root;
        }
        
        if (val < root.val) {
            return searchBST(root.left, val);
        } else if (val > root.val) {
            return searchBST(root.right, val);
        } else {
            return root;
        }
        
    }
}
```

### 11. Search Range in Binary Search Tree

https://www.lintcode.com/problem/search-range-in-binary-search-tree/description

```
Description
中文

English
Given a binary search tree and a range [k1, k2], return node values within a given range in ascending order.
Have you met this question in a real interview?  Yes
Problem Correction
Example
Example 1:

Input：{5},6,10
Output：[]
        5
it will be serialized {5}
No number between 6 and 10
Example 2:

Input：{20,8,22,4,12},10,22
Output：[12,20,22]
Explanation：
        20
       /  \
      8   22
     / \
    4   12
it will be serialized {20,8,22,4,12}
[12,20,22] between 10 and 22
```



```java
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        
        helper(root, k1, k2, ans);
        return ans;
    }
    
    //inorder traverse
    private void helper(TreeNode node, int k1, int k2, List<Integer> ans) {
        if (node == null) {
            return;
        }
        helper(node.left,k1,k2,ans);
        if (node.val >= k1 && node.val <= k2) {
            ans.add(node.val);
        }
        helper(node.right,k1,k2,ans);
    }
}
```

### 173. Binary Search Tree Iterator

Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling `next()` will return the next smallest number in the BST.

 



**Example:**

**![img](https://assets.leetcode.com/uploads/2018/12/25/bst-tree.png)**

```
BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
```

 

**Note:**

- `next()` and `hasNext()` should run in average O(1) time and uses O(*h*) memory, where *h* is the height of the tree.

- You may assume that `next()` call will always be valid, that is, there will be at least a next smallest number in the BST when `next()` is called.

  ```java
  /**
   * Definition for a binary tree node.
   * public class TreeNode {
   *     int val;
   *     TreeNode left;
   *     TreeNode right;
   *     TreeNode(int x) { val = x; }
   * }
   */
  class BSTIterator {
      
      private Stack<TreeNode> stack = new Stack<>();
      
      public BSTIterator(TreeNode root) {
          TreeNode cur = root;
          while (cur != null) {
              stack.push(cur);
              cur = cur.left;
          }
          
      }
      
      /** @return the next smallest number */
      public int next() {
          TreeNode node = stack.pop();
          int res = node.val;
          node = node.right;
          while (node !=null) {
              stack.push(node);
              node = node.left;
          }
          return res;
      }
      
      /** @return whether we have a next smallest number */
      public boolean hasNext() {
          return !stack.isEmpty();
      }
  }
  
  /**
   * Your BSTIterator object will be instantiated and called as such:
   * BSTIterator obj = new BSTIterator(root);
   * int param_1 = obj.next();
   * boolean param_2 = obj.hasNext();
   */
  ```

  

## 5. Linked List

### 2. Add Two Numbers

https://leetcode.com/problems/add-two-numbers/

You are given two **non-empty** linked lists representing two non-negative integers. The digits are stored in **reverse order** and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

**Example:**

```
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//key point: 
（1）计算sum时，分为三部分l1 l2 carry，如果l1或者l2==null则把他们的值当作0，carry设为int而不是boolean，方便计算。
（2）l1 l2指针移动时，要判断是否已经为null，如果为null，就不能改变了，在之后的循环中都会当作0
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            carry = sum / 10;
            int val = sum % 10;
            cur.next = new ListNode(val);
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry == 1) {
            cur.next = new ListNode(1);
        }
        return dummy.next;
    }
}
// 也可以把caryy放到while循环中，因为l1和l2都为null时，carry也可能为1，所以循环继续。
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry == 1) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            carry = sum / 10;
            int val = sum % 10;
            cur.next = new ListNode(val);
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        
        return dummy.next;
    }
}
```

### 83. Remove Duplicates from Sorted List

https://leetcode.com/problems/remove-duplicates-from-sorted-list/

Given a sorted linked list, delete all duplicates such that each element appear only *once*.

**Example 1:**

```
Input: 1->1->2
Output: 1->2
```

**Example 2:**

```
Input: 1->1->2->3->3
Output: 1->2->3
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = head;
        while (head != null && head.next != null) {
            if (head.val == head.next.val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return dummy;
    }
}
```

### 82. Remove Duplicates from Sorted List II

https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only *distinct* numbers from the original list.

**Example 1:**

```
Input: 1->2->3->3->4->4->5
Output: 1->2->5
```

**Example 2:**

```
Input: 1->1->1->2->3
Output: 2->3
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//关键点在于将head移动到dummy，然后while循环，如果head.next和head.next.next相等，值记为value，再遍历head之后所有node直到找到第一个不等于value的node
// O（n）
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        head = dummy;
        
        while (head.next != null && head.next.next != null) {
            if (head.next.val == head.next.next.val) {
                int value = head.next.val;
                 while (head.next != null && head.next.val == value) {
                     if (head.next.val == value ) {
                         head.next = head.next.next;
                     }
                 }
            } else {
                head = head.next;
            }
        }
        
        return dummy.next;
        
    }
}
```

### 206. Reverse Linked List

https://leetcode.com/problems/reverse-linked-list/

Reverse a singly linked list.

**Example:**

```
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
```

**Follow up:**

A linked list can be reversed either iteratively or recursively. Could you implement both?

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//iteration
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = null, node = null;
        while (head != null) {
            temp = head.next;
            head.next = node;
            node = head;
            head = temp;
        }
        return node;
    }
}

//recursion
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }
}
//
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null, cur = head, nxt = head.next;
        while(true) {
            cur.next = pre;
            if (nxt == null) {
                break;
            }
            pre = cur;
            cur = nxt;
            nxt = nxt.next;
        }
        return cur;
    }
}
```

### 92. Reverse Linked List II

https://leetcode.com/problems/reverse-linked-list-ii/

Reverse a linked list from position *m* to *n*. Do it in one-pass.

**Note:** 1 ≤ *m* ≤ *n* ≤ length of list.

**Example:**

```
Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//key point:
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }
        ListNode l1End = null, l2Start = null, l2End = null, l3Start = null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head, nxt = head.next;
        for (int i = 1; i < m; i++) {
            pre = cur;
            cur = nxt;
            nxt = nxt.next;
        }
        l1End = pre;
        l2Start = cur;
        
        for (int j = m; j <= n; j++) {
            cur.next = pre;
            if (j == n) break;
            pre = cur;
            cur = nxt;
            nxt = nxt.next;
        }
        l3Start = nxt;
        l2End = cur;
        
        l1End.next = l2End;
        l2Start.next = l3Start;
        return dummy.next;
    }
}
```

