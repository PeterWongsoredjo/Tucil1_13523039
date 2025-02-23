import java.util.List;

public class Piece extends Matrix {
    public void CreatePiece(List<String> pieceLines) {
        int rows = pieceLines.size();
        int maxCols = 0;
        for (String row : pieceLines) {
            maxCols = Math.max(maxCols, row.length());
        }

        super.CreateMatrix(rows, maxCols);

        for (int i = 0; i < rows; i++) {
            String row = pieceLines.get(i);
            for (int j = 0; j < maxCols; j++) {
                if (j < row.length()) {
                    ELMT[i][j] = row.charAt(j);  
                } else {
                    ELMT[i][j] = '.';  
                }
            }
        }
    }
    
    public void rotate90(Piece pIn) {
        int newRow = pIn.getCol();
        int newCol = pIn.getRow();

        super.CreateMatrix(newRow, newCol);
        for (int i = 0; i < pIn.getRow(); i++) {
            for (int j = 0; j < pIn.getCol(); j++) {
                ELMT[j][newCol - 1 - i] = pIn.getElement(i, j);
            }
        }
    }

    public void mirror(Piece pIn) {
        super.CreateMatrix(pIn.getRow(), pIn.getCol());

        for (int i = 0; i < pIn.getRow(); i++) {
            for (int j = 0; j < pIn.getCol(); j++) {
                ELMT[i][pIn.getCol() - 1 - j] = pIn.getElement(i, j);
            }
        }
    }
}
