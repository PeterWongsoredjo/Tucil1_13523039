import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class BruteForceSolver {
    private Board board;
    private List<Piece> pieces;
    private int totalPieces;
    private long startTime;
    private long endTime;
    private int casesTried;

    public void CreateSolver(Board b, List<Piece> p, int total) {
        board = b;
        pieces = p;
        totalPieces = total;
        casesTried = 0;
        startTime = System.currentTimeMillis();
    }

    private int[] findFirstEmptyCell(){
        int[] cell = new int[2];
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                if (board.getElement(i, j) == '.') {
                    cell[0] = i;
                    cell[1] = j;
                    return cell;
                }
            }
        }
        return null;
    }

    private int[] adjustStartingPosition(Piece piece, int startRow, int startCol) {
        for(int j = 0; j < piece.getCol(); j++){
            if(piece.getElement(0, j) == '.'){
                if(startCol > 0){
                    startCol--;
                }
            } else {
                break;
            }
        }
        return new int[]{startRow, startCol};
    }

    private boolean isPlacementValid(Piece piece, int startRow, int startCol){
        // Geser dulu wak
        int[] adjustedPosition = adjustStartingPosition(piece, startRow, startCol);
        startRow = adjustedPosition[0];
        startCol = adjustedPosition[1];

        // Cek nabrak dan kalo keluar board
        for(int i = 0; i < piece.getRow(); i++){
            for(int j = 0; j < piece.getCol(); j++){
                if(piece.getElement(i, j) == '.'){
                    continue;
                }
                if(!board.isRowValid(startRow + i) || !board.isColValid(startCol + j)){
                    return false;
                }
                if(board.getElement(startRow + i, startCol + j) != '.' && board.getElement(startRow + i, startCol + j) != '#'){ 
                    return false;
                }
                if(board.getElement(startRow + i, startCol + j) == '#'){
                    return false;
                }
            }
        }
        return true;
    }

    private void placePiece(Piece piece, int startRow, int startCol){
        for(int i = 0; i < piece.getRow(); i++){
            for(int j = 0; j < piece.getCol(); j++){
                if(piece.getElement(i, j) != '.'){
                    board.setElement(startRow + i, startCol + j, piece.getElement(i, j));
                }
            }
        }
    }

    private void removePiece(Piece piece, int startRow, int startCol){
        for(int i = 0; i < piece.getRow(); i++){
            for(int j = 0; j < piece.getCol(); j++){
                if(piece.getElement(i, j) != '.' && board.getElement(startRow + i, startCol + j) != '#' ){ 
                    board.setElement(startRow + i, startCol + j, '.');
                }
            }
        }
    }

    public List<Piece> getTransformations(Piece original){
        List<Piece> transformations = new ArrayList<Piece>();
        transformations.add(original);

        Piece temp = new Piece();
        temp.rotate90(original);
        for(int i = 0; i < 3; i++){
            if (!containsEqualPiece(transformations, temp)) {
                transformations.add(temp);
            }
            Piece temp2 = new Piece();
            temp2.rotate90(temp);
            temp = temp2;
        }

        Piece mirrored = new Piece();
        mirrored.mirror(original);
        if (!containsEqualPiece(transformations, mirrored)) {
            transformations.add(mirrored);
        }

        Piece mirroredrotated = new Piece();
        mirroredrotated.rotate90(mirrored);
        for(int i = 0; i < 3; i++){
            if (!containsEqualPiece(transformations, mirroredrotated)) {
                transformations.add(mirroredrotated);
            }
            Piece mirroredrotated2 = new Piece();
            mirroredrotated2.rotate90(mirroredrotated);
            mirroredrotated = mirroredrotated2;
        }

        return transformations;
    }

    private boolean containsEqualPiece(List<Piece> list, Piece piece) {
        for (Piece p : list) {
            if (p.isEqual(piece)) {
                return true;
            }
        }
        return false;
    }

    public boolean solve(int pieceIndex) {
        if (pieceIndex >= totalPieces) {
            if (board.isBoardFull()) {
                endTime = System.currentTimeMillis();
                board.printColoredMatrix();
                System.out.println("Waktu Pencarian: " + (endTime - startTime) + " ms");
                System.out.println("Total kasus yang ditinjau: " + casesTried);
                System.out.println("Solusi ditemukan! Apakah Anda ingin menyimpan solusi? (y/n)");
                Scanner scanner = new Scanner(System.in);
                String response = scanner.next().toLowerCase();
                if (response.equals("y")) {
                    saveBoardToFile("solution.txt");
                }
                else if(response.equals("n")){
                }
                else{
                    System.out.println("Input tidak valid, solusi tidak disimpan.");
                }
                return true; 
            }
            return false;
        }

        int[] firstEmptyCell = findFirstEmptyCell();
        if (firstEmptyCell == null) {
            return false;
        }

        int startingRow = firstEmptyCell[0];
        int startingCol = firstEmptyCell[1];

        for (int i = 0; i < pieces.size(); i++) {
            Piece currentPiece = pieces.get(i);
            List<Piece> transformations = getTransformations(currentPiece);

            for (Piece transformation : transformations) {
                casesTried++;
                int[] adjustedPosition = adjustStartingPosition(transformation, startingRow, startingCol);
                int adjustedRow = adjustedPosition[0];
                int adjustedCol = adjustedPosition[1];

                if (isPlacementValid(transformation, adjustedRow, adjustedCol)) {
                    placePiece(transformation, adjustedRow, adjustedCol);
                    pieces.remove(i);

                    if (solve(pieceIndex + 1)) {
                        return true;
                    }

                    removePiece(transformation, adjustedRow, adjustedCol);
                    pieces.add(i, currentPiece);
                }
            }
        }

        return false;
    }

    private void saveBoardToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < board.getRow(); i++) {
                for (int j = 0; j < board.getCol(); j++) {
                    writer.write(board.getElement(i, j) + " ");
                }
                writer.write("\n");
            }
            System.out.println("Solusi disimpan ke file " + filename);
        } catch (IOException e) {
            System.out.println("Error: Solusi tidak bisa disimpan ke dalam file, keos.");
            e.printStackTrace();
        }
    }

    public int getTimeElapsed(){
        return (int)(endTime - startTime);
    }

    public int getCasesTried(){
        return casesTried;
    }
}
