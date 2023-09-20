package com.yuyonghai.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 归并排序类，用于计算逆序对
 */
public class MergeSortInversePairs {
    public static int mergeSort(List<Integer> nums) {
        if (nums == null || nums.size() <= 1) {
            return 0;
        }

        List<Integer> temp = new ArrayList<>(nums.size());
        for (int i = 0; i < nums.size(); i++) {
            temp.add(0);
        }

        return mergeSort(nums, 0, nums.size() - 1, temp);
    }

    private static int mergeSort(List<Integer> nums, int start, int end, List<Integer> temp) {
        if (start >= end) {
            return 0;
        }

        int mid = start + (end - start) / 2;
        int count = mergeSort(nums, start, mid, temp) + mergeSort(nums, mid + 1, end, temp);
        count += merge(nums, start, mid, end, temp);
        return count;
    }

    private static int merge(List<Integer> nums, int start, int mid, int end, List<Integer> temp) {
        int i = start;
        int j = mid + 1;
        int k = start;
        int count = 0;

        while (i <= mid && j <= end) {
            if (nums.get(i) <= nums.get(j)) {
                temp.set(k++, nums.get(i++));
            } else {
                // 当前元素 nums.get(i) 大于 nums.get(j)，那么 nums.get(i) 之后的所有元素都大于 nums.get(j)
                count += mid - i + 1;
                temp.set(k++, nums.get(j++));
            }
        }

        while (i <= mid) {
            temp.set(k++, nums.get(i++));
        }

        while (j <= end) {
            temp.set(k++, nums.get(j++));
        }

        for (int m = start; m <= end; m++) {
            nums.set(m, temp.get(m));
        }

        return count;
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(7);
        nums.add(5);
        nums.add(6);
        nums.add(4);

        int count = mergeSort(nums);
        System.out.println("逆序对的数量为：" + count); // 输出逆序对的数量
    }
}