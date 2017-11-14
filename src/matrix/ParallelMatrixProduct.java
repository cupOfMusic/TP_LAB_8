package matrix;

import exception.MyException;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ParallelMatrixProduct {

    private Thread [] threads;
    private Queue<Element> operations;

    private IMatrix matrix1;
    private IMatrix matrix2;
    private IMatrix result;

    public ParallelMatrixProduct(int threadsCount) {
        threads = new Thread[threadsCount];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Stream());
        }
    }

    public IMatrix product(IMatrix m1, IMatrix m2) {

        if (m1.getColumns() != m2.getRows()) {
            throw new MyException("Error: Matrix multiplication is impossible. Incorrect matrix length.");
        }

        matrix1 = m1;
        matrix2 = m2;
        result = new UsualMatrix(m1.getRows(), m2.getColumns());
        operations = new ArrayBlockingQueue<Element>(result.getRows() * result.getColumns());

        for (int i = 0; i < m1.getRows(); i++) {
            for (int j = 0; j < m2.getColumns(); j++) {
                operations.add(new Element(i, j));
            }
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Thread is interrupted: " + e);
        }

        return result;
    }

    private class Stream implements Runnable {
        @Override
        public void run() {
            Element element;
            int row;
            int column;
            int value;
            while ((element = operations.poll()) != null) {
                row = element.getRow();
                column = element.getColumn();
                for (int i = 0; i < matrix1.getColumns(); i++) {
                    value = result.getElement(row, column) + matrix1.getElement(row, i) * matrix2.getElement(i, column);
                    result.setElement(row, column, value);
                }
            }
        }
    }


}
