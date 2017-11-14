package sorting;

import java.util.ArrayList;
import java.util.HashMap;


public class MergeSort {

    private Comparable[] array;
    private ArrayList<Thread> threads = new ArrayList<>();


    public void parallelSort(Comparable[] array) {
        this.array = array;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                parallelMergeSort(0, array.length - 1);
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Erorr: " + e);
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Erorr: " + e);
            }
        }
    }

    private void parallelMergeSort(int low, int high) {
        if (low < high) {

            int middle = low + (high - low) / 2;

            parallelMergeSort(low, middle);

            parallelMergeSort(middle + 1, high);

            parallelMerge(low, middle, high);
        }
    }

    private void parallelMerge(int low, int middle, int high) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Comparable[] helper = new Comparable[array.length];

                for (int i = low; i <= high; i++) {
                    helper[i] = array[i];
                }

                int i = low;
                int j = middle + 1;
                int k = low;

                while (i <= middle && j <= high) {
                    if (helper[i].compareTo(helper[j]) > 0) {
                        array[k] = helper[j];
                        j++;
                    } else {
                        array[k] = helper[i];
                        i++;
                    }
                    k++;
                }

                while (i <= middle) {
                    array[k] = helper[i];
                    k++;
                    i++;
                }

            }
        });
        threads.add(thread);
        thread.start();
    }


    public void sort(Comparable[] array) {
        this.array = array;
        mergeSort(0, array.length - 1);
    }

    private void mergeSort(int low, int high) {
        if (low < high) {

            int middle = low + (high - low) / 2;

            mergeSort(low, middle);

            mergeSort(middle + 1, high);

            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {

                Comparable[] helper = new Comparable[array.length];

                for (int i = low; i <= high; i++) {
                    helper[i] = array[i];
                }

                int i = low;
                int j = middle + 1;
                int k = low;

                while (i <= middle && j <= high) {
                    if (helper[i].compareTo(helper[j]) > 0) {
                        array[k] = helper[j];
                        j++;
                    } else {
                        array[k] = helper[i];
                        i++;
                    }
                    k++;
                }

                while (i <= middle) {
                    array[k] = helper[i];
                    k++;
                    i++;
                }
    }

}
