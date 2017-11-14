package matrix;

public interface IMatrix {

    void setElement(int row, int column, int value);
    int getElement(int row, int column);
    IMatrix sum(IMatrix m2);
    IMatrix product(IMatrix m2);
    int getRows();
    int getColumns();

}
