package matrix;

import exception.MyException;

public class UsualMatrix implements IMatrix {

    private int [][] matrix;

    public UsualMatrix(int numberOfRows, int numberOfColumns){
        if (numberOfRows < 1 || numberOfColumns < 1) {
            throw new MyException("Error: Incorrect matrix length.");
        }
        matrix = new int [numberOfRows][numberOfColumns];
    }

    public void setElement(int row, int column, int value){
        matrix[row][column] = value;
    }

    public int getElement(int row, int column){
        return matrix[row][column];
    }

    public int getRows(){
        return this.matrix.length;
    }

    public int getColumns(){
        return this.matrix[0].length;
    }

    public UsualMatrix sum(IMatrix m2){
        if (this.getRows() != m2.getRows() && this.getColumns() != m2.getColumns()){
            throw new MyException("Error: Matrix addition is impossible. Incorrect matrix length.");
        }
        UsualMatrix result = new UsualMatrix(this.getRows(), this.getColumns());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++){
                result.setElement(i,j,0);
            }
        }
        for (int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < this.getColumns(); j++){
                int value = this.getElement(i,j) + m2.getElement(i,j);
                result.setElement(i,j,value);
            }
        }
        return result;
    }

    public UsualMatrix product(IMatrix m2){
        // Кол-во столбцов первой равно кол-ву строк второй
        if (this.getColumns() != m2.getRows()) {
            throw new MyException("Error: Matrix multiplication is impossible. Incorrect matrix length.");
        }
        int numberOfRows1 = this.getRows();
        int numberOfColumns2 = m2.getColumns();
        UsualMatrix result = new UsualMatrix(numberOfRows1, numberOfColumns2);
        for (int i = 0; i < numberOfRows1; i++) {
            for (int j = 0; j < numberOfColumns2; j++){
                result.setElement(i,j,0);
            }
        }
        for (int i = 0; i < numberOfRows1; i++) {
            for (int j = 0; j < numberOfColumns2; j++) {
                for (int k = 0; k < m2.getRows(); k++) {
                    int value = this.getElement(i,k) * m2.getElement(k,j);
                    result.setElement(i,j,result.getElement(i,j) + value);
                }
            }
        }
        return result;
    }

    public boolean equals(Object object) {
        if (!(object instanceof UsualMatrix)) {
            return false;
        }
        UsualMatrix tmp = (UsualMatrix) object;
        if (this.getRows() != tmp.getRows() || this.getColumns() != tmp.getColumns()) {
            return false;
        }
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (this.getElement(i, j) != tmp.getElement(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < this.getRows(); i++){
            for (int j = 0; j < this.getColumns(); j++){
                str.append(this.getElement(i,j));
                str.append(" ");
            }
            str.append("\n");
        }
        return  str.toString();
    }

}
