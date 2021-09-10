package com.qt.service1.leetcode;

/**
 * https://leetcode-cn.com/problems/move-zeroes/submissions/
 * 题号：283
 * @author jiongyang.wjy
 * @since 2021/9/9
 */
public class P1 {

    public static void main(String[] args) {
        P1 p1 = new P1();

        int[] nums = {0,1,0,3,12};
        p1.moveZeroes5(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    /**
     思路一：
     1. i=nums.length-1 左移，直到遇到1个0；
     2. 所有元素往前挪，0 放到最后；
     3. 回到i--的位置，继续1，2步骤，直到i=0；
     * @params nums a
     */
    public void moveZeroes1(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                for (int j = i; j < nums.length - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                nums[nums.length - 1] = 0;
            }
        }
    }

    /**
     * 不符合题目要求，使用了多于的数组
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        /**
         * 1. 开新数组，loop，遇到非0就从前往后放，遇到0就从后往前放
         */
        int[] arr = new int[nums.length];
        int start = 0;
        int end = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (start > end) {
                break;
            }
            if (nums[i] == 0) {
                arr[end--] = nums[i];
            } else {
                arr[start++] = nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = arr[i];
        }
    }

    /**
     * 滚雪球法
     * @param nums
     */
    public void moveZeroes3(int[] nums) {
        int snowballSize = 0;
        for (int i = 0; i < nums.length; i++) {
            // 遇到0，则size++
            if (nums[i] == 0) {
                snowballSize++;
            } else if (snowballSize > 0) {
                // size > 0 则交换雪球第一个元素与雪球后一个元素
                nums[i - snowballSize] = nums[i];
                nums[i] = 0;
            }
        }
    }

    /**
     * 双指针法
     * 遍历一次，把所有的非0移动到数组前面，最后把后面的值赋值为0
     * @param nums
     */
    public void moveZeroes4(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 双指针-原地交换法
     * @param nums a
     */
    public void moveZeroes5(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                if (i != j) {
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

}
