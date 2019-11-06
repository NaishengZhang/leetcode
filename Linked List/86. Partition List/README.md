### 86. Partition List

维护两个dummy node，一个放小于3的，一个放大于等于3的node，最后再将l1的末尾节点连接到dummy2.next。

注意：l2的末尾节点需要置null。

