# 早鸟刷题

## week 03 (5.25- 5.31)

### 94. Binary Tree Inorder Traversal

（1）Recursion

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root);
        return res;
    }
    
    private void helper(List<Integer> res, TreeNode node) {
        if (node == null) {
            return;
        }
        helper(res, node.left);
        res.add(node.val);
        helper(res, node.right);
        
    }
    
}
```

（2）Iteration

代码块1：首先中序遍历的思路就是先左子树，后根节点，再右子树。迭代就是用stack模仿递归的系统栈。一直遍历到左子树的最后一个节点，并且在这个过程中把访问到的节点保存到stack中。

```java
  while (cur != null) {
    stack.push(cur);
    cur = cur.left;
  }
```



代码块2: 执行完上一步后，可能出现a b两种情况。 p是当选指向的节点。

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf5ob3iftaj30h406qdfy.jpg)

b图中，对于最近保存的节点来说，已经没有左子树，那么把根节点的值加到结果中。然后进入右子树，再把它当成根节点，继续执行代码块1的程序，直到栈空并且p==null。

```java
  cur = stack.pop();
  res.add(cur.val);
  cur = cur.right;
```

a图中，对于最近保存的节点来说，已经没有左子树，那么把根节点的值加到结果中。不同的是此时根节点没有了右子树，这个节点遍历完毕，返回上一层节点，再进去这层节点的右子树。

```java
  cur = stack.pop();
  res.add(cur.val);

  cur = stack.pop();
  res.add(cur.val);
  cur = cur.right;
```

值得注意的是，a和b可以合并成一种写法，因为a中cur.right为null，然后再执行代码块1，但并不会进入while循环中，又重新到代码块2。

### 144. Binary Tree Preorder Traversal

























