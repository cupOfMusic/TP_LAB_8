import matrix.ParallelMatrixProduct;
import matrix.UsualMatrix;
import sorting.MergeSort;

import java.util.ArrayList;

public class MainL8 {

    public static void main(String[] args) {
//        UsualMatrix m1 = new UsualMatrix(1000, 500);
//        UsualMatrix m2 = new UsualMatrix(500, 1000);
//
//        for (int i = 0; i < m1.getRows(); i++) {
//            for (int j = 0; j < m1.getColumns(); j++) {
//                m1.setElement(i, j, i + j + 1);
//            }
//        }
//
//        for (int i = 0; i < m2.getRows(); i++) {
//            for (int j = 0; j < m2.getColumns(); j++) {
//                m2.setElement(i, j, i + j + 1);
//            }
//        }
//
//
//        ParallelMatrixProduct multiplier = new ParallelMatrixProduct(6);
//
//        long usualTime = System.currentTimeMillis();
//        m1.product(m2);
//        usualTime -= System.currentTimeMillis();
//
//        long parallelTime = System.currentTimeMillis();
//        multiplier.product(m1, m2);
//        parallelTime -= System.currentTimeMillis();
//
//        System.out.println("Usual: " + usualTime * -1);
//        System.out.println("Parallel: " + parallelTime * -1);

        int arraySize = 100000;
        Integer[] intArray = new Integer[arraySize];
        for (int i = 0; i < arraySize; i++) {
            intArray[i] = arraySize - i;
        }

        MergeSort sorter = new MergeSort();

        long usualTime = System.currentTimeMillis();
        sorter.sort(intArray);
        usualTime -= System.currentTimeMillis();

        long parallelTime = System.currentTimeMillis();
        sorter.parallelSort(intArray);
        parallelTime -= System.currentTimeMillis();

        System.out.println("Usual: " + usualTime * -1);
        System.out.println("Parallel: " + parallelTime * -1);


        for(Integer i: intArray) {
            System.out.print(i + " ");
        }

    }

}
