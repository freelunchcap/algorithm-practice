package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Design a Phone Directory which supports the following operations:
 *
 * get: Provide a number which is not assigned to anyone.
 * check: Check if a number is available or not.
 * release: Recycle or release a number.
 *
 * Example:
 *   // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 *   PhoneDirectory directory = new PhoneDirectory(3);
 *
 *   // It can return any available phone number. Here we assume it returns 0.
 *   directory.get();
 *
 *   // Assume it returns 1.
 *   directory.get();
 *
 *   // The number 2 is available, so return true.
 *   directory.check(2);
 *
 *   // It returns 2, the only number that is left.
 *   directory.get();
 *
 *   // The number 2 is no longer available, so return false.
 *   directory.check(2);
 *
 *   // Release number 2 back to the pool.
 *   directory.release(2);
 *
 *   // Number 2 is available again, return true.
 *   directory.check(2);
 */
public class _379_DesignPhoneDirectory implements Medium {

  public interface Solution {

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    void init(int maxNumbers);

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    int get();

    /** Check if a number is available or not. */
    boolean check(int number);

    void release(int number);

  }

  public static class Solution1 implements Solution {

    @Override
    public void init(int maxNumbers) {
    }

    @Override
    public int get() {
      return 0;
    }

    @Override
    public boolean check(int number) {
      return false;
    }

    @Override
    public void release(int number) {

    }

  }


}
