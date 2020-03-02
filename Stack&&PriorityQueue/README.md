### Stack数据结构

```java
//

//API文档更加推荐，LIFO stack：
Deque<Integer> stack = new ArrayDeque<Integer>();
```

`Stack` 因为继承了Vector（动态数组），导致can access an element by position.

`Deque` (double ended queue): exposes a set of operations which is all about being able to fetch/add/remove items from the start or end of a collection. Deque 有两种实现方法：ArrayDeque and LinkedList