### 52. N-Queens II

模版：

```java
class Solution {
    public int totalNQueens(int n) {
      	corner case
        dfs
      	return result
    }
    private void dfs(int level, int n) {
        退出条件  
        for 选择 in 选择列表：
          	做选择
          	dfs
          	撤销选择
        } 
    }
}
```

- ![avatar](bit_operation.png)

![avatar](bit2.jpg)