public class Matrix {
    protected char[][] ELMT;  
    protected int rows;
    protected int cols;

    public void CreateMatrix(int y, int x) {
        rows = y;
        cols = x;
        ELMT = new char[y][x];

        for (int i = 0; i < y; i++){
            for (int j = 0; j < x; j++){
                ELMT[i][j] = '.';
            }
        }
    }

    public char getElement(int row, int col) {
        return ELMT[row][col];
    }

    public int getRow(){
        return rows;
    }

    public int getCol(){
        return cols;
    }

    public int getLastRowIdx() {
        return getRow() - 1;
    }

    public int getLastColIdx() {
        return getCol() - 1;
    }

    public boolean isRowValid(int i) {
        return i >= 0 && i <= getLastRowIdx();
    }

    public boolean isColValid(int i) {
        return i >= 0 && i <= getLastColIdx();
    }

    public void setElement(int row, int col, char c) {
        if (isRowValid(row) && isColValid(col)) {
            ELMT[row][col] = c;
        }
    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(ELMT[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public boolean isEqual(Matrix m){
        if (rows != m.getRow() || cols != m.getCol()){
            return false;
        }
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (ELMT[i][j] != m.getElement(i, j)){
                    return false;
                }
            }
        }
        return true;
    }
}
