### 450. Delete Node in a BST

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfsutul9yhj30t00fi77u.jpg)

两种方法，用predecessor或者successor代替node

- Find successor

```java
    private TreeNode min(TreeNode root) {
        if (root.left == null) {
            return root;
        }
        return min(root.left);
    }
    
    private TreeNode delMin(TreeNode root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = delMin(root.left);
        return root;
    }
```

- find predecessor

```java

```

