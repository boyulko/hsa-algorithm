package com.hsa.algorithm.bst.sorting;

import java.util.List;

public class CountingSort {

  private static final int[] sortedArray = new int[1000];
  private static int lastInsertedIndex = 0;

  public static int[] sort(List<Integer> unsortedList) {
    int[] array = new int[100];

    for (int i = 0; i < 100; i++) {
      array[i] = count(unsortedList, i);
    }

    for (int k = 0; k < 100; k++) {
      int duplicateNumber = array[k];
      if (duplicateNumber != 0) {
        insertIntoResultArray(k, duplicateNumber);
      }
    }

    int[] outputArray = new int[lastInsertedIndex];

    for (int j = 0; j < lastInsertedIndex; ++j) {
      outputArray[j] = sortedArray[j];
    }
    return outputArray;

  }

  private static void insertIntoResultArray(int value, int duplicateNumber) {
    int i = 0;
    while (i < duplicateNumber) {
      sortedArray[lastInsertedIndex] = value;
      lastInsertedIndex++;
      i++;
    }
  }

  private static int count(List<Integer> unsortedList, int i) {
    int count = 0;
    for (int j = 0; j < unsortedList.size(); j++) {
      if (i == unsortedList.get(j)) {
        count++;
      }
    }
    return count;
  }

}
