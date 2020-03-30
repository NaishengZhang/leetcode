//https://leetcode.com/discuss/interview-question/205360/Given-an-array-find-all-local-maximas-(peak)-Google/212662

import java.util.ArrayList;
import java.util.List;

public class FindLocalMax {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 1, -1, 3, 4, 2, 3, 3, 3};
        int[] arr1 = {1,1,2,2,2,2};
        int[] arr2 = {1,3,2,4,4,4,4};
        System.out.println(search(arr).toString());
        System.out.println(search(arr1).toString());
        System.out.println(search(arr2).toString());
//        int[] arr1 = {1, 1, 1, 1};
//        System.out.println(firstMaxima(arr1).toString());
    }

    private static List<Integer> search(int[] arr) {
        List<Integer> list = new ArrayList<>();

        int pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (i == arr.length - 1) {
                if (arr[i] > pre) {
                    list.add(arr[i]);
                    return list;
                } else {
                    return list;
                }
            }
            if (arr[i] == pre && arr[i] == arr[i + 1]) {
                continue;
            }
//            System.out.println(pre + "," + arr[i] + "," + arr[i+1]);
            if (arr[i] >= pre && arr[i] >= arr[i + 1]) {
                list.add(arr[i]);
            }
            pre = arr[i];
        }
        return list;
    }
}
