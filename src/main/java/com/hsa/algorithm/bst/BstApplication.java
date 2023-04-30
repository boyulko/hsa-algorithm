package com.hsa.algorithm.bst;


import static com.hsa.algorithm.bst.ExcelFileCreator.createSheet;
import static com.hsa.algorithm.bst.ExcelFileCreator.insertElementInfo;

import com.hsa.algorithm.bst.sorting.CountingSort;
import com.hsa.algorithm.bst.tree.AVLTree;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BstApplication {

  private static final Workbook WORKBOOK = new XSSFWorkbook();

  public static void main(String[] args) throws IOException {

    int[] sort = CountingSort.sort(List.of(1, 4, 1, 2, 7, 5, 2));


    AVLTree tree = createTree();

    findElements(tree);

    deleteElements(tree);

  }

  private static void deleteElements(AVLTree tree) throws IOException {
    Sheet sheet = createSheet(WORKBOOK, "Deletion");

    for (int j = 0; j < 10000; j++) {
      long startOperationTime = System.nanoTime();
      tree.deleteNode(tree.getRoot(), ThreadLocalRandom.current().nextInt(0, 50000));
      long finishedOperationTime = System.nanoTime();

      long operationDuration = finishedOperationTime - startOperationTime;
      insertElementInfo(sheet, j, operationDuration);
    }

    saveIntoFile("deletion.xlsx");
  }

  private static void findElements(AVLTree tree) throws IOException {

    Sheet sheet = createSheet(WORKBOOK, "Searching");

    for (int j = 0; j < 10000; j++) {
      long startOperationTime = System.nanoTime();
      tree.search(tree.getRoot(), ThreadLocalRandom.current().nextInt(0, 50000));
      long finishedOperationTime = System.nanoTime();

      long operationDuration = finishedOperationTime - startOperationTime;
      insertElementInfo(sheet, j, operationDuration);
    }

    saveIntoFile("search.xlsx");
  }

  private static AVLTree createTree() throws IOException {

    Sheet sheet = createSheet(WORKBOOK, "Tree_creation");

    AVLTree tree = new AVLTree();

    for (int j = 0; j < 10000; j++) {
      long startOperationTime = System.nanoTime();
      tree.insert(tree.getRoot(), ThreadLocalRandom.current().nextInt(0, 50000));
      long finishedOperationTime = System.nanoTime();

      long operationDuration = finishedOperationTime - startOperationTime;
      insertElementInfo(sheet, j, operationDuration);
    }

    saveIntoFile("insertion.xlsx");
    return tree;
  }

  private static void saveIntoFile(String fileName) throws IOException {
    File currDir = new File(".");
    String path = currDir.getAbsolutePath();
    String fileLocation = path.substring(0, path.length() - 1) + fileName;

    FileOutputStream outputStream = new FileOutputStream(fileLocation);
    WORKBOOK.write(outputStream);
  }


}
