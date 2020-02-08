### 438. Find All Anagrams in a String

创建两个hash table，pMap保存p中字母的frequency，sMap保存sliding window中的frequency，当两个map相等时，记录结果。sliding window的长度时固定的，所以只需要一个指针。


Java (Assuming ASCII 128)

The previous implements all have no assumption on the charset of the string `s`.

If we know that the charset is rather small, we can replace the `Map` with an integer array as direct access table.

Commonly used tables are:

- `int[26]` for Letters 'a' - 'z' or 'A' - 'Z'
- `int[128]` for ASCII
- `int[256]` for Extended ASCII